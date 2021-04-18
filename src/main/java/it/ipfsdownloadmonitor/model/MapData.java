package it.ipfsdownloadmonitor.model;

public class MapData {
//	[
//    { latLng: [41.90, 12.45], name: 'Vatican City' },
	
	private double[] latLng = new double[2];
	private String name;
	
	public double[] getLatLng() {
		return latLng;
	}
	public void setLatLng(double[] latLng) {
		this.latLng = latLng;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
