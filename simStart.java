import java.util.*;

class simStart {

	public static void main(String args[]) {
		// Create new instance of a simulator
		Simulator cslp = new Simulator(args);
		// Parse input in new simulator
		cslp.parseInput();
		// Check for experimental running
		if (!cslp.containsExperiment) {
			// Run normally.
			// If input is valid and can be used in simulator
			if (cslp.checkInput()) {
				// Warn about potential issues with input
				cslp.inputWarnings();
				// Run the simulator outline
				cslp.simOutline(true);	
			}
		}else{
			// Run all experiments
			int expNumb = 1;
			int noDistrRate   = cslp.disposalDistrRateExp.size();
			int noDistrShape  = cslp.disposalDistrShapeExp.size();
			int noServiceFreq = cslp.serviceFreqExp.size();
			// Loop over all possible values of experiments.
			for (int x = 0; x < noDistrRate; x++) {
				for (int y = 0; y < noDistrShape; y++) {
					for (int z = 0; z < noServiceFreq; z++) {
						// Update simulator with new values
						float nextRate		=	cslp.disposalDistrRateExp.get(x);
						int nextShape		=	cslp.disposalDistrShapeExp.get(y);
						float nextFreq		=	cslp.serviceFreqExp.get(z);
						cslp.updateExperiment(nextRate, nextShape, nextFreq, expNumb);
						// Run with new values
						cslp.simOutline(false);
						expNumb++;
					}
				}
			}
		}
	}
	
}