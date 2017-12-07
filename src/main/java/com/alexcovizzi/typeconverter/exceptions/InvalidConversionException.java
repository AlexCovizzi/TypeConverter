package com.alexcovizzi.typeconverter.exceptions;

/**
 * Created by Alex on 05/12/2017.
 */
public class InvalidConversionException extends RuntimeException {

    public InvalidConversionException() {
        super();
    }

    public InvalidConversionException(String s) {
        super(s);
    }
}
