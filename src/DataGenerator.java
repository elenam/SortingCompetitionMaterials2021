import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * 
 * @author Elena Machkasova
 * 
 * Data generator for the 2021 sorting competition.
 * Roughly follows Poisson distribution with 20 successes 
 * and lambda = 1.5, adjusted to range [0-1000000] 
 *
 */

public class DataGenerator {
	
	public static void main(String [] args) throws FileNotFoundException{
		int seed = 1001; // change the seed to get different data
		Random r = new Random(seed);
		
		if (args.length < 2) {
			System.out.println("Please run with two command line arguments: output file name and the number of items");
			System.exit(0);
		}

		String outFileName = args[0];
		int n = Integer.parseInt(args[1]);
		
		// split the range into buckets:
		
		int step = 10000000/20;
		
		// {0.2725317930, 0.3542913309, 0.2302893651, 0.0997920582, 0.0324324189, 0.0084324289, 0.0018270263, 0.0003393049}
		double [] probs = {0.2725317930, 0.3542913309, 0.2302893651, 0.0997920582, 0.0324324189, 0.0084324289, 0.0018270263, 0.0003393049};
		
		//System.out.println(probs.length);
		
		double sum = 0;
		for (double p : probs) {
			sum += p;
		}
		
		//System.out.println("sum = " + sum + " leftover:" + (1 - sum) + " step " + step);
		
		// cumulative p
		double [] cumulProbs = new double[19];
		
		cumulProbs[0] = probs[0];
		for (int i = 1; i < probs.length; ++i) {
			cumulProbs[i] = cumulProbs[i-1] + probs[i];
		}
		
		for (int i = probs.length; i < 19; ++i) {
			cumulProbs[i] = cumulProbs[i-1] + (1-sum)/(19 - probs.length);
		}
		
//		for(double p: cumulProbs) {
//			System.out.print(p + " ");
//		}
//		System.out.println();
		
		// generating data 
		int[] data = new int[n];
		
		for (int i = 0; i < n; ++i) {
			// Roll the dice for probability
			double prob = r.nextDouble();
			// determine the bucket
			int b; // need to handle the last bucket
			for (b = 0; b < cumulProbs.length; ++b) {
				if (cumulProbs[b] >= prob) break; // find the index of the bucket
			} // if none found, b = 20 (prob larger than the last one in cumulProbs)
			// generate a random number in that bucket:
			data[i] = b*step + r.nextInt(step); 
		}
		
		// write them out
		PrintWriter out = new PrintWriter(outFileName);
		for (int s : data) {
			out.println(s);
		}
		out.close();		
		
	}

}
