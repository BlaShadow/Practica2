package org.blashadow.practica05;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by blashadow on 3/23/15.
 */

@Table(name = "Post")
public class Post extends Model {

    public Post(){

    }

    @Column
    public String title;

    @Column
    public String details;

    @Column
    public String date;

}
