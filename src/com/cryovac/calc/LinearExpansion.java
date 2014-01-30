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


public class LinearExpansion extends Activity{
	
	final Context context = this;

	int layoutTextId[], i;
	TextView attributes;
	String material;
	DialogBox dialogBox = new DialogBox(context);
	Spanned spanString;
	ArrayAdapter<CharSequence> materialAdapter;
	
	LinearLayout equationLayout, tableLayout;
	TextView aTextView, bTextView,
		cTextView, dTextView, eTextView,
		tLowTextView, fBiggerThanTextView, textView, 
		attribute1ValueTextView, attribute2ValueTextView, attribute3ValueTextView,
		linearExpansionEquationTextView, linearExpansionTableTitle;
	EditText inputTemperatureEditText;
	LinearExpansionUnits linearExpansionUnits = new LinearExpansionUnits();
	Button calculateButton, selectedMaterialButton;
	Float temperature;
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.linear_expansion);
	     setTitle("CryoCalc: Linear Expansion");
	     //material = getIntent().getStringExtra("selected_material");
	    linearExpansionUnits.setContext(this.getBaseContext());
	     //Log.d("selected material", material);
	     //set selected material
	     selectedMaterialButton = (Button)findViewById(R.id.selected_material);
	     selectedMaterialButton.setOnClickListener(materialButtonListener);
	     selectedMaterialButton.setText("Select material");
	     equationLayout = (LinearLayout)findViewById(R.id.equation_layout);
	     tableLayout = (LinearLayout)findViewById(R.id.table_layout);
	     
	     materialAdapter = ArrayAdapter.createFromResource
	        		(this, R.array.linear_expansion_material_array, 
	        				0);
	     
	     
	     linearExpansionEquationTextView = (TextView)findViewById(R.id.linear_expansion_equation_text_view);
	
	     inputTemperatureEditText = (EditText)findViewById(R.id.input_temperature);
	   
	     linearExpansionTableTitle =  (TextView)findViewById(R.id.linear_expansion_table_title);
	     linearExpansionTableTitle.setText("Linear Expansion Units");
	     
	     
	     //set attributes value
	     textView = (TextView)findViewById( R.id.attributes_1_text);
	     textView.setText("Units");
	     textView = (TextView)findViewById( R.id.attributes_2_text);
	     textView.setText("a");
	     textView = (TextView)findViewById( R.id.attributes_3_text);
	     textView.setText("b");
	     textView = (TextView)findViewById( R.id.attributes_4_text);
	     textView.setText("c");
	     textView = (TextView)findViewById( R.id.attributes_5_text);
	     textView.setText("d");
	     textView = (TextView)findViewById( R.id.attributes_6_text);
	     textView.setText("e");
	     textView = (TextView)findViewById( R.id.attributes_7_text);
	     textView.setText(getText(R.string.t_low_k));
	     textView = (TextView)findViewById( R.id.attributes_8_text);
	     textView.setText("f>");
	     textView = (TextView)findViewById( R.id.attributes_9_text);
	     textView.setText("Data range (K)");
	     textView = (TextView)findViewById( R.id.attributes_10_text);
	     textView.setText("Equation range (K)");
	     textView = (TextView)findViewById( R.id.attributes_11_text);
	     textView.setText("Curve fit % error relative to data");
	     
		 
		 attributes = (TextView)findViewById( R.id.attributes_1_value);
		 attributes.setText(getText(R.string.linear_expansion_units));
		 
		 aTextView =(TextView)findViewById(R.id.a_value);
	     bTextView =(TextView)findViewById(R.id.b_value);
	     cTextView =(TextView)findViewById(R.id.c_value);
	     dTextView =(TextView)findViewById(R.id.d_value);
	     eTextView =(TextView)findViewById(R.id.e_value);
	     tLowTextView =(TextView)findViewById(R.id.t_low_value);
	     fBiggerThanTextView =(TextView)findViewById(R.id.f_bigger_than_value);
	     attribute1ValueTextView =(TextView)findViewById(R.id.attribute_1_value);
	     attribute2ValueTextView =(TextView)findViewById(R.id.attribute_2_value);
	     attribute3ValueTextView = (TextView)findViewById(R.id.attribute_3_value);          
	     
	
	     
	     calculateButton = (Button)findViewById(R.id.calculate_button);
	     calculateButton.setOnClickListener(onClickListener); 
	     //setValues(material);
		 
	 }
	 
	 
	 void setValues(String material){
		 linearExpansionUnits.setMaterial(material);
		 selectedMaterialButton.setText(getText(linearExpansionUnits.getNameId()));    
	     linearExpansionEquationTextView.setText(R.string.linear_expansion_equation_common); 	 
	     aTextView.setText(String.valueOf(linearExpansionUnits.a));
	     bTextView.setText(String.valueOf(linearExpansionUnits.b));
	     cTextView.setText(String.valueOf(linearExpansionUnits.c));
	     dTextView.setText(String.valueOf(linearExpansionUnits.d));
	     eTextView.setText(String.valueOf(linearExpansionUnits.e));
	     
	     if(Double.isNaN(linearExpansionUnits.tLow)){
	    	 tLowTextView.setText("Not Available");
	     }else{
	    	 tLowTextView.setText(String.valueOf(linearExpansionUnits.tLow));
	     }
	     
	     if(Double.isNaN(linearExpansionUnits.fBiggerThan)){
	    	 fBiggerThanTextView.setText("Not Available");
	     }else{
	    	 fBiggerThanTextView.setText(String.valueOf(linearExpansionUnits.fBiggerThan));
	     }

	     attribute1ValueTextView.setText(linearExpansionUnits.dataRange);
	     attribute2ValueTextView.setText(linearExpansionUnits.equationRange);
	     attribute3ValueTextView.setText(String.valueOf(linearExpansionUnits.error));
    
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
					double linearExpansion = linearExpansionUnits.calculate(Double.valueOf(inputTemperatureEditText.getText().toString()));
					linearExpansion = calculateRoundValue(linearExpansion);
					spanString = (Spanned) TextUtils.concat(
						     ("Linear Expansion Calculation \n\n Linear Expansion=\n\t"), 
							 (Double.toString(linearExpansion) + " "),
							 SpannableString.valueOf(context.getText(R.string.linear_expansion_units)),
							 ("\n\n Material= \n\t "),
							 SpannableString.valueOf(context.getText(linearExpansionUnits.getNameId())), 
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
	    		.setItems(R.array.linear_expansion_material_array,
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
				boolean negativeValue = false;
				
				if (actual_value < 0){
					actual_value = Math.abs(actual_value);
					negativeValue = true;
				}
				
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
				if (negativeValue==true){
					round_value = -round_value;
				}
				
				return round_value;
			 }
	 	
}