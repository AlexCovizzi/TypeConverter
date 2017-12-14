package com.alexcovizzi.typeconverter.converters;

import com.alexcovizzi.typeconverter.Converter;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Alex on 13/12/2017.
 */
public class IntegerConverter implements Converter<Integer> {

    @Nullable
    @Override
    public Integer convert(Object value) throws InvalidConversionException {
        if(value == null) return null;
        NumberConverter converter = new NumberConverter();
        return converter.convert(value).intValue();
    }
}
