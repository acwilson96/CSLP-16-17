import java.util.*;

class Simulator {

	public static int currTime;

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
		System.out.println("simOutline");
		while (this.currTime < this.stopTime) {
			determineSetOfEvents();
			System.out.println("NUMBER OF EVENTS STORED = " + nextPossEvents.size());
			System.out.println();
			//Event nextEvent = chooseNextEvent();
			currTime ++;
			if (nextPossEvents.size() == 5) { break; }
		}
	}

	
	
	public void determineSetOfEvents() {
		System.out.println("Determining Set of Events");
		//nextPossEvents.clear();
		for (int i = 0; i < noAreas; i++) {
			int currNoBins = areaMatricesArray.get(i).noBins;
			for (int j = 1; j <= currNoBins; j++) {

				System.out.println();
				System.out.println("currTime = " + currTime);
				System.out.println("timeOfNextBag in bin = " + areaMatricesArray.get(i).binList.get(j).timeOfNextBag);

				if (areaMatricesArray.get(i).binList.get(j).isBagDisposed(currTime)) {
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

}