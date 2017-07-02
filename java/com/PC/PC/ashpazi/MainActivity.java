package com.PC.PC.ashpazi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView txtName;
    private Button btnadd,btnall;
    private ImageButton btnref;
    private Intent intent;
    private Khordani khordani;
    private DataModelKhordani model;
    private SharedPreferences pref=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.PC.PC.ashpazi.R.layout.activity_main);
        txtName=(TextView)findViewById(com.PC.PC.ashpazi.R.id.txt_name);
        Typeface font=Typeface.createFromAsset(getAssets(),"btabassom.ttf");
        txtName.setTypeface(font);
        btnadd=(Button)findViewById(R.id.btnadd);
        btnref=(ImageButton)findViewById(R.id.ref);
        btnall=(Button)findViewById(R.id.allghaza);
        //click rooye yakhchal
        btnref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //baz shodane SearchActivity
                intent=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        pref=getSharedPreferences("first_run",MODE_PRIVATE);
        //click rooye add
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //baz shodane AddActivity
                intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
        //click rooye liste ghazaha
        btnall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////baz shodane KhordaniActivity
                intent=new Intent(MainActivity.this,KhordaniActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        //in tabe dar avvalin ejraye pas az nasb ejra mishavad va tabe loadJSONFromAsset ra ejra mikonad va dar database zakhire mikonad
        if(pref.getBoolean("first_run",true)) {
            /*try {
                JSONObject object=new JSONObject(loadJSONFromAsset());
                JSONArray khordaniArray=object.getJSONArray("ghaza");
                for(int j=0;j<khordaniArray.length();j++) {
                    khordani = new Khordani( khordaniArray.getJSONObject(j).getString("esm"), khordaniArray.getJSONObject(j).getString("mavade_lazem"), khordaniArray.getJSONObject(j).getString("tarze_tahiye"));
                    model=new DataModelKhordani(MainActivity.this);
                    model.addKhordani(khordani);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }*/
        }

            pref.edit().putBoolean("first_run", false).commit();
    }
    //liste khordani.json ke liste khordaniha ast ra be reshte(String) tabdil mikonad
    /*public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getApplication().getAssets().open("khordani.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }*/
}
