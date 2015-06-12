package com.cjlu.newspublish.models;

import java.util.Date;


public class Weather extends BaseEntity {

	private static final long serialVersionUID = -7231691135362868869L;

	private String province;
	private String city;
	private String county;
	private String jweather;
	private String jtemperature;
	private String mweather;
	private String mtemperature;
	private String hweather;
	private String htemperature;
	private Date updateTime;
	
	public Weather() {
		super();
	}

	public Weather(String province, String city, String county,
			String jweather, String jtemperature, String mweather,
			String mtemperature, String hweather, String htemperature,Date updateTime) {
		this.province = province;
		this.city = city;
		this.county = county;
		this.jweather = jweather;
		this.jtemperature = jtemperature;
		this.mweather = mweather;
		this.mtemperature = mtemperature;
		this.hweather = hweather;
		this.htemperature = htemperature;
		this.updateTime = updateTime;
	}

	public String getJweather() {
		return jweather;
	}

	public void setJweather(String jweather) {
		this.jweather = jweather;
	}

	public String getJtemperature() {
		return jtemperature;
	}

	public void setJtemperature(String jtemperature) {
		this.jtemperature = jtemperature;
	}

	public String getMweather() {
		return mweather;
	}

	public void setMweather(String mweather) {
		this.mweather = mweather;
	}

	public String getMtemperature() {
		return mtemperature;
	}

	public void setMtemperature(String mtemperature) {
		this.mtemperature = mtemperature;
	}

	public String getHweather() {
		return hweather;
	}

	public void setHweather(String hweather) {
		this.hweather = hweather;
	}

	public String getHtemperature() {
		return htemperature;
	}

	public void setHtemperature(String htemperature) {
		this.htemperature = htemperature;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
