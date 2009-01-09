package com.neutrine.hserver;

import com.neutrine.hserver.handlers.HServerHandler;
import com.neutrine.hserver.utils.Utils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Properties;

/**
 *
 * @author Luiz Picanço
 */
public class HServer {
    private final String SERVER_PORT_CONFIG_KEY = "server.port";
    private final String SERVER_ROOT_CONFIG_KEY = "server.root";

    private int port;
    private String root;
    private Properties config;

    public HServer(Properties config) {
        this.config = config;
        this.port = Integer.parseInt(config.getProperty(SERVER_PORT_CONFIG_KEY));
        this.root = config.getProperty(SERVER_ROOT_CONFIG_KEY);
    }

    /**
     * Inicializa o http server.
     */
    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext( "/", new DefaultHandler(root) );
        server.start();
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the config
     */
    public Properties getConfig() {
        return config;
    }
}

class DefaultHandler implements HServerHandler {
    private String root;

    public DefaultHandler(String root) {
        this.root = root;
    }

    public void handle(HttpExchange t) throws IOException {
        InputStream is = t.getRequestBody();
        OutputStream os = t.getResponseBody();
        BufferedInputStream response = getFile(t.getRequestURI().getPath());
        
        if (response != null) {
            t.sendResponseHeaders(200, response.available());
            Utils.sendBytes(response, os);
        } else {
            String resp = "Not Found";
            
            t.sendResponseHeaders(404, resp.length());
            os.write(resp.getBytes());
        }

        //os.write(response.getBytes());
        os.close();
    }

    private BufferedInputStream getFile(String file) {
        BufferedInputStream bis = null;
        
        try {
            
            bis = new BufferedInputStream(new FileInputStream(root + file));

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        return bis;
    }

    
}

