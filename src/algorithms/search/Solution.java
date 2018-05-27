package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {

   private ArrayList<AState> solutionPath;

    public Solution() {
        solutionPath=new ArrayList<>();

    }

    public ArrayList<AState> getSolutionPath(){
       return solutionPath;
   }


   public void addState(AState state){
       solutionPath.add(state);
   }


}
