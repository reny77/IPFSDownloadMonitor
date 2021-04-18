package it.ipfsdownloadmonitor.ipfs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import io.ipfs.api.IPFS;
import io.ipfs.api.Peer;
import io.ipfs.multiaddr.MultiAddress;
import io.ipfs.multihash.Multihash;
import it.ipfsdownloadmonitor.commons.GeoLiteManager;
import it.ipfsdownloadmonitor.commons.Utility;
import it.ipfsdownloadmonitor.model.Contributor;
import it.ipfsdownloadmonitor.model.DownloadStats;
import it.ipfsdownloadmonitor.model.MapData;
import it.ipfsdownloadmonitor.model.ObjectInfo;
import it.ipfsdownloadmonitor.model.PieData;

public class IpfsManager {
	
	private Logger logger = LoggerFactory.getLogger(IpfsManager.class);
	
	private IPFS ipfs = null;
	boolean downloadRun = false;
	private Multihash filePointer = null;
	private ObjectInfo objectInfo = null;
	
	private List<Peer> peersList = new ArrayList<>();
	
	private Map<String, Contributor> contributorMap = new HashMap<>(); 

	private List<Double> kbProgress = new ArrayList<>();
	
	private static IpfsManager _INSTANCE = new IpfsManager();
	
	Thread updatePeersTh = null;
	Thread downloadTh = null;
	Thread updateProgressTh = null;
	
	private IpfsManager() {
		updatePeersTh = new Thread(updatePeersRunnable);
		updatePeersTh.start();
	}
	
	public static synchronized IpfsManager getInstance() {
		return _INSTANCE;
	}
	
	public void init(String host, int port) {
		// init ipfs client
		ipfs = new IPFS(host, port);
		try {
			ipfs.repo.gc();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopDownload() {
		if (downloadTh != null) {
			downloadTh.interrupt();
		}
			// fake: for close prev stream beacuse is a static method :(
			if (ipfs != null) {
				IPFS.closeget = true;
			}
		ipfs = null;
		downloadRun = false;
		filePointer = null;
		objectInfo = null;
		contributorMap = new HashMap<>(); 
		kbProgress = new ArrayList<>();
	}
	
	
	
	
	public void startDownload(String cid) throws IOException {
		this.filePointer = Multihash.fromBase58(cid);

		// get object info
		Map stats = ipfs.object.stat(filePointer);		
		String humanReadble = null;
		Long sizeLong = 0L;
		if (stats.get("CumulativeSize") instanceof Long) {
			Long size = (Long) stats.get("CumulativeSize");
			sizeLong = size;
			humanReadble = Utility.humanReadableByteCountBin(size.longValue());
		} else {
			Integer size = (Integer) stats.get("CumulativeSize");
			sizeLong = size.longValue();
			humanReadble = Utility.humanReadableByteCountBin(size.longValue());
		}
		
		//		
		Map fileLs = ipfs.file.ls(filePointer);
		Map objects = (Map) fileLs.get("Objects");
		Map cidDetails = (Map) objects.get(cid);
		String type = (String) cidDetails.get("Type");
		
		objectInfo = new ObjectInfo(cid, "hash", sizeLong, humanReadble, type);
		objectInfo.setStart(new Date());
		logger.info("Type: {}", type);
		
		downloadTh = new Thread(downloadRunnable);
		downloadTh.start();
		updateProgressTh = new Thread(updateProgress);
		updateProgressTh.start();
		
	}
	
	Runnable updatePeersRunnable = new Runnable() {
		@Override
		public void run() {
			while (true) {
				try {
					if (downloadRun) {
						getPeers();
					}
				} catch(Throwable th) {
					th.printStackTrace();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	
	Runnable updateProgress = new Runnable() {
		@Override
		public void run() {
			while (true) {
				try {
					if (downloadRun) {
						// calculate total
						Double recivedTotal = 0D;
						List<Contributor> contributorList = new ArrayList<>(contributorMap.values());
						for (Iterator iterator = contributorList.iterator(); iterator.hasNext();) {
							Contributor contributor = (Contributor) iterator.next();
							recivedTotal += contributor.getRecvLong();
						}
						kbProgress.add(recivedTotal/1024);
					}
				} catch(Throwable th) {
					th.printStackTrace();
				}
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	
	Runnable downloadRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				logger.info("Start download");
				downloadRun = true;
				ipfs.get(filePointer);
				downloadRun = false;
				logger.info("End download");				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				downloadRun = false;
			}

		}
	};
	
	public void getPeers() {
		try {
			
			peersList = ipfs.swarm.peers();
			for (Iterator iterator = peersList.iterator(); iterator.hasNext();) {
				
				Peer peerConnected = (Peer) iterator.next();
				MultiAddress address = peerConnected.address;
				Object streams = peerConnected.streams;
				Object id = peerConnected.id;
				Object latency = peerConnected.latency;
				Object muxer = peerConnected.muxer;
				
				
				Map ledgerMap = ipfs.bitswap.ledger(peerConnected.id);
				String exchanged = ledgerMap.get("Exchanged").toString();
				String peer = ledgerMap.get("Peer").toString();
				String recv = ledgerMap.get("Recv").toString();
				String sent = ledgerMap.get("Sent").toString();
				String value = ledgerMap.get("Value").toString();
				if (!"0".equals(recv)) {
					
					//logger.debug("" + ledgerMap);
					
					String recvReadble = Utility.humanReadableByteCountBin(Long.valueOf(recv));
					String sentReadble = Utility.humanReadableByteCountBin(Long.valueOf(sent));
					Contributor tmp = contributorMap.get(peer);
					if (tmp == null) {
						Contributor contributor = new Contributor();
						contributor.setIp(address.getHost());
						
						contributor.setExchanged(exchanged);
						contributor.setPeer(peer);
						contributor.setRecvLong(Long.valueOf(recv));
						contributor.setRecv(recvReadble);
						contributor.setSent(sentReadble);
						contributor.setValue(value);
						
						try {
							GeoLiteManager.getInstance().resolve(contributor);
						} catch (GeoIp2Exception e) {
							e.printStackTrace();
						}
						contributorMap.put(contributor.getPeer(), contributor);
					} else {
						tmp.setExchanged(exchanged);
						tmp.setPeer(peer);
						tmp.setRecvLong(Long.valueOf(recv));
						tmp.setRecv(recvReadble);
						tmp.setSent(sentReadble);
						tmp.setValue(value);
						contributorMap.put(tmp.getPeer(), tmp);
					}
					
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void bitswapStat() {
		try {
			Map bitswapStat = ipfs.bitswap.stat(true, true);
			logger.debug("bitswapStat : {}", bitswapStat);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ObjectInfo getObjectInfo() {
		return objectInfo;
	}
	
	public DownloadStats getDownloadStats() {
		DownloadStats downloadStats = new DownloadStats();
		downloadStats.setPeersConnected(peersList.size());
		List<Contributor> contributorList = new ArrayList<>(contributorMap.values());
		downloadStats.setContributorList(contributorList);
		downloadStats.setContributor(contributorList.size());
		downloadStats.setRunDownload(downloadRun);
		
		// calculate total
		Long recivedTotal = 0L;
		for (Iterator iterator = contributorList.iterator(); iterator.hasNext();) {
			Contributor contributor = (Contributor) iterator.next();
			recivedTotal += contributor.getRecvLong();
		}
		
		// calculate pie
		List<PieData> pieDataList = new ArrayList<>();
		for (Iterator iterator = contributorList.iterator(); iterator.hasNext();) {
			Contributor contributor = (Contributor) iterator.next();
			long recv = contributor.getRecvLong();
			double perc = Utility.calcPerc(recivedTotal, recv);
			PieData pieData = new PieData();
			pieData.setColor("#3c8dbc");
			pieData.setData(perc);
			pieData.setLabel(contributor.getPeer());
			pieDataList.add(pieData);
		}
		downloadStats.setPieData(pieDataList);
		
		// calculate map
		List<MapData> mapDataList = new ArrayList<>();
		for (Iterator iterator = contributorList.iterator(); iterator.hasNext();) {
			Contributor contributor = (Contributor) iterator.next();
			MapData mapData = new MapData();
			mapData.setName(contributor.getCity());
			mapData.getLatLng()[0] = contributor.getLatitude();
			mapData.getLatLng()[1] = contributor.getLongitude();
			mapDataList.add(mapData);
		}
		downloadStats.setMapDataList(mapDataList);
		
		downloadStats.setKbProgress(kbProgress);
		
		if (objectInfo != null) {
			long size = objectInfo.getSize();
			double perc = Utility.calcPerc(size, recivedTotal);
			downloadStats.setPercDownload(String.valueOf(perc).concat("%"));
			downloadStats.setTotSize(objectInfo.getSizeHumanReadble());
			downloadStats.setRecivedTotal(Utility.humanReadableByteCountBin(recivedTotal));
		}
		
		
		return downloadStats;
	}
	
	
	
	
	public boolean isDownloadRun() {
		return downloadRun;
	}
	
	
}