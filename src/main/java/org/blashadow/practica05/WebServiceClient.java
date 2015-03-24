package org.blashadow.practica05;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by blashadow on 3/23/15.
 */
public class WebServiceClient {

    String url = "http://nightdeveloper.net/feed/json";

    private String inputStreamToString(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringBuilder sb = new StringBuilder();

        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }

    private String parseResponse(HttpResponse response){
        String result = "";
        StatusLine statusLine = response.getStatusLine();

        Log.i("WebServiceClient@request_code","Status code "+statusLine.getStatusCode());
        try {
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                String contentResult = this.inputStreamToString(response.getEntity().getContent());

                Log.i("WebServiceClient@response",contentResult);

                return contentResult;
            }else{
                InputStream content = response.getEntity().getContent();

                if(content  != null) {
                    result = this.inputStreamToString(content);
                    Log.i("WebServiceClient@error", result);
                }else{
                    Log.i("WebServiceClient@error", "null content");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String makeRequest(HttpUriRequest request){
        HttpClient client = new DefaultHttpClient();

        Log.i("webServiceClient@Url", request.getURI().toString());

        try {
            HttpResponse response  = client.execute(request);

            return parseResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    private String makeGetRequest(String url){
        HttpGet request = new HttpGet(url);

        HttpResponse response = null;

        request.addHeader("Content-Type","application/json");

        return makeRequest(request);
    }

    public ArrayList<Post> getPost(){
        ArrayList<Post> result = new ArrayList<Post>();

        String raw = makeGetRequest(url);

        try {
            JSONArray rawData  = new JSONArray(raw);

            for(int i=0;i<rawData.length();i++){
                JSONObject item = new JSONObject(rawData.get(i).toString());

                Post postItem = new Post();

                postItem.title = item.getString("title");
                postItem.details = item.getString("content").substring(0,30);

                result.add(postItem);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  result;
    }
}
