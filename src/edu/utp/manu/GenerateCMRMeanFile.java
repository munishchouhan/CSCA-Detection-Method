package edu.utp.manu;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

public class GenerateCMRMeanFile {
	ArrayList<Double> LLC_Loads_List = new ArrayList<>();
	ArrayList<Double> LLC_Loads_misses_List = new ArrayList<>();	
	ArrayList<Double> means = new ArrayList<>();
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		long time = System.currentTimeMillis();
		GenerateCMRMeanFile GCF = new GenerateCMRMeanFile();
		
		GCF.GenerateMeanRSA();
		GCF.GenerateMeanDSA();
		GCF.GenerateMeanElgamal();
		
		Iterator<Double> means_iterator = GCF.means.iterator();
		int count =0;
		PrintWriter writer = new PrintWriter("data/means.dat", "UTF-8");
		time = System.currentTimeMillis() - time;
		System.out.println("execution time: "+time);
		
		while(means_iterator.hasNext())
		{
			count++;
			writer.println(means_iterator.next());
		}
		System.out.println("number of pre-compiled signatures:"+count);
		writer.close();
	}
	
	public void GenerateMeanRSA() {

		// ArrayList<Double> RSA_CMR_List = new ArrayList<>();
		ArrayList<Double> diffrence = new ArrayList<>();
		double mean = 0;

		// ArrayList<Double> GPG_CMR_List = new ArrayList<>();

		// printCMR();
		// GPG_CMR_List = CMR_List;

		File dir = new File("data/data_time_slots_rsa");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File file : directoryListing) {
			mean = 0;
			// RSA_CMR_List.clear();
			diffrence.clear();
			readlinebyline("data/csca_data_10_200_times_only_gpg.dat");
			caculateCMR();
			Iterator<Double> GPG_CMR_List_Iterator = caculateCMR().iterator();

			readlinebyline("data/data_time_slots_rsa/"+file.getName());

			Iterator<Double> RSA_CMR_List_Iterator = caculateCMR().iterator();

			//System.out.println("RSA here");
			while (RSA_CMR_List_Iterator.hasNext() && GPG_CMR_List_Iterator.hasNext()) {
				double RSA_CMR = RSA_CMR_List_Iterator.next();
				double GPG_CMR = GPG_CMR_List_Iterator.next();
				//System.out.println(RSA_CMR + "-" + GPG_CMR);
				diffrence.add(RSA_CMR - GPG_CMR);
			}

			Iterator<Double> diffrence_Iterator = diffrence.iterator();
			//System.out.println("here");
			while (diffrence_Iterator.hasNext()) {
				mean += diffrence_Iterator.next();
			}
			mean = mean / 200;
			//System.out.println(mean);
			means.add(mean);
		}
		}

	}
	public void GenerateMeanDSA() {

		ArrayList<Double> diffrence = new ArrayList<>();
		double mean = 0;

		File dir = new File("data/data_time_slots_dsa");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File file : directoryListing) {
			mean = 0;
			// RSA_CMR_List.clear();
			diffrence.clear();
			readlinebyline("data/csca_data_10_200_times_only_gpg_dsa.dat");
			caculateCMR();
			Iterator<Double> GPG_CMR_List_Iterator = caculateCMR().iterator();

			readlinebyline("data/data_time_slots_dsa/"+file.getName());

			Iterator<Double> RSA_CMR_List_Iterator = caculateCMR().iterator();

			while (RSA_CMR_List_Iterator.hasNext() && GPG_CMR_List_Iterator.hasNext()) {
				double RSA_CMR = RSA_CMR_List_Iterator.next();
				double GPG_CMR = GPG_CMR_List_Iterator.next();
				diffrence.add(RSA_CMR - GPG_CMR);
			}

			Iterator<Double> diffrence_Iterator = diffrence.iterator();
			while (diffrence_Iterator.hasNext()) {
				mean += diffrence_Iterator.next();
			}
			mean = mean / 200;
			means.add(mean);
		}
		}

	}
	
	public void GenerateMeanElgamal() {

		ArrayList<Double> diffrence = new ArrayList<>();
		double mean = 0;
		File dir = new File("data/data_time_slots_elgamal");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File file : directoryListing) {
			mean = 0;
			// RSA_CMR_List.clear();
			diffrence.clear();
			readlinebyline("data/csca_data_10_200_times_only_gpg_elgamal.dat");
			caculateCMR();
			Iterator<Double> GPG_CMR_List_Iterator = caculateCMR().iterator();
			


			readlinebyline("data/data_time_slots_elgamal/"+file.getName());

			Iterator<Double> RSA_CMR_List_Iterator = caculateCMR().iterator();

			while (RSA_CMR_List_Iterator.hasNext() && GPG_CMR_List_Iterator.hasNext()) {
				double RSA_CMR = RSA_CMR_List_Iterator.next();
				double GPG_CMR = GPG_CMR_List_Iterator.next();
				//System.out.println(RSA_CMR + "-" + GPG_CMR);
				diffrence.add(RSA_CMR - GPG_CMR);
			}

			Iterator<Double> diffrence_Iterator = diffrence.iterator();
			//System.out.println("here");
			while (diffrence_Iterator.hasNext()) {
				mean += diffrence_Iterator.next();
			}
			mean = mean / 200;
			//System.out.println(mean);
			means.add(mean);
		}
		}

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
