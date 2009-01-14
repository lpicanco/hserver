package com.google.code.hserver.handlers;

import com.sun.net.httpserver.HttpHandler;

/**
 *
 * @author Ulysses Souza
 */
public interface HServerHandler extends HttpHandler {

    void setRoot(String root);
}
