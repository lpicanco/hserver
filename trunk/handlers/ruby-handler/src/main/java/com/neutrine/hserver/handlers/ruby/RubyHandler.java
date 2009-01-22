/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.neutrine.hserver.handlers.ruby;

import com.neutrine.hserver.handlers.HServerHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 *
 * @author luizpicanco
 */
public class RubyHandler implements HServerHandler {

    private String root;
    
    public void setRoot(String root) {
        this.root = root;
    }

    public void handle(HttpExchange arg0) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private byte[] getFile(String file) {
        byte[] bytes = null;

        try {
            ScriptEngineManager m = new ScriptEngineManager();
            ScriptEngine engine = m.getEngineByName("jruby");
            ScriptContext context = engine.getContext();
            
            BufferedReader br = new BufferedReader(new FileReader(root + file));
            try {
                StringBuilder contents = new StringBuilder();
                String line = null;

                while ((line = br.readLine()) != null){
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }

                String script = contents.toString();
                bytes = ((String)engine.eval(script)).getBytes();
            } finally {
                br.close();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            bytes = e.getMessage().getBytes();
        }

        return bytes;
    }

}
