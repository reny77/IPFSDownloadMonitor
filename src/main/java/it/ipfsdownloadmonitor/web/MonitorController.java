package it.ipfsdownloadmonitor.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ipfsdownloadmonitor.ipfs.IpfsManager;
import it.ipfsdownloadmonitor.model.ContributorData;
import it.ipfsdownloadmonitor.model.DownloadStats;
import it.ipfsdownloadmonitor.model.ObjectInfo;

@RestController
public class MonitorController {

	@GetMapping(value = "/objectinfo")
	public ObjectInfo getDownloadInfo() {
		ObjectInfo objectInfo = IpfsManager.getInstance().getObjectInfo();
		if (objectInfo == null) {
			objectInfo = new ObjectInfo();
			objectInfo.setCid("-");
			objectInfo.setHash("-");
			objectInfo.setSize(0L);
			objectInfo.setSizeHumanReadble("-");
			objectInfo.setStart(null);
			objectInfo.setType("-");
		}
		return objectInfo;
	}

	@GetMapping(value = "/downloadstats")
	public DownloadStats getDownloadStats() {
		DownloadStats downloadStats = IpfsManager.getInstance().getDownloadStats();
		return downloadStats;
	}

	@GetMapping(value = "/contributors")
	public ContributorData getContributorData() {
		ContributorData contributorData = new ContributorData();    	
		DownloadStats downloadStats = IpfsManager.getInstance().getDownloadStats();
		contributorData.setData(downloadStats.getContributorList());
		return contributorData;
	}
	
	
}