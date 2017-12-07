package com.alexcovizzi.typeconverter.exceptions;

/**
 * Created by Alex on 05/12/2017.
 */
public class UnsupportedTypeException extends RuntimeException {

    public UnsupportedTypeException(Class cls) {
        this(cls.getCanonicalName());
    }

    public UnsupportedTypeException(String type) {
        super("com.alexcovizzi.typeconverter.Type "+type+" is not supported.");
    }
}
