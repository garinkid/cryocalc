package com.cryovac.parametergroup;

public class CriticalPointParameter {
	public String temperature;
	public String pressure;
	
	public void set(String temperatureValue, String pressureValue){
		this.pressure = pressureValue;
		this.temperature = temperatureValue;
	}
}
