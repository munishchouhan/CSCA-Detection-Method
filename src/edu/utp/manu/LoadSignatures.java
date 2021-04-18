package edu.utp.manu;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class LoadSignatures {
	
	BloomFilter load(String filename) throws IOException
	{
		FileInputStream fstream = new FileInputStream(filename);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = "";
		BloomFilter bf = new BloomFilter(21021,5);
		
		while ((line = br.readLine()) != null) {
			if (line.isEmpty())
				continue;
			if (Character.isDigit(line.charAt(0))) {
				
				bf.add(Double.parseDouble(line));
				
			}
		}
		
		return bf;
	}
	

}
