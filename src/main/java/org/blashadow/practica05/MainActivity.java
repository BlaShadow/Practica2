package org.blashadow.practica05;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    ListView lv;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.lv);

        List<Post> data = new ArrayList<Post>();

        for(int i=0;i<data.size();i++){
            data.get(i).save();
        }

        adapter = new ListAdapter(this,0);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Activity context = MainActivity.this;

                Intent tmpIntent = new Intent(context,Detalle.class);

                tmpIntent.putExtra("idpost", ((Post)view.getTag()).getId() );

                context.startActivity(tmpIntent);
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        getData();
    }

    public void salida(View V){
        finish();

    }

    public void getData(){
        List<Post> result = null;

        try{
            WebServiceClient client = new WebServiceClient();

            result  = client.getPost();

            adapter.addAll(result);
        }catch(Exception ex){

        }

        if(result == null || result.size() == 0){
            result = new Select().from(Post.class).execute();

            adapter.addAll(result);

            adapter = new ListAdapter(this,0);
        }

        for(int i=0;i<result.size();i++){
            result.get(i).save();
        }
    }
}
