package com.example.milan.rftproject;

public class Ranklist {
    private int id;
    private int point;

    public void Ranklist(int id,int point){
        this.id=id;
        this.point=point;
    }

    protected int getId()
    {
        return id;
    }

    protected  int getPoint(){
        return point;
    }
}
