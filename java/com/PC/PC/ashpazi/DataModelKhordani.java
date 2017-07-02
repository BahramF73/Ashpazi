package com.PC.PC.ashpazi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 5/23/2017.
 */

public class DataModelKhordani extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="mydb";
    private static final String TABLE_NAME="ashpazi";
    private static final String KEY_ESME_KHORDANI="esmekhordani";
    private static final String KEY_MAVADE_LAZEM="mavadelazem";
    private static final String KEY_TARZE_TAHIYE="tarzetahiye";
    private static final String KEY_ID="id";
    private Khordani khordani;
    private SQLiteDatabase db;
    private Cursor cursor;
    private String esmeKhordani,mavadeLazem,tarzeTahiye;
    private int id;

    public DataModelKhordani(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    //database ra tashkil midahad
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +KEY_ESME_KHORDANI+" TEXT, "+KEY_MAVADE_LAZEM+" TEXT, "+KEY_TARZE_TAHIYE+" TEXT);");
    }
    //database ra update mikonad
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TABLE_NAME);
        this.onCreate(db);
    }
    //ghazai ra ezafe mikonad
    public void addKhordani(Khordani khordani){
        db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_ESME_KHORDANI,khordani.getEsmeKhordani());
        values.put(KEY_TARZE_TAHIYE,khordani.getTarzeTahiye());
        values.put(KEY_MAVADE_LAZEM,khordani.getMavadeLazem());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    //yek ghazaha ra be vasile id az db migirad
    public Khordani getKhordani(int id){
        db=getReadableDatabase();
        cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID+" =?;",new String[]{String.valueOf(id)});
        if(cursor!=null) {
            cursor.moveToFirst();
            khordani = new Khordani();
            khordani.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            khordani.setEsmeKhordani(cursor.getString(cursor.getColumnIndex(KEY_ESME_KHORDANI)));
            khordani.setMavadeLazem(cursor.getString(cursor.getColumnIndex(KEY_MAVADE_LAZEM)));
            khordani.setTarzeTahiye(cursor.getString(cursor.getColumnIndex(KEY_TARZE_TAHIYE)));
        }else
            khordani=null;
        return khordani;
    }
    //kolle ghazaha ra migirad
    public List<Khordani> getAllKhordani(){
        db=getReadableDatabase();
        List<Khordani> khordanis=new ArrayList<Khordani>();
        cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+" ;",null);
        if(cursor.moveToFirst()){
            do{
                esmeKhordani=cursor.getString(cursor.getColumnIndex(KEY_ESME_KHORDANI));
                tarzeTahiye=cursor.getString(cursor.getColumnIndex(KEY_TARZE_TAHIYE));
                mavadeLazem=cursor.getString(cursor.getColumnIndex(KEY_MAVADE_LAZEM));
                id=cursor.getInt(cursor.getColumnIndex(KEY_ID));
                khordani=new Khordani(esmeKhordani,mavadeLazem,tarzeTahiye,id);
                khordanis.add(khordani);
            }while (cursor.moveToNext());
        }
        db.close();
        return khordanis;
    }
    //kalameha ra dar database jostojoo mikonad va listi az ghaza ra barmigardanad
    public List<Khordani> search(String search){
        db=getReadableDatabase();
        List<Khordani> khordanis=new ArrayList<Khordani>();
        cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+search,null);
        //chek mikonad ke agar list khali nabud ghazaha ra begirad
        if(cursor.moveToFirst()){
            do {
                esmeKhordani=cursor.getString(cursor.getColumnIndex(KEY_ESME_KHORDANI));
                tarzeTahiye=cursor.getString(cursor.getColumnIndex(KEY_TARZE_TAHIYE));
                mavadeLazem=cursor.getString(cursor.getColumnIndex(KEY_MAVADE_LAZEM));
                id=cursor.getInt(cursor.getColumnIndex(KEY_ID));
                khordani=new Khordani(esmeKhordani,mavadeLazem,tarzeTahiye,id);
                khordanis.add(khordani);
            }while (cursor.moveToNext());
        }
        db.close();
        return khordanis;
    }
    //yek ghaza ra taghir midahad
    public void edit(int id,Khordani khordani){
        db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_ESME_KHORDANI,khordani.getEsmeKhordani());
        values.put(KEY_MAVADE_LAZEM,khordani.getMavadeLazem());
        values.put(KEY_TARZE_TAHIYE,khordani.getTarzeTahiye());
        db.update(TABLE_NAME,values,KEY_ID+" = "+id,null);
        db.close();
    }
    //yek ghaza ra pak mikonad
    public void delete(int id) {
        db = getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
