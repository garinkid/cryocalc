package com.cryovac.calc;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math.MathException;
import org.apache.commons.math.special.Erf;

public class ThermalConductivityUnits implements UnivariateFunction{
	double a, b, c, d, e, f, h, i, g;
	int nameId;
	Context context;
	String info, dataRange, equationRange, error, highRange, lowRange;
	Resources res;
	@Override
	public double value(double temperatureValue){
		double thermalConductivityValue = 0.0;
		switch(this.nameId){
	    case R.string.copper_RRR_50:
	    case R.string.copper_RRR_100:
	    case R.string.copper_RRR_150:
	    case R.string.copper_RRR_300:	     
	    case R.string.copper_RRR_500:
	    	thermalConductivityValue = calculateThermalConductivityCopperWithoutException(temperatureValue);
	    	break;
	    default:
	    	thermalConductivityValue = calculateThermalConductivityCommonWithoutException(temperatureValue);
	    	break;
		}
		return thermalConductivityValue;
	}
	
	public double calculate(double temperatureValue) throws Exception{
		double thermalConductivityValue = 0.0;
		switch(this.nameId){
		case R.string.kevlar_49_composite:
		case R.string.kevlar_49_fiber:
			try{
				thermalConductivityValue = calculateThermalConductivityKevlar49(temperatureValue);
			}catch(Exception ex){
				throw new Exception("Infinite or NaN result");
			}
			break;
	    case R.string.copper_RRR_50:
	    case R.string.copper_RRR_100:
	    case R.string.copper_RRR_150:
	    case R.string.copper_RRR_300:	     
	    case R.string.copper_RRR_500:
	    	try{
	    		thermalConductivityValue = calculateThermalConductivityCopper(temperatureValue);
	    	}catch (Exception ex){
	    		throw new Exception("Infinite or NaN result");
	    	}
	    	break;
	    default:
	    	try{
	    		thermalConductivityValue = calculateThermalConductivityCommon(temperatureValue);
	    	}catch (Exception ex){
	    		throw new Exception("Infinite or NaN result");
	    	}
	    	break;
		}
		return thermalConductivityValue;
	}
	
	
	
	void setContext(Context contextValue){
		this.context = contextValue;
		res = context.getResources();
	}
	
	
	public int getNameId(){
		return this.nameId;
	}
	
	
	// for integral UnivariateFunction
 	public double calculateThermalConductivityCopperWithoutException(double temperature){
 		double thermalConductivity = (double) 0.0;
 		double aSection = this.a;
 		double bSection = (double)(this.b*(Math.pow(temperature,0.5)));
 		double cSection = (double)(this.c*(Math.pow(temperature,0.5)));
 		double dSection = (double)(this.d*temperature);
 		double eSection = (double)(this.e*temperature);
 		double fSection = (double)(this.f*(Math.pow(temperature,1.5)));
 		double gSection = (double)(this.g*(Math.pow(temperature,1.5)));
 		double hSection = (double)(this.h*(Math.pow(temperature,2)));
 		double iSection = (double)(this.i*(Math.pow(temperature,2)));
 		double sumSection1 = (aSection + cSection + eSection + gSection + iSection);
 		double sumSection2	= (1 + bSection + dSection + fSection + hSection);
 		thermalConductivity = (double) Math.pow(10,(sumSection1/sumSection2));
 		if (thermalConductivity < Double.MIN_VALUE){thermalConductivity = Double.MIN_VALUE;}
 		return thermalConductivity;			
 			
 	}
 	
	
 	public double calculateThermalConductivityCopper(double temperature) throws Exception{
 		double thermalConductivity = (double) 0.0;
 		double aSection = this.a;
 		double bSection = (double)(this.b*(Math.pow(temperature,0.5)));
 		double cSection = (double)(this.c*(Math.pow(temperature,0.5)));
 		double dSection = (double)(this.d*temperature);
 		double eSection = (double)(this.e*temperature);
 		double fSection = (double)(this.f*(Math.pow(temperature,1.5)));
 		double gSection = (double)(this.g*(Math.pow(temperature,1.5)));
 		double hSection = (double)(this.h*(Math.pow(temperature,2)));
 		double iSection = (double)(this.i*(Math.pow(temperature,2)));
 		double sumSection1 = (aSection + cSection + eSection + gSection + iSection);
 		double sumSection2	= (1 + bSection + dSection + fSection + hSection);
 		thermalConductivity = (double) Math.pow(10,(sumSection1/sumSection2));
 		if(Double.isInfinite(thermalConductivity) || Double.isNaN(thermalConductivity)){
 			throw new Exception ("Infinite or NaN result");
 		}else{
 			if (thermalConductivity < Double.MIN_VALUE){thermalConductivity = Double.MIN_VALUE;}
 			return thermalConductivity;			
 		}	
 	}
 	

 	// for integral UnivariateFunction
 	public double calculateThermalConductivityCommonWithoutException(double temperature){
 		double thermalConductivity = (double) 0.0;
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
 		thermalConductivity = (double) Math.pow(10,sumSection);
 		return thermalConductivity;			
 	}
	
 	
 	public double calculateThermalConductivityCommon(double temperature) throws Exception{
 		double thermalConductivity = (double) 0.0;
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
 		thermalConductivity = (double) Math.pow(10,sumSection);
 		if(Double.isInfinite(thermalConductivity) || Double.isNaN(thermalConductivity)){
 			throw new Exception ("Infinite or NaN result");
 		}else{
 			return thermalConductivity;			
 		}	
 	}
	
 	public double calculateThermalConductivityKevlar49(double temperature) throws Exception{
 		double thermalConductivity = (double) 0.0;
 		double aSection = this.a;
 		double bSection = (double)(this.b*(Math.log10(temperature)));
 		double cSection1 = 0.0, cSection2 = 0.0;
 		try{
 			cSection1 = (double) ((1.0 - (Erf.erf(( 2.0*(Math.log10(temperature)-this.c))))) / 2.0);
 			cSection2 = (double) ((1.0 + (Erf.erf(( 2.0*(Math.log10(temperature)-this.c))))) / 2.0);
 		}catch(MathException e){ 
 		}
 		double dSection = (double)(this.d);
 		double eSection = (double)(this.e);
 		double fSection = (double)(Math.exp(-(Math.log10(temperature))/this.f));
 		double sumSection1 = (aSection + bSection)*(cSection1) ;
 		double sumSection2 = (dSection+(eSection*fSection))*(cSection2) ;
 		double sumSection = sumSection1 + sumSection2;
 		

 		Log.d("a", Double.toString(aSection));
 		Log.d("b", Double.toString(bSection));
 		Log.d("cSection1", Double.toString(cSection1));
 		Log.d("cSection2", Double.toString(cSection2));
 		Log.d("d", Double.toString(dSection));
 		Log.d("e", Double.toString(eSection));
 		Log.d("f", Double.toString(fSection));
 		Log.d("sum1", Double.toString(sumSection1));
 		Log.d("sum2", Double.toString(sumSection2));
 		Log.d("sum1+2", Double.toString(sumSection));
 		
 		thermalConductivity = (double) Math.pow(10,sumSection);
 		if(Double.isInfinite(thermalConductivity) || Double.isNaN(thermalConductivity)){
 			throw new Exception ("Infinite or NaN result");
 		}else{
 			if (thermalConductivity < Double.MIN_VALUE){thermalConductivity = Double.MIN_VALUE;}
 			return thermalConductivity;			
 		}	
 			
 	}
	
	public void setMaterial(String material){	
		 	if (material.equals(res.getString(R.string.aluminum_1100))){
		 		setToAluminum1100();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.aluminum_3003f))){
		 		setToAluminum3003f();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.aluminum_5083))){
		 		setToAluminum5083();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.aluminum_6061_T6))){
		 		setToAluminum6061T6();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.aluminum_6063_T5))){
		 		setToAluminum6063T5();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.balsa_density_6))){
		 		setToBalsaDensity6();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.balsa_density_11))){
		 		setToBalsaDensity11();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.beechwood_phenolic_cross_laminate_grain_direction))){
		 		setToBeechwoodPhenolicCrossLaminateGrainDirection();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.beechwood_phenolic_cross_laminate_flatwise))){
		 		setToBeechwoodPhenolicCrossLaminateFlatwise();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.beryllium_copper))){
		 		setToBerylliumCooper();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.brass))){
		 		setToBrass();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.copper_RRR_50))){
		 		setToCopperRRR50();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.copper_RRR_100))){
		 		setToCopperRRR100();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.copper_RRR_150))){
		 		setToCopperRRR150();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.copper_RRR_300))){
		 		setToCopperRRR300();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.copper_RRR_500))){
		 		setToCopperRRR500();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.fiberglass_epoxy_normal_direction))){
		 		setToFiberglassEpoxyNormalDirection();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.fiberglass_epoxy_warp_direction))){
		 		setToFiberglassEpoxyWarpDirection();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.glass_fabric_polyester_helium_warp_direction))){
		 		setToGlassFabricPolyesterHeliumWarpDirection();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.glass_fabric_polyester_nitrogen_warp_direction))){
		 		setToGlassFabricPolyesterNitrogenWarpDirection();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.glass_fabric_polyester_nitrogen_normal_direction))){
		 		setToGlassFabricPolyesterNitrogenNormalDirection();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.inconel_718))){
		 		setToInconel718();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.invar_fe36ni))){
		 		setToInvarfe36ni();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.kevlar_49_fiber))){
		 		setToKevlar49Fiber();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.kevlar_49_composite))){
		 		setToKevlar49Composite();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.lead))){
		 		setToLead();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.molybdenum))){
		 		setToMolybdenum();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.nickel_steel_fe225ni))){
		 		setToNickelSteelfe225ni();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.nickel_steel_fe325ni))){
		 		setToNickelSteelfe325ni();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.nickel_steel_fe50ni))){
		 		setToNickelSteelfe50ni();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.nickel_steel_fe90ni))){
		 		setToNickelSteelfe90ni();
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
		 	if (material.equals(res.getString(R.string.polyethylene_terephthalate_mylar))){
		 		setToPolyethyleneTerephthalateMylar();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.polyimide_kapton))){
		 		setToPolyimideKapton();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.polystyrene_density_31_point_88))){
		 		setToPolystyreneDensity31Point88();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.polystyrene_density_32_point_04))){
		 		setToPolystyreneDensity32Point04();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.polystyrene_density_49_point_98))){
		 		setToPolystyreneDensity49Point98();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.polystyrene_density_99_point_96))){
		 		setToPolystyreneDensity99Point96();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.polyurethane_density_31_point_88))){
		 		setToPolyurethaneDensity31Point88();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.polyurethane_density_32_point_04))){
		 		setToPolyurethaneDensity32Point04();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.polyurethane_density_49_point_02))){
		 		setToPolyurethaneDensity49Point02();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.polyurethane_density_64_point_08))){
		 		setToPolyurethaneDensity64Point08();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.pvc_polyvinyl_chloride_density_20_point_02))){
		 		setToPVCPolyvinylChlorideDensity20Point02();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.pvc_polyvinyl_chloride_density_56_point_06))){
		 		setToPVCPolyvinylChlorideDensity56Point06();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.stainless_steel_304))){
		 		setToStainlessSteel304();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.stainless_steel_304l))){
		 		setToStainlessSteel304l();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.stainless_steel_310))){
		 		setToStainlessSteel310();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.stainless_steel_316))){
		 		setToStainlessSteel316();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.teflon))){
		 		setToTeflon();
		 		return;
		 	}
		 	if (material.equals(res.getString(R.string.ti_6al_4v))){
		 		setToTi6al4v();
		 		return;
		 	}
		 	
		 	if (material.equals(res.getString(R.string.titanium_15_3_3_3))){
		 		setToTitanium15333();
		 		return;
		 	}

	 }
	
	private void setToAluminum1100(){
		// in 	W/(m-K)
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.nameId = R.string.aluminum_1100;
		this.a = (double) 23.39172;
		this.b = (double) -148.5733;
		this.c = (double) 422.1917;
		this.d = (double) -653.6664;
		this.e = (double) 607.0402;
		this.f = (double) -346.152;
		this.g = (double) 118.4276;
		this.h = (double) -22.2781;
		this.i = (double) 1.770187;
		this.error = "2";
	}
	
	private void setToAluminum3003f(){
		// in 	W/(m-K)
		this.nameId = R.string.aluminum_3003f;
		this.a = (double) 0.63736;
		this.b = (double) -1.1437;
		this.c = (double) 7.4624;
		this.d = (double) -12.6905;
		this.e = (double) 11.9165;
		this.f = (double) -6.18721;
		this.g = (double) 1.63939;
		this.h = (double) -0.172667;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "1-300";
		this.error = "2";
	}
	
	private void setToAluminum5083(){
		// in 	W/(m-K)
		this.nameId = R.string.aluminum_5083;
		this.a = (double) -0.90933;
		this.b = (double) 5.751;
		this.c = (double) -11.112;
		this.d = (double) 13.612;
		this.e = (double) -9.3977;
		this.f = (double) 3.6873;
		this.g = (double) -0.77295;
		this.h = (double) 0.067336;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "1-300";
		this.error = "1";
	}
	
	private void setToAluminum6061T6(){
		// in 	W/(m-K)
		this.nameId = R.string.aluminum_6061_T6;
		this.a = (double) 0.07981;
		this.b = (double) 1.0957;
		this.c = (double) -0.07277;
		this.d = (double) 0.08084;
		this.e = (double) 0.02803 ;
		this.f = (double) -0.09464;
		this.g = (double) 0.04179;
		this.h = (double) -0.00571;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "1-300";
		this.error = "0.5";
	}
	
	private void setToAluminum6063T5(){
		// in 	W/(m-K)
		this.nameId = R.string.aluminum_6063_T5;
		this.a = (double) 22.401433;
		this.b = (double) -141.13433;
		this.c = (double) 394.95461;
		this.d = (double) -601.15377;
		this.e = (double) 547.83202 ;
		this.f = (double) -305.99691;
		this.g = (double) 102.38656;
		this.h = (double) -18.810237;
		this.i = (double) 1.4576882;
		this.dataRange = "4-296";
		this.equationRange = "4-295";
		this.error = "2";
	}
	
	private void setToBalsaDensity6(){
		// in 	W/(m-K)
		this.nameId = R.string.balsa_density_6;
		this.a = (double) 4172.447 ;
		this.b = (double) -11309.97;
		this.c = (double) 12745.09;
		this.d = (double) -7647.584;
		this.e = (double) 2577.309 ;
		this.f = (double) -462.538;
		this.g = (double) 34.5351;
		this.h = (double) 0;
		this.i = (double) 0;
		this.dataRange = "88-300";
		this.equationRange = "70-300";
		this.error = "1.5";
	}	
	
	private void setToBalsaDensity11(){
		// in 	W/(m-K)
		this.nameId = R.string.balsa_density_11;
		this.a = (double) 4815.4 ;
		this.b = (double) -12969.63;
		this.c = (double) 14520.76;
		this.d = (double) -8654.164;
		this.e = (double) 2895.712 ;
		this.f = (double) -515.7272;
		this.g = (double) 38.19218;
		this.h = (double) 0;
		this.i = (double) 0;
		this.dataRange = "88-300";
		this.equationRange = "75-300";
		this.error = "3";
	}
	
	private void setToBeechwoodPhenolicCrossLaminateGrainDirection(){
		// in 	W/(m-K)
		this.nameId = R.string.beechwood_phenolic_cross_laminate_grain_direction;
		this.a = (double) -1375.11 ;
		this.b = (double) 3740.69;
		this.c = (double) -4238.465;
		this.d = (double) 2559.333;
		this.e = (double) -868.6067 ;
		this.f = (double) 157.1018;
		this.g = (double) -11.82957;
		this.h = (double) 0;
		this.i = (double) 0;
		this.dataRange = "92-300";
		this.equationRange = "80-300";
		this.error = "1";
	}
	
	private void setToBeechwoodPhenolicCrossLaminateFlatwise(){
		// in 	W/(m-K)
		this.nameId = R.string.beechwood_phenolic_cross_laminate_flatwise;
		this.a = (double) 1035.33 ;
		this.b = (double) -2191.85;
		this.c = (double) 1470.505;
		this.d = (double) 39.845;
		this.e = (double) -541.9035 ;
		this.f = (double) 289.844;
		this.g = (double) -65.2253;
		this.h = (double) 5.59956;
		this.i = (double) 0;
		this.dataRange = "92-300";
		this.equationRange = "75-300";
		this.error =  "1.5";
	}
	
	private void setToBerylliumCooper(){
	// in 	W/(m-K)
		this.nameId = R.string.beryllium_copper;
		this.a = (double) -0.50015;
		this.b = (double) 1.93190;
		this.c = (double) -1.69540;
		this.d = (double) 0.71218;
		this.e = (double) 1.27880 ;
		this.f = (double) -1.61450;
		this.g = (double) 0.68722;
		this.h = (double) -0.10501;
		this.i = (double) 0;
		this.dataRange = "2-80";
		this.equationRange = "1-120";
		this.error = "2";
	}
	
	private void setToBrass(){
	// in 	W/(m-K)
		this.nameId = R.string.brass;
		this.a = (double) 0.021035;
		this.b = (double) -1.01835;
		this.c = (double) 4.54083;
		this.d = (double) -5.03374;
		this.e = (double) 3.20536;
		this.f = (double) -1.12933;
		this.g = (double) 0.174057;
		this.h = (double) -0.0038151;
		this.i = (double) 0;
		this.dataRange = "5-116";
		this.equationRange = "5-110";
		this.error = "1.5";
	}
	
	private void setToCopperRRR50(){
	// in 	W/(m-K)
		this.nameId = R.string.copper_RRR_50;
		this.a = (double) 1.8743;
		this.b = (double) -0.41538;
		this.c = (double) -0.6018;
		this.d = (double) 0.13294;
		this.e = (double) 0.26426;
		this.f = (double) -0.0219;
		this.g = (double) -0.051276;
		this.h = (double) 0.0014871;
		this.i = (double) 0.003723;
		this.lowRange = "4";
		this.highRange = "300";
		this.error = "";
	}

	private void setToCopperRRR100(){
	// in 	W/(m-K)
		this.nameId = R.string.copper_RRR_100;
		this.a = (double) 2.2154;
		this.b = (double) -0.47461;
		this.c = (double) -0.88068;
		this.d = (double) 0.13871;
		this.e = (double) 0.29505;
		this.f = (double) -0.02043;
		this.g = (double) -0.04831;
		this.h = (double) 0.001281;
		this.i = (double) 0.003207;
		this.lowRange = "4 ";
		this.highRange = "300 ";
		this.error = "";
	}
	
	private void setToCopperRRR150(){
	// in 	W/(m-K)
		this.nameId = R.string.copper_RRR_150;
		this.a = (double) 2.3797;
		this.b = (double) -0.4918;
		this.c = (double) -0.98615;
		this.d = (double) 0.13942;
		this.e = (double) 0.30475;
		this.f = (double) -0.019713;
		this.g = (double) -0.046897;
		this.h = (double) 0.0011969;
		this.i = (double) 0.0029988;
		this.lowRange = "4 ";
		this.highRange = "300";
		this.error = "";
	}
	
	private void setToCopperRRR300(){
	// in 	W/(m-K)
		this.nameId = R.string.copper_RRR_300;
		this.a = (double) 1.357;
		this.b = (double) 0.3981;
		this.c = (double) 2.669;
		this.d = (double) -0.1346;
		this.e = (double) -0.6683;
		this.f = (double) 0.01342;
		this.g = (double) 0.05773;
		this.h = (double) 0.0002147;
		this.i = (double) 0;
		this.lowRange = "4K";
		this.highRange = "300K";
		this.error = "";
	}
	
	private void setToCopperRRR500(){
	// in 	W/(m-K)
		this.nameId = R.string.copper_RRR_500;
		this.a = (double) 2.8075;
		this.b = (double) -0.54074;
		this.c = (double) -1.2777;
		this.d = (double) 0.15362;
		this.e = (double) 0.36444;
		this.f = (double) -0.02105;
		this.g = (double) -0.051727;
		this.h = (double) 0.0012226;
		this.i = (double) 0.0030964;
		this.lowRange = "4K";
		this.highRange = "300K";
		this.error = "";
	}
	
	private void setToFiberglassEpoxyNormalDirection(){
	// in 	W/(m-K)
		this.nameId = R.string.fiberglass_epoxy_normal_direction;
		this.a = (double) -4.1236;
		this.b = (double) 13.788;
		this.c = (double) -26.068;
		this.d = (double) 26.272;
		this.e = (double) -14.663;
		this.f = (double) 4.4954;
		this.g = (double) -0.6905;
		this.h = (double) 0.0397;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "10-300";
		this.error = "5";
	}
	
	private void setToFiberglassEpoxyWarpDirection(){
	// in 	W/(m-K)
		this.nameId = R.string.fiberglass_epoxy_warp_direction;
		this.a = (double) -2.64827;
		this.b = (double) 8.80228;
		this.c = (double) -24.8998;
		this.d = (double) 41.1625;
		this.e = (double) -39.8754;
		this.f = (double) 23.1778;
		this.g = (double) -7.95635;
		this.h = (double) 1.48806;
		this.i = (double) -0.11701;
		this.dataRange = "4-300";
		this.equationRange = "12-300";
		this.error = "5";
	}

	private void setToGlassFabricPolyesterHeliumWarpDirection(){
	// in 	W/(m-K)
		this.nameId = R.string.glass_fabric_polyester_helium_warp_direction;
		this.a = (double) 689.532;
		this.b = (double) -2543.63;
		this.c = (double) 3967.067;
		this.d = (double) -3400.366;
		this.e = (double) 1731.725;
		this.f = (double) -524.309;
		this.g = (double) 87.4249;
		this.h = (double) -6.19597;
		this.i = (double) 0;
		this.dataRange = "38-300";
		this.equationRange = "18-300";
		this.error = "1";
	}
	
	private void setToGlassFabricPolyesterNitrogenWarpDirection(){
	// in 	W/(m-K)
		this.nameId = R.string.glass_fabric_polyester_nitrogen_warp_direction;
		this.a = (double) -2141.58;
		this.b = (double) 4639.74;
		this.c = (double) -3249.405;
		this.d = (double) 51.72425;
		this.e = (double) 1101.977;
		this.f = (double) -613.8563;
		this.g = (double) 141.1432;
		this.h = (double) -12.3133;
		this.i = (double) 0;
		this.dataRange = "80-300";
		this.equationRange = "60-300";
		this.error = "1";
	}
	
	private void setToGlassFabricPolyesterNitrogenNormalDirection(){
	// in 	W/(m-K)
		this.nameId = R.string.glass_fabric_polyester_nitrogen_normal_direction;
		this.a = (double) 2909.905;
		this.b = (double) -8616.64;
		this.c = (double) 10542.69;
		this.d = (double) -6832.068;
		this.e = (double) 2475.328;
		this.f = (double) -475.7;
		this.g = (double) 37.9003;
		this.h = (double) 0;
		this.i = (double) 0;
		this.dataRange = "84-300";
		this.equationRange = "70-300";
		this.error = "2";
	}
	
	private void setToInconel718(){
	// in 	W/(m-K)
		this.nameId = R.string.inconel_718;
		this.a = (double) -8.28921;
		this.b = (double) 39.4470;
		this.c = (double) -83.4353;
		this.d = (double) 98.1690;
		this.e = (double) -67.2088;
		this.f = (double) 26.7082;
		this.g = (double) -5.7205;
		this.h = (double) 0.51115;
		this.i = (double) 0;
		this.dataRange = "6-275";
		this.equationRange = "4-300";
		this.error = "2";
	}
	
	
	private void setToInvarfe36ni(){
	// in 	W/(m-K)
		this.nameId = R.string.invar_fe36ni;
		this.a = (double) -2.7064;
		this.b = (double) 8.5191;
		this.c = (double) -15.923;
		this.d = (double) 18.276;
		this.e = (double) -11.9116;
		this.f = (double) 4.40318;
		this.g = (double) -0.86018;
		this.h = (double) 0.068508;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "2";
	}

	private void setToKevlar49Fiber(){
	// in 	W/(m-K)
		this.nameId = R.string.kevlar_49_fiber;
		this.a = (double) -2.4219;
		this.b = (double) 1.986637;
		this.c = (double) 1.257441;
		this.d = (double) 0.961209;
		this.e = (double) -9.6106;
		this.f = (double) 0.777857;
		this.g = (double) 0.0;
		this.h = (double) 0.0;
		this.i = (double) 0.0;
		this.dataRange = "0.1-291";
		this.equationRange = "0-350";
		this.error = "3.1";
	}
	
	private void setToKevlar49Composite(){
	// in 	W/(m-K)
		this.nameId = R.string.kevlar_49_composite;
		this.a = (double) -2.65;
		this.b = (double) 1.986637;
		this.c = (double) 1.24851;
		this.d = (double) 0.57;
		this.e = (double) -8;
		this.f = (double) 0.777857;
		this.g = (double) 0.0;
		this.h = (double) 0.0;
		this.i = (double) 0.0;
		this.dataRange = "6-302";
		this.equationRange = "0-350";
		this.error = "7.4";
	}
	
	private void setToLead(){
	// in 	W/(m-K)
		this.nameId = R.string.lead;
		this.a = (double) 38.963479;
		this.b = (double) -221.40505;
		this.c = (double) 597.56622;
		this.d = (double) -900.93831;
		this.e = (double) 816.40461;
		this.f = (double) -455.08342;
		this.g = (double) 152.94025;
		this.h = (double) -28.451163;
		this.i = (double) 2.2516244;
		this.dataRange = "4-296";
		this.equationRange = "5-295";
		this.error = "2";
	}
	
	private void setToMolybdenum(){
	// in 	W/(m-K)
		this.nameId = R.string.molybdenum;
		this.a = (double) 10.78259;
		this.b = (double) -72.13065;
		this.c = (double) 228.57351;
		this.d = (double) -384.50447;
		this.e = (double) 381.43825;
		this.f = (double) -228.83783;
		this.g = (double) 81.26658;
		this.h = (double) -15.69097;
		this.i = (double) 1.26814;
		this.dataRange = "2-373";
		this.equationRange = "4-300";
		this.error = "3";
	}

	
	private void setToNickelSteelfe225ni(){
	// in 	W/(m-K)
		this.nameId = R.string.nickel_steel_fe225ni;
		this.a = (double) -3.5785;
		this.b = (double) 20.804;
		this.c = (double) -55.826;
		this.d = (double) 87.812;
		this.e = (double) -83.5016;
		this.f = (double) 49.08;
		this.g = (double) -17.4348;
		this.h = (double) 3.4277;
		this.i = (double) -0.28622;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "2";
	}

	private void setToNickelSteelfe325ni(){
	// in 	W/(m-K)
		this.nameId = R.string.nickel_steel_fe325ni;
		this.a = (double) 0.89481;
		this.b = (double) -8.1998;
		this.c = (double) 22.096;
		this.d = (double) -27.645;
		this.e = (double) 19.7524;
		this.f = (double) -8.1113;
		this.g = (double) 1.77866;
		this.h = (double) -0.16172;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "2";
	}
	
	
	private void setToNickelSteelfe50ni(){
	// in 	W/(m-K)
		this.nameId = R.string.nickel_steel_fe50ni;
		this.a = (double) -0.57245;
		this.b = (double) -0.33593;
		this.c = (double) 3.9379;
		this.d = (double) -5.4589;
		this.e = (double) 4.2337;
		this.f = (double) -1.8653;
		this.g = (double) 0.43223;
		this.h = (double) -0.041207;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "2";
	}

	private void setToNickelSteelfe90ni(){
	// in 	W/(m-K)
		this.nameId = R.string.nickel_steel_fe90ni;
		this.a = (double) -0.0712785;
		this.b = (double) -3.48735;
		this.c = (double) 10.6547;
		this.d = (double) -12.9153;
		this.e = (double) 8.89066;
		this.f = (double) -3.51482;
		this.g = (double) 0.743643;
		this.h = (double) -0.0657884;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "5-300";
		this.error = "2";
	}
	
	private void setToPlatinum(){
	// in 	W/(m-K)
		this.nameId = R.string.platinum;
		this.a = (double) -7.33450054;
		this.b = (double) 80.8550484;
		this.c = (double) -268.441084;
		this.d = (double) 481.629105;
		this.e = (double) -503.890454;
		this.f = (double) 314.812622;
		this.g = (double) -115.699394;
		this.h = (double) 23.0957119;
		this.i = (double) -1.93361717;
		this.dataRange = "3-298";
		this.equationRange = "4-295";
		this.error = "3";
	}
	
	private void setToPolyamideNylon(){
	// in 	W/(m-K)
		this.nameId = R.string.polyamide_nylon;
		this.a = (double) -2.6135;
		this.b = (double) 2.3239;
		this.c = (double) -4.7586;
		this.d = (double) 7.1602;
		this.e = (double) -4.9155;
		this.f = (double) 1.6324;
		this.g = (double) -0.2507;
		this.h = (double) 0.0131;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "1-300";
		this.error = "2";
	}
	
	private void setToPolyethyleneTerephthalateMylar(){
	// in 	W/(m-K)
		this.nameId = R.string.polyethylene_terephthalate_mylar;
		this.a = (double) -1.37737;
		this.b = (double) -3.40668;
		this.c = (double) 20.5842;
		this.d = (double) -53.1244;
		this.e = (double) 73.2476;
		this.f = (double) -57.6546;
		this.g = (double) 26.1192;
		this.h = (double) -6.34790;
		this.i = (double) 0.640331;
		this.dataRange = "1-83";
		this.equationRange = "2-80";
		this.error = "1";
	}
	
	private void setToPolyimideKapton(){
	// in 	W/(m-K)
		this.nameId = R.string.polyimide_kapton;
		this.a = (double) 5.73101;
		this.b = (double) -39.5199;
		this.c = (double) 79.9313;
		this.d = (double) -83.8572;
		this.e = (double) 50.9157;
		this.f = (double) -17.9835;
		this.g = (double) 3.42413;
		this.h = (double) -0.27133;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "2";
	}
	
	private void setToPolystyreneDensity31Point88(){
	// in 	W/(m-K)
		this.nameId = R.string.polystyrene_density_31_point_88;
		this.a = (double) -1557.5;
		this.b = (double) 3984.7;
		this.c = (double) -3940.245;
		this.d = (double) 1649.668;
		this.e = (double) 12.80097;
		this.f = (double) -294.2616;
		this.g = (double) 119.5898;
		this.h = (double) -20.6301;
		this.i = (double) 1.36067;
		this.dataRange = "90-300";
		this.equationRange = "90-300";
		this.error = "2";
	}
	
	private void setToPolystyreneDensity32Point04(){
	// in 	W/(m-K)
		this.nameId = R.string.polystyrene_density_32_point_04;
		this.a = (double) -1145.45;
		this.b = (double) 4086.02;
		this.c = (double) -6234.293;
		this.d = (double) 5260.106;
		this.e = (double) -2649.914;
		this.f = (double) 797.0372;
		this.g = (double) -132.5244;
		this.h = (double) 9.3968;
		this.i = (double) 0;
		this.dataRange = "33-300";
		this.equationRange = "25-300";
		this.error = "2";
	}
	
	private void setToPolystyreneDensity49Point98(){
	// in 	W/(m-K)
		this.nameId = R.string.polystyrene_density_49_point_98;
		this.a = (double) -1.5194;
		this.b = (double) -4.6449;
		this.c = (double) 11.643;
		this.d = (double) -15.969;
		this.e = (double) 12.722;
		this.f = (double) -5.821;
		this.g = (double) 1.4174;
		this.h = (double) -0.14128;
		this.i = (double) 0;
		this.dataRange = "7-300";
		this.equationRange = "4-300";
		this.error = "2";
	}
	
	private void setToPolystyreneDensity99Point96(){
	// in 	W/(m-K)
		this.nameId = R.string.polystyrene_density_99_point_96;
		this.a = (double) 7.39582;
		this.b = (double) -59.6737;
		this.c = (double) 160.58;
		this.d = (double) -240.33;
		this.e = (double) 218.817;
		this.f = (double) -124.155;
		this.g = (double) 42.9088;
		this.h = (double) -8.26683;
		this.i = (double) 0.68082;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "3";
	}

	private void setToPolyurethaneDensity31Point88(){
	// in 	W/(m-K)
		this.nameId = R.string.polyurethane_density_31_point_88;
		this.a = (double) -3218.679;
		this.b = (double) 9201.61;
		this.c = (double) -10956.66;
		this.d = (double) 6950.102;
		this.e = (double) -2476.94;
		this.f = (double) 470.284;
		this.g = (double) -37.1669;
		this.h = (double) 0;
		this.i = (double) 0;
		this.dataRange = "76-300";
		this.equationRange = "60-300";
		this.error = "2";
	}
	
	private void setToPolyurethaneDensity32Point04(){
		// in 	W/(m-K)
			this.nameId = R.string.polyurethane_density_32_point_04;
			this.a = (double) 3788.43;
			this.b = (double) -7642.66;
			this.c = (double) 4592.448;
			this.d = (double) 778.8423;
			this.e = (double) -2214.434;
			this.f = (double) 1090.293;
			this.g = (double) -235.6349;
			this.h = (double) 19.66088;
			this.i = (double) 0;
			this.dataRange = "100-300";
			this.equationRange = "85-300";
			this.error = "1";
	}
	
	private void setToPolyurethaneDensity49Point02(){
		// in 	W/(m-K)
			this.nameId = R.string.polyurethane_density_49_point_02;
			this.a = (double) -33.898;
			this.b = (double) 117.81;
			this.c = (double) -178.376;
			this.d = (double) 142.038;
			this.e = (double) -63.034;
			this.f = (double) 14.958;
			this.g = (double) -1.5468;
			this.h = (double) 0.020625;
			this.i = (double) 0;
			this.dataRange = "30-300";
			this.equationRange = "20-300";
			this.error = "1";
	}
	
	private void setToPolyurethaneDensity64Point08(){
		// in 	W/(m-K)
			this.nameId = R.string.polyurethane_density_64_point_08;
			this.a = (double) 789.79;
			this.b = (double) -2347.94;
			this.c = (double) 3024.61;
			this.d = (double) -2206.76;
			this.e = (double) 989.238;
			this.f = (double) -273.18;
			this.g = (double) 43.065;
			this.h = (double) -2.9863;
			this.i = (double) 0;
			this.dataRange = "88-300";
			this.equationRange = "55-300";
			this.error = "2";
	}
	
	private void setToPVCPolyvinylChlorideDensity20Point02(){
		// in 	W/(m-K)
			this.nameId = R.string.pvc_polyvinyl_chloride_density_20_point_02;
			this.a = (double) 11314.56;
			this.b = (double) -30824.32;
			this.c = (double) 34964.24;
			this.d = (double) -21141.43;
			this.e = (double) 7187.43;
			this.f = (double) -1302.708;
			this.g = (double) 98.35252;
			this.h = (double) 0;
			this.i = (double) 0;
			this.dataRange = "100-300";
			this.equationRange = "90-300";
			this.error = "1.5";
	}

	private void setToPVCPolyvinylChlorideDensity56Point06(){
		// in 	W/(m-K)
			this.nameId = R.string.pvc_polyvinyl_chloride_density_56_point_06;
			this.a = (double) -4123.51;
			this.b = (double) 9690.59;
			this.c = (double) -7920.09;
			this.d = (double) 1572.897;
			this.e = (double) 1459.993;
			this.f = (double) -1028.329;
			this.g = (double) 255.773;
			this.h = (double) -23.31925;
			this.i = (double) 0;
			this.dataRange = "125-300";
			this.equationRange = "100-300";
			this.error = "2";
	}

	//////////////////////////////////////////////////////
	private void setToStainlessSteel304(){
		// in 	W/(m-K)
		this.dataRange = "4-300";
		this.equationRange = "1-300";
		this.nameId = R.string.stainless_steel_304;
		this.a = (double) -1.4087;
		this.b = (double) 1.3982;
		this.c = (double) 0.2543;
		this.d = (double) -0.6260;
		this.e = (double) 0.2334;
		this.f = (double) 0.4256;
		this.g = (double) -0.4658;
		this.h = (double) 0.1650;
		this.i = (double) -0.0199;
		this.error = "2";
	}

	private void setToStainlessSteel304l(){
		// in 	W/(m-K)
		this.dataRange = "4-300";
		this.equationRange = "1-300";
		this.nameId = R.string.stainless_steel_304l;
		this.a = (double) -1.4087;
		this.b = (double) 1.3982;
		this.c = (double) 0.2543;
		this.d = (double) -0.6260;
		this.e = (double) 0.2334;
		this.f = (double) 0.4256;
		this.g = (double) -0.4658;
		this.h = (double) 0.1650;
		this.i = (double) -0.0199;
		this.error = "2";
	}

	private void setToStainlessSteel310(){
		// in 	W/(m-K)
		this.dataRange = "4-300";
		this.equationRange = "1-300";
		this.nameId = R.string.stainless_steel_310;
		this.a = (double) -0.81907;
		this.b = (double) -2.1967;
		this.c = (double) 9.1059;
		this.d = (double) -13.078;
		this.e = (double) 10.853;
		this.f = (double) -5.1269;
		this.g = (double) 1.2583;
		this.h = (double) -0.12395;
		this.i = (double) 0;
		this.error = "2";
	}

	private void setToStainlessSteel316(){
		// in 	W/(m-K)
		this.dataRange = "4-300";
		this.equationRange = "1-300";
		this.nameId = R.string.stainless_steel_316;
		this.a = (double) -1.4087;
		this.b = (double) 1.3982;
		this.c = (double) 0.2543;
		this.d = (double) -0.6260;
		this.e = (double) 0.2334;
		this.f = (double) 0.4256;
		this.g = (double) -0.4658;
		this.h = (double) 0.1650;
		this.i = (double) -0.0199;
		this.error = "2";
	}

	private void setToTeflon(){
		// in 	W/(m-K)
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.nameId = R.string.teflon;
		this.a = (double) 2.7380;
		this.b = (double) -30.677;
		this.c = (double) 89.430;
		this.d = (double) -136.99;
		this.e = (double) 124.69;
		this.f = (double) -69.556;
		this.g = (double) 23.320;
		this.h = (double) -4.3135;
		this.i = (double) 0.33829;
		this.error = "";
	}

	private void setToTi6al4v(){
		// in 	W/(m-K)
		this.dataRange = "23-300";
		this.equationRange = "20-300";
		this.nameId = R.string.ti_6al_4v;
		this.a = (double) -5107.8774;
		this.b = (double) 19240.422;
		this.c = (double) -30789.064;
		this.d = (double) 27134.756;
		this.e = (double) -14226.379;
		this.f = (double) 4438.2154;
		this.g = (double) -763.07767;
		this.h = (double) 55.796592;
		this.i = (double) 0;
		this.error = "2";
	}

	private void setToTitanium15333(){
		// in 	W/(m-K)
		this.dataRange = "1.4-300";
		this.equationRange = "1.4-300";
		this.nameId = R.string.titanium_15_3_3_3;
		this.a = (double) -2.398794842;
		this.b = (double) 8.970743802;
		this.c = (double) -29.19286973;
		this.d = (double) 54.87139779;
		this.e = (double) -59.67137228;
		this.f = (double) 38.89321714;
		this.g = (double) -14.94175848;
		this.h = (double) 3.111616089;
		this.i = (double) -0.270452768;
		this.error = "3";
	}

	
}



