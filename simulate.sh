#!/bin/bash
# This script will be used to launch simulations
input=$1
options=$2
runProg=1
if [ $# -eq 0 ]
	then
		runProg=0
		echo "Usage: ./simulate.sh <input_filename> [options]"
		echo ""
		echo "        Available [options] are:"
		echo "        tests     -     runs all the test input files"
		echo "        file      -     outputs to file output.txt"
fi
if [ "$2" == "tests" ]
	then
		runProg=2
fi
if [ "$2" == "file" ]
	then
		runProg=3
fi
if [[ "$runProg" == 1 ]]
	then
		echo "Starting simulation"
		echo ""
		java simStart $input
fi
if [[ "$runProg" == 2 ]]
	then
		echo "Starting simulation with flag <tests>"
		echo ""
		echo "Starting grids.txt"
		java simStart FILES/grids.txt > FILES/gridsOUTPUT.txt
		echo "Finished grids.txt"
		echo "Starting input.txt"
		java simStart FILES/input.txt > FILES/inputOUTPUT.txt
		echo "Finished input.txt"
		echo "Starting invalid.txt"
		java simStart FILES/invalid.txt > FILES/invalidOUTPUT.txt
		echo "Finished invalid.txt"
fi
if [[ "$runProg" == 3 ]]
	then
		echo "Starting simulation with flag <file>"
		java simStart $input > output.txt
fi