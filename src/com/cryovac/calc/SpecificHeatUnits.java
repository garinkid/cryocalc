package com.cryovac.calc;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;


public class SpecificHeatUnits {
	double a, b, c, d, e, f, h, i, g;
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
	
	public double calculateSpecificHeatCommon(double temperature) throws Exception{
 		double specificHeat = (double) 0.0;
 		double logTemperature = (double)(Math.log10(temperature));
 		double aSection = this.a;
 		double bSection = (double)(this.b*(logTemperature));
 		double cSection = (double)(this.c*(Math.pow(logTemperature,2)));
 		double dSection = (double)(this.d*(Math.pow(logTemperature,3)));
 		double eSection = (double)(this.e*(Math.pow(logTemperature,4)));
 		double fSection = (double)(this.f*(Math.pow(logTemperature,5)));
 		double gSection = (double)(this.g*(Math.pow(logTemperature,6)));
 		double hSection = (double)(this.h*(Math.pow(logTemperature,7)));
 		double iSection = (double)(this.i*(Math.pow(logTemperature,8)));
 		double sumSection = (aSection + bSection + cSection + dSection + eSection +
 				fSection + gSection + hSection + iSection);
 		
 		Log.d("SumSection:", String.valueOf(sumSection));
		specificHeat  = (double) Math.pow(10,sumSection);
		if(Double.isInfinite(specificHeat) || Double.isNaN(specificHeat)){
			throw new Exception("Infinite or NaN result");
		}else{
			if (specificHeat < Double.MIN_VALUE){specificHeat = Double.MIN_VALUE;}
			return specificHeat;		
		}
 	}
	
	
	public void setMaterial(String material){	
	 	if (material.equals(res.getString(R.string.aluminum_3003f_5083_6061T6))){
	 		setToAluminum3003f50836061T6();
	 		return;
	 	}
	 	if (material.equals(res.getString(R.string.apiezon_n))){
	 		setToApiezonN();
	 		return;
	 	}
	 	if (material.equals(res.getString(R.string.beryllium))){
	 		setToBeryllium();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.copper))){
	 		setToCopper();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.fiberglass_epoxy))){
	 		setToFiberglassEpoxy();
	 		return;
	 	}
	 	if (material.equals(res.getString(R.string.indium))){
	 		setToIndium();
	 		return;
	 	}
	 	if (material.equals(res.getString(R.string.invar_fe36ni))){
	 		setToInvarFe36Ni();
	 		return;
	 	}
	 	if (material.equals(res.getString(R.string.nickel_steel_fe225ni_fe325ni_fe50ni_fe90ni))){
	 		setToNickelSteelFe225NiFe325NiFe50NiFe90Ni();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.platinum))){
	 		setToPlatinum();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.polyamide_nylon))){
	 		setToPolyamideNylon();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.polyimide_kapton))){
	 		setToPolyimideKapton();
	 		return;
	 	}
	 	if (material.equals(res.getString(R.string.polystyrene_density_99_point_96))){
	 		setToPolystyreneDensity99Point96();
	 		return;
	 	}
	 	if (material.equals(res.getString(R.string.polystyrene_density_9_point_93))){
	 		setToPolystyreneDensity9Point93();
	 		return;
	 	}
	 	if (material.equals(res.getString(R.string.polystyrene_density_60_point_07))){
	 		setToPolystyreneDensity60Point07();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.polyurethane_density_49_point_02))){
	 		setToPolyurethaneDensity49Point02();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.polyurethane_density_389_point_25))){
	 		setToPolyurethaneDensity389Point25();
	 		return;
	 	}
	 	if (material.equals(res.getString(R.string.pvc_polyvinyl_chloride_density_48_point_05))){
	 		setToPVCPolyvinylChlorideDensity48Point05();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.stainless_steel_304))){
	 		setToStainlessSteel304();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.stainless_steel_304l))){
	 		setToStainlessSteel304L();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.stainless_steel_310_temperature_range_in_kelvin_4_to_47))){
	 		setToStainlessSteel310TemperatureRangeInKelvin4To47();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.stainless_steel_310_temperature_range_in_kelvin_47_to_300))){
	 		setToStainlessSteel310TemperatureRangeInKelvin47To300();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.stainless_steel_316_temperature_range_in_kelvin_4_to_50))){
	 		setToStainlessSteel316TemperatureRangeInKelvin4To50();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.stainless_steel_316_temperature_range_in_kelvin_50_to_300))){
	 		setToStainlessSteel316TemperatureRangeInKelvin50To300();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.teflon))){
	 		setToTeflon();
	 		return;
	 	}
	}

	public void setToAluminum3003f50836061T6(){
		// in 	J/(KgK)
		this.nameId = R.string.aluminum_3003f_5083_6061T6;
		this.a = (double) 46.6467;
		this.b = (double) -314.292;
		this.c = (double) 866.662;
		this.d = (double) -1298.3;
		this.e = (double) 1162.27;
		this.f = (double) -637.795;
		this.g = (double) 210.351;
		this.h = (double) -38.3094;
		this.i = (double) 2.96344;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "5";
	}
	
	public void setToApiezonN(){
		// in 	J/(KgK)
		this.nameId = R.string.apiezon_n;
		this.a = (double) -1.61975;
		this.b = (double) 3.10923;
		this.c = (double) -0.712719;
		this.d = (double) 4.93675;
		this.e = (double) -9.37993;
		this.f = (double) 7.58304;
		this.g = (double) -3.11048;
		this.h = (double) 0.628309;
		this.i = (double) -0.0482634;
		this.dataRange = "1-200";
		this.equationRange = "2-200";
		this.error = "2";
	}
	
	public void setToBeryllium(){
		// in 	J/(KgK)
		this.nameId = R.string.beryllium;
		this.a = (double) -526.84477;
		this.b = (double) 2755.4105;
		this.c = (double) -6209.8985;
		this.d = (double) 7859.2257;
		this.e = (double) -6106.2095;
		this.f = (double) 2982.9958;
		this.g = (double) -894.99967;
		this.h = (double) 150.85256;
		this.i = (double) -10.943615;
		this.dataRange = "10-284";
		this.equationRange = "14-284";
		this.error = "--";
	}
	
	public void setToCopper(){
		// in 	J/(KgK)
		this.nameId = R.string.copper;
		this.a = (double) -1.91844;
		this.b = (double) -0.15973;
		this.c = (double) 8.61013;
		this.d = (double) -18.996;
		this.e = (double) 21.9661;
		this.f = (double) -12.7328;
		this.g = (double) 3.54322;
		this.h = (double) -0.3797;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "--";
	}

	public void setToFiberglassEpoxy(){
		// in 	J/(KgK)
		this.nameId = R.string.fiberglass_epoxy;
		this.a = (double) -2.4083;
		this.b = (double) 7.6006;
		this.c = (double) -8.2982;
		this.d = (double) 7.3301;
		this.e = (double) -4.2386;
		this.f = (double) 1.4294;
		this.g = (double) -0.24396;
		this.h = (double) 0.015236;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "2";
	}
	
	public void setToIndium(){
		// in 	J/(KgK)
		this.nameId = R.string.indium;
		this.a = (double) -2.4259351;
		this.b = (double) 12.613611;
		this.c = (double) -46.472893;
		this.d = (double) 104.64717;
		this.e = (double) -127.18630;
		this.f = (double) 88.805612;
		this.g = (double) -35.915625;
		this.h = (double) 7.8307989;
		this.i = (double) -.71218931;
		this.dataRange = "1-300";
		this.equationRange = "4-295";
		this.error = "3";
	}
	
	public void setToInvarFe36Ni(){
		// in 	J/(KgK)
		this.nameId = R.string.invar_fe36ni;
		this.a = (double) 28.08;
		this.b = (double) -228.23;
		this.c = (double) 777.587;
		this.d = (double) -1448.423;
		this.e = (double)  1596.567;
		this.f = (double) -1040.294;
		this.g = (double) 371.2125;
		this.h = (double) -56.004;
		this.i = (double) 0;
		this.dataRange = "4-20";
		this.equationRange = "4.27";
		this.error = "2";
	}
	
	public void setToNickelSteelFe225NiFe325NiFe50NiFe90Ni(){
		this.nameId = R.string.nickel_steel_fe225ni_fe325ni_fe50ni_fe90ni;
		this.a = (double) 15503.108;
		this.b = (double) -37280.377;
		this.c = (double) 26788.417;
		this.d = (double) 7010.0877;
		this.e = (double) -22731.651;
		this.f = (double) 15386.526;
		this.g = (double) -5175.7968;
		this.h = (double) 896.97274;
		this.i = (double) -64.055866;
		this.dataRange = "60-300";
		this.equationRange = "55-300";
		this.error = "1.5";
	}
	
	public void setToPlatinum(){
		this.nameId = R.string.platinum;
		this.a = (double) -1.6135538;
		this.b = (double) 0.95823584;
		this.c = (double) 1.4317770;
		this.d = (double) -3.5963989;
		this.e = (double) 5.1299735;
		this.f = (double) -2.4186452;
		this.g = (double) -0.12560841;
		this.h = (double) 0.34342394;
		this.i = (double) -0.06198179;
		this.dataRange = "1-296";
		this.equationRange = "1-295";
		this.error = "3";
	}
	
	public void setToPolyamideNylon(){
		this.nameId = R.string.polyamide_nylon;
		this.a = (double) -5.2929;
		this.b = (double) 25.301;
		this.c = (double) -54.874;
		this.d = (double) 71.061;
		this.e = (double) -52.236;
		this.f = (double) 21.648;
		this.g = (double) -4.7317;
		this.h = (double) 0.42518;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "4";
	}

	public void setToPolyimideKapton(){
		this.nameId = R.string.polyimide_kapton;
		this.a = (double) -1.3684;
		this.b = (double) 0.65892;
		this.c = (double) 2.8719;
		this.d = (double) 0.42651;
		this.e = (double) -3.0088;
		this.f = (double) 1.9558;
		this.g = (double) -0.51998;
		this.h = (double) 0.051574;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "3";
	}
	
	public void setToPolystyreneDensity99Point96(){
		this.nameId = R.string.polystyrene_density_99_point_96;
		this.a = (double) -5911.474;
		this.b = (double) 14991.16;
		this.c = (double) -15513.753;
		this.d = (double) 8232.5666;
		this.e = (double) -2237.3586;
		this.f = (double) 225.423;
		this.g = (double) 20.33505;
		this.h = (double) -4.6169;
		this.i = (double) 0;
		this.dataRange = "100-300";
		this.equationRange = "90-300";
		this.error = "1";
	}
	
	public void setToPolystyreneDensity9Point93(){
		this.nameId = R.string.polystyrene_density_9_point_93;
		this.a = (double) -734.172;
		this.b = (double) 1163.613;
		this.c = (double) -135.157;
		this.d = (double) -878.514;
		this.e = (double) 791.787;
		this.f = (double) -308.6236;
		this.g = (double) 58.6764;
		this.h = (double) -4.4494;
		this.i = (double) 0;
		this.dataRange = "100-300";
		this.equationRange = "90-300";
		this.error = "1.5";
	}

	public void setToPolystyreneDensity60Point07(){
		this.nameId = R.string.polystyrene_density_60_point_07;
		this.a = (double) 2139.33;
		this.b = (double) -6518.015;
		this.c = (double) 9650.919;
		this.d = (double) -9229.889;
		this.e = (double) 6104.0571;
		this.f = (double) -2739.14;
		this.g = (double) 784.878;
		this.h = (double) -128.40103;
		this.i = (double) 0;
		this.dataRange = "100-300";
		this.equationRange = "80-300";
		this.error = "1";
	}	
	
	public void setToPolyurethaneDensity49Point02(){
		this.nameId = R.string.polyurethane_density_49_point_02;
		this.a = (double) 89.69;
		this.b = (double) -269.32;
		this.c = (double) 333.276;
		this.d = (double) -214.635;
		this.e = (double) 76.2052;
		this.f = (double) -14.1137;
		this.g = (double) 1.061;
		this.h = (double) 0;
		this.i = (double) 0;
		this.dataRange = "23-300";
		this.equationRange = "16-300";
		this.error = "1";
	}	
	
	public void setToPolyurethaneDensity389Point25(){
		this.nameId = R.string.polyurethane_density_389_point_25;
		this.a = (double) 4894.36;
		this.b = (double) -11608.63;
		this.c = (double) 10463.31;
		this.d = (double) -3895.8;
		this.e = (double) -40.0053;
		this.f = (double) 497.4517;
		this.g = (double) -147.7555;
		this.h = (double) 14.19365;
		this.i = (double) 0;
		this.dataRange = "100-300";
		this.equationRange = "80-300";
		this.error = "2";
	}
	
	private void setToPVCPolyvinylChlorideDensity48Point05(){
		this.nameId = R.string.pvc_polyvinyl_chloride_density_48_point_05;
		this.a = (double) 190.776;
		this.b = (double) -991.521;
		this.c = (double) 2200.811;
		this.d = (double) -2726.414;
		this.e = (double) 2069.971;
		this.f = (double) -988.4221;
		this.g = (double) 290.405;
		this.h = (double) -48.0737;
		this.i = (double) 3.437928;
		this.dataRange = "4-300";
		this.equationRange = "6-300";
		this.error = "2";	
	}

	private void setToStainlessSteel304() {
		this.nameId = R.string.stainless_steel_304;
		this.a = (double) 22.0061;
		this.b = (double) -127.5528;
		this.c = (double) 303.647;
		this.d = (double) -381.0098;
		this.e = (double) 274.0328;
		this.f = (double) -112.9212;
		this.g = (double) 24.7593;
		this.h = (double) -2.239153;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "5";	
		
	}
	
	private void setToStainlessSteel304L() {
		this.nameId = R.string.stainless_steel_304l;
		this.a = (double) -351.51;
		this.b = (double) 3123.695;
		this.c = (double) -12017.28;
		this.d = (double) 26143.99;
		this.e = (double) -35176.33;
		this.f = (double) 29981.75;
		this.g = (double) -15812.78;
		this.h = (double) 4719.64;
		this.i = (double) -610.515;
		this.dataRange = "4-20";
		this.equationRange = "4-23";
		this.error = "1";	
		
	}	
	
	private void setToStainlessSteel310TemperatureRangeInKelvin4To47() {
		this.nameId = R.string.stainless_steel_310_temperature_range_in_kelvin_4_to_47;
		this.a = (double) 20.694;
		this.b = (double) -171.007;
		this.c = (double) 600.6256;
		this.d = (double) -1162.748;
		this.e = (double) 1361.931;
		this.f = (double) -986.2934;
		this.g = (double) 430.093;
		this.h = (double) -102.85;
		this.i = (double) 10.275;
		this.dataRange = "4-300";
		this.equationRange = "4-47";
		this.error = "2.5";	
		
	}
	
	private void setToStainlessSteel310TemperatureRangeInKelvin47To300() {
		this.nameId = R.string.stainless_steel_310_temperature_range_in_kelvin_47_to_300;
		this.a = (double) -2755.63;
		this.b = (double) 9704.831;
		this.c = (double) -14618.36;
		this.d = (double) 12202.74;
		this.e = (double) -6092.339;
		this.f = (double) 1818.555;
		this.g = (double) -300.458;
		this.h = (double) 21.1942;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "47-300";
		this.error = "2.5";		
	}
	
	private void setToStainlessSteel316TemperatureRangeInKelvin4To50() {
		this.nameId = R.string.stainless_steel_316_temperature_range_in_kelvin_4_to_50;
		this.a = (double) 12.2486;
		this.b = (double) -80.6422;
		this.c = (double) 218.743;
		this.d = (double) -308.854;
		this.e = (double) 239.5296;
		this.f = (double) -89.9982;
		this.g = (double) 3.15315;
		this.h = (double) 8.44996;
		this.i = (double) -1.91368;
		this.dataRange = "4-300";
		this.equationRange = "4-50";
		this.error = "2";	
		
	}
	

	private void setToStainlessSteel316TemperatureRangeInKelvin50To300() {
		this.nameId = R.string.stainless_steel_316_temperature_range_in_kelvin_50_to_300;
		this.a = (double) -1879.464;
		this.b = (double) 3643.198;
		this.c = (double) 76.70125;
		this.d = (double) -6176.028;
		this.e = (double) 7437.6247;
		this.f = (double) -4305.7217;
		this.g = (double) 1382.4627;
		this.h = (double) -237.22704;
		this.i = (double) 17.05262;
		this.dataRange = "4-300";
		this.equationRange = "50-300";
		this.error = "2";	
	}

	private void setToTeflon() {
		this.nameId = R.string.teflon;
		this.a = (double) 31.88256;
		this.b = (double) -166.51949;
		this.c = (double) 352.01879;
		this.d = (double) -393.44232;
		this.e = (double) 259.98072;
		this.f = (double) -104.61429;
		this.g = (double) 24.99276;
		this.h = (double) -3.20792;
		this.i = (double) 0.16503;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "1.5";	
		
	}

}
