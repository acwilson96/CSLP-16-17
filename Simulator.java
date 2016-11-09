import java.util.*;
import java.lang.*;

class Simulator {



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
	 public static ArrayList<areaMapMatrix> areaMatricesArray = new ArrayList<areaMapMatrix>();
	 public static float stopTime;
	 public static float warmUpTime;
	 public static float globaLserviceFreq;

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

	// Variables to track events
	 public static int currTime;
	 public ArrayList<Event> nextPossEvents = new ArrayList<Event>();
	 public boolean eventsExist;

	public static ArrayList<String> input;



	public Simulator(String args[]) {
		// Read input file, ArrayList input = list of words in input file.
		readfile r = new readfile();
		r.openFile(args[0]);
		input = r.readFile();
		r.closeFile();
		this.currTime = 0;
		eventsExist = true;
	}

	// Input parsing and validation
	public void parseInput() {
		initValidVars();
		// Loop through every word in the input file.
		for (int i = 0; i < input.size(); i++) {
			// If the word = a keyword such as a variable, store the next value
			// Standard Variables ||
			if (input.get(i).equals("lorryVolume")) {
				try {
					lorryVolume = Integer.parseInt(input.get(i+1));
					lorryVolumeOccur++;
					lorryVolumeValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + input.get(i+1) + " is not a valid type for lorryVolume.		lorryVolume is of type Integer");
					lorryVolumeValid = false;
				}
			} else if (input.get(i).equals("lorryMaxLoad")) {
				try {
					lorryMaxLoad = Integer.parseInt(input.get(i+1));
					lorryMaxLoadOccur++;
					lorryMaxLoadValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + input.get(i+1) + " is not a valid type for lorryMaxLoad.		lorryMaxLoad is of type Integer");
					lorryMaxLoadValid = false;
				}
			} else if (input.get(i).equals("binServiceTime")) {
				try {
					binServiceTime = Integer.parseInt(input.get(i+1));
					binServiceTimeOccur++;
					binServiceTimeValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + input.get(i+1) + " is not a valid type for binServiceTime.		binServiceTime is of type Integer");
					binServiceTimeValid = false;
				}
			} else if (input.get(i).equals("binVolume")) {
				try {
					binVolume = Float.parseFloat(input.get(i+1));
					binVolumeOccur++;
					binVolumeValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + input.get(i+1) + " is not a valid type for binVolume.		binVolume is of type Float");
					binVolumeValid = false;
				}
			} else if (input.get(i).equals("disposalDistrRate")) {
				try {
					if (input.get(i+1).equals("experiment")) {
						i++;
					}
					disposalDistrRate = Float.parseFloat(input.get(i+1));
					disposalDistrRateOccur++;
					disposalDistrRateValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + input.get(i+1) + " is not a valid type for disposalDistrRate.		disposalDistrRate is of type Float");
					disposalDistrRateValid = false;
				}
			} else if (input.get(i).equals("disposalDistrShape")) {
				try {
					if (input.get(i+1).equals("experiment")) {
						i++;
					}
					disposalDistrShape = Integer.parseInt(input.get(i+1));
					disposalDistrShapeOccur++;
					disposalDistrShapeValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + input.get(i+1) + " is not a valid type for disposalDistrShape.		disposalDistrShape is of type Integer");
					disposalDistrShapeValid = false;
				}
			} else if (input.get(i).equals("serviceFreq") && input.get(i+1).equals("experiment")) {
				i++;
				try {
					globaLserviceFreq = Float.parseFloat(input.get(i+1));
					globaLserviceFreqFOUND = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: serviceFreq found for experiment purposes, but " + input.get(i+1) + " is not of type Float");
				}
			}else if (input.get(i).equals("bagVolume")) {
				try {
					bagVolume = Float.parseFloat(input.get(i+1));
					bagVolumeOccur++;
					bagVolumeValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + input.get(i+1) + " is not a valid type for bagVolume.			bagVolume is of type Float");
					bagVolumeValid = false;
				}
			} else if (input.get(i).equals("bagWeightMin")) {
				try {
					bagWeightMin = Float.parseFloat(input.get(i+1));
					bagWeightMinOccur++;
					bagWeightMinValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + input.get(i+1) + " is not a valid type for bagWeightMin.		bagWeightMin is of type Float");
					bagWeightMinValid = false;
				}
			} else if (input.get(i).equals("bagWeightMax")) {
				try {
					bagWeightMax = Float.parseFloat(input.get(i+1));
					bagWeightMaxOccur++;
					bagWeightMaxValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + input.get(i+1) + " is not a valid type for bagWeightMax.		bagWeightMax is of type Float");
					bagWeightMaxValid = false;
				}
			} else if (input.get(i).equals("noAreas")) {
				try {
					noAreas = Integer.parseInt(input.get(i+1));
					noAreasOccur++;
					noAreasValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + input.get(i+1) + " is not a valid type for noAreas.			noAreas is of type Integer");
					noAreasValid = false;
				}
			} else if (input.get(i).equals("stopTime")) {
			  	try {
			  		stopTime 	= Float.parseFloat(input.get(i+1)) * 60 * 60;
					stopTimeOccur++;
					stopTimeValid = true;
			  	}
				catch (NumberFormatException e) {
					System.err.println("Error: " + input.get(i+1) + " is not a valid type for stopTime.			stopTime is of type Float");
					stopTimeValid = false;
				}
			} else if (input.get(i).equals("warmUpTime")) {
				try {
					warmUpTime 	= Float.parseFloat(input.get(i+1))  * 60 * 60;
					warmUpTimeOccur++;
					warmUpTimeValid = true;
				}
				catch (NumberFormatException e) {
					System.err.println("Error: " + input.get(i+1) + " is not a valid type for warmUpTime.		warmUpTime is of type Float");
					warmUpTimeValid = false;
				}
			}
			// Store Area Maps    ||
			 else if (input.get(i).equals("areaIdx")) {
				int areaIdx			= 0;
				float serviceFreq	= 0;
				float thresholdVal	= 0;
				int noBins 			= 0;
				int validCount		= 0;
				// areaIdx      ||
			  	 try {
			  		areaIdx 		= Integer.parseInt(input.get(i+1));
			  		validCount++;
			  	 }
			  	 catch (NumberFormatException e) {
			  		System.err.println("Error: " + input.get(i+1) + " is not a valid type for areaIdx. areaIdx is of type Integer");

			  	 }
			  	// serviceFreq  ||
			  	 try {
			  		serviceFreq 	= Float.parseFloat(input.get(i+3));
			  		validCount++;
			  	 }
			  	 catch (NumberFormatException e) {
			  		System.err.println("Error: " + input.get(i+3) + " is not a valid type for serviceFreq. serviceFreq is of type Float");
			  	 }
			  	// thresholdVal ||
			  	 try {
			  		thresholdVal 	= Float.parseFloat(input.get(i+5));
			  		validCount++;
			  	 }
			  	 catch (NumberFormatException e) {
			  		System.err.println("Error: " + input.get(i+5) + " is not a valid type for thresholdVal. thresholdVal is of type Float");
			  	 }
			  	// noBins       ||
			  	 try {
			  		noBins			= Integer.parseInt(input.get(i+7));
			  		validCount++;
			  	 }
			  	 catch (NumberFormatException e) {
			  		System.err.println("Error: " + input.get(i+7) + " is not a valid type for noBins. noBins is of type Integer");
			  	 }
			  	 System.err.println();
			  	if (validCount == 4){
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
			  					int ijk = i + j + k;
			  					if (i+j+k >= input.size()) {
			  						System.err.println("Error: Possible mismatch in noBins and size of areaIdx " + areaIdx + "'s roadsLayout matrix");
			  						break;
			  					} else {
			  						// Store distance
			  						 try {
			  							roadMatrix[j][k] = Integer.parseInt(input.get(i+j+k));
											this.areaMapsValid = true;
			  						 }
			  						 catch (NumberFormatException e) {
			  							System.err.println("Error: areaIdx " + areaIdx + "'s roadsLayout could not be parsed. Either an element is not of type Integer, or mismatch in noBins and size of roadsLayout matrix");
			  							this.areaMapsValid = false;
			  						 }
			  					}
			  				}
			  				i = i + noBins;
			  			}
			  		}
			  		// Save area map to array \\
			  		if (globaLserviceFreqFOUND) {
			  			areaMapMatrix currentAreaMatrix = new areaMapMatrix(currTime, areaIdx, globaLserviceFreq, thresholdVal, noBins, roadMatrix, binVolume, disposalDistrRate, disposalDistrShape, bagVolume, bagWeightMin, bagWeightMax);
			  			areaMatricesArray.add(currentAreaMatrix);
			  		} else {
			  			areaMapMatrix currentAreaMatrix = new areaMapMatrix(currTime, areaIdx, serviceFreq, thresholdVal, noBins, roadMatrix, binVolume, disposalDistrRate, disposalDistrShape, bagVolume, bagWeightMin, bagWeightMax);
			  			areaMatricesArray.add(currentAreaMatrix);
			  		}
			  	} else {
			  		System.err.println("Error: Area Map could not be created to due one or more of the errors above");
			  	}
			}
		}
		inputWarnings();
	}

	public void initValidVars() {
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
	}

	public boolean checkInput() {
		boolean output = true;
		System.err.println();
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
			System.err.println("Error: Invalid input file provided. Please check the area maps are valid");
			output = false;
		 }
		System.err.println();
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
		 	System.err.println("Error: disposalDistrShape cannot be 0 negative");
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
		// Check that warm-up time <= simulation time
		 if (this.warmUpTime > this.stopTime){
			System.out.println("Warning: warmUpTime is more than stopTime");
		 }
		 System.out.println();
	}

	// Start of simulation
	public void simOutline() {
		while (this.currTime < this.stopTime) {
			determineSetOfEvents();
			// Determine if events exist
			if (eventsExist) {
				Event nextEvent = chooseNextEvent();
				triggerNextEvent(nextEvent);
			} else { break; }
		}
	}

	public void determineSetOfEvents() {
		// Clear possible events so that old events don't interfere and create infinite loop of same event being triggered.
		nextPossEvents.clear();
		// Loop through all the areas
		for (int i = 0; i < noAreas; i++) {
			int currNoBins = areaMatricesArray.get(i).noBins;
			// Loop through all the bins in said area.
			for (int j = 1; j <= currNoBins; j++) {
				// Check to see if current bin can have valid disposal(i.e. bin has not overflowed)
				if (!getBin(i,j).isBinOverflowed()) {
					// Store the time until each bin in that area has a bag disposed.
					int delay = getBin(i,j).timeUntilNextDisposal(currTime);
					Event nextEvent = new Event(1, i, j, delay);
					nextPossEvents.add(nextEvent);
				}
			}

		}
		if (nextPossEvents.size() == 0) { eventsExist = false; }
	}

	public Event chooseNextEvent() {
		for (int i = 0; i < nextPossEvents.size(); i++) {
		}
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

	public void triggerNextEvent(Event nextEvent) {
		// Bag disposed in bin event.
		if (nextEvent.eventType == 1) {
			// Update system time to catch up to this event.
			this.currTime = this.currTime + nextEvent.getDelay();
			// Extract relevant data from next event to output and update system
			int binNum				= nextEvent.binNo;
			int areaNum				= nextEvent.areaNo;
			// binX.disposeBag() returns weight of bag disposed and updates binX's volume and weight
			float bagWeight 		= getBin(areaNum, binNum).disposeBag();
			float binVol 			= (float) Math.round(getBin(areaNum, binNum).currVol * 10) /10;
			float binWeight			= (float) Math.round(getBin(areaNum, binNum).currWeight * 10) /10;
			// Output information about bag disposed event
			String bagOutput		= timeToString() + " -> bag weighing " + bagWeight + " kg disposed of at bin " + areaNum + "." + binNum;
			String binOutput 		= timeToString() + " -> load of bin " + areaNum + "." + binNum + " became " + binWeight + " kg and contents volume " + binVol + " m^3";
			System.out.println(bagOutput);
			System.out.println(binOutput);

			// Check to see if the bins volume exceeds a threshold or if the bin is overflowing
			if (getBin(areaNum, binNum).isThresholdExceeded() && getBin(areaNum, binNum).thresholdReported == false) {
					String threshOutput		= timeToString() + " -> occupancy threshold of bin " + areaNum + "." + binNum + " exceeded";
					getBin(areaNum, binNum).thresholdReported = true;
					System.out.println(threshOutput);
			}
			if (getBin(areaNum, binNum).isBinOverflowed()) {
					String overflowOutput		= timeToString() + " -> bin " + areaNum + "." + binNum + " overflowed";
					getBin(areaNum, binNum).overflowReported = true;
					System.out.println(overflowOutput);
			}
			// Update this bin's time of next disposal
			getBin(areaNum, binNum).updateDisposalInterval(currTime);
		}
	}

	public String timeToString() { // Returns current system time as a string
		int days 		= (int) currTime / 86400;
		String daysS 	= String.format("%02d", days);
		int hours		= (int) (currTime % 86400 ) / 3600 ;
		String hoursS	= String.format("%02d", hours);
		int minutes 	= (int) ((currTime % 86400 ) % 3600 ) / 60 ;
		String minutesS	= String.format("%02d", minutes);
		int seconds		= (int) ((currTime % 86400 ) % 3600 ) % 60 ;
		String secondsS = String.format("%02d", seconds);
		String output = daysS + ":" + hoursS + ":" + minutesS + ":" + secondsS;
		return output;
	}

	public bin getBin(int areaNo, int binNo) { // Returns bin number binNo in area number areaNo
		bin output = areaMatricesArray.get(areaNo).binList.get(binNo);
		return output;
	}

}
