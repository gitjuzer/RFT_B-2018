package com.example.milan.rftproject;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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
public class PersonalHsFragment extends Fragment {
    StringBuilder builder;
    View view;
    TextView resulttext;
    static List<Ranklist> ranklist=new ArrayList<Ranklist>();;
    LinearLayout charlayout;
    TableLayout tl;
    public PersonalHsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_personal_hs, container, false);
        charlayout=(LinearLayout) view.findViewById(R.id.charlayout);
        tl=(TableLayout) view.findViewById(R.id.tabl);
        String url="http://srv21.firstheberg.net:5000/getPersonalHighScore?username="+SharedUtils.getUsername(getContext());
        OkHttpClient client = new OkHttpClient();
        String cred=Credentials.basic(SharedUtils.getUsername(getContext()),SharedUtils.getPassword(getContext()));
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization",cred)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getContext(),"Server unavaible",Toast.LENGTH_LONG);
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
                            if (ranklist.isEmpty()){
                                isemptyranklist();
                            }else {
                                tableplayoutcreate(ranklist);
                            }

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        });

        return view;
    }

    public void isemptyranklist(){
        TableRow tr=new TableRow(getContext());
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
        TextView emptytext=new TextView(getContext());
        emptytext.setText("You don't have highscore yet.");
        emptytext.setTextSize(24);
        emptytext.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
        tr.addView(emptytext);
        tl.addView(tr);
    }
    public void tableplayoutcreate(List<Ranklist> rl){
        for(int i=0;i<rl.size();i++){
            TextView ranknum=new TextView(getContext());
            TextView usernametext=new TextView(getContext());
            TextView difficultytext=new TextView(getContext());
            TextView pointtext=new TextView(getContext());
            int first= Color.rgb(245,255,63);
            int second= Color.rgb(198,198,190);
            int third=Color.rgb(221,185,37);
            int top=5;
            int bottom=5;
            int left=20;
            int right=30;
            int textsize;
            switch (i){
                case 0:
                    textsize=24;
                    ranknum.setTextColor(first);
                    usernametext.setTextColor(first);
                    difficultytext.setTextColor(first);
                    pointtext.setTextColor(first);
                    break;
                case 1:
                    textsize=20;
                    ranknum.setTextColor(second);
                    usernametext.setTextColor(second);
                    difficultytext.setTextColor(second);
                    pointtext.setTextColor(second);
                    break;
                case 2:
                    textsize=18;
                    ranknum.setTextColor(third);
                    usernametext.setTextColor(third);
                    difficultytext.setTextColor(third);
                    pointtext.setTextColor(third);
                    break;
                default:
                    textsize=16;
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
