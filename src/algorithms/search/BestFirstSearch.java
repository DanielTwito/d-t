package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch() {

        this.visited =new HashMap<>();
        stateQueue=new PriorityQueue<>((o1, o2) -> o1.getCost()-o2.getCost());
        goalFound =false;
        NumberOfNodesEvaluated =0;
        currentState=null;
    }


    @Override
    public int getNumberOfNodesEvaluated() {
        return NumberOfNodesEvaluated;
    }

    @Override
    public String getName() {
        return "Best First Search";
    }
}
