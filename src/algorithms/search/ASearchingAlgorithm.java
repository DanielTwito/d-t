package algorithms.search;

import java.util.LinkedList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected int NumberOfNodesEvaluated;
    protected AState currentState;
    protected Solution solution;


    protected Solution getSolution(AState s, AState start) {
        Solution sol = new Solution();
        LinkedList<AState> path =new LinkedList<>();

        while(s.prviousState!=null) {
            path.add(s);
            s = s.prviousState;
        }
        sol.addState(start);
        while (!path.isEmpty())
            sol.addState(path.removeLast());
        return sol;


    }
    //public abstract void  generateGraph();

}
