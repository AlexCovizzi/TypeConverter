package com.alexcovizzi.typeconverter.converters;

import com.alexcovizzi.typeconverter.Converter;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Alex on 13/12/2017.
 */
public class CharConverter implements Converter<Character> {

    @Nullable
    @Override
    public Character convert(Object value) throws InvalidConversionException {
        if(value == null) return null;

        if(value instanceof Number) {
            return (char) ((Number) value).doubleValue();
        } else if(value instanceof Boolean) {
            if(value.equals(true)) return '1';
            else return '0';
        } else {
            String v = value.toString();
            if(v.length() == 1) {
                return v.charAt(0);
            } else {
                throw new InvalidConversionException(v, Character.class);
            }
        }
    }
}
