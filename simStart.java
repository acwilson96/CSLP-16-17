class simStart {
	
	public static void main(String args[]) {
		
		Simulator cslp = new Simulator(args);
		cslp.parseInput();
		if (cslp.checkInput()) {
			cslp.inputWarnings();
			cslp.simOutline();	
		}
	}
	
}