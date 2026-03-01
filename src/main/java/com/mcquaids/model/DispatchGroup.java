package com.mcquaids.model;

import java.util.ArrayList;
import java.util.List;

public class DispatchGroup {

    private String label;
    private List<DispatchAction> actions = new ArrayList<>();

    public DispatchGroup(String label) {
        this.label = label;
    }

    public void add(DispatchAction action) {
        actions.add(action);
    }

    public boolean isEmpty() {
        return actions.isEmpty();
    }

    public String getLabel() {
        return label;
    }

    public List<DispatchAction> getActions() {
        return actions;
    }
}