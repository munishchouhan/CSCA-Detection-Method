package edu.utp.manu;

import java.io.IOException;
import java.util.Iterator;

public class StartDetection {
	/*public static void main(String[] args) throws IOException {
		BloomFilter bf = new LoadSignatures().load("data/means.dat");
		
		RealTimeMonitoring RTM = new RealTimeMonitoring();
		
		int hit =0;
		int miss =0;
		
		double mean = RTM.GenerateMean();
		if(bf.contains(mean)){
			hit++;
	}
	else{
		if(mean >=0.00230 && mean <= 0.0031)
		{
			hit++;
		}
		miss++;
	}
		
		System.out.println("hit : "+hit+" miss "+miss);
}*/

	public static void main(String[] args) throws IOException {
		
		BloomFilter bf = new LoadSignatures().load("data/means.dat");
		Test_RealTimeMonitoring TRTM = new Test_RealTimeMonitoring();
		
		int hit =0;
		int miss =0;
		long time = System.currentTimeMillis();
		Iterator<Double> mean_iterator = TRTM.GenerateMean().iterator();
		while(mean_iterator.hasNext()){
			double mean = mean_iterator.next();
		if(bf.contains(mean)){
			hit++;
	}
	else{
		if(mean >=0.00230 && mean <= 0.0031)
		{
			hit++;
		}
		else{
		miss++;
		}
	}
		}
		System.out.println("hit : "+hit+" miss "+miss);
		time = System.currentTimeMillis() - time;
		System.out.println(time);
	}
}
