package com.cryovac.calc;
import com.cryovac.parametergroup.*;

import android.content.Context;
import android.content.res.Resources;

public class CryogenicFluidUnits {
	int nameId;
	Context context;
	double molecularWeight, boilingTemperatureAt100kPa;
	CriticalPointParameter criticalPointParameter = new CriticalPointParameter();
	TriplePointParameter triplePointParameter = new TriplePointParameter();
	LatentHeatParameter latentHeatParameter = new LatentHeatParameter();
	DensityParameter densityParameter = new DensityParameter();
	String meltingPoint, vaporPressure;
	Resources res; 
	
	void setContext(Context contextValue){
		this.context = contextValue;
		res = context.getResources();
	}
	
	public int getNameId(){
		return this.nameId;
	}
	
	public void setCryogenicFluid(String cryogenicFluid){
		if(cryogenicFluid.equals(res.getString(R.string.HE3))){
			setToHE3();
			return;
		}
		
		if(cryogenicFluid.equals(res.getString(R.string.HE4))){
			setToHE4();
			return;
		}
		if(cryogenicFluid.equals(res.getString(R.string.p_H2))){
			setTopH2();
			return;
		}
		if(cryogenicFluid.equals(res.getString(R.string.n_H2))){
			setTonH2();
			return;
		}
		if(cryogenicFluid.equals(res.getString(R.string.Ne))){
			setToNe();
			return;
		}
		if(cryogenicFluid.equals(res.getString(R.string.N2))){
			setToN2();
			return;
		}

		if(cryogenicFluid.equals(res.getString(R.string.O2))){
			setToO2();
			return;
		}

		
		
	}
	
	public void setToHE3(){
		this.nameId = R.string.HE3;
		this.molecularWeight = 3.0160;
		this.boilingTemperatureAt100kPa = 3.190;
		this.criticalPointParameter.set("3.33", "0.117");
		this.triplePointParameter.set("---", "---");
		this.densityParameter.set("0.13448", "41.8", "27.3", "58.9", "---", "438");
		this.latentHeatParameter.set("15.87", "---", "14.9");
	}
	
	
	public void setToHE4(){
		this.nameId = R.string.HE4;
		this.molecularWeight = 4.003;
		this.boilingTemperatureAt100kPa = 4.215;
		this.criticalPointParameter.set("5.22", "0.25");
		//HE4 doesn't have triple point
		//the following are the parameter for lambda point
		this.triplePointParameter.set("2.173", "5.025");
		this.densityParameter.set("0.17847", "69.5", "17.2", "124.8", "---", "699");
		this.latentHeatParameter.set("20.91", "5.22 (10.3 MPa)", "19.9");
	}
	
	public void setTopH2(){
		this.nameId = R.string.p_H2;
		this.molecularWeight = 2.016;
		this.boilingTemperatureAt100kPa = 20.28;
		this.criticalPointParameter.set("32.98", "1.293");
		this.triplePointParameter.set("13.81", "7.04");
		this.densityParameter.set("0.08985", "31.4", "13.4", "70.8", "87", "788");
		this.latentHeatParameter.set("446.5", "58.04", "44.4");
	}
	
	public void setTonH2(){
		this.nameId = R.string.n_H2;
		this.molecularWeight = 2.0159;
		this.boilingTemperatureAt100kPa = 20.397;
		this.criticalPointParameter.set("33.24", "1.298");
		this.triplePointParameter.set("13.956", "7.2");
		this.densityParameter.set("0.08985", "30.1", "13.3", "70.8", "---", "788");
		this.latentHeatParameter.set("448.03", "58.04", "44.4");
	}
	
	public void setToNe(){
		this.nameId = R.string.Ne;
		this.molecularWeight = 20.183;
		this.boilingTemperatureAt100kPa = 27.102;
		this.criticalPointParameter.set("44.39", "2.722");
		this.triplePointParameter.set("24.555", "43.3");
		this.densityParameter.set("0.0899", "483.5", "9.46", "1204", "---", "1338");
		this.latentHeatParameter.set("87.20", "16.60", "64.6");
	}
	
	public void setToN2(){
		this.nameId = R.string.N2;
		this.molecularWeight = 28.013;
		this.boilingTemperatureAt100kPa = 77.348;
		this.criticalPointParameter.set("125.98", "3.394");
		this.triplePointParameter.set("63.148", "12.612");
		this.densityParameter.set("1.2505", "311", "4.59", "804.2", "946", "644");
		this.latentHeatParameter.set("199.1", "25.73", "72.0");
	}
	
	public void setToO2(){
		this.nameId = R.string.O2;
		this.molecularWeight = 31.999;
		this.boilingTemperatureAt100kPa = 90.188;
		this.criticalPointParameter.set("154.78", "5.082");
		this.triplePointParameter.set("54.361", "0.152");
		this.densityParameter.set("1.4289", "380", "4.44", "1140", "---", "798");
		this.latentHeatParameter.set("213.1", "13.88", "74.5");
	}
	
	
}
