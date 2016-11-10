class simStart {
	
	public static void main(String args[]) {
		// Create new instance of a simulator
		Simulator cslp = new Simulator(args);
		// Parse input in new simulator
		cslp.parseInput();
		// If input is valid and can be used in simulator
		if (cslp.checkInput()) {
			// Warn about potential issues with input
			cslp.inputWarnings();
			// Run the simulator outline
			cslp.simOutline();	
		}
	}
	
}