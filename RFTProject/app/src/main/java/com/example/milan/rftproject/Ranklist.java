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

    public String getUsername()
    {
        return username;
    }

    public  int getPoint(){
        return point;
    }

    public String getDifficulty(){return difficulty;}
}
