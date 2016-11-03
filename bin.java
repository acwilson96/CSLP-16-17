import java.math.*;

class bin {

	public static float binVolume;
	public static float disposalDistrRate;
	public static int disposalDistrShape;
	public static float bagVolume;
	public static float bagWeightMin;
	public static float bagWeightMax;
	public static float thresholdVal;

	public static float currVol;

	public static float erlangMean;
	public static double currDisposalInterval;


	public bin(float binVolume, float disposalDistrRate, int disposalDistrShape, float bagVolume, float bagWeightMin, float bagWeightMax, float thresholdVal) {
		this.binVolume				=	binVolume;
		this.disposalDistrRate		=	disposalDistrRate;
		this.disposalDistrShape		=	disposalDistrShape;
		this.bagVolume				=	bagVolume;
		this.bagWeightMin			=	bagWeightMin;
		this.bagWeightMax			=	bagWeightMax;
		this.thresholdVal			= 	thresholdVal;
		this.currVol 				=	0;
		initDisposalInterval();
	}
	
	private void initDisposalInterval() {
		this.erlangMean				=	disposalDistrShape / disposalDistrRate;

		this.currDisposalInterval	=	-1 * erlangMean * Math.log(rand());
		System.out.println(currDisposalInterval);
	}

	public double rand() {
		double output = 0;
		while (true) {
			if (output == 0 || output == 1) {
				output = Math.random();
			}
			else { break; }
		}
		return output;
	}

	public void updateDisposalInterval(int time) {

	}





	public float getCurrVol() {
		return this.currVol;
	}



	public void isBagDisposed() {

	}

	public boolean isThresholdExceeded() {
		if (this.currVol > thresholdVal) { return true; }
		else { return false; }
	}

	public boolean isBinOverflowed() {
		if (this.currVol > binVolume) { return true; }
		else { return false; }
	
	}

}