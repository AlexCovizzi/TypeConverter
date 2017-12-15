package com.alexcovizzi.typeconverter.converters;

import com.alexcovizzi.typeconverter.Converter;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import org.jetbrains.annotations.Nullable;

/**
 * Convert to Byte.
 */
public class ByteConverter  implements Converter<Byte> {

    @Nullable
    @Override
    public Byte convert(Object value) throws InvalidConversionException {
        if(value == null) return null;
        NumberConverter converter = new NumberConverter();
        return converter.convert(value).byteValue();
    }
}
