package main;

import main.FlowData;

import java.io.Serializable;

public class Flows implements Serializable {
    FlowData flow;
    int count;
    double direction;
    public FlowData getFlow(){return flow;}
    public int getCount(){return count;}
    Flows(FlowData flow, int count){
        this.flow = flow;
        this.count = count;
    }
}