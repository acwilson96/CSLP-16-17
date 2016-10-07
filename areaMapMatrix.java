class areaMapMatrix {
	
	private int areaIdx;
	private float serviceFreq;
	private float thresholdVal;
	private int noBins;
	private int[][] roadsLayout;

	public areaMapMatrix(int areaIdx, float serviceFreq, float thresholdVal, int noBins, int roadsLayout[][]) {
		this.areaIdx = areaIdx;
		this.serviceFreq = serviceFreq;
		this.thresholdVal = thresholdVal;
		this.noBins = noBins;
		this.roadsLayout = roadsLayout;
	}

	public int getAreaIdx() {
		return this.areaIdx;
	}

	public float getServiceFreq() {
		return this.serviceFreq;
	}

	public float getThresholdVal() {
		return this.thresholdVal;
	}

	public int getNoBins() {
		return this.noBins;
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