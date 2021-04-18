package it.ipfsdownloadmonitor.model;

import java.util.ArrayList;
import java.util.List;

public class DownloadStats {
	boolean runDownload = false;
	private int peersConnected = 0;
	private int contributor = 0;
	
	private String totSize = "";
	private String percDownload = "";
	private String recivedTotal = "";
	private List<PieData> pieData;
	private List<MapData> mapDataList = new ArrayList<>();
	private List<Double> kbProgress = new ArrayList<>();
	
	private List<Contributor> contributorList;
	
	public int getPeersConnected() {
		return peersConnected;
	}

	public void setPeersConnected(int peersConnected) {
		this.peersConnected = peersConnected;
	}
	
	public void setContributor(int contributor) {
		this.contributor = contributor;
	}
	
	public int getContributor() {
		return contributor;
	}
	
	public void setContributorList(List<Contributor> contributorList) {
		this.contributorList = contributorList;
	}
	
	public List<Contributor> getContributorList() {
		return contributorList;
	}
	public boolean isRunDownload() {
		return runDownload;
	}
	public void setRunDownload(boolean runDownload) {
		this.runDownload = runDownload;
	}
	public void setPercDownload(String percDownload) {
		this.percDownload = percDownload;
	}
	public String getPercDownload() {
		return percDownload;
	}
	public void setTotSize(String totSize) {
		this.totSize = totSize;
	}
	public String getTotSize() {
		return totSize;
	}
	public void setRecivedTotal(String recivedTotal) {
		this.recivedTotal = recivedTotal;
	}
	public String getRecivedTotal() {
		return recivedTotal;
	}
	public void setPieData(List<PieData> pieData) {
		this.pieData = pieData;
	}
	public List<PieData> getPieData() {
		return pieData;
	}
	public void setMapDataList(List<MapData> mapDataList) {
		this.mapDataList = mapDataList;
	}
	public List<MapData> getMapDataList() {
		return mapDataList;
	}
	public void setKbProgress(List<Double> kbProgress) {
		this.kbProgress = kbProgress;
	}
	public List<Double> getKbProgress() {
		return kbProgress;
	}
}