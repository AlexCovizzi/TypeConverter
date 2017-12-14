package com.alexcovizzi.typeconverter.converters;

import com.alexcovizzi.typeconverter.Converter;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Alex on 14/12/2017.
 */
public class ShortConverter  implements Converter<Short> {

    @Nullable
    @Override
    public Short convert(Object value) throws InvalidConversionException {
        if(value == null) return null;
        NumberConverter converter = new NumberConverter();
        return converter.convert(value).shortValue();
    }
}
