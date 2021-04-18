package edu.utp.manu;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class SDHelper {
	
	public double[] getSDofCurrentFile(String filename)
	{
		readCurrentFile(filename);
		
		return caculateCMR();
	}
	
	ArrayList<Double> LLC_Loads_List = new ArrayList<>();
	ArrayList<Double> LLC_Loads_misses_List = new ArrayList<>();
	public void readCurrentFile(String filename)
	{
		try{
			LLC_Loads_List.clear();
			LLC_Loads_misses_List.clear();

		FileInputStream fstream = new FileInputStream(filename);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = "";

		while ((line = br.readLine()) != null) {
			if (line.isEmpty())
				continue;
			if (Character.isDigit(line.charAt(0))) {
				String SplitLine[] = line.split(",");
				// System.err.println(SplitLine[0]);
				if (SplitLine[1].trim().equalsIgnoreCase("LLC-loads")) {

					LLC_Loads_List.add(Double.parseDouble(SplitLine[0]));
				} else if (SplitLine[1].trim().equalsIgnoreCase("LLC-load-misses")) {
					LLC_Loads_misses_List.add(Double.parseDouble(SplitLine[0]));
				} else {
					// System.out.println("Data missing");
				}

			}

		}
		in.close();
	}catch (Exception e) {
		e.printStackTrace();
		System.out.println("Error while reading the file:" + e.getMessage());
	}

}
	public double[] caculateCMR() {
		Iterator<Double> LLC_Loads_List_Iterator = LLC_Loads_List.iterator();
		Iterator<Double> LLC_Loads_misses_List_Iterator = LLC_Loads_misses_List.iterator();
		ArrayList<Double> CMR_List = new ArrayList<>();
		CMR_List.clear();
		while (LLC_Loads_List_Iterator.hasNext() && LLC_Loads_misses_List_Iterator.hasNext()) {
			Double CMR = LLC_Loads_misses_List_Iterator.next() / LLC_Loads_List_Iterator.next();
			CMR_List.add(CMR);
		}
		double cmr_array[]= new double[CMR_List.size()];
		Iterator<Double> CMR_List_Iterator = CMR_List.iterator();
		
		for(int i=0;i<cmr_array.length;i++)
		{
			if(CMR_List_Iterator.hasNext())
			cmr_array[i] = CMR_List_Iterator.next();
		}
		return cmr_array;
	}
}
