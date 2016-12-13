class Event {

	public int eventType;
	public int areaNo;
	public int binNo;
	public int delay;

	/* eventType
	*	1 = bag disposal
	*	2 = arrive at bin
	*	3 = depart from bin
	*/
	public Event(int eventType, int areaNo, int binNo, int delay) {
		this.eventType 	= 	eventType;
		this.areaNo		=	areaNo;
		this.binNo		=	binNo;
		this.delay 		=	delay;
	}

	public int getDelay() {
		return this.delay;
	}

	public String toString() {
		String output = "eventType " + eventType + ". areaNo = " + areaNo + ". binNo = " + binNo + ". delay = " + delay;
		return output;
	}

}
