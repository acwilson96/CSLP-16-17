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

	public boolean isBagDisposed(int currTime) {
		if (currTime < timeOfNextBag)		{ return false; }
		else								{ return true;  }
	}

	public float disposeBag() {
		this.currVol	=	this.currVol + bagVolume;
		float bagWeight = 	(float) (Math.random() * (bagWeightMax - bagWeightMin)) + bagWeightMin;
		bagWeight 		= 	(float) Math.round(bagWeight * 10) /10;
		this.currWeight	=	this.currWeight + bagWeight;
		return bagWeight;
	}

	public void updateDisposalInterval(int time) {
		this.currTime			=	time;
		double numBagsPerHour	=	-1 * this.erlangMean * Math.log(rand());
		double nextBag 			=	3600 / numBagsPerHour;
		this.timeOfNextBag		=	(int) nextBag + currTime;
	}

	public boolean isThresholdExceeded() {
		if (this.currVol > thresholdVol) 	{ return true;  }
		else 								{ return false; }
	}

	public boolean isBinOverflowed() {
		if (this.currVol > binVolume) 		{ return true;  }
		else 								{ return false; }
	
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

}