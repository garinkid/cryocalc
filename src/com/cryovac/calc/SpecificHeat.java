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

public class SpecificHeat  extends Activity{
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
		specificEquationTextView, specificHeatEquationTextView, specificHeatTableTitle;
	EditText inputTemperatureEditText;
	SpecificHeatUnits specificHeatUnits = new SpecificHeatUnits();
	Button calculateButton, selectedMaterialButton;
	Float temperature;
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.specific_heat);
	     setTitle("CryoCalc: Specific Heat");
	     
	     specificHeatUnits.setContext(this.getBaseContext());

	     selectedMaterialButton = (Button)findViewById(R.id.selected_material);
	     selectedMaterialButton.setOnClickListener(materialButtonListener);
	     selectedMaterialButton.setText("Select material");

	     equationLayout = (LinearLayout)findViewById(R.id.equation_layout);
	     tableLayout = (LinearLayout)findViewById(R.id.table_layout);
	     materialAdapter = ArrayAdapter.createFromResource
	        		(this, R.array.specific_heat_material_array, 0);
	    
	     specificHeatTableTitle =  (TextView)findViewById(R.id.specific_heat_table_title);
	     specificHeatTableTitle.setText("Specific Heat Units");
	     
	     specificHeatEquationTextView = (TextView)findViewById(R.id.specific_heat_equation_text_view);
	
	     inputTemperatureEditText = (EditText)findViewById(R.id.input_temperature);
	   

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
		 attributes.setText("J/(Kg-K)");
		 
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
				
				String specificHeatText;
				
				try{
					double specificHeat = specificHeatUnits.calculateSpecificHeatCommon(Double.valueOf(inputTemperatureEditText.getText().toString()));
					specificHeat = calculateRoundValue(specificHeat);
					specificHeatText = Double.toString(specificHeat);
				} catch (Exception e){
					specificHeatText = "Infinity or NaN result";
					
				}
				
				spanString = (Spanned) TextUtils.concat(
						 ("Specific Heat Calculation \n\n  Specific Heat=\n\t"),
						 specificHeatText,
						 (" J/(Kg-K)"),
						 ( "\n\n Material= \n\t "),
						 SpannableString.valueOf(context.getText(specificHeatUnits.getNameId())), 
						 ("\n\n"+ "Temperature=" + inputTemperatureEditText.getText().toString() + "K"));
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
    		.setItems(R.array.specific_heat_material_array,
    				new DialogInterface.OnClickListener(){
    					public void onClick(DialogInterface dialogInterface, int i){
    						setValues(materialAdapter.getItem(i).toString());
    						equationLayout.setVisibility(View.VISIBLE);
    						tableLayout.setVisibility(View.VISIBLE);
    						
    					}
    				}
    		).show();
    }
	
	 void setValues(String material){
		 specificHeatUnits.setMaterial(material);
		 selectedMaterialButton.setText(getText(specificHeatUnits.getNameId()));
	     
	     specificHeatEquationTextView.setText(R.string.thermal_conductivity_or_specific_heat_or_coefficient_expansion_equation_common); 
	
	     
	     aTextView.setText(String.valueOf(specificHeatUnits.a));
	     bTextView.setText(String.valueOf(specificHeatUnits.b));
	     cTextView.setText(String.valueOf(specificHeatUnits.c));
	     dTextView.setText(String.valueOf(specificHeatUnits.d));
	     eTextView.setText(String.valueOf(specificHeatUnits.e));
	     fTextView.setText(String.valueOf(specificHeatUnits.f));
	     gTextView.setText(String.valueOf(specificHeatUnits.g));
	     hTextView.setText(String.valueOf(specificHeatUnits.h));
	     iTextView.setText(String.valueOf(specificHeatUnits.i));
	     
	     attributes = (TextView)findViewById(layoutTextId[10]);
	     attributes.setText("Data range");
	     attributes = (TextView)findViewById(layoutTextId[11]);
	     attributes.setText("Equation range");
	     attributes = (TextView)findViewById(layoutTextId[12]);
	     attributes.setText("curve fit % error relative to data");
	     
	     attribute1ValueTextView.setText(specificHeatUnits.dataRange);
	     attribute2ValueTextView.setText(specificHeatUnits.equationRange);
	     attribute3ValueTextView.setText(String.valueOf(specificHeatUnits.error));

	     
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
