import java.util.*;
import java.io.*;



class cslpProgram {

	public static int lorryVolume;
	public static int lorryMaxLoad;
	public static float binServiceTime;
	public static float binVolume;
	public static float disposalDistrRate;
	public static int disposalDistrShape;
	public static float bagVolume;
	public static float bagWeightMin;
	public static float bagWeightMax;
	public static int noAreas;
	public static ArrayList<areaMapMatrix> areaMatricesArray = new ArrayList<areaMapMatrix>();
	public static float stopTime;
	public static float warmUpTime;

	public static void main(String args[]) {
		parseInput(args);
		
		for (int i=0; i < areaMatricesArray.size(); i++) {
			System.out.println(areaMatricesArray.get(i).toString());
		}
	}

	public static void parseInput(String args[]) {
		// Open file
		readfile r = new readfile();
		r.openFile(args[0]);
		ArrayList<String> input = r.readFile();
		r.closeFile();

		for (int i = 0; i < input.size(); i++) {
			if (input.get(i).equals("lorryVolume")) {	
				lorryVolume = Integer.parseInt(input.get(i+1));
			} else if (input.get(i).equals("lorryMaxLoad")) {
				lorryMaxLoad = Integer.parseInt(input.get(i+1));
			} else if (input.get(i).equals("binServiceTime")) {
				binServiceTime = Float.parseFloat(input.get(i+1));
			} else if (input.get(i).equals("binVolume")) {
				binVolume = Float.parseFloat(input.get(i+1));
			} else if (input.get(i).equals("disposalDistrRate")) {
				disposalDistrRate = Float.parseFloat(input.get(i+1));
			} else if (input.get(i).equals("disposalDistrShape")) {
				disposalDistrShape = Integer.parseInt(input.get(i+1));
			} else if (input.get(i).equals("bagVolume")) {
				bagVolume = Float.parseFloat(input.get(i+1));
			} else if (input.get(i).equals("bagWeightMin")) {
				bagWeightMin = Float.parseFloat(input.get(i+1));
			} else if (input.get(i).equals("bagWeightMax")) {
				bagWeightMax = Float.parseFloat(input.get(i+1));
			}
			// Store Area Maps \\
			  else if (input.get(i).equals("areaIdx")) {
			  	// New Area discovered, instantiate areaMapMatrix
			  	int areaIdx 		= Integer.parseInt(input.get(i+1));
			  	float serviceFreq 	= Float.parseFloat(input.get(i+3));
			  	float thresholdVal 	= Float.parseFloat(input.get(i+5));
			  	int noBins			= Integer.parseInt(input.get(i+7));
			  	int[][] roadMatrix 	= new int[noBins+1][noBins+1];
			  	// Move down input until you reach next roadsLayout
			  	while (!input.get(i).equals("roadsLayout")) {
			  		i++;
			  	}
			  	// Populate roadMatrix \\
			  	if (input.get(i).equals("roadsLayout")) {
			  		i++;
			  		for (int j=0; j < (noBins+1); j++) {
			  			for (int k=0; k < (noBins+1); k++) {
			  				roadMatrix[j][k] = Integer.parseInt(input.get(i+j+k));
			  			}
			  			i = i + noBins;
			  		}
			  	}
			  	// Save area map to array \\
			  	areaMapMatrix currentAreaMatrix = new areaMapMatrix(areaIdx, serviceFreq, thresholdVal, noBins, roadMatrix);
			  	areaMatricesArray.add(currentAreaMatrix);
			} 
			// Store final two variables \\
			  else if (input.get(i).equals("stopTime")) {
				stopTime 	= Float.parseFloat(input.get(i+1));
			} else if (input.get(i).equals("warmUpTime")) {
				warmUpTime 	= Float.parseFloat(input.get(i+1));
			}
		}

	}
}