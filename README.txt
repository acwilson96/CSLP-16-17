The simulator runs as follows:

simStart creates instance of Simulator:
	Simulator instance creates instance of readfile:
	Simulator instance uses readfile to load input into a local variable, excluding comment lines
simStart calls Simultor methods to parse the input
  	Simulator runs parseInput(), going through every line of the input and checking for keywords,
  	and creates areaMapMatrix objects
  		areaMapMatrix objects then create bin objects based on that specific area
  			bin objects initialise
simStart calls checkInput() in an IF statement
	Simultor's checkInput does checks to make sure that all necessary variables were parsed, and checks
	to see that parsed values aren't invalid or logically flawed, it then checks to make sure no rogue
	parameters exist in input. Finally it checks that the roadsLayouts are valid. If all are valid, it
	returns true.
IF checkInput() passes, simStart calls inputWarnings() to check input for warnings
	inputWarnings() checks the input to see if multiple instances of a variable exist, and warn user of
	outcome, it also checks for values that will allow the simulator to run, but may not be what the
	user wants, and warns the user. It also checks for rogue tokens lying after variables in the input.
simStart calls Simulator's simOutline() to start generating events
	simOutline() starts a while loop that runs as long as the current time < end time
	  the loop iteratively:
	  Determines set of possible events by
	  	looping through all areas
	  		looping through all that areas bins, and takes note of time
	  			Creates Event objects with key information needed to update system 
	  IF events exist(i.e. at this stage, if bins haven't overflowed yet):
	   choose next event and execute it
	   update system time


HOW TO RUN:

use ./compile.sh to compile all necessary Java files
use ./simulate.sh [input file] [options] to start simulator with given input
Available [options] are:
	tests     -     runs all the test input files in "inputs" folder, and outputs to files in "outputs" folder
	file      -     outputs to file output.txt in the folder "outputs" in directory run in