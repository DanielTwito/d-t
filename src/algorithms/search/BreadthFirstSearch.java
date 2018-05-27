package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    protected HashMap<String,Boolean> visited;
    protected boolean goalFound;
    protected Queue<AState> stateQueue;

    public BreadthFirstSearch() {
        this.visited =new HashMap<>();
        stateQueue=new LinkedList<>();
        goalFound =false;
        NumberOfNodesEvaluated =0;
        currentState=null;
    }

    @Override
    public Solution solve(ISearchable Isearchable) {

        if(Isearchable==null)
            return new Solution();
        ArrayList<AState> states;
        AState s=null,startState=Isearchable.getStartState();
        stateQueue.add(startState);
        visited.put(startState.toString(), true);
        NumberOfNodesEvaluated++;
        while (!stateQueue.isEmpty()){
            currentState=stateQueue.poll();
            states=Isearchable.getAllPossibleStates(currentState);
            int i=0;
            while(!states.isEmpty() && !goalFound) {
                s = states.remove(i);
                s.prviousState = currentState;
                if (s.equals(Isearchable.getGoalState())) {
                    goalFound = true;
                    break;
                }
                if (!visited.containsKey(s.toString())) {
                    visited.put(s.toString(), true);
                    stateQueue.add(s);
                    NumberOfNodesEvaluated++;
                }
            }
        }

        if(!goalFound || currentState==null)
            return new Solution();
        goalFound=false;
        return getSolution(s,startState);
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return NumberOfNodesEvaluated;
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }


}
