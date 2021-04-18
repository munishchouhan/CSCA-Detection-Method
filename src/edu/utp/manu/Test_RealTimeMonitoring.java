package edu.utp.manu;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Test_RealTimeMonitoring {
	ArrayList<Double> LLC_Loads_List = new ArrayList<>();
	ArrayList<Double> LLC_Loads_misses_List = new ArrayList<>();
	
	

	public ArrayList<Double> GenerateMean() {
		ArrayList<Double> means = new ArrayList<>();

		readlinebyline("data/csca_data_10_200_times_only_gpg.dat");

		File dir = new File("data/real_time_data");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File file : directoryListing) {
				double mean = 0;
				ArrayList<Double> diffrence = new ArrayList<>();
				Iterator<Double> CMR_List_Iterator = caculateCMR().iterator();
				
				readlinebyline("data/real_time_data/" + file.getName());
				
				Iterator<Double> live_CMR_List_Iterator = caculateCMR().iterator();
				
				while (live_CMR_List_Iterator.hasNext() && CMR_List_Iterator.hasNext()) {
					double live_CMR = live_CMR_List_Iterator.next();
					double CMR = CMR_List_Iterator.next();
					diffrence.add(live_CMR - CMR);
				}

				Iterator<Double> diffrence_Iterator = diffrence.iterator();
				// System.out.println("here");
				int countmeans = 0;
				while (diffrence_Iterator.hasNext()) {
					mean += diffrence_Iterator.next();
					countmeans++;
				}
				mean = mean / countmeans;
				//System.out.println(mean);
				means.add(mean);
			}
		}
		return means;
	}

	public void readlinebyline(String filename) {
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while reading the file:" + e.getMessage());
		}

		// System.err.println(i);
	}

	public ArrayList<Double> caculateCMR() {
		Iterator<Double> LLC_Loads_List_Iterator = LLC_Loads_List.iterator();
		Iterator<Double> LLC_Loads_misses_List_Iterator = LLC_Loads_misses_List.iterator();
		ArrayList<Double> CMR_List = new ArrayList<>();
		CMR_List.clear();
		while (LLC_Loads_List_Iterator.hasNext() && LLC_Loads_misses_List_Iterator.hasNext()) {
			Double CMR = LLC_Loads_misses_List_Iterator.next() / LLC_Loads_List_Iterator.next();
			CMR_List.add(CMR);
		}
		return CMR_List;
	}

}
