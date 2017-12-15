package com.alexcovizzi.typeconverter.exceptions;

/**
 * Thrown when no converter is found for the given type or class.
 */
public class ConverterNotFoundException extends RuntimeException {

    public ConverterNotFoundException(Class cls) {
        this(cls.getCanonicalName());
    }

    public ConverterNotFoundException(String type) {
        super("No converter found for type "+type+".");
    }
}
