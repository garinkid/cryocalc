package com.cryovac.calc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.text.TextUtils;

import org.apache.commons.math3.analysis.integration.*;
import org.apache.commons.math3.exception.TooManyEvaluationsException;


public class HeatFlowCalc extends Activity{
	final Context context = this;
	ArrayAdapter<CharSequence> materialAdapter, shapeAdapter;
	EditText temperatureLowerEditText, temperatureUpperEditText, 
		areaAttribute1EditText, areaAttribute2EditText,
		lengthEditText;
	Button calculateButton, selectedMaterialButton, selectedShapeButton;
	LinearLayout areaAttribute2Layout, areaAttribute1Layout;
	TrapezoidIntegrator integrator;
	ThermalConductivityUnits materialUnits = new ThermalConductivityUnits();
	TextView result, areaAttribute1Text, areaAttribute2Text, dialogBoxText;
	DialogBox dialogBox = new DialogBox(context);
	String material;
	Spanned spanString;
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.heat_flow_calc);
	     setTitle("CryoCalc: Heat Flow");
	     materialUnits.setContext(this.getBaseContext());
	     
	     materialAdapter = ArrayAdapter.createFromResource
	        		(this, R.array.thermal_conductivity_material_array, 0);

	     selectedMaterialButton = (Button)findViewById(R.id.selected_material);
	     selectedMaterialButton.setText("Select Material");
	     selectedMaterialButton.setOnClickListener(buttonListener);
	     
	     
		 temperatureLowerEditText = (EditText) findViewById(R.id.input_temperature_lower);
		 temperatureUpperEditText = (EditText) findViewById(R.id.input_temperature_upper);
		 lengthEditText = (EditText) findViewById(R.id.length_edit_text);
		 areaAttribute1Text = (TextView) findViewById(R.id.area_attribute_1_text);
		 areaAttribute2Text = (TextView) findViewById(R.id.area_attribute_2_text);
		 areaAttribute1EditText = (EditText) findViewById(R.id.area_attribute_1_edit_text);
		 areaAttribute2EditText = (EditText) findViewById(R.id.area_attribute_2_edit_text);
		 areaAttribute1Layout = (LinearLayout) findViewById(R.id.area_attribute_1_layout);
		 areaAttribute2Layout = (LinearLayout) findViewById(R.id.area_attribute_2_layout);
		 
		 areaAttribute1Layout.setVisibility(View.GONE);
		 areaAttribute2Layout.setVisibility(View.GONE);
		 
	     shapeAdapter = ArrayAdapter.createFromResource
	        		(this, R.array.cross_sectional_shape_array, 0);
	   
	     
	     selectedShapeButton = (Button)findViewById(R.id.cross_sectional_shape_button);
	     selectedShapeButton.setOnClickListener(buttonListener);
		 selectedShapeButton.setText("Select shape");
		 
		 calculateButton = (Button) findViewById(R.id.calculate_button);
		 calculateButton.setOnClickListener(onCalculateButtonListener);
		 
	 }
	 
	 void setUnits(String material){
		 materialUnits.setMaterial(material);
		 selectedMaterialButton.setText(getText(materialUnits.getNameId()));
	 }
	 
 
	 void setShape(int selected){
			switch(selected){
				case 0:
					 areaAttribute1Layout.setVisibility(View.VISIBLE);
					 areaAttribute1Text.setText(R.string.radius);
					 areaAttribute2Layout.setVisibility(View.GONE);
					 break;
				case 1:
					 areaAttribute1Layout.setVisibility(View.VISIBLE);
					 areaAttribute1Text.setText(R.string.width);
					 areaAttribute2Text.setText(R.string.height);
					 areaAttribute2Layout.setVisibility(View.VISIBLE);
					 break;
				case 2:
					 areaAttribute1Layout.setVisibility(View.VISIBLE);
					 areaAttribute1Text.setText(R.string.width);
					 areaAttribute2Layout.setVisibility(View.GONE);
					 break;	 
				case 3:
					 areaAttribute1Layout.setVisibility(View.VISIBLE);
					 areaAttribute1Text.setText(R.string.area_with_star);
					 areaAttribute2Layout.setVisibility(View.GONE);
					 break;	 
				case 4:
					 areaAttribute1Layout.setVisibility(View.VISIBLE);
					 areaAttribute1Text.setText(R.string.inner_radius);
					 areaAttribute2Layout.setVisibility(View.VISIBLE);
					 areaAttribute2Text.setText(R.string.outer_radius);
					 break;	 
			}
		}
			 

	 
	 OnClickListener onCalculateButtonListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			double area = 0.0;
			double length;
			double thermal_conductivity_integral;
			double heat_flow = 0.0;
		 	dialogBoxText = new TextView(context);
		 	dialogBoxText.setPadding(5, 5, 5, 5);
		 	
			if(selectedMaterialButton.getText().equals("Select Material")){
				dialogBox.showDialog("Please select a material");
				return;
			}
			
			if(temperatureLowerEditText.getText().length() == 0){
				spanString = (Spanned) TextUtils.concat("Please input '", 
						SpannableString.valueOf(context.getText(R.string.temperature_lower)), 
						"' value");
				dialogBox.showDialog(spanString);
				return;
			}
			if(temperatureUpperEditText.getText().length() == 0){
				spanString = (Spanned) TextUtils.concat("Please input '", 
						SpannableString.valueOf(context.getText(R.string.temperature_upper)), 
						"' value");
				dialogBox.showDialog(spanString);
				return;
			}
			
			
			if(lengthEditText.getText().length() == 0){
				dialogBox.showDialog("Please input 'Length' value");
				return;
			}

			if(selectedShapeButton.getText().equals("Select shape")){
				dialogBox.showDialog("Please select the shape for cross sectional area");
				return;
			}
			
			if(areaAttribute1Layout.getVisibility() == View.VISIBLE && 
					areaAttribute1EditText.getText().length()==0){
				dialogBox.showDialog("Please input '" + areaAttribute1Text.getText().toString() + "' value");
				return;
			}		
			
			if(areaAttribute2Layout.getVisibility() == View.VISIBLE && 
					areaAttribute2EditText.getText().length()==0){
				dialogBox.showDialog("Please input '" + areaAttribute2Text.getText().toString() + "' value");
				return;
			}
			
			
			double lowerLimit =  Double.valueOf(temperatureLowerEditText.getText().toString());
			double upperLimit =  Double.valueOf(temperatureUpperEditText.getText().toString());
			
			if(upperLimit <= lowerLimit){
				spanString = (Spanned) TextUtils.concat(
					SpannableString.valueOf(context.getText(R.string.temperature_upper)),
					" should be higher than ",
					SpannableString.valueOf(context.getText(R.string.temperature_lower)));
				dialogBox.showDialog(spanString);
				return;
			}
			
			// calculate area
			String buttonText;
			buttonText = selectedShapeButton.getText().toString();
			if (buttonText.contentEquals("Circular")){
					area = calculateCircleArea(
							Double.valueOf(areaAttribute1EditText.getText().toString()));				
			}else if(buttonText.contentEquals("Rectangular")){
					area = calculateRectangleArea(
							Double.valueOf(areaAttribute1EditText.getText().toString()),
							Double.valueOf(areaAttribute2EditText.getText().toString()));			
			}else if(buttonText.contentEquals("Square")){
					area = calculateSquareArea(
							Double.valueOf(areaAttribute1EditText.getText().toString()));
			}else if(buttonText.contentEquals("Area (m2)")){
					area = Double.valueOf(areaAttribute1EditText.getText().toString());
			}else {// rod
				double inner_radius = Double.valueOf(areaAttribute1EditText.getText().toString());
				double outer_radius = Double.valueOf(areaAttribute2EditText.getText().toString());
				if (shapeAdapter.getCount() == 5){ //if "rod is selected"
						if(outer_radius <= inner_radius){
							dialogBox.showDialog("Outer radius should be bigger than inner radius");
						return;
						}
				}
					area = calculateCircleArea(outer_radius) -
							calculateCircleArea(inner_radius);
			}
			
			// calculate thermal length
			length=Double.valueOf(lengthEditText.getText().toString());
			
			
			double upperThermalConductivity = materialUnits.value(upperLimit);
			double lowerThermalConductivity = materialUnits.value(lowerLimit);
			double lowestValue = Math.min(upperThermalConductivity, lowerThermalConductivity);
			
			integrator = new TrapezoidIntegrator(0.1, lowestValue/100, 5, 50);
			// calculate thermal conductivity integral
			int maxEval = 128;
			try{
				thermal_conductivity_integral = integrator.integrate(maxEval, materialUnits, lowerLimit, upperLimit);
			}catch (TooManyEvaluationsException e){
				dialogBox.showDialog(e.getMessage() + "\n\n" + "Temperature values are out of equation range");
				return;
			}

			// calculate heat flow
			
			
			
			heat_flow = ( area / length ) * thermal_conductivity_integral;
			
			//result.setText(Double.toString(heat_flow));
		

			spanString = (Spanned) TextUtils.concat(
					 ("Heat Flow Calculation Result \n\n"), 
					 ("Heat flow =" + calculateRoundValue(heat_flow) + " watts" + "\n\n" ),
					 ("Material= \n\t "), 
					SpannableString.valueOf(context.getText(materialUnits.nameId)), 
					 ("\n\n"), SpannableString.valueOf(context.getText(R.string.temperature_lower)),
					 ("=" + temperatureLowerEditText.getText().toString() + " K" + "\n"),
					 SpannableString.valueOf(context.getText(R.string.temperature_upper)),
					 ("=" + temperatureUpperEditText.getText().toString() + " K" + "\n\n" + 
					 "Thermal conductivity integral=" +  "\n\t" +
					 calculateRoundValue(thermal_conductivity_integral) +
					 " watts/m" + "\n\n" +
					 "Length=" + calculateRoundValue(length) + " m" + "\n" + 
					"Area=" + calculateRoundValue(area) + " "), 
					SpannableString.valueOf(context.getText(R.string.m_power_two)));
			dialogBox.showDialog(spanString);
			
		}
		 
	 };
	 
	 
	 
	 double calculateCircleArea(double radius){
		 double area = Math.PI * Math.pow(radius, 2);
		 return area; 
	 }
	 
	 double calculateRectangleArea(double length, double width){
		 double area = length * width;
		 return area; 
	 }
	 
	 double calculateSquareArea(double width){
		 double area = Math.pow(width, 2);
		 return area; 
	 }
	 
	 double calculateRoundValue(double actualValue){
		int roundDegree;
		double roundValue = 0;
		int i = 0;
		if(actualValue > 1){
			while(true){
				roundDegree = 5-i;
				if(Math.pow(10, i)>actualValue){break;} 
				else{i++;}
				}
			}
		else{
			while(true){
				roundDegree = 4-i;
				if(Math.pow(10, i)<actualValue){break;} 
				else{i--;}
			}
		}
		
		roundValue = (Math.round(actualValue * Math.pow(10, roundDegree))) /  Math.pow(10, roundDegree);
		return roundValue;

	 }
	 
	 OnClickListener buttonListener = new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				switch(arg0.getId()){
				case R.id.selected_material:
					materialSelectDialog();
					break;
				case R.id.cross_sectional_shape_button:
					shapeSelectDialog();
					break;
				}
			}
	 		
	 	};
	 	
	   void materialSelectDialog(){  
		    	new AlertDialog.Builder(this)
		    		.setTitle(R.string.select_material)
		    		.setItems(R.array.thermal_conductivity_material_array,
		    				new DialogInterface.OnClickListener(){
		    					public void onClick(DialogInterface dialogInterface, int i){
		    						setUnits(materialAdapter.getItem(i).toString());	    							
		    					}
		    				}
		    		).show();
		 }
	   
	   void shapeSelectDialog(){  
	    	new AlertDialog.Builder(this)
	    		.setTitle(R.string.select_shape)
	    		.setItems(R.array.cross_sectional_shape_array,
	    				new DialogInterface.OnClickListener(){
	    					public void onClick(DialogInterface dialogInterface, int i){
	    						setShape(i);
	    						selectedShapeButton.setText(shapeAdapter.getItem(i));
	    					}
	    				}
	    		).show();
	   }
	 
	 
}
