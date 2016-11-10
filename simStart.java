class simStart {
	
	public static void main(String args[]) {
		
		Simulator cslp = new Simulator(args);
		//cslp.parseInput();
		cslp.parseInput2();
		if (cslp.checkInput()) {
			cslp.simOutline();	
		}
	}
	
}