package algorithms.search;

public interface ISearchingAlgorithm {

    Solution solve(ISearchable Isearchable);
    int getNumberOfNodesEvaluated();
    String getName();
}
