package com.itsm;

import com.itsm.generate.PetGenerate;
import com.itsm.generate.WXGenerate;

/**
 * Created by itsm on 16/4/18.
 */
public class SmartMain {
    public static void main(String[] args){
        WXGenerate wxGenerate = new WXGenerate();
        wxGenerate.generate();
    }
}
