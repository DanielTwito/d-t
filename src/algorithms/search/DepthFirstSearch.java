package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm {


    private HashMap<String,Boolean> visited ;
    private boolean goalFound;
    private Stack<AState> stateStack;

    public DepthFirstSearch(){

        this.visited= new HashMap<>();
        stateStack = new Stack<>();
        goalFound=false;
        currentState=null;
    }

    @Override
    public Solution solve(ISearchable Isearchable) {
        ArrayList <AState> states;
        AState s=null;
        if(Isearchable==null)
            throw new Error("The given problem to solve is null");
        AState startState = Isearchable.getStartState();
        stateStack.push(startState);

        while (!stateStack.isEmpty()){
            currentState=stateStack.pop();
            states=Isearchable.getAllPossibleStates(currentState);
            while (!states.isEmpty() && !goalFound){
                s=states.remove(0);
                s.prviousState=currentState;
                if(s.equals(Isearchable.getGoalState())){
                    goalFound=true;
                    break;
                }
                if(!visited.containsKey(s.toString())){
                    visited.put(s.toString(),true);
                    stateStack.push(s);
                    NumberOfNodesEvaluated++;
                }
            }


        }

        if(!goalFound || currentState==null)
            return new Solution();
        return getSolution(s,startState);
    }



    @Override
    public int getNumberOfNodesEvaluated() {
        return NumberOfNodesEvaluated;
    }

    @Override
    public String getName() {
         return "Depth First Search";
    }
}
