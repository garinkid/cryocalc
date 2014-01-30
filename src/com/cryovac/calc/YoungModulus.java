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


public class YoungModulus extends Activity{
	
	final Context context = this;
	String attributesText[] = {"Units", "a", "b", "c", "d", "e", 
			"Data range", "Equation range", 
			"curve fit % error relative to data"};

	int layoutTextId[], i;
	TextView attributes;
	String material;
	DialogBox dialogBox = new DialogBox(context);
	Spanned spanString;
	ArrayAdapter<CharSequence> materialAdapter;
	
	LinearLayout equationLayout, tableLayout;
	TextView aTextView, bTextView,
		cTextView, dTextView, eTextView,
		attribute1ValueTextView, attribute2ValueTextView, attribute3ValueTextView,
		youngModulusEquationTextView, youngModulusTableTitle;
	EditText inputTemperatureEditText;
	YoungModulusUnits youngModulusUnits = new YoungModulusUnits();
	Button calculateButton, selectedMaterialButton;
	Float temperature;
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.young_modulus);
	     setTitle("CryoCalc: Young's Modulus");
	     youngModulusUnits.setContext(this.getBaseContext());
	     selectedMaterialButton = (Button)findViewById(R.id.selected_material);
	     selectedMaterialButton.setOnClickListener(materialButtonListener);
	     selectedMaterialButton.setText("Select material");
	     equationLayout = (LinearLayout)findViewById(R.id.equation_layout);
	     tableLayout = (LinearLayout)findViewById(R.id.table_layout);
	     materialAdapter = ArrayAdapter.createFromResource
	        		(this, R.array.young_modulus_material_array, 0);
	     
	     
	     youngModulusEquationTextView = (TextView)findViewById(R.id.young_modulus_equation_text_view);
	
	     inputTemperatureEditText = (EditText)findViewById(R.id.input_temperature);
	   
	     youngModulusTableTitle =  (TextView)findViewById(R.id.young_modulus_table_title);
	     youngModulusTableTitle.setText("Young's Modulus Units");
	     
	     
	     //set attributes value
	     layoutTextId = new int[9];
	     layoutTextId[0] = R.id.attributes_1_text;
	     layoutTextId[1] = R.id.attributes_2_text;
	     layoutTextId[2] = R.id.attributes_3_text;
	     layoutTextId[3] = R.id.attributes_4_text;
	     layoutTextId[4] = R.id.attributes_5_text;
	     layoutTextId[5] = R.id.attributes_6_text;
	     layoutTextId[6] = R.id.attributes_7_text;
	     layoutTextId[7] = R.id.attributes_8_text;
	     layoutTextId[8] = R.id.attributes_9_text;

	     
		 for(i=0; i<9;i++){
		    	attributes = (TextView)findViewById(layoutTextId[i]);
		    	attributes.setText(attributesText[i]);
		  }
		 
		 attributes = (TextView)findViewById( R.id.attributes_1_value);
		 attributes.setText("GPa");
		 
		 aTextView =(TextView)findViewById(R.id.a_value);
	     bTextView =(TextView)findViewById(R.id.b_value);
	     cTextView =(TextView)findViewById(R.id.c_value);
	     dTextView =(TextView)findViewById(R.id.d_value);
	     eTextView =(TextView)findViewById(R.id.e_value);
	     attribute1ValueTextView =(TextView)findViewById(R.id.attribute_1_value);
	     attribute2ValueTextView =(TextView)findViewById(R.id.attribute_2_value);
	     attribute3ValueTextView = (TextView)findViewById(R.id.attribute_3_value);          
	     
	     // modification for copper
	     
	     calculateButton = (Button)findViewById(R.id.calculate_button);
	     calculateButton.setOnClickListener(onClickListener); 
	     //setValues(material);
		 
	 }
	 
	 
	 void setValues(String material){
		 youngModulusUnits.setMaterial(material);
		 selectedMaterialButton.setText(getText(youngModulusUnits.getNameId()));    
	     youngModulusEquationTextView.setText(R.string.young_modulus_equation_common); 	 
	     aTextView.setText(String.valueOf(youngModulusUnits.a));
	     bTextView.setText(String.valueOf(youngModulusUnits.b));
	     cTextView.setText(String.valueOf(youngModulusUnits.c));
	     dTextView.setText(String.valueOf(youngModulusUnits.d));
	     eTextView.setText(String.valueOf(youngModulusUnits.e));
	     attribute1ValueTextView.setText(youngModulusUnits.dataRange);
	     attribute2ValueTextView.setText(youngModulusUnits.equationRange);
	     attribute3ValueTextView.setText(String.valueOf(youngModulusUnits.error));
    
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
					double youngModulus = youngModulusUnits.calculate(Double.valueOf(inputTemperatureEditText.getText().toString()));
					youngModulus = calculateRoundValue(youngModulus);
					spanString = (Spanned) TextUtils.concat(
							  ("Young's Modulus Calculation \n\n Young's Modulus=\n\t"),
							  Double.toString(youngModulus), (" GPa"),
							  ("\n\n Material= \n\t "),
							  SpannableString.valueOf(context.getText(youngModulusUnits.getNameId())), 
							  ("\n\n"+ "Temperature=" + inputTemperatureEditText.getText().toString() + "K"));
				}catch(Exception ex){
					spanString = (Spanned) TextUtils.concat(
						     ex.getMessage()
							 );
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
	    		.setItems(R.array.young_modulus_material_array,
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