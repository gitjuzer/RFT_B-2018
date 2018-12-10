package com.example.milan.rftproject;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    View view;
    TextView resulttext,usernametext,pointtext,difficultytext;
    List<Ranklist> ranklist=new ArrayList<Ranklist>();
    TableLayout ranklistlayout;
    TableRow ranklistrow;
    public GlobalHsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_global_hs, container, false);
        ranklistlayout=(TableLayout) view.findViewById(R.id.ranklayout);
        ranklistrow=(TableRow) view.findViewById(R.id.rankrow);
        ranklistlayout.setColumnStretchable(0,true);
        ranklistlayout.setColumnStretchable(1,true);
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
                String myResponse =response.body().string();
                JSONArray jsonarray = null;
                String username="asd";
                try {
                    jsonarray = new JSONArray(myResponse);
                    for(int i=0;i<jsonarray.length();i++){
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        String difficulty = jsonobject.getString("difficulty");
                        int point = jsonobject.getInt("point");
                        username = jsonobject.getString("username");
                        Ranklist rank=new Ranklist(username,point,difficulty);
                        ranklist.add(rank);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i=0;i<ranklist.size();i++){
                    ranklistrow=new TableRow(getContext());
                    usernametext=new TextView(getContext());
                    pointtext=new TextView(getContext());
                    difficultytext=new TextView(getContext());
                    usernametext.setText("adsad");
                    pointtext.setText("22");
                    difficultytext.setText("medium");
                    ranklistrow.addView(usernametext);
                    ranklistrow.addView(pointtext);
                    ranklistrow.addView(difficultytext);
                    ranklistlayout.addView(ranklistrow);
                }
               // resulttext.setText(ranklist.get(0).getUsername()+""+ranklist.get(0).getPoint()+""+ranklist.get(0).getDifficulty());
                //resulttext.setText(myResponse.substring(0,50));

            }
        });
        return view;
    }

}
