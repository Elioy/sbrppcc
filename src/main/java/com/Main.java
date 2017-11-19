/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eliot
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, YamlException{
        App a1 = new App("application.yml");
        a1.parseYAML();
        Service s1 = a1.getServiceParNom("service 1");
        Service s2 = a1.getServiceParNom("service 2");
        Service s3 = a1.getServiceParNom("service 3");
        for(int i = 0; i<10; i++){
        s1.franchirTransitions();
        s2.franchirTransitions();
        s3.franchirTransitions();
        }
        System.out.println(a1);
    }
}
