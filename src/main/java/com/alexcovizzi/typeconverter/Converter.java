package com.alexcovizzi.typeconverter;

import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import org.jetbrains.annotations.Nullable;

public interface Converter<T> {
    @Nullable
    T convert(Object value) throws InvalidConversionException;
}
