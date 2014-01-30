package com.cryovac.parametergroup;

public class DensityParameter {
	public String gasAt273KAt100kPa = new String();
	public String criticalPoint = new String();
	public String vaporAtBoilingPoint = new String();
	public String liquidAtBoilingPoint = new String();
	public String solidAtMeltingPoint = new String();
	public String volumetricGasToLiquidRatioAt273KAt100kPaFrom1CubicMeterLiquid = new String();

	public void set(String gasAt273KAt100kPaValue, String criticalPointValue,
			String vaporAtBoilingPointValue, String liquidAtBoilingPointValue,
			String solidAtMeltingPointValue,
			String volumetricGasToLiquidRatioAt273KAt100kPaFrom1CubicMeterLiquidValue){
		
		this.gasAt273KAt100kPa = gasAt273KAt100kPaValue;
		this.criticalPoint = criticalPointValue;
		this.vaporAtBoilingPoint = vaporAtBoilingPointValue;
		this.liquidAtBoilingPoint = liquidAtBoilingPointValue;
		this.solidAtMeltingPoint = solidAtMeltingPointValue;
		this.volumetricGasToLiquidRatioAt273KAt100kPaFrom1CubicMeterLiquid = 
				volumetricGasToLiquidRatioAt273KAt100kPaFrom1CubicMeterLiquidValue;
		
	}
	
}
