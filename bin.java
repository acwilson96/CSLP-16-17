import java.math.*;

class bin {

	public int binNo;
	public float binVolume;
	public float disposalDistrRate;
	public int disposalDistrShape;
	public float bagVolume;
	public float bagWeightMin;
	public float bagWeightMax;
	public float thresholdVal;
	public float thresholdVol;

	public float currVol;
	public float currWeight;

	public float erlangMean;
	public int currTime;
	public int timeOfNextBag;

	public boolean thresholdReported;
	public boolean overflowReported;


	public bin(int binNo, float binVolume, float disposalDistrRate, int disposalDistrShape, float bagVolume, float bagWeightMin, float bagWeightMax, float thresholdVal) {
		this.binNo					=	binNo;
		this.binVolume				=	binVolume;
		this.disposalDistrRate		=	disposalDistrRate;
		this.disposalDistrShape		=	disposalDistrShape;
		this.bagVolume				=	bagVolume;
		this.bagWeightMin			=	bagWeightMin;
		this.bagWeightMax			=	bagWeightMax;
		this.thresholdVal			= 	thresholdVal;
		this.thresholdVol			=	thresholdVal * binVolume;
		this.currVol 				=	0;
		this.currWeight				=	0;
		this.erlangMean				=	disposalDistrShape / disposalDistrRate;
		this.thresholdReported		=	false;
		this.overflowReported		=	false;
		updateDisposalInterval(0);
	}

	public int timeUntilNextDisposal(int currTime) { // Returns time until next bag is disposed
		return timeOfNextBag - currTime;
	}

	public float disposeBag() { // Updates instance with new weight and volume, and returns the weight of bag just disposed.
		float bagWeight = 	(float) (Math.random() * (bagWeightMax - bagWeightMin)) + bagWeightMin;
		bagWeight 		= 	(float) Math.round(bagWeight * 1000) /1000;
		if (!isBinOverflowed()) {
			this.currVol	=	this.currVol + bagVolume;
			this.currWeight	=	this.currWeight + bagWeight;
		}
		return bagWeight;
	}

	public void updateDisposalInterval(int time) { // Generates the time of next bag disposal for this bin
		this.currTime			=	time;
		double numHourToNext	=	-1 * this.erlangMean * Math.log(rand());
		double numSecToNext		=	3600 * numHourToNext;
		this.timeOfNextBag		=	(int) numSecToNext + currTime;
	}

	public void serviceBin(int time) {
		this.currVol 			=	0;
		this.currWeight			=	0;
		this.thresholdReported  = false;
		this.overflowReported 	= false;
	}

	public boolean isThresholdExceeded() { // Returns whether the threshold has been exceeded
		if (this.currVol > thresholdVol) 	{ return true;  }
		else 								{ return false; }
	}

	public boolean isBinOverflowed() { // Returns whether the bin has overflowed
		if (this.currVol > binVolume) 		{ return true;  }
		else 								{ return false; }
	}

	public double rand() { // Generates random number between 0 and 1 exclusive
		double output = 0;
		while (true) {
			if (output == 0 || output == 1) {
				output = Math.random();
			}
			else { break; }
		}
		return output;
	}

}