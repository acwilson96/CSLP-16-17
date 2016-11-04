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
	
	public Simulator(String args[]) {
		readfile r = new readfile();
		r.openFile(args[0]);
		input = r.readFile();
		r.closeFile();
		this.currTime = 0;

	}

	public void parseInput() {
		for (int i = 0; i < input.size(); i++) {
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
			}
		}
		
	}

	
	
	public void simOutline() {
		while (this.currTime < this.stopTime) {
			determineSetOfEvents();
			if (nextPossEvents.size() > 0) {
				Event nextEvent = chooseNextEvent();
				triggerNextEvent(nextEvent);
			} else {
				this.currTime++;
			}
		}
	}

	
	
	public void determineSetOfEvents() {
		nextPossEvents.clear();
		for (int i = 0; i < noAreas; i++) {
			int currNoBins = areaMatricesArray.get(i).noBins;
			for (int j = 1; j <= currNoBins; j++) {
				if (getBin(i,j).isBagDisposed(currTime)) {
					
					Event nextEvent = new Event(1, i, j, 0);
					nextPossEvents.add(nextEvent);
					areaMatricesArray.get(i).binList.get(j).updateDisposalInterval(currTime);
				}
				
				
				
			}

		}

	}

	public Event chooseNextEvent() {
		int lowestDelay		 =	nextPossEvents.get(0).getDelay();
		int lowestDelayIndex =	0;
		for (int i = 0; i < nextPossEvents.size(); i++) {
			if (nextPossEvents.get(i).getDelay() < lowestDelay) {
				lowestDelay 		= nextPossEvents.get(i).getDelay();
				lowestDelayIndex	= i;
			}
		}
		return nextPossEvents.get(lowestDelayIndex);
	}

	public void triggerNextEvent(Event nextEvent) {
		if (nextEvent.eventType == 1) {
			int binNum				= nextEvent.binNo;
			int areaNum				= nextEvent.areaNo;
			float bagWeight 		= getBin(areaNum, binNum).disposeBag();
			float binVol 			= (float) Math.round(getBin(areaNum, binNum).currVol * 10) /10;
			float binWeight			= (float) Math.round(getBin(areaNum, binNum).currWeight * 10) /10;
			String bagOutput		= timeToString() + " -> bag weighing " + bagWeight + " kg disposed of at bin " + areaNum + "." + binNum;
			String binOutput 		= timeToString() + " -> load of bin " + areaNum + "." + binNum + " became " + binWeight + " kg and contents volume " + binVol + " m^3";
			System.out.println(bagOutput);
			System.out.println(binOutput);
			if (getBin(areaNum, binNum).isThresholdExceeded() && getBin(areaNum, binNum).thresholdReported == false) {
					String threshOutput		= timeToString() + " -> occupancy threshold of bin " + areaNum + "." + binNum + " exceeded";
					System.out.println(threshOutput);
					getBin(areaNum, binNum).thresholdReported = true;
			}
			if (getBin(areaNum, binNum).isBinOverflowed() && getBin(areaNum, binNum).overflowReported == false) {
					String overflowOutput		= timeToString() + " -> bin " + areaNum + "." + binNum + " overflowed";
					System.out.println(overflowOutput);
					getBin(areaNum, binNum).overflowReported = true;
			}
				
		}


	}

	public String timeToString() {
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

	public bin getBin(int areaNo, int binNo) {
		bin output = areaMatricesArray.get(areaNo).binList.get(binNo);
		return output;
	}

}