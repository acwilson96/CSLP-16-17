import java.util.*;

class binLorry {

		public int areaIdx;
		public float lorryVolume;
		public float lorryMaxLoad;
		public int binServiceTime;
		public float serviceFreq;
		public int timeToNextService;

		private ArrayList<Integer> route = new ArrayList<Integer>();

		public int currTime;
		
		public boolean isCollecting;
		public boolean isInService;

		public float currWeight;
		public float currVolume;

		public int lastBin;
		public int currentBin;
		public int nextBin;
		public int interruptedBin;

		public boolean didService;

		public binLorry(int areaIdx, float lorryVolume, float lorryMaxLoad) {
			this.currentBin 	= 	0;
			this.lastBin		=	0;
			this.nextBin		=	0;
			this.areaIdx		=	areaIdx;
			this.lorryVolume	=	lorryVolume;
			this.lorryMaxLoad	=	lorryMaxLoad;
			this.binServiceTime = 	binServiceTime;
			this.serviceFreq	=	serviceFreq;
			this.currWeight		=	0;
			this.currVolume		=	0;
			this.didService 	= 	true;
			this.isInService	=	false;
			this.isCollecting	=	false;
		}

	   // Update Lorry Status
		public void startService() {
			this.isInService	=	true;

		}
		public void arrivedAtBin(int binNo) {
			this.isCollecting	= true;
			this.currentBin 	= binNo;
		}
		public void departedBin(int binNo, float binWeight, float binVolume, int nextBin) {
			this.currWeight 	= this.currWeight + binWeight;
			this.currVolume 	= this.currVolume + binVolume;
			this.isCollecting 	= false;
			this.lastBin 		= binNo;
			this.nextBin		= nextBin;
		}
}