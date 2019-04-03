# Mathematics_Thesis_Java
The following are the paths in this folder, where the written algorithms can be found:

Ant Colony System: src/TSP/ApproximationAlgorithms/ACO
	Note that in this path there are two classes. The first class in the Ant class which encodes the artificial ant. The second class is the ACOEngine class which encodes the ACS
	algorithm logic.

Nearest Neighbour Algorithm : src/TSP/ApproximationAlgorithms/NearestNeighbourApproximation
	Note that in this path, a class called NearestNeighbourHeuristicEngine is found, and this encodes the Nearest Neighbour Algorithm 

Twice Around the MST Algorithm : src/TSP/ApproximationAlgorithms/TwiceAroundMST
	Note that in this path 3 classes can be found. These are :
		PrimMst -> This class encodes Prim's algorithm for finding a MST using an adjacency matrix + priority queue for efficiency.
		PrimsPriorityQueueEntry -> This encodes the priority queue entries for each vertex in the graph
		TwiceAroundMstHeuristic -> This class encodes the algorithm for approximating the TSP based on 2*MST heuristic

Note that for the running of the algorithms provided in the paths above, TSPLIB instances were used. The used TSPLIB instances are all defined in the 2-D euclidean space, for reasons 
mentioned in the write up. The used TSPLIB instances can be found in the folder src/TSP/GraphInstances. Note that in the current implementation, the TSPLIB instance which is 
approximated is eil51.txt only, whose optimal Hamiltonian cycle has length 426. Note that this can be modified by changing the path of the graph from the respective classes. 
Note that there are also graph related classes in src/TSP/Graphs. The city class encoding can also be found in src/TSP. Also note that the main class is the class AlgorithmLauncher
found in the folder src.

In the folder src, one can also see the python file plot_script.py, which was used to output the plots mentioned in the write up. Also, the writeup can be found in the folder 
MathematicsThesis.

Note : That these programs were designed to be as Object Oriented as possible. Further documentation can be read through java docs or in the source files. Explanations of the written
methods can be found in inline comments in the source files.

Compilation and Running of Programs
-----------------------------------
To compile the algorithms' source code, use cmd to enter the MathematicsThesis folder, and write "AlgorithmsCompile" in cmd 
To run the algorithms, remain in the same path and write "AlgorithmsRun" in cmd.
To run the python script related to the plots, write "PythonScriptRun" in cmd.

NOTE: IT is important that the javac and python paths are set correctly in the Environment Variable PATH.
NOTE: The instuctions are only given for WINDOWS OS. 

To open JAVADOCS
--------------------------

Locate the file index.html in the folder MathematicsThesis, and double click on it.