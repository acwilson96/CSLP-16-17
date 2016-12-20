import java.util.*;
import java.lang.*;

class Simulator {

	// ArrayList of input file, where each String in the ArrayList is a line of input file, excluding commented lines.
	public static ArrayList<String> input;

	// Variables that should be defined in input file
	 public static int lorryVolume;
	 public static int lorryMaxLoad;
	 public static int binServiceTime;
	 public static float binVolume;
	 public static float disposalDistrRate;
	 public static int disposalDistrShape;
	 public static float bagVolume;
	 public static float bagWeightMin;
	 public static float bagWeightMax;
	 public static int noAreas;
	 public static float stopTime;
	 public static float warmUpTime;
	 public static float globaLserviceFreq;
	 private ArrayList<String> validTokens = new ArrayList<String>();

	// Valid variable declarations
	 private boolean lorryVolumeValid;
	 private boolean lorryMaxLoadValid;
	 private boolean binServiceTimeValid;
	 private boolean binVolumeValid;
	 private boolean disposalDistrRateValid;
	 private boolean disposalDistrShapeValid;
	 private boolean bagVolumeValid;
	 private boolean bagWeightMinValid;
	 private boolean bagWeightMaxValid;
	 private boolean noAreasValid;
	 private boolean stopTimeValid;
	 private boolean warmUpTimeValid;
	 private boolean areaMapsValid;
	 private boolean globaLserviceFreqFOUND;

	// Variable occurences declarations
 	 private int lorryVolumeOccur;
	 private int lorryMaxLoadOccur;
	 private int binServiceTimeOccur;
	 private int binVolumeOccur;
	 private int disposalDistrRateOccur;
	 private int disposalDistrShapeOccur;
	 private int bagVolumeOccur;
	 private int bagWeightMinOccur;
	 private int bagWeightMaxOccur;
	 private int noAreasOccur;
	 private int stopTimeOccur;
	 private int warmUpTimeOccur;

	// Variables to track events and areas
	 public static int currTime;
	 public boolean eventsExist;
	 public ArrayList<Event> nextPossEvents = new ArrayList<Event>();
	 public static ArrayList<areaMapMatrix> areaMatricesArray = new ArrayList<areaMapMatrix>();

	// Experiment support.	
	private boolean output;
	public boolean containsExperiment;
	public int numberExperiments;
	public int numberOfDistrRate;
	public int numberOfDistrShape;
	public int numberOfServiceFreq;
	public ArrayList<Float> disposalDistrRateExp = new ArrayList<Float>();
	public ArrayList<Integer> disposalDistrShapeExp = new ArrayList<Integer>();
	public ArrayList<Float> serviceFreqExp = new ArrayList<Float>();


	public Simulator(String args[]) {
		// Initialise some variables required to run simulator
		this.currTime = 0;
		eventsExist = true;
		// Create a new readfile object, and use said object to create ArrayList of input file
		readfile s = new readfile();
		input = s.readFile(args[0]);
	}


	// Input parsing & validation
	public void initValidVars() {
		// ArrayList of valid Tokens
		 validTokens.add("lorryVolume");
		 validTokens.add("lorryMaxLoad");
		 validTokens.add("binServiceTime");
		 validTokens.add("binVolume");
	 	 validTokens.add("disposalDistrRate");
		 validTokens.add("disposalDistrShape");
		 validTokens.add("bagVolume");
		 validTokens.add("bagWeightMin");
		 validTokens.add("bagWeightMax");
		 validTokens.add("noAreas");
		 validTokens.add("stopTime");
		 validTokens.add("warmUpTime");

		 validTokens.add("areaIdx");
		 validTokens.add("serviceFreq");
		 validTokens.add("thresholdVal");
		 validTokens.add("noBins");
		 validTokens.add("roadsLayout");
		// Variables have stored a value
		 globaLserviceFreqFOUND		= false;
		 lorryVolumeValid 			= false;
		 lorryMaxLoadValid	 		= false;
		 binServiceTimeValid 		= false;
		 binVolumeValid 			= false;
		 disposalDistrRateValid 	= false;
		 disposalDistrShapeValid	= false;
		 bagVolumeValid 			= false;
		 bagWeightMinValid 			= false;
		 bagWeightMaxValid 			= false;
		 noAreasValid 				= false;
		 stopTimeValid 				= false;
		 warmUpTimeValid 			= false;
		 areaMapsValid 				= true;
		// Variable occurences
		 lorryVolumeOccur		= 0;
		 lorryMaxLoadOccur		= 0;
		 binVolumeOccur			= 0;
		 binServiceTimeOccur	= 0;
		 binVolumeOccur			= 0;
		 disposalDistrRateOccur	= 0;
		 disposalDistrShapeOccur= 0;
		 bagVolumeOccur			= 0;
		 bagWeightMinOccur		= 0;
		 bagWeightMaxOccur		= 0;
		 noAreasOccur			= 0;
		 stopTimeOccur			= 0;
		 warmUpTimeOccur		= 0;

		 numberExperiments		= 0;
		 numberOfDistrRate		= 0;
		 numberOfDistrShape		= 0;
		 numberOfServiceFreq	= 0;
	}

	public void parseInput() {
		initValidVars();
		// Loop through every line in the input file.
		for (int i = 0; i < input.size(); i++) {			
			// If the first word in the line = a keyword such as a variable, store the next value
			// Standard Variables ||
			if (getWord(input.get(i), 0).equals("lorryVolume")) {
				try {
					lorryVolume = Integer.parseInt(getWord(input.get(i), 1));
					lorryVolumeOccur++;
					lorryVolumeValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + getWord(input.get(i), 1) + " is not a valid type for lorryVolume.		lorryVolume is of type Integer");
					lorryVolumeValid = false;
				}
			} else if (getWord(input.get(i), 0).equals("lorryMaxLoad")) {
				try {
					lorryMaxLoad = Integer.parseInt(getWord(input.get(i), 1));
					lorryMaxLoadOccur++;
					lorryMaxLoadValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + getWord(input.get(i), 1) + " is not a valid type for lorryMaxLoad.		lorryMaxLoad is of type Integer");
					lorryMaxLoadValid = false;
				}
			} else if (getWord(input.get(i), 0).equals("binServiceTime")) {
				try {
					binServiceTime = Integer.parseInt(getWord(input.get(i), 1));
					binServiceTimeOccur++;
					binServiceTimeValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + getWord(input.get(i), 1) + " is not a valid type for binServiceTime.		binServiceTime is of type Integer");
					binServiceTimeValid = false;
				}
			} else if (getWord(input.get(i), 0).equals("binVolume")) {
				try {
					binVolume = Float.parseFloat(getWord(input.get(i), 1));
					binVolumeOccur++;
					binVolumeValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + getWord(input.get(i), 1) + " is not a valid type for binVolume.		binVolume is of type Float");
					binVolumeValid = false;
				}
			} else if (getWord(input.get(i), 0).equals("disposalDistrRate")) {
				try {
					if (getWord(input.get(i), 1).equals("experiment")) {
						this.numberOfDistrRate = countWords(input.get(i));
						for (int x = 2; x < this.numberOfDistrRate; x++) {
							Float val = Float.parseFloat(getWord(input.get(i), x));
							disposalDistrRateExp.add(val);

						}
						disposalDistrRate = disposalDistrRateExp.get(0);
						disposalDistrRateOccur++;
						disposalDistrRateValid = true;
						this.containsExperiment = true;
						this.numberExperiments++;
					} else {
						disposalDistrRate = Float.parseFloat(getWord(input.get(i), 1));
						disposalDistrRateExp.add(disposalDistrRate);
						disposalDistrRateOccur++;
						disposalDistrRateValid = true;
					}
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + getWord(input.get(i), 1) + " is not a valid type for disposalDistrRate.		disposalDistrRate is of type Float");
					disposalDistrRateValid = false;
				}
			} else if (getWord(input.get(i), 0).equals("disposalDistrShape")) {
				try {
					if (getWord(input.get(i), 1).equals("experiment")) {
						this.numberOfDistrShape = countWords(input.get(i));
						for (int x = 2; x < this.numberOfDistrShape; x++) {
							int val = Integer.parseInt(getWord(input.get(i), x));
							disposalDistrShapeExp.add(val);
						}
						disposalDistrShape = disposalDistrShapeExp.get(0);
						disposalDistrShapeOccur++;
						disposalDistrShapeValid = true;
						this.containsExperiment = true;
						this.numberExperiments++;
					} else {
						disposalDistrShape = Integer.parseInt(getWord(input.get(i), 1));
						disposalDistrShapeExp.add(disposalDistrShape);
						disposalDistrShapeOccur++;
						disposalDistrShapeValid = true;
					}
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + getWord(input.get(i), 1) + " is not a valid type for disposalDistrShape.		disposalDistrShape is of type Integer");
					disposalDistrShapeValid = false;
				}
			} else if (getWord(input.get(i), 0).equals("serviceFreq") && getWord(input.get(i), 1).equals("experiment")) {
				try {
					this.numberOfServiceFreq = countWords(input.get(i));
					for (int x = 2; x < this.numberOfServiceFreq; x++) {
						Float val = Float.parseFloat(getWord(input.get(i), x));
						serviceFreqExp.add(val);
					}
					globaLserviceFreq = serviceFreqExp.get(0);
					globaLserviceFreqFOUND = true;
					this.containsExperiment = true;
					this.numberExperiments++;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: serviceFreq found for experiment purposes, but one or more of its values are not of type Float");
				}
			} else if (getWord(input.get(i), 0).equals("bagVolume")) {
				try {
					bagVolume = Float.parseFloat(getWord(input.get(i), 1));
					bagVolumeOccur++;
					bagVolumeValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + getWord(input.get(i), 1) + " is not a valid type for bagVolume.			bagVolume is of type Float");
					bagVolumeValid = false;
				}
			} else if (getWord(input.get(i), 0).equals("bagWeightMin")) {
				try {
					bagWeightMin = Float.parseFloat(getWord(input.get(i), 1));
					bagWeightMinOccur++;
					bagWeightMinValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + getWord(input.get(i), 1) + " is not a valid type for bagWeightMin.		bagWeightMin is of type Float");
					bagWeightMinValid = false;
				}
			} else if (getWord(input.get(i), 0).equals("bagWeightMax")) {
				try {
					bagWeightMax = Float.parseFloat(getWord(input.get(i), 1));
					bagWeightMaxOccur++;
					bagWeightMaxValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + getWord(input.get(i), 1) + " is not a valid type for bagWeightMax.		bagWeightMax is of type Float");
					bagWeightMaxValid = false;
				}
			} else if (getWord(input.get(i), 0).equals("noAreas")) {
				try {
					noAreas = Integer.parseInt(getWord(input.get(i), 1));
					noAreasOccur++;
					noAreasValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + getWord(input.get(i), 1) + " is not a valid type for noAreas.			noAreas is of type Integer");
					noAreasValid = false;
				}
			} else if (getWord(input.get(i), 0).equals("stopTime")) {
			  	try {
			  		stopTime 	= Float.parseFloat(getWord(input.get(i), 1)) * 60 * 60;
					stopTimeOccur++;
					stopTimeValid = true;
			  	}
				catch (NumberFormatException e) {
					System.err.println("Error: " + getWord(input.get(i), 1) + " is not a valid type for stopTime.			stopTime is of type Float");
					stopTimeValid = false;
				}
			} else if (getWord(input.get(i), 0).equals("warmUpTime")) {
				try {
					warmUpTime 	= Float.parseFloat(getWord(input.get(i), 1))  * 60 * 60;
					warmUpTimeOccur++;
					warmUpTimeValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + getWord(input.get(i), 1) + " is not a valid type for warmUpTime.		warmUpTime is of type Float");
					warmUpTimeValid = false;
				}
			}
			// Store Area Maps    || 
			// If the keyword "areaIdx" is found, then we are at the area maps part of the input file
			 else if (getWord(input.get(i), 0).equals("areaIdx")) {
			 	// Initialise current area's variables
				int areaIdx			= 0;
				float serviceFreq	= 0;
				float thresholdVal	= 0;
				int noBins 			= 0;
				int validCount		= 0;
				// areaIdx      ||
			  	 try {
			  		areaIdx 		= Integer.parseInt(getWord(input.get(i), 1));
			  		validCount++;
			  	 }
			  	 catch (NumberFormatException e) {
			  		System.err.println("Error: " + getWord(input.get(i), 1) + " is not a valid type for areaIdx. areaIdx is of type Integer");

			  	 }
			  	// serviceFreq  ||
			  	 try {
			  		serviceFreq 	= Float.parseFloat(getWord(input.get(i), 3));
			  		serviceFreqExp.add(serviceFreq);
			  		validCount++;
			  	 }
			  	 catch (NumberFormatException e) {
			  		System.err.println("Error: " + getWord(input.get(i), 3) + " is not a valid type for serviceFreq. serviceFreq is of type Float");
			  	 }
			  	// thresholdVal ||
			  	 try {
			  		thresholdVal 	= Float.parseFloat(getWord(input.get(i), 5));
			  		validCount++;
			  	 }
			  	 catch (NumberFormatException e) {
			  		System.err.println("Error: " + getWord(input.get(i), 5) + " is not a valid type for thresholdVal. thresholdVal is of type Float");
			  	 }
			  	// noBins       ||
			  	 try {
			  		noBins			= Integer.parseInt(getWord(input.get(i), 7));
			  		validCount++;
			  	 }
			  	 catch (NumberFormatException e) {
			  		System.err.println("Error: " + getWord(input.get(i), 7) + " is not a valid type for noBins. noBins is of type Integer");
			  	 }
			  	// If areaIdx, serviceFreq, thresholdVal, noBins were succesfully parsed:
			  	if (validCount == 4){
			  		int[][] roadMatrix 	= new int[noBins+1][noBins+1];
			  		// Move down input until you reach next roadsLayout
			  		while (!getWord(input.get(i), 0).equals("roadsLayout")) {
			  			i++;
			  		}
			  		// Populate roadMatrix \\
			  		// If statement checks to see if there is a mismatch in size of input, and size of array. This avoids exceptions.
			  		if (getWord(input.get(i), 0).equals("roadsLayout") && ((i + 1 + noBins + 1)<=input.size())) {
			  			i++;
			  			// Loop through the next (noBins + 1) lines, where the integer values for the roadsLayout should be
			  			for (int j=0; j < (noBins+1); j++) {
			  				for (int k=0; k < (noBins+1); k++) {
			  					try {
			  						roadMatrix[j][k] = Integer.parseInt(getWord(input.get(i + j), k));
									this.areaMapsValid = true;
			  					}
			  					catch (NumberFormatException e) {
			  						System.err.println("Error: areaIdx " + areaIdx + "'s roadsLayout could not be parsed. Either an element is not of type Integer, or mismatch in noBins and size of roadsLayout matrix");
			  						this.areaMapsValid = false;
			  					}
			  				}
			  			}
			  		} else {
			  			System.err.println("Error: areaIdx " + areaIdx + "'s roadsLayout dimensions are smaller than noBins");
			  		}
			  		// Save area map to array \\
			  		// If experimental serviceFreq was found, then use that value.
			  		if (globaLserviceFreqFOUND) {
			  			areaMapMatrix currentAreaMatrix = new areaMapMatrix(currTime, areaIdx, globaLserviceFreq, thresholdVal, noBins, roadMatrix, binVolume, disposalDistrRate, disposalDistrShape, bagVolume, bagWeightMin, bagWeightMax, lorryVolume, lorryMaxLoad, binServiceTime, warmUpTime, stopTime);
			  			areaMatricesArray.add(currentAreaMatrix);
			  		} else {
				  		areaMapMatrix currentAreaMatrix = new areaMapMatrix(currTime, areaIdx, serviceFreq, thresholdVal, noBins, roadMatrix, binVolume, disposalDistrRate, disposalDistrShape, bagVolume, bagWeightMin, bagWeightMax, lorryVolume, lorryMaxLoad, binServiceTime, warmUpTime, stopTime);
				  		areaMatricesArray.add(currentAreaMatrix);
			  		}
			  	} else {
			 		System.err.println("Error: Area Map could not be created to due one or more of the errors above");
			 }
		   	}
		   	
		}	
	}
	
	public boolean checkInput() {
		boolean output = true;
		// Check every required variable has a value
		 if (!lorryVolumeValid) {
			System.err.println("Error: Invalid input file provided. Parameter 'lorryVolume'		missing. The simulation will terminate.");
			output = false;
		 }
		 if (!lorryMaxLoadValid) {
			System.err.println("Error: Invalid input file provided. Parameter 'lorryMaxLoad'		missing. The simulation will terminate.");
			output = false;
		 }
		 if (!binServiceTimeValid) {
			System.err.println("Error: Invalid input file provided. Parameter 'binServiceTime'		missing. The simulation will terminate.");
			output = false;
		 }
		 if (!binVolumeValid) {
			System.err.println("Error: Invalid input file provided. Parameter 'binVolume'		missing. The simulation will terminate.");
			output = false;
		 }
		 if (!disposalDistrRateValid) {
			System.err.println("Error: Invalid input file provided. Parameter 'disposalDistrRate'	missing. The simulation will terminate.");
			output = false;
		 }
		 if (!disposalDistrShapeValid) {
			System.err.println("Error: Invalid input file provided. Parameter 'disposalDistrShape'	missing. The simulation will terminate.");
			output = false;
		 }
		 if (!bagVolumeValid) {
			System.err.println("Error: Invalid input file provided. Parameter 'bagVolume' 		missing. The simulation will terminate.");
			output = false;
		 }
		 if (!bagWeightMinValid) {
			System.err.println("Error: Invalid input file provided. Parameter 'bagWeightMin' 		missing. The simulation will terminate.");
			output = false;
		 }
		 if (!bagWeightMaxValid) {
			System.err.println("Error: Invalid input file provided. Parameter 'bagWeightMax' 		missing. The simulation will terminate.");
			output = false;
		 }
		 if (!noAreasValid) {
			System.err.println("Error: Invalid input file provided. Parameter 'noAreas' 		missing. The simulation will terminate.");
			output = false;
		 }
		 if (!stopTimeValid) {
			System.err.println("Error: Invalid input file provided. Parameter 'stopTime'		missing. The simulation will terminate.");
			output = false;
		 }
		 if (!warmUpTimeValid) {
			System.err.println("Error: Invalid input file provided. Parameter 'warmUpTime' 		missing. The simulation will terminate.");
			output = false;
		 }
		 if (!areaMapsValid) {
		 	System.err.println();
			System.err.println("Error: Invalid input file provided. Please check the area maps are valid.");
			output = false;
		 }
		// Check for valid values
		// No negatives or 0's
		 if (lorryVolume <= 0) {
		 	System.err.println("Error: lorryVolume cannot be 0 m^3 or negative");
		 	output = false;
		 }
		 if (lorryMaxLoad <= 0) {
		 	System.err.println("Error: lorryMaxLoad cannot be 0 kg or negative");
		 	output = false;
		 }
		 if (binServiceTime <= 0) {
		 	System.err.println("Error: binServiceTime cannot be 0 seconds or negative");
		 	output = false;
		 }
		 if (disposalDistrRate <= 0) {
		 	System.err.println("Error: disposalDistrRate cannot be 0 or less");
		 	output = false;
		 }
		 if (disposalDistrShape <= 0) {
		 	System.err.println("Error: disposalDistrShape cannot be 0 or negative");
		 	output = false;
		 }
		 if (bagVolume <= 0) {
		 	System.err.println("Error: bagVolume cannot be 0 m^3 or negative");
		 	output = false;
		 }
		 if (bagWeightMin <= 0) {
		 	System.err.println("Error: bagWeightMin cannot be 0 kg or negative");
		 	output = false;
		 }
		 if (bagWeightMax <= 0) {
		 	System.err.println("Error: bagWeightMax cannot be 0 kg or negative");
		 	output = false;
		 }
		 if (noAreas <= 0) {
		 	System.err.println("Error: noAreas cannot be 0 or negative");
		 	output = false;
		 }
		// Logical errors
		 if (bagWeightMax < bagWeightMin) {
		 	System.err.println("Error: bagWeightMax cannot be less than bagWeightMin");
		 	output = false;
		 }
		// Check for rogue parameters, which will exclude lines beginning with numbers, so that roadLayouts aren't flagged as Errors
		 for (int i = 0; i < input.size(); i++) {
		 	String nextToken = getWord(input.get(i), 0);
		 	if (!validTokens.contains(nextToken) && !isNumerical(nextToken)){
		 		System.err.println("Error: Unknown token found: " + nextToken + ". Simulation will terminate." + input.get(i));
		 		output = false;
		 	}
		 }
		// Check area maps have no 0 entries outside of diagonal
		 //Loop through all areas
		 for (int i = 0; i < areaMatricesArray.size(); i++) {
		 	int arrayLength = areaMatricesArray.get(i).noBins + 1;
		 	//Loop through the roadLayour matrix of that area
		 	for (int j = 0; j < arrayLength; j++) {
		 		for (int k = 0; k < arrayLength; k++) {
		 			if (j!=k && areaMatricesArray.get(i).roadsLayout[j][k] == 0) {
		 				System.err.println("Error: Area " + i + " has a weight of 0 in the roadLayout matrix that is not on the diagnoal at: [" + j + "," + k +"].");
		 				output = false;
		 			}
		 		}
		 	}
		 }
		// Check areaIdx run in correct order & correct number
		 int[] areaIdxOrder = new int[this.noAreas];
		 int count = 0;
		 int numberAreaIdx = 0;
		 for (int i = 0; i < input.size(); i++) {
		 	if (getWord(input.get(i), 0).equals("areaIdx")) {
		 		numberAreaIdx++;
		 		try {
		 			areaIdxOrder[count] = Integer.parseInt(getWord(input.get(i), 1));
		 			count++;
		 		}
		 		catch (Exception e) {
		 			output = false;
		 		}
		 	}
		 }
		 if (numberAreaIdx != noAreas) {
		 	System.err.println("Error: Number of areaIdx does not match noAreas : Number of areaIdx = " + numberAreaIdx + ". noAreas = " + noAreas);
		 	output = false;
		 }
		return output;
	}

	public void inputWarnings() {
		// Warn if more than one instance of a variable exists
		 if (lorryVolumeOccur > 1) {
			System.out.println("Warning: more than one instance of lorryVolume was detected. The one used will be the one that appears latest in the input.");
		 }
		 if (lorryMaxLoadOccur > 1) {
			System.out.println("Warning: more than one instance of lorryMaxLoad was detected. The one used will be the one that appears latest in the input.");
		 }
		 if (binServiceTimeOccur > 1) {
			System.out.println("Warning: more than one instance of binServiceTime was detected. The one used will be the one that appears latest in the input.");
		 }
		 if (binVolumeOccur > 1) {
		 	System.out.println("Warning: more than one instance of binVolume was detected. The one used will be the one that appears latest in the input.");
		 }
		 if (disposalDistrRateOccur > 1) {
			System.out.println("Warning: more than one instance of disposalDistrRate was detected. The one used will be the one that appears latest in the input.");
		 }
		 if (disposalDistrShapeOccur > 1) {
			System.out.println("Warning: more than one instance of disposalDistrShape was detected. The one used will be the one that appears latest in the input.");
		 }
		 if (bagVolumeOccur > 1) {
			System.out.println("Warning: more than one instance of bagVolume was detected. The one used will be the one that appears latest in the input.");
		 }
		 if (bagWeightMinOccur > 1) {
			System.out.println("Warning: more than one instance of bagWeightMin was detected. The one used will be the one that appears latest in the input.");
		 }
		 if (bagWeightMaxOccur > 1) {
			System.out.println("Warning: more than one instance of bagWeightMax was detected. The one used will be the one that appears latest in the input.");
		 }
		 if (noAreasOccur > 1) {
			System.out.println("Warning: more than one instance of noAreas was detected. The one used will be the one that appears latest in the input.");
		 }
		 if (stopTimeOccur > 1) {
			System.out.println("Warning: more than one instance of stopTime was detected. The one used will be the one that appears latest in the input.");
		 }
		 if (warmUpTimeOccur > 1) {
			System.out.println("Warning: more than one instance of warmUpTime was detected. The one used will be the one that appears latest in the input.");
		 }
		// Service rate > disposal rate
		 for (int i= 0; i < noAreas; i++) {
		 	if (areaMatricesArray.get(i).serviceFreq > disposalDistrRate) {
		 		System.out.println("Warning: service frequency of area " + i + " is greater than the disposal rate");
		 	}
		 }
		if (this.lorryVolume < this.binVolume) {
			System.out.println("Warning: lorryVolume is less than binVolume");
		}
		if (this.warmUpTime > this.stopTime){
			System.out.println("Warning: warmUpTime is more than stopTime");
		}
		// Check for multiple arguments on a line
		 for (int i = 0; i < input.size(); i++) {
		 	String nextLine = input.get(i);
		 	String[] currLine = getWords(nextLine);
		 	if (currLine.length > 2) {
				for (int x = 2; x < currLine.length; x++) {
		 			if (!isNumerical(currLine[0])) {
		 				if (!currLine[0].equals("areaIdx")) {
		 					if (!currLine[1].equals("experiment")) {
		 						System.err.println("Warning: Unknown token found in same line as variable " + currLine[0] + " : " + currLine[x]);
		 					}
		 				}
		 			}
		 		}
			}
		 }
		// Print a new line to seperate warnings from actual simulation.
		System.out.println();
	}

	// Experimentation functions.
	public void updateExperiment(float nextDistRate, int nextDistrShape, float nextServiceFreq, int expNumb) {
		this.currTime = 0;
		this.disposalDistrRate 	= nextDistRate;
		this.disposalDistrShape = nextDistrShape;
		for (int i = 0; i < areaMatricesArray.size(); i++) {
			areaMatricesArray.get(i).updateExperiment(nextDistRate, nextDistrShape, nextServiceFreq);
		}
		String output = "Experiment #" + expNumb + ":";
		if (disposalDistrRateExp.size() > 1) {
			output = output + " disposalDistrRate " + nextDistRate + " ";
		}
		if (disposalDistrShapeExp.size()  > 1) {
			output = output + " disposalDistrShape " + nextDistrShape + " ";
		}
		if (serviceFreqExp.size() > 1) {
			output = output + " serviceFreq " + nextServiceFreq;
		}
		System.out.println(output);
	}


	// Simulation functions
	public void simOutline(boolean outputEnabled) {
		while (this.currTime < this.stopTime) {
			determineSetOfEvents();
			// Determine if events exist, otherwise break the currTime < stopTime loop for efficiency
			if (eventsExist) {
				// Call chooseNextEvent() to determine the next event and then trigger it
				Event nextEvent = chooseNextEvent();
				triggerNextEvent(nextEvent, outputEnabled);
			} else { break; }
		}
		System.out.println("---");
		calculateStatistics();
		System.out.println("---");
	}

	public void determineSetOfEvents() {
		// Clear possible events so that old events don't interfere and create infinite loop of same event being triggered.
		nextPossEvents.clear();
		// Check for disposal events
		for (int i = 0; i < noAreas; i++) {
			int currNoBins = areaMatricesArray.get(i).noBins;
			// Loop through all the bins in said area.
			for (int j = 1; j <= currNoBins; j++) {
				// Store the time until each bin in that area has a bag disposed.
				int delay = getBin(i,j).timeUntilNextDisposal(currTime);
				Event nextEvent = new Event(1, i, j, delay);
				nextPossEvents.add(nextEvent);
			}
		}
		// Check for lorry events
		for (int i = 0; i < noAreas; i++) {
			int arriveDelay = getArea(i).timeUntilNextArrival(currTime);
			int departDelay = getArea(i).timeUntilNextDeparture(currTime);
			if (arriveDelay < departDelay) {
				Event nextEvent = new Event(2, i, getLorry(i).nextBin, arriveDelay);
				nextPossEvents.add(nextEvent);
			} else {
				Event nextEvent = new Event(3, i, getLorry(i).lastBin, departDelay);
				nextPossEvents.add(nextEvent);
			}
		}
		
		if (nextPossEvents.size() == 0) { eventsExist = false; }
	}

	public Event chooseNextEvent() {
		// Initialise the output Event to be the first in the list of saved events.
		int lowestDelay		 =	nextPossEvents.get(0).getDelay();
		int lowestDelayIndex =	0;
		// Loop through all possible events
		for (int i = 0; i < nextPossEvents.size(); i++) {
			// If an event in list happens before currently saved 'soonest' event, update it.
			if (nextPossEvents.get(i).getDelay() < lowestDelay) {
				lowestDelay 		= nextPossEvents.get(i).getDelay();
				lowestDelayIndex	= i;
			}
		}
		return nextPossEvents.get(lowestDelayIndex);
	}

	public void triggerNextEvent(Event nextEvent, boolean outputEnabled) {
		// Bag disposed in bin.
		if (nextEvent.eventType == 1) {
			// Update system time to catch up to this event.
			this.currTime = this.currTime + nextEvent.getDelay();
			// Extract relevant data from next event to output and update system
			int binNum				= nextEvent.binNo;
			int areaNum				= nextEvent.areaNo;
			// binX.disposeBag() returns weight of bag disposed and updates binX's volume and weight
			float bagWeight 		= getBin(areaNum, binNum).disposeBag();
			float binVol 			= (float) Math.round(getBin(areaNum, binNum).currVol * 1000) /1000;
			float binWeight			= (float) Math.round(getBin(areaNum, binNum).currWeight * 1000) /1000;
			// Output information about bag disposed event
			String bagOutput		= timeToString(this.currTime) + " -> bag weighing " + bagWeight + " kg disposed of at bin " + areaNum + "." + binNum;
			String binOutput 		= timeToString(this.currTime) + " -> load of bin " + areaNum + "." + binNum + " became " + binWeight + " kg and contents volume " + binVol + " m^3";
			if (outputEnabled) { System.out.println(bagOutput); }
			if (!getBin(areaNum, binNum).isBinOverflowed()) {
				if (outputEnabled) { System.out.println(binOutput); }
			}
			// Check to see if the bins volume exceeds a threshold or if the bin is overflowing
			if (getBin(areaNum, binNum).isThresholdExceeded() && getBin(areaNum, binNum).thresholdReported == false) {
					String threshOutput		= timeToString(this.currTime) + " -> occupancy threshold of bin " + areaNum + "." + binNum + " exceeded";
					getBin(areaNum, binNum).thresholdReported = true;
					if (outputEnabled) { System.out.println(threshOutput); }
			}
			if (getBin(areaNum, binNum).isBinOverflowed()) {
					String overflowOutput		= timeToString(this.currTime) + " -> bin " + areaNum + "." + binNum + " overflowed";
					getBin(areaNum, binNum).overflowReported = true;
					if (outputEnabled) { System.out.println(overflowOutput); }
			}
			// Update this bin's time of next disposal
			getBin(areaNum, binNum).updateDisposalInterval(currTime);
		}
		// Lorry arrived at a bin.
		if (nextEvent.eventType == 2) {
			// Update system time to catch up to this event.
			this.currTime = this.currTime + nextEvent.getDelay();
			// Extract relevant data from next event to output and update system.
			int binNum 				= nextEvent.binNo;
			int areaNum 			= nextEvent.areaNo;
			// Output info regarding arrival
			String arrivalOutput 	= timeToString(this.currTime) + " -> lorry " + areaNum + " arrived at location " + areaNum + "." + binNum;
			if (outputEnabled) { System.out.println(arrivalOutput); }
			// Update area and its lorry with arrival event.
			getArea(areaNum).lorryArrived(this.currTime, binNum);
		}
		// Lorry departed from a bin(Implies lorry emptied bin).
		if (nextEvent.eventType == 3) {
			// Update system time to catch up to this event.
			this.currTime = this.currTime + nextEvent.getDelay();
			// Extract relevant data from next event to output and update system.
			int binNum 				= nextEvent.binNo;
			int areaNum 			= nextEvent.areaNo;
			// Update area and its lorry with arrival event.
			getArea(areaNum).lorryDeparted(this.currTime, binNum);
			float currLorryWeight 	= (float) Math.round(getLorry(areaNum).currWeight * 1000) / 1000;
			float currLorryVolume	= (float) Math.round(getLorry(areaNum).currVolume * 1000) / 1000;
			// Output info regarding arrival
			String binEmptyOutput	= timeToString(this.currTime) + " -> load of bin " + areaNum + "." + binNum + " became 0 kg and contents volume 0 m^3";
			String lorryFillOutput  = timeToString(this.currTime) + " -> load of lorry " + areaNum + " became " + currLorryWeight + " kg and contents volume " + currLorryVolume + " m^3";
			String departOutput		= timeToString(this.currTime) + " -> lorry " + areaNum + " left location " + areaNum + "." + binNum;
			if (getArea(areaNum).canDepart) {
				if (binNum == 0) {
					if (outputEnabled) { System.out.println(departOutput); }
				}else{
					if (getLorry(areaNum).didService) {
						if (outputEnabled) { System.out.println(binEmptyOutput); }
						if (outputEnabled) { System.out.println(lorryFillOutput); }
					}
					if (outputEnabled) { System.out.println(departOutput); }
				}
			}
		}
	}
	public void calculateStatistics() {
		// Calculate average trip duration.
		float totalAvg = 0;
		for (int i = 0; i < noAreas; i++) {
			float areaTripDuration	=	(float)(getArea(i).totalTripDuration);
			float areaNumberOfTrips = 	(float)(getArea(i).numberOfTrips);
			float areaAvg 			= 	areaTripDuration/areaNumberOfTrips;
			areaAvg = (float) Math.round(areaAvg *100)/100;
			totalAvg = totalAvg + areaAvg;

			int areaAvgInt 			=	(int)(areaAvg);
			System.out.println("area " + i + ": average trip duration " + timeToString(areaAvgInt));
		}
		totalAvg = totalAvg / noAreas;
		int outputTotalAvg = (int)(totalAvg);
		totalAvg = (float) Math.round(totalAvg *100)/100;
		System.out.println("overall average trip duration " + timeToString(outputTotalAvg));

		// Calculate number of trips per schedule.
		totalAvg = 0;
		for (int i = 0; i < noAreas; i++) {
			float areaTripsStarted		=	(float)(getArea(i).numberOfTripsStarted);
			float areaServicesStarted 	= 	(float)(getArea(i).servicesStarted);
			float areaAvg 				= 	areaTripsStarted/areaServicesStarted;
			areaAvg = (float) Math.round(areaAvg *100)/100;
			totalAvg = totalAvg + areaAvg;

			System.out.println("area " + i + ": average no. trips " + areaAvg);
		}
		totalAvg = totalAvg / noAreas;
		totalAvg = (float) Math.round(totalAvg *100)/100;
		System.out.println("overall average no. trips " + totalAvg);

		// Calculate trip efficiency.
		totalAvg = 0;
		for (int i = 0; i < noAreas; i++) {
			float weight 	= getArea(i).totalWeightCollected;
			float timeMins 		= (float)(getArea(i).totalTripDuration)/60;
			float areaAvg 	= weight / timeMins;
			areaAvg = (float) Math.round(areaAvg *100)/100;
			System.out.println("area " + i + ": trip efficiency " + areaAvg);
			totalAvg = totalAvg + areaAvg;
		}
		totalAvg = totalAvg / noAreas;
		totalAvg = (float) Math.round(totalAvg *100)/100;
		System.out.println("overall trip efficiency " + totalAvg);

		// Calculate average volume collected.
		totalAvg = 0;
		for (int i = 0; i < noAreas; i++) {
			float areaVolCollected 		=	(float)(getArea(i).totalVolumeCollected);
			float areaNumberOfTrips		= 	(float)(getArea(i).numberOfTrips);
			float areaAvg 				=	areaVolCollected/areaNumberOfTrips;
			areaAvg = (float) Math.round(areaAvg *100)/100;
			totalAvg = totalAvg + areaAvg;
			System.out.println("area " + i + ": average volume collected " + areaAvg);
		}
		totalAvg = totalAvg / noAreas;
		totalAvg = (float) Math.round(totalAvg *100)/100;
		System.out.println("overall average volume collected " + totalAvg);

		// Calculate average percentage of overflown bins.
		totalAvg = 0;
		for (int i = 0; i < noAreas; i++) {
			float areaNumBinsOverflown 		= 	(float)(getArea(i).binsOverflownAtStart);
			float areaNumServicesStarted	=	(float)(getArea(i).servicesStarted);
			float areaNumBins 				=	(float)(getArea(i).noBins);
			float areaAvg 					= 	(areaNumBinsOverflown / (areaNumServicesStarted * areaNumBins))*100;
			areaAvg = (float) Math.round(areaAvg *100)/100;
			totalAvg = totalAvg + areaAvg;
			System.out.println("area " + i + ": percentage of bins overflowed " + areaAvg);
		}
		totalAvg = totalAvg / noAreas;
		totalAvg = (float) Math.round(totalAvg *100)/100;
		System.out.println("overall percentage of bins overflowed " + totalAvg);
	}


	// Supporting functions
	public String timeToString(int time) { // Returns current system time as a string
		int days 		= (int) time / 86400;
		String daysS 	= String.format("%02d", days);
		int hours		= (int) (time % 86400 ) / 3600 ;
		String hoursS	= String.format("%02d", hours);
		int minutes 	= (int) ((time % 86400 ) % 3600 ) / 60 ;
		String minutesS	= String.format("%02d", minutes);
		int seconds		= (int) ((time % 86400 ) % 3600 ) % 60 ;
		String secondsS = String.format("%02d", seconds);
		String output = daysS + ":" + hoursS + ":" + minutesS + ":" + secondsS;
		return output;
	}

	public areaMapMatrix getArea(int areaNo) {
		areaMapMatrix output = areaMatricesArray.get(areaNo);
		return output;
	}

	public bin getBin(int areaNo, int binNo) { // Returns bin number binNo in area number areaNo
		bin output = getArea(areaNo).binList.get(binNo);
		return output;
	}

	public binLorry getLorry(int areaNo) {
		binLorry output = getArea(areaNo).lorry;
		return output;
	}

	public String getWord(String text, int x) { // Returns String that is word x of String text
		String[] words = text.split("\\$|\\s+");
		int size = 0;
		for (int i = 0; i <words.length; i++) {
			if (!words[i].equals("")) {
				size++;
			}
		}
		String[] output = new String[size];
		int count = 0;
		for (int i = 0; i <words.length; i++) {
			if (!words[i].equals("")) {
				output[count] = words[i];
				count++;
			}
		}
		return output[x];
	}

	public String[] getWords(String text) { // Returns String array of words in a text
		String[] words = text.split("\\$|\\s+");
		int size = 0;
		for (int i = 0; i <words.length; i++) {
			if (!words[i].equals("")) {
				size++;
			}
		}
		String[] output = new String[size];
		int count = 0;
		for (int i = 0; i <words.length; i++) {
			if (!words[i].equals("")) {
				output[count] = words[i];
				count++;
			}
		}
		return output;
	}

	public boolean isNumerical(String text) { // Determines if a String is numerical. i.e. it can only contain [-,0-9,.]
		boolean output;
		output = text.matches("-?\\d+(\\.\\d+)?");
		return output;
	}

	public static int countWords(String text) {
	    String[] words = text.split("\\$|\\s+");
		int size = 0;
		for (int i = 0; i <words.length; i++) {
			if (!words[i].equals("")) {
				size++;
			}
		}
		String[] output = new String[size];
		int count = 0;
		for (int i = 0; i <words.length; i++) {
			if (!words[i].equals("")) {
				output[count] = words[i];
				count++;
			}
		}
		return output.length;
	}

}
