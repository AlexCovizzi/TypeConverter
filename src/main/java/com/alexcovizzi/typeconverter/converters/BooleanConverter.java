package com.alexcovizzi.typeconverter.converters;

import com.alexcovizzi.typeconverter.Converter;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Alex on 13/12/2017.
 */
public class BooleanConverter implements Converter<Boolean> {

    @Nullable
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
