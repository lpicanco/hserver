package com.google.code.hserver.helpers;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author Ulysses Souza
 */
public class GenericFileFilter implements FilenameFilter {

    private String fileExtension;

    public GenericFileFilter() {}

    public GenericFileFilter(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Override
    public boolean accept(File dir, String name) {
        String[] nameTokens = name.split("\\.");
        return nameTokens.length < 1 && nameTokens[ nameTokens.length - 1 ]
                .equals( fileExtension );
    }
   
    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}
