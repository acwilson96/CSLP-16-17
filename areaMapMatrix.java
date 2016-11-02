class areaMapMatrix {
	
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

	public bin[] binArray;

	public areaMapMatrix(int areaIdx, float serviceFreq, float thresholdVal, int noBins, int roadsLayout[][], float  binVolume, float disposalDistrRate, int disposalDistrShape, float bagVolume, float bagWeightMin, float bagWeightMax) {
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
		binArray 				= setUpBins();
	}
	
	private bin[] setUpBins() {
		bin[] output = new bin[this.noBins];
		for (int i = 0; i < this.noBins; i++) {
			output[i] = new bin(binVolume, disposalDistrRate, disposalDistrShape, bagVolume, bagWeightMin, bagWeightMax);
		}
		return output;
	}
	

	public String toString() {
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