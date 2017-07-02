package com.PC.PC.ashpazi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class FoodActivity extends AppCompatActivity {
    private Khordani khordani;
    private DataModelKhordani model;
    private TextView txttarzetahiye,txtesm,txtmavadelazem;

    //ghazai ke entekhab shode ra neshan midahad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        txtesm=(TextView)findViewById(R.id.tesm);
        txtmavadelazem=(TextView)findViewById(R.id.tmavadelazem);
        txttarzetahiye=(TextView)findViewById(R.id.ttarzetahiye);
        //database ra be in activity vasl mikonad
        model=new DataModelKhordani(FoodActivity.this);
        //ghazaye morede nazar ra az database seda mizanad
        khordani=model.getKhordani(getIntent().getExtras().getInt("id"));
        //name ghaza ra neshan midahad
        txtesm.setText(khordani.getEsmeKhordani());
        //tarze tahiye ghaza ra neshan midahad
        txttarzetahiye.setText(khordani.getTarzeTahiye());
        //mavade lazeme ghaza ra neshan midahad
        txtmavadelazem.setText(khordani.getMavadeLazem());

    }
}
