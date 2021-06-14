package com.geek.house.db;

public class Meter {
	private long Time;
	private String Water;
	private String Power;

	public long getTime() {
		return Time;
	}

	public void setTime(long time) {
		Time = time;
	}

	public String getWater() {
		return Water;
	}

	public void setWater(String water) {
		Water = water;
	}

	public String getPower() {
		return Power;
	}

	public void setPower(String power) {
		Power = power;
	}
}
