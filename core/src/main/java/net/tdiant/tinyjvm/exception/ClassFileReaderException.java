package net.tdiant.tinyjvm.exception;

import java.io.IOException;

public class ClassFileReaderException extends IOException {

    private String src;

    public ClassFileReaderException(String src, String message) {
        super(message);
        this.src = src;
    }
}
