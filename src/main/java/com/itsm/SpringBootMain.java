package com.itsm;

import com.itsm.generate.AbstractGenerate;
import com.itsm.generate.SpringBootBusinessGenerate;
import com.itsm.generate.SpringBootWorkGenerate;

/**
 * Created by itsm on 16/4/18.
 */
public class SpringBootMain {
    public static void main(String[] args){
//        AbstractGenerate generate = new SpringBootGenerate();
        AbstractGenerate generate = new SpringBootWorkGenerate();
        generate.generate();
    }
}
