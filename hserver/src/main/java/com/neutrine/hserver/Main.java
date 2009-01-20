/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.neutrine.hserver;

import com.neutrine.hserver.HServer;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.Properties;

/**
 *
 * @author Luiz Picanço
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        Properties config = new Properties();
        File file = new File("./hserver.properties");
        config.load(new FileInputStream(file));

        HServer server = new HServer(config);
        server.start();
    }

}
