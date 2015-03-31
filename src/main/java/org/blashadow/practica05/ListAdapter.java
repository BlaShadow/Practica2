package org.blashadow.practica05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by blashadow on 3/23/15.
 */
public class ListAdapter extends ArrayAdapter<Post> {

    private Context context;

    public ListAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rootView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.item_post, null);

        Post itemPost = getItem(position);

        TextView title = (TextView)rootView.findViewById(R.id.title_post);
        TextView details = (TextView)rootView.findViewById(R.id.details_post);

        title.setText(itemPost.title);
        details.setText(itemPost.details);

        rootView.setTag(itemPost);

        return  rootView;
    }

}
