import java.util.*;

class areaMapMatrix {
	
	public int currTime;

	public int areaIdx;
	public float serviceFreq;
	public float thresholdVal;
	public int noBins;
	public int[][] roadsLayout;
	public float binVolume;
	public float disposalDistrRate;
	public int disposalDistrShape;
	public float bagVolume;
	public float bagWeightMin;
	public float bagWeightMax;
	public binLorry lorry;

	public ArrayList<bin> binList = new ArrayList<bin>();

	public areaMapMatrix(int currTime, int areaIdx, float serviceFreq, float thresholdVal, int noBins, int roadsLayout[][], float  binVolume, float disposalDistrRate, int disposalDistrShape, float bagVolume, float bagWeightMin, float bagWeightMax) {
		this.currTime			= currTime;
		this.areaIdx 			= areaIdx;
		this.serviceFreq 		= serviceFreq;
		this.thresholdVal 		= thresholdVal;
		this.noBins 			= noBins;
		this.roadsLayout 		= roadsLayout;
		this.binVolume			= binVolume;
		this.disposalDistrRate	= disposalDistrRate;
		this.disposalDistrShape	= disposalDistrShape;
		this.bagVolume			= bagVolume;
		this.bagWeightMin		= bagWeightMin;
		this.bagWeightMax		= bagWeightMax;
		this.lorry				= new binLorry();
		setUpBins();

	}
	
	private void setUpBins() { // Initialises the bins in this area.
		for (int i = 0; i <= this.noBins; i++) {
			bin aBin = new bin(i, binVolume, disposalDistrRate, disposalDistrShape, bagVolume, bagWeightMin, bagWeightMax, thresholdVal);
			binList.add(aBin);
		}
	}
	
	public String toString() { // Returns string map of this area (used for testing)
		String output = "";
		for (int i=0; i <= this.noBins; i++) {
			for (int j=0; j <= this.noBins; j++) {
				if (roadsLayout[i][j] >= 0 && roadsLayout[i][j] <= 9) {
					output = output + "  " + roadsLayout[i][j];
					if (j == this.noBins) { output = output + "\n"; }
				}
				else{
					output = output + " " + roadsLayout[i][j];
					if (j == this.noBins) { output = output + "\n"; }
				}
			}
		}
		return output;
	}
}