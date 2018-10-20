# Mathematics_Thesis_Java
The following are the paths in the folder where the algorithms can be found:

Ant Colony Optimization(Ant System): src/TSP/ApproximationAlgorithms/ACO
	Note that in this path a class for the aritificial ant and the actual aco engine can be found 

Nearest Neighbour Heuristic : src/TSP/ApproximationAlgorithms/NearestNeighbourApproximation
	Note that in this path a class called NearestNeighbourHeuristicEngine is found that encodes the nearest neighbour heuristic algorithm 

Twice Around MST Heuristic : src/TSP/ApproximationAlgorithms/TwiceAroundMST
	Note that in this path 3 classes can be found , these :
		PrimMst -> This class encodes Prim's algorithm for finding a MST using an adjacency matrix + priority queue for efficiency
		PrimsPriorityQueueEntry -> This encodes the priority queue entries for each vertex in the graph
		TwiceAroundMstHeuristic -> This class encodes the algorithm for approximating the TSP based on 2*MST heuristic

Note that in these algorithms TSPLIB was used because it provides graph instances in which euclidean distance can be defined which are essential for the 2*MST heuristic .Also ,
the graphs are assumed to be complete because any incomplete graph can be transformed to a complete graph by adding missing edges to the original graph with infinite weights. This 
construction would not effect both the theory and the approximation algorithms provided a hamiltonian path does exist. Thus by considering complete graphs only we have to avoid 
checking whether a hamiltonian path exists , the reason is that determining whether a hamiltonian path exists is NP complete. Also TSPLIB provided a bench mark of what the optimal
paths are , and thus it could show whether the ACO algorithm was implemented correctly or not .

In src/TSP/GraphInstances these TSPLIB instances that were used could be seen . Note that in the current implementation , only berlin52.txt is being considered which has an optimal
path of length 7542. This can be modified by changing the path of the graph from the respective classes. Note that there are also graph related classes in src/TSP/Graphs and the 
main class in src . The city class encoding can also be found in src/TSP

Note : That these programs were designed to be as Object Oriented as possible . Further documentation can be read through java docs in : or in the source files. Explanations of 
methods can be found in inline comments in the source files.
