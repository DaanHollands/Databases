package be.kuleuven.tennistoernooijava.models.SessionHolders;

import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;

import java.util.List;
import java.util.Map;

public class MatchenHolderSessie {
    private static MatchenHolderSessie instance = new MatchenHolderSessie();

    private Map<Map<Integer, List<Integer>>, Map<ReeksenWaardes, Integer>> data;

    private MatchenHolderSessie() { }

    public static MatchenHolderSessie getInstance() {
        return instance;
    }

    public static void clearInstance() {
        instance = new MatchenHolderSessie();
    }

    public Map<Map<Integer, List<Integer>>, Map<ReeksenWaardes, Integer>> getData() {
        return data;
    }

    public void setData(Map<Map<Integer, List<Integer>>, Map<ReeksenWaardes, Integer>> data) {
        this.data = data;
    }

}
