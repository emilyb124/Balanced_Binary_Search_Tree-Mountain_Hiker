# Balanced_Binary_Search_Tree-Mountain_Hiker
Self Balancing Binary Search Tree - Mountain Hiker
_Note: This is a Data Structures 2021 class project, assigned by Professor Joanna Klukowska, NYU._

This is an object oriented program, that uses implimentation of a self balancing binary search tree (AVL)
to print out survivable paths down a mountain.
When MountainHike.java is run with an input text file in command line (Mountain.text is a sample input file of
labeled rest stops down a mountain, each with supplies and obstacles) the program prints out all distinct,
successful paths. Recursive backtracking is used in order to do this.

The input file is a list of line separated rest stops, with each line having a label (any character),
then supplies(food, axe, or raft), then obstacles (fallen tree, river), all separated by a space.
The program ignores any text that falls outside these parameters.
