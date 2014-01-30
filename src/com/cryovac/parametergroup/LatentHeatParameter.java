package com.cryovac.parametergroup;

public class LatentHeatParameter {
	public String evaporationAtBoilingPoint;
	public String liquefyAtMeltingPoint;
	public String evaporationEntropy;
	
	public void set(String evaporationAtBoilingPointValue,
			String liquefyAtMeltingPointValue, String evaporationEntropyValue){
		this.evaporationAtBoilingPoint = evaporationAtBoilingPointValue;
		this.liquefyAtMeltingPoint = liquefyAtMeltingPointValue;
		this.evaporationEntropy = evaporationEntropyValue;
	}
}
