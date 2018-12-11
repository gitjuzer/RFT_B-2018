package com.example.milan.rftproject;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class GlobalHsFragment extends Fragment {
    StringBuilder builder;
    View view;
    TextView resulttext;
    static List<Ranklist> ranklist=new ArrayList<Ranklist>();;
    LinearLayout charlayout;
    TableLayout tl;
    public GlobalHsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_global_hs, container, false);
        charlayout=(LinearLayout) view.findViewById(R.id.charlayout);
        tl=(TableLayout) view.findViewById(R.id.tabl);
        String url="http://srv21.firstheberg.net:5000/getHighScores";
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
                ranklist.clear();
                try {
                    jsonarray = new JSONArray(myResponse);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        String difficulty = jsonobject.getString("difficulty");
                        int point = jsonobject.getInt("point");
                        String username = jsonobject.getString("username");
                        Ranklist rank = new Ranklist(username, point, difficulty);
                        ranklist.add(rank);
                    }
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            tableplayoutcreate(ranklist);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        });

        return view;
    }
    public void tableplayoutcreate(List<Ranklist> rl){
        for(int i=0;i<rl.size();i++){
            TextView ranknum=new TextView(getContext());
            TextView usernametext=new TextView(getContext());
            TextView difficultytext=new TextView(getContext());
            TextView pointtext=new TextView(getContext());
            int first= Color.rgb(255,216,61);
            int second= Color.rgb(198,198,190);
            int third=Color.rgb(221,185,37);
            int top=5;
            int bottom=5;
            int left;
            int right;
            int textsize;
            switch (i){
                case 0:
                    textsize=24;
                    left=20;
                    right=30;
                    ranknum.setTextColor(first);
                    usernametext.setTextColor(first);
                    difficultytext.setTextColor(first);
                    pointtext.setTextColor(first);
                    break;
                case 1:
                    textsize=20;
                    left=25;
                    right=35;
                    ranknum.setTextColor(second);
                    usernametext.setTextColor(second);
                    difficultytext.setTextColor(second);
                    pointtext.setTextColor(second);
                    break;
                case 2:
                    textsize=18;
                    left=35;
                    right=45;
                    ranknum.setTextColor(third);
                    usernametext.setTextColor(third);
                    difficultytext.setTextColor(third);
                    pointtext.setTextColor(third);
                    break;
                default:
                    textsize=16;
                    left=20;
                    right=30;
                    break;
            }
            TableRow tr=new TableRow(getContext());
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));

            ranknum.setText(""+(i+1));
            ranknum.setTextSize(textsize);
            ranknum.setPadding(left,top,right,bottom);
            ranknum.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            tr.addView(ranknum);


            usernametext.setText(rl.get(i).getUsername());//
            usernametext.setTextSize(textsize);
            usernametext.setPadding(left,top,right,bottom);
            usernametext.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            tr.addView(usernametext);


            difficultytext.setText(rl.get(i).getDifficulty());
            difficultytext.setTextSize(textsize);
            difficultytext.setPadding(left,top,right,bottom);
            difficultytext.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            tr.addView(difficultytext);


            pointtext.setText(""+rl.get(i).getPoint());
            pointtext.setTextSize(textsize);
            pointtext.setPadding(left,top,right,bottom);
            pointtext.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            tr.addView(pointtext);

            tl.addView(tr);
        }
    }
}
