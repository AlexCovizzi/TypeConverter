package com.alexcovizzi.typeconverter.converters;

import com.alexcovizzi.typeconverter.Converter;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;

/**
 * Convert to Boolean.
 */
public class BooleanConverter implements Converter<Boolean> {

    /**
     * Convert to Boolean.
     *
     * @param value The object to convert.
     * @return {@code true} if the string value of the given object matches '1' or 'true'.
     * {@code false} if the string value of the given object matches '0' or 'false'.
     * {@code null} if the given object is null.
     * @throws InvalidConversionException If the object can't be converted to a boolean.
     */
    @Override
    public Boolean convert(Object value) throws InvalidConversionException {
        if(value == null) return null;

        String v = value.toString();
        if(v.equalsIgnoreCase("true")) return true;
        else if(v.equals("1")) return true;
        else if(v.equalsIgnoreCase("false")) return false;
        else if(v.equals("0")) return false;
        else throw new InvalidConversionException(value, Boolean.class);
    }
}
