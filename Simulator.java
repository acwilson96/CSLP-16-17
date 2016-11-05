import java.util.*;
import java.lang.*;

class Simulator {

	public static int currTime;

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
	public static ArrayList<String> input;

	public ArrayList<Event> nextPossEvents = new ArrayList<Event>();

	public boolean eventsExist;
	
	public Simulator(String args[]) {
		// Read input file, ArrayList input = list of words in input file.
		readfile r = new readfile();
		r.openFile(args[0]);
		input = r.readFile();
		r.closeFile();
		this.currTime = 0;
		eventsExist = true;
	}

	public void parseInput() {
		// Loop through every word in the input file.
		for (int i = 0; i < input.size(); i++) {
			// If the word = a keyword such as a variable, store the next value
			if (input.get(i).equals("lorryVolume")) {	
				lorryVolume = Integer.parseInt(input.get(i+1));
			} else if (input.get(i).equals("lorryMaxLoad")) {
				lorryMaxLoad = Integer.parseInt(input.get(i+1));
			} else if (input.get(i).equals("binServiceTime")) {
				binServiceTime = Integer.parseInt(input.get(i+1));
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
			} else if (input.get(i).equals("noAreas")) {
				noAreas = Integer.parseInt(input.get(i+1));
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
			  	areaMapMatrix currentAreaMatrix = new areaMapMatrix(currTime, areaIdx, serviceFreq, thresholdVal, noBins, roadMatrix, binVolume, disposalDistrRate, disposalDistrShape, bagVolume, bagWeightMin, bagWeightMax);
			  	areaMatricesArray.add(currentAreaMatrix);
			} 
			// Store final two variables \\
			  else if (input.get(i).equals("stopTime")) {
				stopTime 	= Float.parseFloat(input.get(i+1));
				stopTime	= stopTime * 60 * 60;
			} else if (input.get(i).equals("warmUpTime")) {
				warmUpTime 	= Float.parseFloat(input.get(i+1));
				warmUpTime	= warmUpTime  * 60 * 60;
			}
		}
		
	}

	public boolean checkInput() {
		boolean output = true;
		// Check lorry capacity > bin capacity
		if (this.lorryVolume < this.binVolume) {
			output = false;
			System.out.println("Error: lorryVolume cannot be less than binVolume");
		}
		// Check that warm-up time <= simulation time
		if (this.warmUpTime > this.stopTime){
			output = false;
			System.out.println("Error: warmUpTime cannot be more than stopTime");
		}
		return output;
	}
	
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