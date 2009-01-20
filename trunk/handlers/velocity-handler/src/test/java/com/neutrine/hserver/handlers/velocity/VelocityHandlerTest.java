/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.neutrine.hserver.handlers.velocity;

import com.neutrine.hserver.HServer;
import java.util.Properties;
import junit.framework.TestCase;

/**
 *
 * @author Luiz Picanço
 */
public class VelocityHandlerTest extends TestCase {
    
    public VelocityHandlerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
    public void testStart() throws Exception {
        Properties config = new Properties();

        config.setProperty("server.port", "8081");
        config.setProperty("server.root", "/temp");
        config.setProperty("server.handler", "com.neutrine.hserver.handlers.velocity.VelocityHandler");
        config.setProperty("server.lib", "/lib");
        config.setProperty("server.handlers", "/handlers");
 
        HServer server = new HServer(config);
        server.start();
        //Thread.currentThread().join();
    }

}
