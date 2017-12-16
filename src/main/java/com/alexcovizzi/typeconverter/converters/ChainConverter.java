package com.alexcovizzi.typeconverter.converters;

import com.alexcovizzi.typeconverter.Converter;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 12/12/2017.
 */
public class ChainConverter implements Converter {

    private List<Converter> converters;

    public ChainConverter(Converter...converterArray) {
        converters = new ArrayList<Converter>();
        for(Converter converter : converterArray) {
            converters.add(converter);
        }
    }

    public void concat(Converter converter) {
        converters.add(converter);
    }

    @Override
    public Object convert(Object value) throws InvalidConversionException {
        Object result = value;
        for(Converter converter : converters) {
            result = converter.convert(result);
        }
        return result;
    }
}
