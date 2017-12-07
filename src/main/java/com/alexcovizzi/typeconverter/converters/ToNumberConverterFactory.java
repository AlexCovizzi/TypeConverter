package com.alexcovizzi.typeconverter.converters;

import com.alexcovizzi.typeconverter.Converter;
import com.alexcovizzi.typeconverter.ConverterFactory;
import com.alexcovizzi.typeconverter.Type;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import com.alexcovizzi.typeconverter.exceptions.UnsupportedTypeException;

/**
 * Created by Alex on 07/12/2017.
 */
public class ToNumberConverterFactory implements ConverterFactory {

    @Override
    public Converter getConverter(String type) {
        if(Type.isNumber(type)) {
            return new FromNumberConverter();
        } else if(type.equals(Type.BOOLEAN)) {
            return new FromBooleanConverter();
        } else if(type.equals(Type.STRING)) {
            return new FromStringConverter();
        }
        throw new UnsupportedTypeException(type);
    }

    /* Converters */

    public class FromStringConverter implements Converter<String, Number> {
        @Override
        public Number convert(String value) throws InvalidConversionException {
            if(value == null) return null;

            try {
                return Double.valueOf(value);
            } catch (NumberFormatException e) {
                throw new InvalidConversionException();
            }
        }
    }

    public class FromBooleanConverter implements Converter<Boolean, Number> {
        @Override
        public Number convert(Boolean value) throws InvalidConversionException {
            if(value == null) return null;

            if(value) return 1;
            else return 0;
        }
    }

    public class FromNumberConverter implements Converter<Number, Number> {
        @Override
        public Number convert(Number value) throws InvalidConversionException {
            if(value == null) return null;

            return value;
        }
    }
}
