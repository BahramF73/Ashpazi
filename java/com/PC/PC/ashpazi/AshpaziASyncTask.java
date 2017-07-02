package com.PC.PC.ashpazi;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by BahRam on 16/06/2017.
 */

public class AshpaziASyncTask extends AsyncTask<URL,Void,Khordani> {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String link;
    @Override
    protected void onPostExecute(Khordani khordani) {
        super.onPostExecute(khordani);
    }

    @Override
    protected Khordani doInBackground(URL... urls) {
        URL url=createUrl(link);
        return null;
    }
    private URL createUrl(String stringUrl){
        URL url=null;
        try {
            url=new URL(stringUrl);
        }catch (Exception e){
            Log.e(LOG_TAG,e.toString());
            return null;
        }
        return url;
    }
    private String makeHttpRequest(URL url) throws IOException{
        String jsonResponse="";
        HttpURLConnection urlConnection=null;
        InputStream inputStream=null;
        try {
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            inputStream=urlConnection.getInputStream();
            jsonResponse=readFromStream(inputStream);
        }catch (IOException e){
        }finally {
            if (urlConnection!=null){
                urlConnection.disconnect();
            }if(inputStream!=null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    private readFromStream()
}
