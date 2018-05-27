package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public abstract class AState {

    private String state;
    public AState prviousState;
    protected int cost;

    public AState(String state) {
        this.state = state;
        cost=0;

    }
    public AState(String state,int cost) {
        this.state = state;
        this.cost=cost;

    }

    //public abstract ArrayList<AState>getAllPossibleNextStates();



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState aState = (AState) o;
        return state.equals(aState.state) ;
    }

    @Override
    public int hashCode() {

        return Objects.hash(state, prviousState);
    }

    @Override
    public String toString() {
        return "AState{" +
                "state='" + state + '\'' +
                '}';
    }

    public abstract int getCost();
}
