# A-Star-Algorithm-Research
Testing various data structures for A* algorithm to see if their are possible performance increases.

This code will work with grid maps (where path distances are always 1) or real-world maps (where path distances vary)

Run Main.java. Program will ask for which data structure you want to test. Then enter the name of the data file (a .txt file. See below).
If the data file has a path, the path will be output to the console, 
  and a file contain the time, number of removeMin operations, and number of insert operations will be created.
The file name will be <data file name>_<data structure name>.txt

To create a data file, either use my map generator code (found in a separate repository), or follow the following rules:
  1. Nodes are defined as:
      Name distance_to_end_node <T if start node, F otherwise> <T if end node, F otherwise>
      Example: A1 11 T F
  2. All nodes should be defined with no empty lines between them
  3. Once all the nodes are defined, leave an empty line and start defining paths
  4. Paths are defined as:
      Node1 Node2 Distance
      Example:  A1 A2 1
                A1 D4 6
  
 See testData1.txt or GeneratedMap1.txt for examples of test data.
