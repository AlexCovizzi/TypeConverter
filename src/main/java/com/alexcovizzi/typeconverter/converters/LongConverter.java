package com.alexcovizzi.typeconverter.converters;

import com.alexcovizzi.typeconverter.Converter;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;

/**
 * Created by Alex on 14/12/2017.
 */
public class LongConverter  implements Converter<Long> {

    @Override
    public Long convert(Object value) throws InvalidConversionException {
        if(value == null) return null;
        NumberConverter converter = new NumberConverter();
        return converter.convert(value).longValue();
    }
}
