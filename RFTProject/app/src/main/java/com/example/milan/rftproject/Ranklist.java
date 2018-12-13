package com.example.milan.rftproject;

import java.util.Collections;
import java.util.Comparator;

public class Ranklist implements Comparable<Ranklist>{
    private String username;
    private int point;
    private String difficulty;

    public Ranklist(String username,int point,String difficulty){
        this.username=username;
        this.point=point;
        this.difficulty=difficulty;
    }

    public String getUsername()
    {
        return username;
    }

    public  int getPoint(){
        return point;
    }

    public String getDifficulty(){
        return difficulty;
    }

    @Override
    public int compareTo(Ranklist o) {
        return o.getPoint() - getPoint();
    }
}
