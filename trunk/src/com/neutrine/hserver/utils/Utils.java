package com.neutrine.hserver.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Ulysses Souza
 */
public class Utils {
    public static void sendBytes(InputStream fis, OutputStream os) throws IOException {
        byte[] buffer = new byte[1024];
        int bytes = 0;
        while ( (bytes = fis.read(buffer) ) != -1) {
            os.write(buffer, 0, bytes);
        }
    }
}
