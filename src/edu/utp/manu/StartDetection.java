package edu.utp.manu;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

import javax.print.attribute.standard.MediaName;

public class StartDetection {
	
	
	double tdr =0;
	static long time=0;
	
	public static void main(String[] args) throws IOException {
		StartDetection startdetection = new StartDetection();
		double avg_exe_time =0;
		for (int i=0;i<100;i++)
		{
		startdetection.startdetect();
		avg_exe_time +=time;
		}
		
		avg_exe_time = avg_exe_time/100;
		
		System.out.println("Average execution time: "+avg_exe_time);
	}
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

	public void startdetect() throws IOException {
		
		BloomFilter bf = new LoadSignatures().load("data/means.dat");
		Test_RealTimeMonitoring TRTM = new Test_RealTimeMonitoring();
		MeanLimits meanlimits = new MeanLimits("data/means.dat");
		SDHelper sdh = new SDHelper();
		
		int hit =0;
		int miss =0;
		time = System.currentTimeMillis();
		Iterator<Double> mean_iterator = TRTM.GenerateMean().iterator();
		while(mean_iterator.hasNext()){
			double mean = mean_iterator.next();
		if(bf.contains(mean)){
			hit++;
			System.out.println(mean);
	}
		
	else{
		/*BigDecimal t_mean = new BigDecimal(mean);
		BigDecimal min_mean  = new BigDecimal("0.00150");
		BigDecimal max_mean  = new BigDecimal("0.00385");
		System.out.println(t_mean);
		int min_res = t_mean.compareTo(min_mean);
		int max_res = t_mean.compareTo(max_mean);*/
		
		//checking the min and max limits
		
		if(mean >= meanlimits.min_mean && mean <= meanlimits.max_mean)
		{
			hit++;
			//standard deviation to verify the detection result
			
			sdh.getSDofCurrentFile("data/real_time_data/csca_data_2500_6000.dat");			
		}
		else{
		miss++;
		}
	}
		}
		//true detection rate
		tdr = (double)hit/(hit+miss);
		System.out.println("hit : "+hit+" miss "+miss+" \ntrue detection rate : "+tdr*100 +"%");
		time = System.currentTimeMillis() - time;
		System.out.println("execution time: "+time);
	}
}
