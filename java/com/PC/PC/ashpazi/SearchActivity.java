package com.PC.PC.ashpazi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private Button btnsearch;
    private EditText searchet;
    private String searchtext;
    private ListView searchlstv;
    private DataModelKhordani model;
    private KhordaniAdapter adapter;
    private static final String KEY_MAVADE_LAZEM="mavadelazem";
    private List<String> st;
    private Intent intent;
    private int positionList;
    private List<Khordani> khordanis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchet=(EditText)findViewById(R.id.searchet);
        btnsearch=(Button)findViewById(R.id.btnsearch);
        searchlstv=(ListView)findViewById(R.id.searchlstv);
        //zamani ke rooye yeki az ghazaha touch mishavad
        searchlstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent=new Intent(getApplicationContext(),FoodActivity.class);
                positionList=position;
                intent.putExtra("id",khordanis.get(positionList).getId());
                startActivity(intent);
            }
        });
        //vaghti kelide jostojoo zade mishavad
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st=new ArrayList<String>();
                String t="";
                //check mikonad ke fielde jostojoo khali nabashad
                if(searchtext!=""){
                    //texte jostojoo ra migirad
                    searchtext=searchet.getText().toString();
                    //tabe jostojoo ke check mikonad har tedad kalamei ke be fielde jostojoo dade shavad va ghazai ke hame in kalamate kelidi ra shamel shavad
                    for(int i=0;i<searchtext.length();i++){
                        if(searchtext.charAt(i)==' '&&t!=""){
                           st.add(t);
                           t="";
                        }
                        else {
                            t += searchtext.charAt(i);
                            if((searchtext.length()-1)==i)
                                st.add(t);
                        }
                    }
                    String s="";
                    for(int j=0;j<st.size();j++){
                        s+=(KEY_MAVADE_LAZEM+" LIKE '%"+st.get(j)+"%' ");
                        s+="AND ";
                    }
                    String stx="";
                    for(int k=0;k<(s.length()-4);k++){
                        stx+=s.charAt(k);
                    }
                    model=new DataModelKhordani(SearchActivity.this);
                    khordanis=model.search(stx);
                    adapter=new KhordaniAdapter(SearchActivity.this,khordanis);
                    searchlstv.setAdapter(adapter);
                }else
                    Toast.makeText(SearchActivity.this,"فیلد جستجو خالی است",Toast.LENGTH_LONG).show();

            }
        });
    }
}
