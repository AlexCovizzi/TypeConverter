package com.alexcovizzi.typeconverter.converters;

import com.alexcovizzi.typeconverter.Converter;
import com.alexcovizzi.typeconverter.ConverterFactory;
import com.alexcovizzi.typeconverter.Type;
import com.alexcovizzi.typeconverter.TypeConverter;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import com.alexcovizzi.typeconverter.exceptions.UnsupportedTypeException;

/**
 * Created by Alex on 05/12/2017.
 */
public class ToIntegerConverterFactory implements ConverterFactory {

    @Override
    public Converter getConverter(String type) {
        return new FromObjectConverter();
    }

    /* Converters */

    public class FromObjectConverter implements Converter<Object, Integer> {
        @Override
        public Integer convert(Object value) throws InvalidConversionException {
            if(value == null) return null;

            Number n = TypeConverter.convert(value).toNumber();
            return n.intValue();
        }
    }
}
