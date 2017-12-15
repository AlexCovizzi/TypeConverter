package com.alexcovizzi.typeconverter.exceptions;


import com.alexcovizzi.typeconverter.Converter;

/**
 * Thrown by {@link Converter#convert(Object)}
 * when the given value can't be converted to the converter type.
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
