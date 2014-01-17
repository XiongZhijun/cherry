/*
 * Copyright © 2013-2014 Xiong Zhijun, All Rights Reserved. 
 */
package os.cherry.android.lbs;

import java.io.Serializable;

/**
 * 定义了一个经纬度定位模型
 * 
 * @author Xiong Zhijun
 * @email hust.xzj@gmail.com
 * 
 */
public class Location implements Serializable {
	private static final long serialVersionUID = 8597305990067275881L;
	/** 经度 */
	private double longitude;
	/** 纬度 */
	private double latitude;

	public Location() {
		super();
	}

	public Location(double longitude, double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}
