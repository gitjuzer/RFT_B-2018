package com.example.milan.rftproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Homefragment extends Fragment {
    TextView leveltext,difficultytext,question;
    ImageView lifeimage;
    RadioGroup difficultygroup,answergroup;
    RadioButton answer1,answer2,answer3,answer4,selectedRadioButton,selectedRadioButton2;
    View view;
    LinearLayout gamelayout;
    Button startbutton,nextbutton;
    List<Question> questionList=new ArrayList<Question>();
    private int health=3;
    private int level=0;
    private int point=0;
    private int difficultymultipler=0;
    Random rnd;

    public Homefragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_homefragment, container, false);
        leveltext=(TextView) view.findViewById(R.id.leveltext);
        difficultytext=(TextView) view.findViewById(R.id.difficultytext);
        lifeimage=(ImageView) view.findViewById(R.id.lifeimage);
        answer1=(RadioButton) view.findViewById(R.id.answer1);
        answer2=(RadioButton) view.findViewById(R.id.answer2);
        answer3=(RadioButton) view.findViewById(R.id.answer3);
        answer4=(RadioButton) view.findViewById(R.id.answer4);
        gamelayout=(LinearLayout) view.findViewById(R.id.gamelayout);
        startbutton=(Button) view.findViewById(R.id.startbutton);
        nextbutton=(Button) view.findViewById(R.id.nextbutton);
        difficultygroup=(RadioGroup) view.findViewById(R.id.difficultygroup);
        question=(TextView) view.findViewById(R.id.question);
        answergroup=(RadioGroup) view.findViewById(R.id.answergroup);
        gamelayout.setVisibility(LinearLayout.GONE);

        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(difficultygroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getContext(), "You must select level before start.", Toast.LENGTH_SHORT).show();
                }else{
                    int selectedId = difficultygroup.getCheckedRadioButtonId();
                    selectedRadioButton = (RadioButton)view.findViewById(selectedId);
                    startgame(selectedRadioButton.getText().toString());
                    switch (selectedRadioButton.getText().toString()){
                        case "Easy":difficultymultipler=2;break;
                        case "Medium":difficultymultipler=3;break;
                        case "Hard":difficultymultipler=4;break;
                    }
                }
            }
        });
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = answergroup.getCheckedRadioButtonId();
                selectedRadioButton2 = (RadioButton)view.findViewById(selectedId);
                if (selectedRadioButton2.getText().toString().equals(questionList.get(level).getCorrect())){
                }else{
                    if (health==1){
                        Toast.makeText(getContext(),"Game over",Toast.LENGTH_SHORT).show();
                        gamelayout.setVisibility(LinearLayout.GONE);
                        startbutton.setEnabled(true);
                    }
                    health--;
                }
                point=point+(health*difficultymultipler);
                level++;
                int textlevel=level+1;
                if (level==10){
                    Toast.makeText(getContext(),"The game has ended, you earned"+point+"points.",Toast.LENGTH_SHORT).show();
                    sendhighscore(SharedUtils.getUsername(getContext()),selectedRadioButton.getText().toString(),point);
                    gamelayout.setVisibility(LinearLayout.GONE);
                    startbutton.setEnabled(true);
                }else{
                    nextlevel(level,health,textlevel);
                }

            }
        });
        getquestions();
        return view;
    }

    public void startgame(String difficulty){
        level=0;
        point=0;
        health=3;
        lifeimage.setImageResource(R.drawable.life3);
        leveltext.setText("1/10");
        difficultytext.setText(difficulty);
        gamelayout.setVisibility(LinearLayout.VISIBLE);
        startbutton.setEnabled(false);
        question.setText(questionList.get(level).getQuestion());
        answer1.setText(questionList.get(level).getWrong3());
        answer2.setText(questionList.get(level).getWrong2());
        answer3.setText(questionList.get(level).getCorrect());
        answer4.setText(questionList.get(level).getWrong1());

    }

    public void nextlevel(int level,int health,int textlevel){
        switch (health){
            case 1:lifeimage.setImageResource(R.drawable.life1);break;
            case 2:lifeimage.setImageResource(R.drawable.life2);break;
            case 3:lifeimage.setImageResource(R.drawable.life3);break;
        }
        leveltext.setText(textlevel+"/10");
        List<RadioButton> radioButtonList = new ArrayList<RadioButton>();
        radioButtonList.add(answer1);
        radioButtonList.add(answer2);
        radioButtonList.add(answer3);
        radioButtonList.add(answer4);
        List<String> answers = questionList.get(level).getAnswers();
        Collections.shuffle(answers);
        for(int i = 0; i < 4; i++){
            RadioButton thisButton = radioButtonList.get(i);
            thisButton.setText(answers.get(i));
        }

        question.setText(questionList.get(level).getQuestion());
        /*answer1.setText(questionList.get(level).getWrong1());
        answer2.setText(questionList.get(level).getWrong2());
        answer3.setText(questionList.get(level).getWrong3());
        answer4.setText(questionList.get(level).getCorrect());*/
    }
    public void getquestions(){
        String url="http://srv21.firstheberg.net:5000/getTenRandomQuestion";
        OkHttpClient client = new OkHttpClient();
        String cred=Credentials.basic(SharedUtils.getUsername(getContext()),SharedUtils.getPassword(getContext()));
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization",cred)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String myResponse = response.body().string();
                JSONArray jsonarray = null;
                questionList.clear();
                try {
                    jsonarray = new JSONArray(myResponse);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        String category = jsonobject.getString("category");
                        String correct = jsonobject.getString("correct");
                        String question= jsonobject.getString("question");
                        String answer1 = jsonobject.getString("wrong_1");
                        String answer2 = jsonobject.getString("wrong_2");
                        String answer3 = jsonobject.getString("wrong_3");
                        Question question1 = new Question(category, correct, question,answer1,answer2,answer3);
                        questionList.add(question1);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendhighscore(String username,String difficulty,int point){
        String sendhsurl="http://srv21.firstheberg.net:5000/newHighScore?username="+username+"&difficulty="+difficulty+"&point="+point;
        String cred=Credentials.basic(SharedUtils.getUsername(getContext()),SharedUtils.getPassword(getContext()));
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("difficulty", difficulty)
                .add("point", ""+point)
                .build();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(sendhsurl)
                .header("Authorization",cred)
                .post(requestBody)
                .build();

        try {
            Response response =  client.newCall(request).execute();
            final String respMsg = response.body().string();
            final int respcode=response.code();
            Log.d("ALMA","ALMA"+respMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
