cslp-16-17
s1421803 - Alexander Wilson
###########################

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

###########################

HOW TO RUN:

use ./compile.sh to compile all necessary Java files
use ./simulate.sh [input file] [options] to start simulator with given input
Available [options] are:
	tests     -     runs all the test input files in "inputs" folder, and outputs to files in "outputs" folder
	file      -     outputs to file output.txt in the folder "outputs" in directory run in

###########################

Classes:

simStart
 - The purpose of this class is to create a top level management class to split up the simulation into its key processes: input parsing; input validation;
   event generation and event execution

readfile 
 - The purpose of this class is to seperate the process of parsing the input with storing it. The method readFile(String y) is used to read a file into 
   an ArrayList<String>, so that it can be parsed.

	- readFile(String y) returns ArrayList<String> where each string is a line in input, excluding comment lines.

Simulator
 - The purpose of this class is to manage and update the different componenets of the simulator such as the different areas, and their respective bins.
   It also manages a list of Events, which is the data type used to store possibe events. This class also stores all relevant information to the simulator.

	- initValidVars() initialises variables in the Simulator that are used for Input Validation
	- parseInput() loops through each line of the input, and saves appropriate parameters, and flags incorrect values.
	- checkInput() returns a boolean value, based on if both the input parsed and the input supplied is suitable for running a simulator
	- inputWarnings() outputs warnings to the user about the parsed, but accepted input, incase values are not as intended.
	- simOutline() is a structure which organises the execution of event tasks within the simulation timeframe.[ determineSetOfEvents() , chooseNextEvent() , triggerNextEvent() ]
	- determineSetOfEvents() loops through all objects that are associated with, and generate events. It then puts together an ArrayList of Events.
	- chooseNextEvent() returns an Event, which is chosen because it has the smallest time delay attached to it.
	- triggerNextEvent() matches the chosen Event's type and then updates the simulator with the appropriate changes[relevant to Part II]. It also outputs information.
	##SUPPORTING FUNCTIONS##
	- timeToString() returns a String version of the current system time, since the time is measured in seconds.
	- getBin(int areaNo, int binNo) returns bin number binNo in area areaNo.
	- getWord(String text, int x) returns a String which is word number x in a String of words, where whitespace denotes the seperator of words.
	- getWords(String text) returns an array of Strings, where each element is a word from 'text'.
	- isNumerical(String text) returns a boolean value, based on if the String supplied is of the form: (-)[0.9]*(.)[0-9]* :i.e. it can be any negative or positive decimal.
	
areaMapMatrix
 - The purpose of this class is to manage data specific to areas, this includes data like service frequency, threshold value of bins in that area.
   The two main purposes of this class is to store the roadsLayout matrix specific to that area, and the bin objects specific to that area.

	- setUpBins() intialises the bins for a given area, simply by appending created bins to an ArrayList of type bin.
	- toString() returns a String output of the roadsLayout. This was used for testing if parsing of roadsLayout was correct.

bin
 - The purpose of this class is to manage specific instances of bins, and host methods which will be used in the aiding of generating events, and updating
   the state of the system(in regards to bin volume & weight). 

	- timeUntilNextDisposal(int currTime) returns an integer value, which is the time in seconds until the next bag disposal at this bin.
	- disposeBag() updates the current volume & weight of that instance of bin with a new bag disposal, and also returns the weight of the bag disposed.
	- updateDisposalInterval(int time) takes the current system time, and calculates the time that the next bag will be disposed, and updates that instance of bin with the value.
	- isThresholdExceeded() returns a boolean based on if the current volume of the bin exceeds the threshold volume allocated to that bin.
	- isBinOverflowed() returns a boolean based on if the current volume of the bin exceeds the maximum volume of that bin.
	- rand() generates and returns a random number between 0 and 1 exclusive. Is used as supporting function for updateDisposalInterval().

Event
 - The purpose of this class is to create a data type which contains only the identifying information of an event, with an associated delay in time. This means
   that once a list of all possible events has been created, Events can easily be compared based on their delay. Then using indentifying information in the
   event object, the relevant objects can have values updated.

	- getDelay() returns an integer which is the time in seconds until this event happens.
	- toString() returns a String which contains all information stored in the event. Was used for testing.