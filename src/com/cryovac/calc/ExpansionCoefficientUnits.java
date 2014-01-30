package com.cryovac.calc;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

public class ExpansionCoefficientUnits {
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
	
	public double calculate(double temperature) throws Exception{
 		double expansionCoefficient = (double) 0.0;
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
		expansionCoefficient  = (double) Math.pow(10,sumSection);
		if(Double.isInfinite(expansionCoefficient) || Double.isNaN(expansionCoefficient)){
			throw new Exception("Infinite or NaN result");
		}else{
			if (expansionCoefficient < Double.MIN_VALUE){expansionCoefficient = Double.MIN_VALUE;}
			return expansionCoefficient;		
		}
 	}

	public void setMaterial(String material){
	 	if (material.equals(res.getString(R.string.sapphire))){
	 		setToSapphire();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.copper))){
	 		setToCopper();
	 		return;
	 	}
	}
	
	public void setToSapphire(){
		this.nameId = R.string.sapphire;
		this.a = (double) 10.97236;
		this.b = (double) -97.23540;
		this.c = (double) 240.2436;
		this.d = (double) -294.9933;
		this.e = (double) 195.9244;
		this.f = (double) -66.89247;
		this.g = (double) 9.19921;
		this.h = (double) 0;
		this.i = (double) 0;
		this.dataRange = "4-83";
		this.equationRange = "5-80";
		this.error = "3";
	}
	
	public void setToCopper(){
		this.nameId = R.string.copper;
		this.a = (double) -17.9081289;
		this.b = (double) 67.131914;
		this.c = (double) -118.809316;
		this.d = (double) 109.9845997;
		this.e = (double) -53.8696089;
		this.f = (double) 13.30247491;
		this.g = (double) -1.30843441;
		this.h = (double) 0;
		this.i = (double) 0;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "3";
	}
	
}
