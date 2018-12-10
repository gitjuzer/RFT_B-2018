package com.example.milan.rftproject;

public class Ranklist {
    private String username;
    private int point;
    private String difficulty;

    public Ranklist(String username,int point,String difficulty){
        this.username=username;
        this.point=point;
        this.difficulty=difficulty;
    }

    protected String getUsername()
    {
        return username;
    }

    protected  int getPoint(){
        return point;
    }

    protected String getDifficulty(){return difficulty;}
}
