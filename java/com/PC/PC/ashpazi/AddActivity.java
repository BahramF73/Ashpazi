package com.PC.PC.ashpazi;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private Button btnadd;
    private Khordani khordani;
    private DataModelKhordani model;
    private EditText etesm,etmavadelazem,ettarzetahiye;
    private TextView txtezafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btnadd=(Button)findViewById(R.id.btnadd2);
        etesm=(EditText)findViewById(R.id.etesm);
        etmavadelazem=(EditText)findViewById(R.id.etmavadelazem);
        ettarzetahiye=(EditText)findViewById(R.id.ettarzetahiye);
        txtezafe=(TextView)findViewById(R.id.txtezafe);
        //fonte title ra taghir midahad
        Typeface font=Typeface.createFromAsset(getAssets(),"btabassom.ttf");
        txtezafe.setTypeface(font);
        //vaghti kelide zakhire kon zade mishavad
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check mikonad ke hame fieldha por bashad
                if(etmavadelazem.getText().toString().equals("")||ettarzetahiye.getText().toString().equals("")||etesm.getText().toString().equals("")){
                    Toast.makeText(AddActivity.this,"همه فیلدها باید پر باشد",Toast.LENGTH_LONG).show();
                }else {
                    //ghaza ra migirad va tabe add ra seda mizanad ke ghaza be database ferestade shavad
                    khordani = new Khordani(etesm.getText().toString(), etmavadelazem.getText().toString(), ettarzetahiye.getText().toString());
                    model = new DataModelKhordani(AddActivity.this);
                    model.addKhordani(khordani);
                    //activity ra mibandad
                    AddActivity.this.finish();
                }
            }
        });
    }
}
