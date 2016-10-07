import java.util.ArrayList;

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
		// Search Input File for Data \\
		for (int i = 0; i < args.length; i++) {
			// Store Simple Variables \\
			if (args[i].equals("lorryVolume")) {	
				lorryVolume = Integer.parseInt(args[i+1]);
			} else if (args[i].equals("lorryMaxLoad")) {
				lorryMaxLoad = Integer.parseInt(args[i+1]);
			} else if (args[i].equals("binServiceTime")) {
				binServiceTime = Float.parseFloat(args[i+1]);
			} else if (args[i].equals("binVolume")) {
				binVolume = Float.parseFloat(args[i+1]);
			} else if (args[i].equals("disposalDistrRate")) {
				disposalDistrRate = Float.parseFloat(args[i+1]);
			} else if (args[i].equals("disposalDistrShape")) {
				disposalDistrShape = Integer.parseInt(args[i+1]);
			} else if (args[i].equals("bagVolume")) {
				bagVolume = Float.parseFloat(args[i+1]);
			} else if (args[i].equals("bagWeightMin")) {
				bagWeightMin = Float.parseFloat(args[i+1]);
			} else if (args[i].equals("bagWeightMax")) {
				bagWeightMax = Float.parseFloat(args[i+1]);
			}
			// Store Area Maps \\
			  else if (args[i].equals("areaIdx")) {
			  	// New Area discovered, instantiate areaMapMatrix
			  	int areaIdx 		= Integer.parseInt(args[i+1]);
			  	float serviceFreq 	= Float.parseFloat(args[i+3]);
			  	float thresholdVal 	= Float.parseFloat(args[i+5]);
			  	int noBins			= Integer.parseInt(args[i+7]);
			  	int[][] roadMatrix 	= new int[noBins+1][noBins+1];
			  	// Move down input until you reach next roadsLayout
			  	while (!args[i].equals("roadsLayout")) {
			  		i++;
			  	}
			  	// Populate roadMatrix \\
			  	if (args[i].equals("roadsLayout")) {
			  		i++;
			  		for (int j=0; j < (noBins+1); j++) {
			  			for (int k=0; k < (noBins+1); k++) {
			  				roadMatrix[j][k] = Integer.parseInt(args[i+j+k]);
			  			}
			  			i = i + noBins;
			  		}
			  	}
			  	// Save area map to array \\
			  	areaMapMatrix currentAreaMatrix = new areaMapMatrix(areaIdx, serviceFreq, thresholdVal, noBins, roadMatrix);
			  	areaMatricesArray.add(currentAreaMatrix);
			} 

			// Store final two variables \\
			  else if (args[i].equals("stopTime")) {
				stopTime 	= Float.parseFloat(args[i+1]);
			} else if (args[i].equals("warmUpTime")) {
				warmUpTime 	= Float.parseFloat(args[i+1]);
			}
		}
	}



}