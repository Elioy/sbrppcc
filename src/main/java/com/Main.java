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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eliot
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, YamlException {
        App a1 = new App("application.yml");
        a1.parseYAML();
        System.out.println("##############################");
        System.out.println(a1);
        System.out.println("##############################");
        try {
            a1.franchirTransitions();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(a1);
        System.out.println("##############################");

    }
}
