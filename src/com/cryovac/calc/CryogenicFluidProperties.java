package com.cryovac.calc;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CryogenicFluidProperties extends Activity{
	final Context context = this;
	Spinner fluidSpinner;
	ArrayAdapter<CharSequence> fluidAdapter;
	TextView cryogenicTableTitle, molecularWeightText, molecularWeightValue, 
		boilingTemperatureAt100kPaText, boilingTemperatureat100kPaValue,	
		criticalPointPropertiesText, 
		criticalPointTemperatureValue, criticalPointTemperatureText,
		criticalPointPressureValue, criticalPointPressureText,
		triplePointPropertiesText, 
		triplePointPressureValue, triplePointPressureText,
		triplePointTemperatureValue, triplePointTemperatureText,
		densityPropertiesText,
		densityGasAt273KAt100kPaValue, densityGasAt273KAt100kPaText,
		densityCriticalPointValue, densityCriticalPointText,
		densityVaporAtBoilingPointValue, densityVaporAtBoilingPointText,
		densityLiquidAtBoilingPointValue, densityLiquidAtBoilingPointText,
		densitySolidAtMeltingPointValue, densitySolidAtMeltingPointText,
		densityVolumetricGasToLiquidRatioAt273KAt100kPaFrom1CubicMeterLiquidValue, 
		densityVolumetricGasToLiquidRatioAt273KAt100kPaFrom1CubicMeterLiquidText,
		latentHeatPropertiesText,
		latentHeatEvaporationAtBoilingPointValue, latentHeatEvaporationAtBoilingPointText,
		latentHeatLiquefyAtMeltingPointValue, latentHeatLiquefyAtMeltingPointText,
		latentHeatEvaporationEntropyValue, latentHeatEvaporationEntropyText;
	Spanned spanString;
	
	Button selectedFluidButtonView;
	CryogenicFluidUnits unit = new CryogenicFluidUnits();
	int layoutValueId[], i;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setTitle("CryoCalc: Cryogenic Properties");
	        setContentView(R.layout.cryogenic_fluid_properties);
		    unit.setContext(this.getBaseContext());
	        //fluid = getIntent().getStringExtra("SelectedFluid");
		    selectedFluidButtonView = (Button)findViewById(R.id.selected_fluid);
		    selectedFluidButtonView.setOnClickListener(onFluidButtonListener);
		    fluidAdapter = ArrayAdapter.createFromResource
	        		(this, R.array.fluid_properties_array, 0);
		    
		    // set table title
		    cryogenicTableTitle = (TextView)findViewById(R.id.cryogenic_table_title);
		    cryogenicTableTitle.setText("Cryogenic Properties");
		    
		    molecularWeightValue = (TextView)findViewById(R.id.molecular_weight_value);
		    molecularWeightText = (TextView)findViewById(R.id.molecular_weight_text);  
		    molecularWeightText.setText("Molecular weight (kg/mol)");
		    
		    boilingTemperatureat100kPaValue = (TextView)findViewById(R.id.boiling_temperature_at_100_kPa_value);
		    boilingTemperatureAt100kPaText = (TextView)findViewById(R.id.boiling_temperature_at_100_kPa_text);  
		    boilingTemperatureAt100kPaText.setText("Boiling Temperature (K)");
		    
		    criticalPointPropertiesText = (TextView)findViewById(R.id.critical_point_properties_text);
		    criticalPointPropertiesText.setText("Critical Point");
		    
		    criticalPointTemperatureValue = (TextView)findViewById(R.id.critical_point_temperature_value);
		    criticalPointTemperatureText = (TextView)findViewById(R.id.critical_point_temperature_text);
		    criticalPointTemperatureText.setText("Temperature (K)"); 
		    
		    criticalPointPressureValue = (TextView)findViewById(R.id.critical_point_pressure_value);
		    criticalPointPressureText = (TextView)findViewById(R.id.critical_point_pressure_text);
		    criticalPointPressureText.setText("Presurre (MPa) "); 
		    
		    
		    triplePointPropertiesText = (TextView)findViewById(R.id.triple_point_properties_text);
		    triplePointPropertiesText.setText("Triple Point");
		    
		    
		    triplePointTemperatureValue = (TextView)findViewById(R.id.triple_point_temperature_value);
		    triplePointTemperatureText = (TextView)findViewById(R.id.triple_point_temperature_text);
		    triplePointTemperatureText.setText("Temperature (K)"); 
		    
		    triplePointPressureValue = (TextView)findViewById(R.id.triple_point_pressure_value);
		    triplePointPressureText = (TextView)findViewById(R.id.triple_point_pressure_text);
		    triplePointPressureText.setText("Presurre (kPa) "); 
		  
		    densityPropertiesText = (TextView)findViewById(R.id.density_properties_text);
		    densityPropertiesText.setText("Density");
		    
		    densityGasAt273KAt100kPaValue = (TextView)findViewById(R.id.density_gas_at_273K_at_100kPa_value);
		    densityGasAt273KAt100kPaText = (TextView)findViewById(R.id.density_gas_at_273K_at_100kPa_text);
		    densityGasAt273KAt100kPaText.setText("Gas, 273 K, 100 kPa ");
		    
		    densityCriticalPointValue = (TextView)findViewById(R.id.density_critical_point_value);
		    densityCriticalPointText = (TextView)findViewById(R.id.density_critical_point_text);
		    spanString = (Spanned) TextUtils.concat("Critical Point (kg/", context.getText(R.string.meter_cubic), ")");
		    densityCriticalPointText.setText(spanString);
		    
		    densityVaporAtBoilingPointValue = (TextView)findViewById(R.id.density_vapor_at_boiling_point_value);
		    densityVaporAtBoilingPointText = (TextView)findViewById(R.id.density_vapor_at_boiling_point_text);
		    spanString = (Spanned) TextUtils.concat("Vapor at Boiling Point (kg/", 
		    		context.getText(R.string.meter_cubic), ")");
		    densityVaporAtBoilingPointText.setText(spanString);
		    
		    densityLiquidAtBoilingPointValue = (TextView)findViewById(R.id.density_liquid_at_boiling_point_value);
		    densityLiquidAtBoilingPointText = (TextView)findViewById(R.id.density_liquid_at_boiling_point_text);
		    spanString = (Spanned) TextUtils.concat("Liquid at Boiling Point (kg/",
		    		context.getText(R.string.meter_cubic), ")");
		    densityLiquidAtBoilingPointText.setText(spanString);
		    
		    densitySolidAtMeltingPointValue = (TextView)findViewById(R.id.density_solid_at_melting_point_value);
		    densitySolidAtMeltingPointText = (TextView)findViewById(R.id.density_solid_at_melting_point_text);
		    spanString = (Spanned) TextUtils.concat("Solid at Melting Point (kg/",
		    		context.getText(R.string.meter_cubic), ")");
		    densitySolidAtMeltingPointText.setText(spanString);
		    
		    
		    densityVolumetricGasToLiquidRatioAt273KAt100kPaFrom1CubicMeterLiquidValue = 
		    		(TextView)findViewById(R.id.density_volumetric_gas_to_liquid_ratio_at_273K_at100kPa_from_1cubicmeter_liquid_value);
		    densityVolumetricGasToLiquidRatioAt273KAt100kPaFrom1CubicMeterLiquidText = 
		    		(TextView)findViewById(R.id.density_volumetric_gas_to_liquid_ratio_at_273K_at100kPa_from_1cubicmeter_liquid_text);
		    spanString = (Spanned) TextUtils.concat(context.getText(R.string.volumetric_gas_to_liquid_ratio_at_273K_at100kPa_from_1cubicmeter_liquid_text));
		    densityVolumetricGasToLiquidRatioAt273KAt100kPaFrom1CubicMeterLiquidText
		    	.setText(spanString);
		    
		    latentHeatPropertiesText = (TextView)findViewById(R.id.latent_heat_properties_text);
		    latentHeatPropertiesText.setText("Latent Heat");
		    
		    latentHeatEvaporationAtBoilingPointValue = (TextView)findViewById(R.id.latent_heat_evaporation_at_boiling_point_value);
		    latentHeatEvaporationAtBoilingPointText = (TextView)findViewById(R.id.latent_heat_evaporation_at_boiling_point_text);
		    latentHeatEvaporationAtBoilingPointText.setText("Evaporation at boiling point (kJ/kg)");
		    
		    latentHeatLiquefyAtMeltingPointValue = (TextView)findViewById(R.id.latent_heat_liquefy_at_melting_point_value);
		    latentHeatLiquefyAtMeltingPointText = (TextView)findViewById(R.id.latent_heat_liquefy_at_melting_point_text);
		    latentHeatLiquefyAtMeltingPointText.setText("Liquefy at melting point (kJ/kg)");
		    
		    latentHeatEvaporationEntropyValue = (TextView)findViewById(R.id.latent_heat_evaporation_entropy_value);
		    latentHeatEvaporationEntropyText = (TextView)findViewById(R.id.latent_heat_evaporation_entropy_text);
		    latentHeatEvaporationEntropyText.setText("Evaporation Entropy (kJ/kmol K)");
		    
		    //setValues(fluid);
		    
	    }
	 
	 	void setValues(String fluid){
	 		unit.setCryogenicFluid(fluid);
		    selectedFluidButtonView.setText(getText(unit.getNameId()));
		    molecularWeightValue.setText(Double.toString(unit.molecularWeight));
		    boilingTemperatureat100kPaValue.setText(Double.toString(unit.boilingTemperatureAt100kPa));
		    
		    criticalPointTemperatureValue.setText(unit.criticalPointParameter.temperature);
		    criticalPointPressureValue.setText(unit.criticalPointParameter.pressure);
		    
		    triplePointTemperatureValue.setText(unit.triplePointParameter.temperature);
		    triplePointPressureValue.setText(unit.triplePointParameter.pressure);
		    
		    densityGasAt273KAt100kPaValue.setText(unit.densityParameter.gasAt273KAt100kPa);
		    densityCriticalPointValue.setText(unit.densityParameter.criticalPoint);
		    densityVaporAtBoilingPointValue.setText(unit.densityParameter.vaporAtBoilingPoint);
		    densityLiquidAtBoilingPointValue.setText(unit.densityParameter.liquidAtBoilingPoint);
		    densitySolidAtMeltingPointValue.setText(unit.densityParameter.solidAtMeltingPoint);
		    densityVolumetricGasToLiquidRatioAt273KAt100kPaFrom1CubicMeterLiquidValue
		    	.setText(unit.densityParameter.volumetricGasToLiquidRatioAt273KAt100kPaFrom1CubicMeterLiquid);
		    
		    latentHeatEvaporationAtBoilingPointValue.setText(unit.latentHeatParameter.evaporationAtBoilingPoint);
		    latentHeatLiquefyAtMeltingPointValue.setText(unit.latentHeatParameter.liquefyAtMeltingPoint);
		    latentHeatEvaporationEntropyValue.setText(unit.latentHeatParameter.evaporationEntropy);
		    
		    if (unit.nameId == R.string.HE4){
		    	triplePointPropertiesText.setText("Lambda Point");
		    }else{
		    	triplePointPropertiesText.setText("Triple Point");
		    }
		
	 		
	 	}
	 	
	 	OnClickListener onFluidButtonListener = new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				fluidSelectDialog();
			}
	 		
	 	};
	 	
	    public void fluidSelectDialog(){
	    	new AlertDialog.Builder(this)
	    		.setTitle(R.string.select_fluid)
	    		.setItems(R.array.fluid_properties_array,
	    				new DialogInterface.OnClickListener(){
	    					public void onClick(DialogInterface dialogInterface, int i){
	    						setValues(fluidAdapter.getItem(i).toString());
	    					}
	    				}
	    		).show();
	    	
	    }
	

}
