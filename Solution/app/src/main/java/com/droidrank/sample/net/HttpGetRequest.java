package com.droidrank.sample.net;

import android.os.AsyncTask;
import android.util.Log;

import com.droidrank.sample.MainActivity;
import com.droidrank.sample.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by pragati.singh on 7/4/2017.
 */

public class HttpGetRequest extends AsyncTask<String, Void, String> {
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    @Override
    protected String doInBackground(String... params){
        String stringUrl = params[0];
        String result = Utility.getResponse(stringUrl);

       /* try {
            //Create a URL object holding our url
            URL myUrl = new URL(stringUrl);

            //Create a connection
            HttpURLConnection connection =(HttpURLConnection)
                    myUrl.openConnection();

            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            //Connect to our url
            connection.connect();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return result;
    }

    @Override
    protected void onPostExecute(String responseImageList) {
        super.onPostExecute(responseImageList);

        if (!responseImageList.trim().isEmpty()){
            try {
                JSONObject responseJson = new JSONObject(responseImageList);
                JSONArray jsonArray = JsonUtils.getJsonArrayFromJSON(responseJson, "images");
                if (jsonArray != null) {
                    int listSize=jsonArray.length();
                    for (int i =0;i<listSize;i++) {
                        MainActivity.imageList.add(JsonUtils.getStringFromJSON((JSONObject) jsonArray.get(i),"imageUrl"));
                    }
                    MainActivity.imageList.trimToSize();
                }
                Log.d("response",jsonArray.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}