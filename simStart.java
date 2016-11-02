class simStart {
	
	public static void main(String args[]) {
		Simulator cslp = new Simulator(args);
		cslp.parseInput();
		System.out.println("Area 0, Bin 1 Volume = " + (((cslp.areaMatricesArray).get(0)).binArray[0]).binVolume);
	}
	
}