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

public class EditActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intent=new Intent(EditActivity.this,KhordaniActivity.class);
        startActivity(intent);
    }

    private EditText etesm,etmavadelazem,ettarzetahiye;
    private Button btnEdit;
    private DataModelKhordani model;
    Khordani khordani;
    private String[] no ={"salad","torshi","ghaza","deser"};
    private Intent intent;
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        txt=(TextView)findViewById(R.id.txtezafe);
        Typeface font=Typeface.createFromAsset(getAssets(),"btabassom.ttf");
        txt.setTypeface(font);
        btnEdit=(Button)findViewById(R.id.btnedit);
        etesm=(EditText)findViewById(R.id.etesm);
        etmavadelazem=(EditText)findViewById(R.id.etmavadelazem);
        ettarzetahiye=(EditText)findViewById(R.id.ettarzetahiye);
        //database ra be in activity vasl mikonad
        model=new DataModelKhordani(EditActivity.this);
        khordani=model.getKhordani(getIntent().getExtras().getInt("id"));
        //name ghaza ra neshan midahad
        etesm.setText(khordani.getEsmeKhordani());
        //tarze tahiye ghaza ra neshan midahad
        ettarzetahiye.setText(khordani.getTarzeTahiye());
        //mavade lazeme ghaza ra neshan midahad
        etmavadelazem.setText(khordani.getMavadeLazem());
        //vaghti kelide zakhire kon zade mishavad
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check mikonad ke hame fieldha por bashad
                if(etmavadelazem.getText().toString().equals("")||ettarzetahiye.getText().toString().equals("")||etesm.getText().toString().equals("")){
                    Toast.makeText(EditActivity.this,"همه فیلدها باید پر باشد",Toast.LENGTH_LONG).show();
                }else {
                    //tarze tahiye ghaza ke taghir karde ra zakhire mikonad
                    khordani.setTarzeTahiye(ettarzetahiye.getText().toString());
                    //mavade lazeme ghaza ke taghir karde ra zakhire mikonad
                    khordani.setMavadeLazem(etmavadelazem.getText().toString());
                    //name ghaza ke taghir karde ra zakhire mikonad
                    khordani.setEsmeKhordani(etesm.getText().toString());
                    //id ghaza ra be database midahad va tabe edit ejra mishavad
                    model.edit(getIntent().getExtras().getInt("id"), khordani);
                    intent = new Intent(EditActivity.this, KhordaniActivity.class);
                    EditActivity.this.finish();
                    startActivity(intent);
                }
            }
        });
    }
}
