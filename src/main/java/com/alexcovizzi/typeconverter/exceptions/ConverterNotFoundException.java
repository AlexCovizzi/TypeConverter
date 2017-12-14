package com.alexcovizzi.typeconverter.exceptions;

public class ConverterNotFoundException extends RuntimeException {

    public ConverterNotFoundException(Class cls) {
        this(cls.getCanonicalName());
    }

    public ConverterNotFoundException(String type) {
        super("No converter found for type "+type+".");
    }
}
