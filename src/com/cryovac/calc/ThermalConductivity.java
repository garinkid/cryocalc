package com.cryovac.calc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ThermalConductivity extends Activity{
	
	final Context context = this;
	String attributesText[] = {"Units", "a", "b", "c", "d", "e", 
			"f", "g", "h", "i", "Data range", "Equation range", 
			"curve fit % error relative to data"};

	int layoutTextId[], i;
	TextView attributes;
	String material;
	DialogBox dialogBox = new DialogBox(context);
	Spanned spanString;
	ArrayAdapter<CharSequence> materialAdapter;
	
	LinearLayout equationLayout, tableLayout;
	TextView aTextView, bTextView,
		cTextView, dTextView, eTextView, fTextView, gTextView, hTextView, iTextView,
		attribute1ValueTextView, attribute2ValueTextView, attribute3ValueTextView,
		thermalConductivityEquationTextView, thermalConductivityTableTitle;
	EditText inputTemperatureEditText;
	ThermalConductivityUnits thermalConductivityUnits = new ThermalConductivityUnits();
	Button calculateButton, selectedMaterialButton;
	Float temperature;
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.thermal_conductivity);
	     setTitle("CryoCalc: Thermal Conductivity");
	     
	     thermalConductivityUnits.setContext(this.getBaseContext());

	     selectedMaterialButton = (Button)findViewById(R.id.selected_material);
	     selectedMaterialButton.setOnClickListener(materialButtonListener);
	     selectedMaterialButton.setText("Select material");
	     equationLayout = (LinearLayout)findViewById(R.id.equation_layout);
	     tableLayout = (LinearLayout)findViewById(R.id.table_layout);
	     
	     materialAdapter = ArrayAdapter.createFromResource
	        		(this, R.array.thermal_conductivity_material_array, 0);
	     
	     
	     thermalConductivityEquationTextView = (TextView)findViewById(R.id.thermal_conductivity_equation_text_view);
	
	     inputTemperatureEditText = (EditText)findViewById(R.id.input_temperature);
	   
	     thermalConductivityTableTitle =  (TextView)findViewById(R.id.thermal_conductivity_table_title);
	     thermalConductivityTableTitle.setText("Thermal Conductivity Units");
	     
	     layoutTextId = new int[13];
	     layoutTextId[0] = R.id.attributes_1_text;
	     layoutTextId[1] = R.id.attributes_2_text;
	     layoutTextId[2] = R.id.attributes_3_text;
	     layoutTextId[3] = R.id.attributes_4_text;
	     layoutTextId[4] = R.id.attributes_5_text;
	     layoutTextId[5] = R.id.attributes_6_text;
	     layoutTextId[6] = R.id.attributes_7_text;
	     layoutTextId[7] = R.id.attributes_8_text;
	     layoutTextId[8] = R.id.attributes_9_text;
	     layoutTextId[9] = R.id.attributes_10_text;
	     layoutTextId[10] = R.id.attributes_11_text;
	     layoutTextId[11] = R.id.attributes_12_text;
	     layoutTextId[12] = R.id.attributes_13_text;
	     
		 for(i=0; i<13;i++){
		    	attributes = (TextView)findViewById(layoutTextId[i]);
		    	attributes.setText(attributesText[i]);
		  }
		 
		 attributes = (TextView)findViewById( R.id.attributes_1_value);
		 attributes.setText("W/(m-K)");
		 
		 aTextView =(TextView)findViewById(R.id.a_value);
	     bTextView =(TextView)findViewById(R.id.b_value);
	     cTextView =(TextView)findViewById(R.id.c_value);
	     dTextView =(TextView)findViewById(R.id.d_value);
	     eTextView =(TextView)findViewById(R.id.e_value);
	     fTextView =(TextView)findViewById(R.id.f_value);
	     gTextView =(TextView)findViewById(R.id.g_value);
	     hTextView =(TextView)findViewById(R.id.h_value);
	     iTextView =(TextView)findViewById(R.id.i_value);
	     attribute1ValueTextView =(TextView)findViewById(R.id.attribute_1_value);
	     attribute2ValueTextView =(TextView)findViewById(R.id.attribute_2_value);
	     attribute3ValueTextView = (TextView)findViewById(R.id.attribute_3_value);          
	     
	     calculateButton = (Button)findViewById(R.id.calculate_button);
	     calculateButton.setOnClickListener(onClickListener); 
		 
	 }
	 
	 
	 void setValues(String material){
		 thermalConductivityUnits.setMaterial(material);
		 selectedMaterialButton.setText(getText(thermalConductivityUnits.getNameId()));    
	     switch(thermalConductivityUnits.nameId){
	     case R.string.copper_RRR_50:
	     case R.string.copper_RRR_100:
	     case R.string.copper_RRR_150:
	     case R.string.copper_RRR_300:	     
	     case R.string.copper_RRR_500:
	    	 thermalConductivityEquationTextView.setText(R.string.thermal_conductivity_equation_copper);
	    	 gTextView.setVisibility(View.VISIBLE);
	    	 hTextView.setVisibility(View.VISIBLE);
	    	 iTextView.setVisibility(View.VISIBLE);
			 for(i=7; i<10;i++){
			    	attributes = (TextView)findViewById(layoutTextId[i]);
			    	attributes.setVisibility(View.VISIBLE);
			  }
	    	 break;
	     case R.string.kevlar_49_fiber:
	     case R.string.kevlar_49_composite:
	    	 thermalConductivityEquationTextView.setText(R.string.thermal_conductivity_equation_kevlar_49);
	    	 gTextView.setVisibility(View.GONE);
	    	 hTextView.setVisibility(View.GONE);
	    	 iTextView.setVisibility(View.GONE);
			 for(i=7; i<10;i++){
			    	attributes = (TextView)findViewById(layoutTextId[i]);
			    	attributes.setVisibility(View.GONE);
			  }
	    	 break;
	     default:
	    	 thermalConductivityEquationTextView.setText(R.string.thermal_conductivity_or_specific_heat_or_coefficient_expansion_equation_common); 
	    	 gTextView.setVisibility(View.VISIBLE);
	    	 hTextView.setVisibility(View.VISIBLE);
	    	 iTextView.setVisibility(View.VISIBLE);
			 for(i=7; i<10;i++){
			    	attributes = (TextView)findViewById(layoutTextId[i]);
			    	attributes.setVisibility(View.VISIBLE);
			  }
	    	 break;
	     }
	     
	     aTextView.setText(String.valueOf(thermalConductivityUnits.a));
	     bTextView.setText(String.valueOf(thermalConductivityUnits.b));
	     cTextView.setText(String.valueOf(thermalConductivityUnits.c));
	     dTextView.setText(String.valueOf(thermalConductivityUnits.d));
	     eTextView.setText(String.valueOf(thermalConductivityUnits.e));
	     fTextView.setText(String.valueOf(thermalConductivityUnits.f));
	     gTextView.setText(String.valueOf(thermalConductivityUnits.g));
	     hTextView.setText(String.valueOf(thermalConductivityUnits.h));
	     iTextView.setText(String.valueOf(thermalConductivityUnits.i));
	     
	     switch(thermalConductivityUnits.nameId){
	     	case R.string.copper_RRR_50: 
	     	case R.string.copper_RRR_100:
	     	case R.string.copper_RRR_150:
	     	case R.string.copper_RRR_300:
	     	case R.string.copper_RRR_500:
	     		attributes = (TextView)findViewById(layoutTextId[10]);
	     		attributes.setText("low range");
	     		attributes = (TextView)findViewById(layoutTextId[11]);
	     		attributes.setText("high range");
	     		attributes = (TextView)findViewById(layoutTextId[12]);
	     		attributes.setText("curve fit % error relative to data");
	     		attribute1ValueTextView.setText(thermalConductivityUnits.lowRange);
	     		attribute2ValueTextView.setText(thermalConductivityUnits.highRange);
	     		attribute3ValueTextView.setText(String.valueOf(thermalConductivityUnits.error));
	     		break;
	     	default:
	     		attributes = (TextView)findViewById(layoutTextId[10]);
	     		attributes.setText("Data range");
	     		attributes = (TextView)findViewById(layoutTextId[11]);
	     		attributes.setText("Equation range");
	     		attributes = (TextView)findViewById(layoutTextId[12]);
	     		attributes.setText("curve fit % error relative to data");
	     		attribute1ValueTextView.setText(thermalConductivityUnits.dataRange);
	     		attribute2ValueTextView.setText(thermalConductivityUnits.equationRange);
	     		attribute3ValueTextView.setText(String.valueOf(thermalConductivityUnits.error));
	     		break;
	     }
	     
	 }

	 
	 	OnClickListener onClickListener = new OnClickListener(){
			@Override
			public void onClick(View view) {
				if(selectedMaterialButton.getText().equals("Select material")){
					dialogBox.showDialog("Please select a material");
					return;
				}		
				
				if(inputTemperatureEditText.getText().length() == 0){
					dialogBox.showDialog("Please input 'temperature' value");
					return;
				}
				
				try{
					double thermalConductivity = thermalConductivityUnits.calculate(Double.valueOf(inputTemperatureEditText.getText().toString()));
					thermalConductivity = calculateRoundValue(thermalConductivity);
					spanString = (Spanned) TextUtils.concat(
						     ("Thermal Conductivity Calculation \n\n Thermal Conductivity=\n\t"),
						     Double.toString(thermalConductivity), (" W/m-K"),
							 ("\n\n Material= \n\t "),
							 SpannableString.valueOf(context.getText(thermalConductivityUnits.getNameId())), 
							 ("\n\n"+ "Temperature=" + inputTemperatureEditText.getText().toString() + "K"));

				}catch(Exception ex){
					spanString = (Spanned) TextUtils.concat(ex.getMessage());

				}

				dialogBox.showDialog(spanString);
				
			}
	 		
	 	};
	 	
	 	OnClickListener materialButtonListener = new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				materialSelectDialog();
			}
	 		
	 	};
	 	
	    public void materialSelectDialog(){
	    	new AlertDialog.Builder(this)
	    		.setTitle(R.string.select_material)
	    		.setItems(R.array.thermal_conductivity_material_array,
	    				new DialogInterface.OnClickListener(){
	    					public void onClick(DialogInterface dialogInterface, int i){
	    						setValues(materialAdapter.getItem(i).toString());
	    						equationLayout.setVisibility(View.VISIBLE);
	    						tableLayout.setVisibility(View.VISIBLE);
	    						
	    					}
	    				}
	    		).show();
	    }
	    
	 	 double calculateRoundValue(double actual_value){
				int round_degree;
				double round_value = 0;
				int i = 0;
				if(actual_value > 1){
					while(true){
						round_degree = 5-i;
						if(Math.pow(10, i)>actual_value){break;} 
						else{i++;}
						}
					}
				else{
					while(true){
						round_degree = 4-i;
						if(Math.pow(10, i)<actual_value){break;} 
						else{i--;}
					}
				}
				round_value = (Math.round(actual_value * Math.pow(10, round_degree))) /  Math.pow(10, round_degree);
				return round_value;
			 }
	 	
}