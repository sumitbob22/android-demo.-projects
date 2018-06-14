package com.example.sumitbobade.explicitintent;

import java.io.Serializable;

/**
 * Created by sumitbobade on 13/03/18.
 */

public class StudentData implements Serializable{

    int id;
    String name;


    public StudentData(int id,String name)
    {
        this.id=id;
        this.name=name;
    }

}
