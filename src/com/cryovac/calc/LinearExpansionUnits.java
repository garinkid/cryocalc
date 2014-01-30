package com.cryovac.calc;

import android.content.Context;
import android.content.res.Resources;

public class LinearExpansionUnits {
	double a, b, c, d, e, tLow, fBiggerThan;
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
	
	public double calculate(double temperatureValue) throws Exception{
		double linearExpansion = 0.0;
		if (Double.isNaN(this.fBiggerThan) == false
				&& temperatureValue< this.tLow){
			linearExpansion = this.fBiggerThan;;
		}else{
			double aSection = this.a;
			double bSection = this.b*temperatureValue;
			double cSection = (this.c)*(Math.pow(temperatureValue, 2));
			double dSection = (this.d)*(Math.pow(temperatureValue, 3));
			double eSection = (this.e)*(Math.pow(temperatureValue, 4));
			linearExpansion = aSection + bSection + cSection + dSection + eSection;
		}
		return linearExpansion;	
	}
	
	public void setMaterial(String material){	

	 	if (material.equals(res.getString(R.string.aluminum_3003f_5083_6061T6))){
	 		setToAluminum3003f50836061T6();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.beechwood_phenolic_unidirectional_flatwise))){
	 		setToBeechwoodPhenolicUnidirectionalFlatwise();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.beechwood_phenolic_unidirectional_grain_direction))){
	 		setToBeechwoodPhenolicUnidirectionalGrainDirection();
	 		return;
	 	}

	 	if (material.equals(res.getString(R.string.beechwood_phenolic_cross_laminate_grain_direction))){
	 		setToBeechwoodPhenolicCrossLaminateGrainDirection();
	 		return;
	 	}
	 
	 	if (material.equals(res.getString(R.string.beryllium_a_axis))){
	 		setToBerylliumAAxis();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.beryllium_c_axis))){
	 		setToBerylliumCAxis();
	 		return;
	 	}

	 	if (material.equals(res.getString(R.string.beryllium_polyscrystalline))){
	 		setToBerylliumPolyscrystalline();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.beryllium_copper))){
	 		setToBerylliumCopper();
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
	 	
	 	if (material.equals(res.getString(R.string.glass_fabric_polyester_normal_direction))){
	 		setToGlassFabricPolyesterNormalDirection();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.glass_fabric_polyester_warp_direction))){
	 		setToGlassFabricPolyesterWarpDirection();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.glass_mat_epoxy_chopped_strand_mat))){
	 		setToGlassMatEpoxyChoppedStrandMat();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.glass_mat_epoxy_continuous_strand))){
	 		setToGlassMatEpoxyContinuousStrand();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.inconel_718))){
	 		setToInconel718();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.invar_fe36ni))){
	 		setToInvarFe36Ni();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.molybdenum))){
	 		setToMolybdenum();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.nickel_steel_fe225ni_fe325ni_fe50ni_fe90ni))){
	 		setToNickelSteelFe225NiFe325NiFe50NiFe90Ni();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.polyamide_nylon))){
	 		setToPolyamideNylon();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.polystyrene_density_51_point_42_temperature_range_in_kelvin_4_to_105))){
	 		setToPolystyreneDensity51Point42TemperatureRangeInKelvin4To105();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.polystyrene_density_51_point_42_temperature_range_in_kelvin_105_to_278))){
	 		setToPolystyreneDensity51Point42TemperatureRangeInKelvin105To278();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.polystyrene_density_102_point_2_temperature_range_in_kelvin_4_to_102))){
	 		setToPolystyreneDensity102Point2TemperatureRangeInKelvin4To102();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.polystyrene_density_102_point_2_temperature_range_in_kelvin_102_to_286))){
	 		setToPolystyreneDensity102Point2TemperatureRangeInKelvin102To286();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.polyurethane_density_32_point_04_freon_blown_parallel_to_foam_rise))){
	 		setToPolyurethaneDensity32Point04FreonBlownParallelToFoamRise();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.polyurethane_density_64_point_07_co2_blown_parallel_to_foam_rise))){
	 		setToPolyurethaneDensity64Point07Co2BlownParallelToFoamRise();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.pvc_polyvinyl_chloride))){
	 		setToPvcPolyvinylChloride();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.sapphire))){
	 		setToSapphire();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.stainless_steel_304_304l_310_316))){
	 		setToStainlessSteel304304l310316();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.teflon))){
	 		setToTeflon();
	 		return;
	 	}
	 	
	 	if (material.equals(res.getString(R.string.ti_6al_4v))){
	 		setToTi6Al4V();
	 		return;
	 	}
	 	
	 	return;
	 	
	 
	}
	
	public void setToAluminum3003f50836061T6(){
		this.nameId = R.string.aluminum_3003f_5083_6061T6;
		this.a = (double) -4.1277E2;
		this.b = (double) -3.0389E-1;
		this.c = (double) 8.7696E-3;
		this.d = (double) -9.9821E-6;
		this.e = (double) 0;
		this.tLow = (double) 18;
		this.fBiggerThan = (double) -415.45;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "4";			
	}
	
	public void setToBeechwoodPhenolicUnidirectionalFlatwise(){
		this.nameId = R.string.beechwood_phenolic_unidirectional_flatwise;
		this.a = (double) -683.9;
		this.b = (double) 2.18264;
		this.c = (double) 0.001701;
		this.d = (double) -7.1E-06;
		this.e = (double) 1.04E-8;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "77-293";
		this.equationRange = "77-293";
		this.error = "0.57";	
		
	}
	
	public void setToBeechwoodPhenolicUnidirectionalGrainDirection(){
		this.nameId = R.string.beechwood_phenolic_unidirectional_grain_direction;
		this.a = (double) -189.138;
		this.b = (double) 0.646759;
		this.c = (double) 0.000202;
		this.d = (double) -1.2E-06;
		this.e = (double) 1.85e-09;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "77-293";
		this.equationRange = "77-270";
		this.error = "0.54";		
	}
	
	public void setToBeechwoodPhenolicCrossLaminateGrainDirection(){
		this.nameId = R.string.beechwood_phenolic_cross_laminate_grain_direction;
		this.a = (double) -944.081;
		this.b = (double) 2.909782;
		this.c = (double) 0.002971;
		this.d = (double) -1E-05;
		this.e = (double) 1.23E-08;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "77-293";
		this.equationRange = "77-293";
		this.error = "0.89";		
	}

	public void setToBerylliumAAxis(){
		this.nameId = R.string.beryllium_a_axis;
		this.a = (double) -134.17300;
		this.b = (double) -4.4051E-01;
		this.c = (double) 3.4063E-03;
		this.d = (double) -6.5593E-07;
		this.e = (double) -1.8112E-09;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "75-500";
		this.equationRange = "75-500";
		this.error = "1";		
	}
	
	public void setToBerylliumCAxis(){
		this.nameId = R.string.beryllium_c_axis;
		this.a = (double) -86.08100;
		this.b = (double) -2.6372E-01;
		this.c = (double) 1.3623E-03;
		this.d = (double) 3.2709E-06;
		this.e = (double) -4.8719E-09;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "75-500";
		this.equationRange = "75-500";
		this.error = "1";		
	}
	
	public void setToBerylliumPolyscrystalline(){
		this.nameId = R.string.beryllium_polyscrystalline;
		this.a = (double) -120.29800;
		this.b = (double) -3.4687E-01;
		this.c = (double) 2.5691E-03;
		this.d = (double) 8.9848E-07;
		this.e = (double) -2.9422E-09;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "75-500";
		this.equationRange = "75-500";
		this.error = "1";	
		
	}
	
	public void setToBerylliumCopper(){
		this.nameId = R.string.beryllium_copper;
		this.a = (double) -3.1150E2;
		this.b = (double) -4.4498E-1;
		this.c = (double) 1.0133E-2;
		this.d = (double) -2.4718E-5;
		this.e = (double) 2.6277E-8;
		this.tLow = (double) 24.0;
		this.fBiggerThan = (double) -316.68;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "1.5";	
		
	}
	
	public void setToFiberglassEpoxyNormalDirection(){
		this.nameId = R.string.fiberglass_epoxy_normal_direction;
		this.a = (double) -7.198E2;
		this.b = (double) 4.455E-1;
		this.c = (double) 7.505E-3;
		this.d = (double) -2.219E-6;
		this.e = (double) 0;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "4-300";
		this.equationRange = "10-300";
		this.error = "1.5";		
	}
	
	public void setToFiberglassEpoxyWarpDirection(){
		this.nameId = R.string.fiberglass_epoxy_warp_direction;
		this.a = (double) -2.469E2;
		this.b = (double) 2.064E-1;
		this.c = (double) 3.072E-3;
		this.d = (double) -3.226E-6;
		this.e = (double) 0;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "4-300";
		this.equationRange = "12-300";
		this.error = "2";	
	}
	
	public void setToGlassFabricPolyesterNormalDirection(){
		this.nameId = R.string.glass_fabric_polyester_normal_direction;
		this.a = (double) -7.179E2;
		this.b = (double) -3.157E0;
		this.c = (double) 5.251E-2;
		this.d = (double) -1.947E-4;
		this.e = (double) 2.752E-7;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "80-293";
		this.equationRange = "80-285";
		this.error = "2";	
	}

	public void setToGlassFabricPolyesterWarpDirection(){
		this.nameId = R.string.glass_fabric_polyester_warp_direction;
		this.a = (double) -3.0897E2;
		this.b = (double) 1.0245E0;
		this.c = (double) -2.9503E-3;
		this.d = (double) 1.8323E-5;
		this.e = (double) -2.7013E-8;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "80-293";
		this.equationRange = "80-280";
		this.error = "2";	
	}
	
	public void setToGlassMatEpoxyChoppedStrandMat(){
		this.nameId = R.string.glass_mat_epoxy_chopped_strand_mat;
		this.a = (double) -6.5898E2;
		this.b = (double) 4.7697E0;
		this.c = (double) -2.9638E-2;
		this.d = (double) 1.1501E-4;
		this.e = (double) -1.4763E-7;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "77-293";
		this.equationRange = "77-293";
		this.error = "2";	
	}
	
	public void setToGlassMatEpoxyContinuousStrand(){
		this.nameId = R.string.glass_mat_epoxy_continuous_strand;
		this.a = (double) -4.392E2;
		this.b = (double) 1.525E0;
		this.c = (double) -2.384E-3;
		this.d = (double) 8.665E-6;
		this.e = (double) -2.857E-9;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "77-293";
		this.equationRange = "77-293";
		this.error = "2";	
	}
	
	public void setToInconel718(){
		this.nameId = R.string.inconel_718;
		this.a = (double) -2.368E2;
		this.b = (double) -2.120E-1;
		this.c = (double) 5.497E-3;
		this.d = (double) -6.882E-6;
		this.e = (double) 0;
		this.tLow = (double) 20.0;
		this.fBiggerThan = (double) -238.87;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "1.1";	
	}
	
	public void setToInvarFe36Ni(){
		this.nameId = R.string.invar_fe36ni;
		this.a = (double) -5.265E1;
		this.b = (double) 1.009E-1;
		this.c = (double) 8.395E-4;
		this.d = (double) -1.973E-6;
		this.e = (double) 8.794E-11;
		this.tLow = (double) 80;
		this.fBiggerThan = (double) -40;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "5";	
	}
	
	public void setToMolybdenum(){
		this.nameId = R.string.molybdenum;
		this.a = (double) -90.912613;
		this.b = (double) -0.127173;
		this.c = (double) 0.00266801;
		this.d = (double) -5.0432E-06;
		this.e = (double) 3.5183E-09;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "20-500";
		this.equationRange = "20-350";
		this.error = "4";	
	}
	
	public void setToNickelSteelFe225NiFe325NiFe50NiFe90Ni(){
		this.nameId = R.string.nickel_steel_fe225ni_fe325ni_fe50ni_fe90ni;
		this.a = (double) -2.104E2;
		this.b = (double) -5.699E-2;
		this.c = (double) 5.072E-3;
		this.d = (double) -1.381E-5;
		this.e = (double) 1.897E-8;
		this.tLow = (double) 6.0;
		this.fBiggerThan = (double) -210.56;
		this.dataRange = "4-300";
		this.equationRange = "4-265";
		this.error = "1.5";	
	}
	
	public void setToPolyamideNylon(){
		this.nameId = R.string.polyamide_nylon;
		this.a = (double) -1.389E3;
		this.b = (double) -1.561E-1;
		this.c = (double) 2.988E-2;
		this.d = (double) -7.948E-5;
		this.e = (double) 1.181E-7;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "1";	
	}
	
	public void setToPolystyreneDensity51Point42TemperatureRangeInKelvin4To105(){
		this.nameId = R.string.polystyrene_density_51_point_42_temperature_range_in_kelvin_4_to_105;
		this.a = (double) -1.6948E3;
		this.b = (double) -9.6845E-1;
		this.c = (double) 7.8268E-2;
		this.d = (double) -2.4831E-4;
		this.e = (double) 0;
		this.tLow = (double) 6.4;
		this.fBiggerThan = (double) -1697.9;
		this.dataRange = "4-300";
		this.equationRange = "4-105";
		this.error = "1";	
	}
	
	public void setToPolystyreneDensity51Point42TemperatureRangeInKelvin105To278(){
		this.nameId = R.string.polystyrene_density_51_point_42_temperature_range_in_kelvin_105_to_278;
		this.a = (double) -2.1168E3;
		this.b = (double) 1.0963E1;
		this.c = (double) -3.5335E-2;
		this.d = (double) 1.3552E-4;
		this.e = (double) -1.9890E-7;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "4-300";
		this.equationRange = "105-278";
		this.error = "1";	
	}
	
	public void setToPolystyreneDensity102Point2TemperatureRangeInKelvin4To102(){
		this.nameId = R.string.polystyrene_density_102_point_2_temperature_range_in_kelvin_4_to_102;
		this.a = (double) -1.7494E3;
		this.b = (double) 2.0607E0;
		this.c = (double) -4.7467E-2;
		this.d = (double) 1.2156E-3;
		this.e = (double) -5.4405E-6;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "4-300";
		this.equationRange = "4-102";
		this.error = "1";	
	}
	
	public void setToPolystyreneDensity102Point2TemperatureRangeInKelvin102To286(){
		this.nameId = R.string.polystyrene_density_102_point_2_temperature_range_in_kelvin_102_to_286;
		this.a = (double) -1.9374E3;
		this.b = (double) 3.6139E0;
		this.c = (double) 3.5974E-2;
		this.d = (double) -1.5164E-4;
		this.e = (double) 2.1764E-7;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "4-300";
		this.equationRange = "102-286";
		this.error = "2";	
	}
	
	public void setToPolyurethaneDensity32Point04FreonBlownParallelToFoamRise(){
		this.nameId = R.string.polyurethane_density_32_point_04_freon_blown_parallel_to_foam_rise;
		this.a = (double) -8.064E2;
		this.b = (double) -5.049E-1;
		this.c = (double) 2.140E-2;
		this.d = (double) -5.036E-5;
		this.e = (double) 5.192E-8;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "20-300";
		this.equationRange = "20-275";
		this.error = "1.5";	
	}
	
	public void setToPolyurethaneDensity64Point07Co2BlownParallelToFoamRise(){
		this.nameId = R.string.polyurethane_density_64_point_07_co2_blown_parallel_to_foam_rise;
		this.a = (double) -1.0647E3;
		this.b = (double) 3.1238E-1;
		this.c = (double) 2.2854E-2;
		this.d = (double) -5.9123E-5;
		this.e = (double) 6.7482E-8;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "20-300";
		this.equationRange = "20-293";
		this.error = "1";	
	}

	public void setToPvcPolyvinylChloride(){
		this.nameId = R.string.pvc_polyvinyl_chloride;
		this.a = (double) -1033.8;
		this.b = (double) 2.1922;
		this.c = (double) 8.7335E-3;
		this.d = (double) -3.1408E-5;
		this.e = (double) 5.9411E-8;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "4-300";
		this.equationRange = "4-280";
		this.error = "1";	
	}
	
	public void setToSapphire(){
		this.nameId = R.string.sapphire;
		this.a = (double) -7.8850E+01;
		this.b = (double) -2.2346E-02;
		this.c = (double) 1.0185E-04;
		this.d = (double) 5.5594E-06;
		this.e = (double) -8.5422E-09;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "20-293";
		this.equationRange = "15-300";
		this.error = "4";	
	}
	
	public void setToStainlessSteel304304l310316(){
		this.nameId = R.string.stainless_steel_304_304l_310_316;
		this.a = (double) -2.9554E2;
		this.b = (double) -3.9811E-1;
		this.c = (double) 9.2683E-3;
		this.d = (double) -2.0261E-5;
		this.e = (double) 1.7127E-8;
		this.tLow = (double) 23.0;
		this.fBiggerThan = (double) -300.04;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "5";		
	}
	
	public void setToTeflon(){
		this.nameId = R.string.teflon;
		this.a = (double) -2.125E3;
		this.b = (double) -8.201E-1;
		this.c = (double) 6.161E-2;
		this.d = (double) -3.171E-4;
		this.e = (double) 6.850E-7;
		this.tLow = (double) Double.NaN;
		this.fBiggerThan = (double) Double.NaN;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "2";		
	}
	
	public void setToTi6Al4V(){
		this.nameId = R.string.ti_6al_4v;
		this.a = (double) -1.711E2;
		this.b = (double) -2.140E-1;
		this.c = (double) 4.807E-3;
		this.d = (double) -7.111E-6;
		this.e = (double) 0;
		this.tLow = (double) 24.0;
		this.fBiggerThan = (double) -173.61;
		this.dataRange = "4-300";
		this.equationRange = "4-300";
		this.error = "1.5";		
	}
	
	
}
