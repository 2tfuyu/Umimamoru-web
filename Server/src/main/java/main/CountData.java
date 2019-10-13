package main;

import javax.persistence.EmbeddedId;

public class CountData {
    @EmbeddedId
    ModulePK pk;
    int count;
    boolean occurrence;
    int getLoc(){
        return pk.loc;
    }
    int getNet(){
        return pk.net;
    }
    int getCount(){
        return count;
    }
    boolean getOccurrence(){
        return occurrence;
    }
}
