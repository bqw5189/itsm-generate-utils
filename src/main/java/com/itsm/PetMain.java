package com.itsm;

import com.itsm.generate.PetGenerate;
import com.itsm.generate.SpringBootBusinessGenerate;

/**
 * Created by itsm on 16/4/18.
 */
public class PetMain {
    public static void main(String[] args){
//        PetGenerate petGenerate = new PetGenerate();
//        petGenerate.generate();

        SpringBootBusinessGenerate springBootBusinessGenerate = new SpringBootBusinessGenerate();
        springBootBusinessGenerate.generate();
    }
}
