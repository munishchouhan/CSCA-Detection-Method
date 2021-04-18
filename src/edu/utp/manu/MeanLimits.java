package edu.utp.manu;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MeanLimits {
	double max_mean =0;
	double min_mean =0;
	
	public MeanLimits(String filename) throws NumberFormatException, IOException
	{
		FileInputStream fstream = new FileInputStream(filename);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = "";
		
		while ((line = br.readLine()) != null) {
			if (line.isEmpty())
				continue;
			if (Character.isDigit(line.charAt(0))) {
				
				double mean = Double.parseDouble(line);
				
				if(mean >max_mean)
				{
					max_mean = mean;
				}
				else if(mean < min_mean)
				{
					min_mean = mean;
				}
				
			}
		}
	}

}
