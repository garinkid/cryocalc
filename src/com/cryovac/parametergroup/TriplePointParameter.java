package com.cryovac.parametergroup;

public class TriplePointParameter {
	public String temperature;
	public String pressure;
	
	public void set(String temperatureValue, String pressureValue){
		this.pressure = pressureValue;
		this.temperature = temperatureValue;
	}

}
