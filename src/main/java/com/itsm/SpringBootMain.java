package com.itsm;

import com.itsm.generate.SpringBootGenerate;

/**
 * Created by itsm on 16/4/18.
 */
public class SpringBootMain {
    public static void main(String[] args){
        SpringBootGenerate generate = new SpringBootGenerate();
        generate.generate();
    }
}
