package com.PC.PC.ashpazi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class KhordaniActivity extends AppCompatActivity {
    private ListView khordaniLstv;
    private KhordaniAdapter adapter;
    private DataModelKhordani model;
    private List<Khordani> khordanis;
    private Intent intent;
    private TextView txtitle;
    private int positionList;
    private String[] no ={"salad","torshi","ghaza","deser"};

    @Override
    //zamani ke kelide back ra mizanid
    public void onBackPressed() {
        super.onBackPressed();
        //baz shodane MainActivity
        intent=new Intent(KhordaniActivity.this,MainActivity.class);
        startActivity(intent);
        //baste shodane KhordaniActivity
        KhordaniActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khordani);
        khordaniLstv = (ListView) findViewById(R.id.khordani_lstv);
        txtitle=(TextView)findViewById(R.id.txt_name);
        registerForContextMenu(khordaniLstv);
        Typeface font=Typeface.createFromAsset(getAssets(),"btabassom.ttf");
        txtitle.setTypeface(font);
        refresh();
        //zamani ke rooye yeki az ghazaha touch mishavad
        khordaniLstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //FoodActivity ra be intent midahad
                intent=new Intent(getApplicationContext(),FoodActivity.class);
                //makane ghazai ke touch mishavad dar list
                positionList=position;
                //id ghaza ra be intent vasl mikonad
                intent.putExtra("id",khordanis.get(positionList).getId());
                //intent ejra mishavad va FoodActivity baz mishavad
                startActivity(intent);
            }
        });
        //zamani ke rooye yeki az ghazaha angosht ra negah midarid
        khordaniLstv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //makane ghazai ke touch mishavad dar list
                positionList=position;
                return false;
            }
        });
    }
    //menui ke vaghti angosht ra negah midarid baz mishavad vas shamele taghir dadan va pak kardan ast
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //vaghti taghir dadan ya pak kardan ra mizanid
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //taghir dadan
            case R.id.edit:
                editAction();
                KhordaniActivity.this.finish();
                break;
            //pak kardan
            case R.id.delete:
                removeDialog();
                break;
        }
        return super.onContextItemSelected(item);
    }
    //taghir dadane ghaza
    public void editAction(){
        //EditActivity ra be intent midahad
        intent=new Intent(KhordaniActivity.this,EditActivity.class);
        //id ghaza ra be intent vasl mikonad
        intent.putExtra("id",khordanis.get(positionList).getId());
        //intent ejra mishavad va EditActivity baz mishavad
        startActivity(intent);
    }
    //pak kardane ghaza
    public void removeDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(KhordaniActivity.this);
        builder.setMessage("مطمئن هستید میخواهید پاک شود؟");
        builder.setCancelable(false);
        //agar bale zade shod
        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                remove();
            }
        });
        //agar kheyr zade shod
        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
    //pak kardane ghaza
    public void remove(){
        model.delete(khordanis.get(positionList).getId());
        refresh();
    }
    //list ra berooz resani mikonad
    public void refresh(){
        model=new DataModelKhordani(this);
        //hame ghazaha ra az database seda mizanad
        khordanis=model.getAllKhordani();
        //list ra be in activity vasl mikonad va neshan midahad
        adapter=new KhordaniAdapter(KhordaniActivity.this,khordanis);
        khordaniLstv.setAdapter(adapter);
    }
}
