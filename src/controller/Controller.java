package controller;

import model.Model;

import java.util.ArrayList;

public class Controller {
    private Model m;

    // initializing the reference of model class
    public void setModel(Model m) {
        this.m = m;
    }

    // function to request the model to update the board
    public void setRequest(ArrayList<Integer> position) {
        m.PlayMove(position.get(0), position.get(1));
    }

    // overloaded function to request model to reset
    public void setRequest() {
        m.ResetModel();
    }
}
