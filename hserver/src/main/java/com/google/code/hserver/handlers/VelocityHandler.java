/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.google.code.hserver.handlers;

import com.google.code.hserver.handlers.HServerHandler;
import com.neutrine.hserver.utils.Utils;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 *
 * @author Luiz Picanço
 */
public class VelocityHandler implements HServerHandler {
    private String root;

    public void handle(HttpExchange t) throws IOException {
        InputStream is = t.getRequestBody();
        OutputStream os = t.getResponseBody();
        byte[] response =  getFile(t.getRequestURI().getPath());

        if (response != null) {
            t.sendResponseHeaders(200, response.length);
            Utils.sendBytes(response, os);
        } else {
            String resp = "Not Found";

            t.sendResponseHeaders(404, resp.length());
            os.write(resp.getBytes());
        }

        os.close();
    }

    private byte[] getFile(String file) {
        byte[] bytes = null;

        try {
            VelocityEngine ve = new VelocityEngine();
            ve.setProperty("file.resource.loader.path", root );
            ve.setProperty("resource.loader", "file" );
            ve.init();

            VelocityContext context = new VelocityContext();
            context.put("file", file);
            Template t = ve.getTemplate(file);

            StringWriter writer = new StringWriter();
            t.merge(context, writer);

            bytes = writer.toString().getBytes();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            bytes = e.getMessage().getBytes();
        }

        return bytes;
    }

    public void setRoot(String root) {
        this.root = root;
    }
}
