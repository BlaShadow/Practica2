package org.blashadow.practica05;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.List;


public class Detalle extends ActionBarActivity {

    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        try{
            Long idPost = getIntent().getExtras().getLong("idpost");

            post = new Select().from(Post.class).where("id=?",idPost).executeSingle();

            ((TextView)findViewById(R.id.details)).setText(post.details);
            ((TextView)findViewById(R.id.title)).setText(post.title);
        }catch(Exception ex){
            finish();
        }


        bindComentario();;

    }


    public void bindComentario(){
        ListView lv = (ListView)findViewById(R.id.comentarios_lv);

        List<Comentario> comentarios = new Select().from(Comentario.class).where("postid = ?",post.getId()).execute();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        for(int i=0;i<comentarios.size();i++){
            adapter.add(comentarios.get(i).comentario);
        }

        lv.setAdapter(adapter);
    }

    public void crearComentario(View v){
        EditText txt = (EditText)findViewById(R.id.add_comment);

        String comentario = txt.getText().toString();

        Comentario tmp = new Comentario();

        tmp.postid = post.getId();
        tmp.comentario = comentario;

        tmp.save();

        bindComentario();;
    }
}
