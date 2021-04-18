package edu.utp.manu;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GenerateStandardDeviationPreCompiledSign {
	ArrayList<Double> LLC_Loads_List = new ArrayList<>();
	ArrayList<Double> LLC_Loads_misses_List = new ArrayList<>();	
	
	public static void main(String[] args) {
		
		GenerateStandardDeviationPreCompiledSign gsd = new GenerateStandardDeviationPreCompiledSign();
		
		System.out.println(gsd.getminSD());
	}
	
	public double getminSD()
	{
		SDHelper sdh = new SDHelper();
		double min_sd = 0;
		int count=0;
		
		File dir = new File("data/data_time_slots_elgamal");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File file : directoryListing) {
				
				double mean[] = sdh.getSDofCurrentFile("data/data_time_slots_elgamal/"+file.getName());
				
				Statistics st = new Statistics(mean);
				
				double sd = st.getStdDev();
				System.out.println(sd);
				if(count==0)
				{
					min_sd =sd;
				}else{
				if(min_sd > sd)
					min_sd =sd;
				
				}
				count++;
			}
	}
		return min_sd;
	}

}
