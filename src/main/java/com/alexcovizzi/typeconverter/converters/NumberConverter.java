package com.alexcovizzi.typeconverter.converters;

import com.alexcovizzi.typeconverter.Converter;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Alex on 13/12/2017.
 */
public class NumberConverter implements Converter<Number> {

    @Nullable
    @Override
    public Number convert(Object value) throws InvalidConversionException {
        if(value == null) return null;

        if(value instanceof Number) {
            return (Number)value;
        } else if(value instanceof Boolean) {
            if(value.equals(true)) return 1;
            else return 0;
        } else {
            try {
                return Double.valueOf(value.toString());
            } catch (NumberFormatException e) {
                throw new InvalidConversionException(value, Number.class);
            }
        }
    }
}
