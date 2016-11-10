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
		java simStart $input
fi
if [[ "$runProg" == 2 ]]
	then
		echo "Starting simulation with flag <tests>"
		echo ""
		echo "------------inv_empty_input------------"
		java simStart inputs/inv_empty_input.txt > outputs/inv_empty_input_OUT.txt
		echo ""
		echo ""
		echo "------------inv_exp_input------------"
		java simStart inputs/inv_exp_input.txt > outputs/inv_exp_input_OUT.txt
		echo ""
		echo ""
		echo "inv_exp_input2------------"
		java simStart inputs/inv_exp_input2.txt > outputs/inv_exp_input2_OUT.txt
		echo ""
		echo ""
		echo "------------inv_exp_input3------------"
		java simStart inputs/inv_exp_input3.txt > outputs/inv_exp_input3_OUT.txt
		echo ""
		echo ""
		echo "------------inv_vartypes_input------------"
		java simStart inputs/inv_vartypes_input.txt > outputs/inv_vartypes_input_OUT.txt
		echo ""
		echo ""
		echo "------------invalid0------------"
		java simStart inputs/invalid0.txt > outputs/invalid0_OUT.txt		
		echo ""
		echo ""
		echo "------------v_grid_input------------"
		java simStart inputs/v_grid_input.txt > outputs/v_grid_input_OUT.txt
		echo ""
		echo ""
		echo "------------v_grid_input2------------"
		java simStart inputs/v_grid_input2.txt > outputs/v_grid_input2_OUT.txt
		echo ""
		echo ""
		echo "------------v_handout_input------------"
		java simStart inputs/v_handout_input.txt > outputs/v_handout_input_OUT.txt
		echo ""
		echo ""
		echo "------------v_w_input1------------"
		java simStart inputs/v_w_input1.txt > outputs/v_w_input1_OUT.txt
		echo ""
		echo ""
		echo "------------v_w_input2------------"
		java simStart inputs/v_w_input2.txt > outputs/v_w_input2_OUT.txt
		echo ""
		echo ""
		echo "------------valid_default_input------------"
		java simStart inputs/valid_default_input.txt > outputs/valid_default_input_OUT.txt
		echo ""
		echo ""
		echo "------------valid_spaces_input.txt------------"
		java simStart inputs/valid_spaces_input.txt > outputs/valid_spaces_input_OUT.txt

fi
if [[ "$runProg" == 3 ]]
	then
		echo "Starting simulation with flag <file>"
		java simStart $input > outputs/output.txt
fi