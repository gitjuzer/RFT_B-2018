package com.example.milan.rftproject;

public class Question {
private int id;
    private String question;
    private String category;
    private String difficulty;
    private String wrong1;
    private String wrong2;
    private String wrong3;
    private String correct;


    public void User(int id,String question,String category,String difficulty,String wrong1,String wrong2,String wrong3,String correct) {
        this.id=id;
        this.question = question;
        this.category = category;
        this.difficulty = difficulty;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
        this.wrong3 = wrong3;
        this.correct = correct;
    }  

}
