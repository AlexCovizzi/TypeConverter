package com.alexcovizzi.typeconverter;


import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;

/**
 * Created by Alex on 05/12/2017.
 */
public class Main {
    public static void main(String[] args) {
        String s = "3.14";
        print((int)TypeConverter.convert(s).def(1).with(new Converter<String, Integer>() {
            @Override
            public Integer convert(String value) throws InvalidConversionException {
                return Integer.parseInt(value);
            }
        }));
    }

    private static void print(Object obj) {
        System.out.println(obj);
    }
}
