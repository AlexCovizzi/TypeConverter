package com.alexcovizzi.typeconverter.converters;

import com.alexcovizzi.typeconverter.Converter;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Alex on 15/12/2017.
 */
public class BlankConverter implements Converter {

    @Nullable
    @Override
    public Object convert(Object value) throws InvalidConversionException {
        return value;
    }
}
