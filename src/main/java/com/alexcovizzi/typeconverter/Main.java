package com.alexcovizzi.typeconverter;

import com.alexcovizzi.typeconverter.converters.CharConverter;
import com.alexcovizzi.typeconverter.converters.IntegerConverter;
import com.alexcovizzi.typeconverter.converters.NumberConverter;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Alex on 05/12/2017.
 */
public class Main {
    public static void main(String[] args) {
        Converter<String> c = new Converter<String>() {
            @Nullable
            @Override
            public String convert(Object value) throws InvalidConversionException {
                return value.toString()+"";
            }
        };
        TypeConverter.addConverter("c", c);
        print(TypeConverter.convert("1").with(c,new CharConverter(), new NumberConverter()));
    }

    private static void print(Object obj) {
        System.out.println(obj);
    }
}
