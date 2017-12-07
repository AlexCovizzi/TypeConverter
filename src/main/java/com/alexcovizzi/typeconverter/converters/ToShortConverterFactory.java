package com.alexcovizzi.typeconverter.converters;

import com.alexcovizzi.typeconverter.Converter;
import com.alexcovizzi.typeconverter.ConverterFactory;
import com.alexcovizzi.typeconverter.Type;
import com.alexcovizzi.typeconverter.TypeConverter;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import com.alexcovizzi.typeconverter.exceptions.UnsupportedTypeException;

/**
 * Created by Alex on 06/12/2017.
 */
public class ToShortConverterFactory implements ConverterFactory {

    @Override
    public Converter getConverter(String type) {
        return new FromObjectConverter();
    }

    /* Converters */

    public class FromObjectConverter implements Converter<Object, Short> {
        @Override
        public Short convert(Object value) throws InvalidConversionException {
            if(value == null) return null;

            Number n = TypeConverter.convert(value).toNumber();
            return n.shortValue();
        }
    }
}
