import java.math.*;

class bin {

	public static int binNo;
	public static float binVolume;
	public static float disposalDistrRate;
	public static int disposalDistrShape;
	public static float bagVolume;
	public static float bagWeightMin;
	public static float bagWeightMax;
	public static float thresholdVal;

	public static float currVol;

	public static float erlangMean;
	public static int currTime;
	public static double timeOfNextBag;


	public bin(int binNo, float binVolume, float disposalDistrRate, int disposalDistrShape, float bagVolume, float bagWeightMin, float bagWeightMax, float thresholdVal) {
		this.binNo					=	binNo;
		this.binVolume				=	binVolume;
		this.disposalDistrRate		=	disposalDistrRate;
		this.disposalDistrShape		=	disposalDistrShape;
		this.bagVolume				=	bagVolume;
		this.bagWeightMin			=	bagWeightMin;
		this.bagWeightMax			=	bagWeightMax;
		this.thresholdVal			= 	thresholdVal;
		this.currVol 				=	0;
		this.erlangMean				=	disposalDistrShape / disposalDistrRate;
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
		this.currTime			=	time;
		double numBagsPerHour	=	-1 * this.erlangMean * Math.log(rand());
		this.timeOfNextBag		=	60 / numBagsPerHour;
		this.timeOfNextBag		=	Math.round(this.timeOfNextBag * 1000.0) / 1000.0;	
	}

	public float getCurrVol() {
		return this.currVol;
	}



	public boolean isBagDisposed(int currTime) {
		if (currTime < timeOfNextBag)		{ return false; }
		else								{ return true;  }
	}

	public boolean isThresholdExceeded() {
		if (this.currVol > thresholdVal) 	{ return true;  }
		else 								{ return false; }
	}

	public boolean isBinOverflowed() {
		if (this.currVol > binVolume) 		{ return true;  }
		else 								{ return false; }
	
	}

}