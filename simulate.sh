#!/bin/bash
# This script will be used to launch simulations
input=$1
runProg=1
if [ $# -eq 0 ]
	then
		runProg=0
		echo "No input file given"
		echo "Usage Instructions:"
		echo "Run ./simulate.sh INPUTFILE.txt [options]"
		echo "Available options are:"
		echo "N/A"
fi
if [[ "$runProg" == 1 ]]
	then
		echo "Starting simulation"
		echo ""
		java cslpProgram $input
fi