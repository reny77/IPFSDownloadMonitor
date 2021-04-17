package it.ipfsdownloadmonitor.model;

import java.util.Date;

public class ObjectInfo {
	/*{Hash=QmSnuWmxptJZdLJpKRarxBMS2Ju2oANVrgbr2xWbie9b2D, 
	NumLinks=6,
			BlockSize=345,
			LinksSize=343, 
			DataSize=2, 
			CumulativeSize=61702258918}*/
	
	private String cid = null;
	private String hash = null;
	private Date start = null;
	private Long size = null;
	private String sizeHumanReadble = null;
	private String type = null;
	
	public ObjectInfo() {
	}
	
	
	public ObjectInfo(String cid, String hash, Long size, String sizeHumanReadble, String type) {
		super();
		this.cid = cid;
		this.hash = hash;
		this.start = start;
		this.size = size;
		this.sizeHumanReadble = sizeHumanReadble;
		this.type = type;
	}


	public Date getStart() {
		return start;
	}
	
	public void setStart(Date start) {
		this.start = start;
	}
	
	public Long getSize() {
		return size;
	}
	
	public void setSize(Long size) {
		this.size = size;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public void setSizeHumanReadble(String sizeHumanReadble) {
		this.sizeHumanReadble = sizeHumanReadble;
	}
	public String getSizeHumanReadble() {
		return sizeHumanReadble;
	}


	public String getCid() {
		return cid;
	}


	public void setCid(String cid) {
		this.cid = cid;
	}


	public String getHash() {
		return hash;
	}


	public void setHash(String hash) {
		this.hash = hash;
	}
	
}
