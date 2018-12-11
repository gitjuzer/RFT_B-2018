package com.example.milan.rftproject;

public class Question {
    private String question;
    private String category;
    private String wrong1;
    private String wrong2;
    private String wrong3;
    private String correct;


    public Question(String category,String correct,String question,String wrong1,String wrong2,String wrong3) {
        this.category = category;
        this.correct = correct;
        this.question = question;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
        this.wrong3 = wrong3;
    }

    protected String getQuestion()
    {
        return question;
    }

    protected String getWrong1()
    {
        return wrong1;
    }

    protected String getWrong2()
    {
        return wrong2;
    }

    protected String getWrong3()
    {
        return wrong3;
    }

    protected String getCorrect()
    {
        return correct;
    }

    protected String getCategory()
    {
        return category;
    }
}
