package be.kuleuven.tennistoernooijava.service;

import be.kuleuven.tennistoernooijava.enums.ReeksenWaardes;
import be.kuleuven.tennistoernooijava.models.Reeksen;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.Map;

public class MatchenHolderService {
    private static MatchenHolderService instance = new MatchenHolderService();

    private Map<Map<Integer, List<Integer>>, Map<ReeksenWaardes, Integer>> data;

    private MatchenHolderService() { }

    public static MatchenHolderService getInstance() {
        return instance;
    }

    public static void clearInstance() {
        instance = new MatchenHolderService();
    }

    public Map<Map<Integer, List<Integer>>, Map<ReeksenWaardes, Integer>> getData() {
        return data;
    }

    public void setData(Map<Map<Integer, List<Integer>>, Map<ReeksenWaardes, Integer>> data) {
        this.data = data;
    }

    public void updateMatches(){

    }
}
