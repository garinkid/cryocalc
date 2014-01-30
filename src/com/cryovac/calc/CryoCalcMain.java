package com.cryovac.calc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class CryoCalcMain extends Activity {
    /** Called when the activity is first created. */
	
	Button propertyButton, heatFlowButton, thermalConductivityButton, specificHeatButton,
		youngModulusButton, linearExpansionButton, expansionCoefficientButton,
		aboutButton, exitButton;
	
	LinearLayout titleLayout;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /*
		Remove due to unclear information source
		 */
        propertyButton = (Button)findViewById(R.id.main_cryogenic_fluid_properties_button);
        propertyButton.setOnClickListener(onClickListener);
       
        titleLayout = (LinearLayout)findViewById(R.id.main_title_layout);
        titleLayout.setOnClickListener(onClickListener);
        
        heatFlowButton = (Button)findViewById(R.id.main_heat_flow_button);
        heatFlowButton.setOnClickListener(onClickListener);
        
        thermalConductivityButton = (Button)findViewById(R.id.main_thermal_conductivity_button);
        thermalConductivityButton.setOnClickListener(onClickListener);
        
        specificHeatButton = (Button)findViewById(R.id.main_specific_heat_button);
        specificHeatButton.setOnClickListener(onClickListener);
        
        youngModulusButton = (Button)findViewById(R.id.main_young_modulus_button);
        youngModulusButton.setOnClickListener(onClickListener);
        
        linearExpansionButton = (Button)findViewById(R.id.main_linear_expansion_button);
        linearExpansionButton.setOnClickListener(onClickListener);
        
        expansionCoefficientButton = (Button)findViewById(R.id.main_expansion_coefficient_button);
        expansionCoefficientButton.setOnClickListener(onClickListener);
        
        aboutButton = (Button)findViewById(R.id.main_about_button);
        aboutButton.setOnClickListener(onClickListener);
        
        exitButton = (Button)findViewById(R.id.main_exit_button);
        exitButton.setOnClickListener(onClickListener);
        
    }
    
    private OnClickListener onClickListener = new OnClickListener(){
		@Override
		public void onClick(View view) {
			Intent newActivity = new Intent();
			switch(view.getId()){
			
			/*
			Remove due to unclear information source		
			*/
			case R.id.main_title_layout:
				String url = "http://www.cryovac.de";
				newActivity = new Intent(Intent.ACTION_VIEW);
				newActivity.setData(Uri.parse(url));
				startActivity(newActivity);
				break;
			
			case R.id.main_cryogenic_fluid_properties_button:
				newActivity = new Intent(view.getContext(),  CryogenicFluidProperties.class);
				startActivity(newActivity);
				break;
		
			case R.id.main_heat_flow_button:
				newActivity = new Intent(view.getContext(), HeatFlowCalc.class);
				startActivity(newActivity);
				break;
			case R.id.main_thermal_conductivity_button:
				newActivity = new Intent(view.getContext(), ThermalConductivity.class);
				startActivity(newActivity);
				break;
			case R.id.main_specific_heat_button:
				newActivity = new Intent(view.getContext(), SpecificHeat.class);
				startActivity(newActivity);
				break;
			case R.id.main_young_modulus_button:
				newActivity = new Intent(view.getContext(), YoungModulus.class);
				startActivity(newActivity);
				break;
			case R.id.main_linear_expansion_button:
				newActivity = new Intent(view.getContext(), LinearExpansion.class);
				startActivity(newActivity);
				break;
			case R.id.main_expansion_coefficient_button:
				newActivity = new Intent(view.getContext(), ExpansionCoefficient.class);
				startActivity(newActivity);
				break;
			case R.id.main_about_button:
				newActivity = new Intent(view.getContext(), About.class);
				startActivity(newActivity);
				break;
			case R.id.main_exit_button:
				finish();
				break;
			}
			
			
		}
    	
    };
    
}