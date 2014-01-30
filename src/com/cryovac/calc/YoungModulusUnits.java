package com.cryovac.calc;

import android.content.Context;
import android.content.res.Resources;

public class YoungModulusUnits {
	double a, b, c, d, e;
	int nameId;
	Context context;
	String info, dataRange, equationRange, error, highRange, lowRange;
	Resources res;
	
	void setContext(Context contextValue){
		this.context = contextValue;
		res = context.getResources();
	}
	
	
	public int getNameId(){
		return this.nameId;
	}
	
	public double calculate(double temperatureValue) throws Exception{
		double youngModulus = 0.0;
		double aSection = this.a;
		double bSection = this.b*temperatureValue;
		double cSection = (this.c)*(Math.pow(temperatureValue, 2));
		double dSection = (this.d)*(Math.pow(temperatureValue, 3));
		double eSection = (this.e)*(Math.pow(temperatureValue, 4));
		youngModulus = aSection + bSection + cSection + dSection + eSection;
		return youngModulus;
		
	}
	
	public void setMaterial(String material){	

	 	if (material.equals(res.getString(R.string.aluminum_5083))){
	 		setToAluminum5083();
	 		return;
	 	}
	 	if (material.equals(res.getString(R.string.aluminum_6061_T6))){
	 		setToAluminum6061T6();
	 		return;
	 	}
	 	if (material.equals(res.getString(R.string.invar_fe36ni))){
	 		setToInvarFe36Ni();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.nickel_steel_fe225ni))){
	 		setToNickelSteelFe225Ni();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.nickel_steel_fe325ni))){
	 		setToNickelSteelFe325Ni();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.nickel_steel_fe50ni))){
	 		setToNickelSteelFe50Ni();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.nickel_steel_fe90ni))){
	 		setToNickelSteelFe90Ni();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.stainless_steel_304_temperature_range_in_kelvin_5_to_57))){
	 		setToStainlessSteel304TemperatureRangeInKelvin5To57();
	 		return;
	 	}
	 
	 	if (material.equals(res.getString(R.string.stainless_steel_304_temperature_range_in_kelvin_57_to_293))){
	 		setToStainlessSteel304TemperatureRangeInKelvin57To293();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.stainless_steel_310))){
	 		setToStainlessSteel310();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.stainless_steel_316_temperature_range_in_kelvin_8_to_50))){
	 		setToStainlessSteel316TemperatureRangeInKelvin8To50();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.stainless_steel_316_temperature_range_in_kelvin_50_to_294))){
	 		setToStainlessSteel316TemperatureRangeInKelvin50To294();
	 		return;
	 	}
	 	
	 	
	}
	
	private void setToAluminum5083(){
		this.nameId = R.string.aluminum_5083;
		this.a = (double) 8.083212E1;
		this.b = (double) 1.061708E-2;
		this.c = (double) -3.016100E-4;
		this.d = (double) 7.561340E-7;
		this.e = (double) -6.994800E-10 ;
		this.dataRange = "0-300";
		this.equationRange = "2-295";
		this.error = "1";
	}
	
	
	private void setToAluminum6061T6(){
		this.nameId = R.string.aluminum_6061_T6;
		this.a = (double) 7.771221E1;
		this.b = (double) 1.030646E-2;
		this.c = (double) -2.924100E-4;
		this.d = (double) 8.993600E-7;
		this.e = (double) -1.070900E-9;
		this.dataRange = "0-299";
		this.equationRange = "2-295";
		this.error = "1";
	}
	private void setToInvarFe36Ni(){
		this.nameId = R.string.invar_fe36ni;
		this.a = (double) 1.41565E2;
		this.b = (double) 2.54435E-2;
		this.c = (double) -1.00842E-3;
		this.d = (double) 6.72797E-6;
		this.e = (double) -1.08230E-8;
		this.dataRange = "1-298";
		this.equationRange = "3-298";
		this.error = "1";
	}
	
	private void setToNickelSteelFe225Ni(){
		this.nameId = R.string.nickel_steel_fe225ni;
		this.a = (double) 2.17656E2;
		this.b = (double) 1.11923E-2;
		this.c = (double) -3.54650E-4;
		this.d = (double) 8.31168E-7;
		this.e = (double) -7.03680E-10;
		this.dataRange = "0-297";
		this.equationRange = "0-297";
		this.error = "1";
	}
	
	private void setToNickelSteelFe325Ni(){
		this.nameId = R.string.nickel_steel_fe325ni;
		this.a = (double) 2.15351E2;
		this.b = (double) 1.04624E-2;
		this.c = (double) -3.55710E-4;
		this.d = (double) 8.9497E-7;
		this.e = (double) -8.48640E-10;
		this.dataRange = "0-298";
		this.equationRange = "0-298";
		this.error = "1";
	}
	
	private void setToNickelSteelFe50Ni(){
		this.nameId = R.string.nickel_steel_fe50ni;
		this.a = (double) 2.09182E2;
		this.b = (double) 1.66974E-2;
		this.c = (double) -2.06410E-4;
		this.d = (double) -1.82360E-7;
		this.e = (double) 9.27168E-10;
		this.dataRange = "0-297";
		this.equationRange = "0-297";
		this.error = "1";
	}
	
	
	private void setToNickelSteelFe90Ni(){
		this.nameId = R.string.nickel_steel_fe90ni;
		this.a = (double) 2.05335E2;
		this.b = (double) 1.74835E-2;
		this.c = (double) -3.65760E-4;
		this.d = (double) 8.71545E-7;
		this.e = (double) -7.78130E-10;
		this.dataRange = "0-298";
		this.equationRange = "0-298";
		this.error = "1";
	}

	private void setToStainlessSteel304TemperatureRangeInKelvin5To57(){
		this.nameId = R.string.stainless_steel_304_temperature_range_in_kelvin_5_to_57;
		this.a = (double) 2.098145E2;
		this.b = (double) 1.217019E-1;
		this.c = (double) -1.146999E-2;
		this.d = (double) 3.605430E-4;
		this.e = (double) -3.017900E-6;
		this.dataRange = "5-65";
		this.equationRange = "5-57";
		this.error = "1";
	}
	
	private void setToStainlessSteel304TemperatureRangeInKelvin57To293(){
		this.nameId = R.string.stainless_steel_304_temperature_range_in_kelvin_57_to_293;
		this.a = (double) 2.100593E2;
		this.b = (double) 1.534883E-1;
		this.c = (double) -1.617390E-3;
		this.d = (double) 5.117060E-6;
		this.e = (double) -6.154600E-9;
		this.dataRange = "48-293";
		this.equationRange = "57-293";
		this.error = "1";
	}
	
	private void setToStainlessSteel310(){
		this.nameId = R.string.stainless_steel_310;
		this.a = (double) 2.066588E2;
		this.b = (double) 6.375129E-3;
		this.c = (double) -4.345200E-4;
		this.d = (double) 1.129930E-6;
		this.e = (double) -1.191600E-9;
		this.dataRange = "5-295";
		this.equationRange = "8-290";
		this.error = "1";
	}
	
	private void setToStainlessSteel316TemperatureRangeInKelvin8To50(){
		this.nameId = R.string.stainless_steel_316_temperature_range_in_kelvin_8_to_50;
		this.a = (double) 2.084729E2;
		this.b = (double) -1.358965E-1;
		this.c = (double) 8.368629E-3;
		this.d = (double) -1.381700E-4;
		this.e = (double) 6.831930E-7;
		this.dataRange = "5-60";
		this.equationRange = "8-50";
		this.error = "1";
	}
	
	private void setToStainlessSteel316TemperatureRangeInKelvin50To294(){
		this.nameId = R.string.stainless_steel_316_temperature_range_in_kelvin_50_to_294;
		this.a = (double) 2.079488E2;
		this.b = (double) 7.394241E-2;
		this.c = (double) -9.627200E-4;
		this.d = (double) 2.845560E-6;
		this.e = (double) -3.240800E-9;
		this.dataRange = "48-294";
		this.equationRange = "50-294";
		this.error = "1";
	}
	
}
