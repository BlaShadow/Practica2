package org.blashadow.practica05;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by blashadow on 3/30/15.
 */

@Table(name="hola")
public class Comentario extends Model {

    @Column
    long postid;

    @Column
    String comentario;
}
