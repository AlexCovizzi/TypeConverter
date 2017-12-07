package com.alexcovizzi.typeconverter;

/**
 * Created by Alex on 06/12/2017.
 */
public interface ConverterFactory {

    Converter getConverter(String type);
}
