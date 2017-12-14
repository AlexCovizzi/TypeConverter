package com.alexcovizzi.typeconverter.exceptions;

/**
 * Thrown
 */
public class InvalidConversionException extends RuntimeException {

    public InvalidConversionException(Class valueClass, Class resultClass) {
        super("Can't convert "+valueClass.getCanonicalName()+" to "+resultClass.getCanonicalName());
    }

    public InvalidConversionException(Object valueOrType, Class resultClass) {
        super("Can't convert "+valueOrType+" to "+resultClass.getCanonicalName());
    }

    public InvalidConversionException(Object valueOrType, String resultType) {
        super("Can't convert "+valueOrType+" to "+resultType);
    }
}
