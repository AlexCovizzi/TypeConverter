package com.alexcovizzi.typeconverter;

import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;

/**
 * Convert a value to the specified type.
 * @param <T> Result type.
 */
public interface Converter<T> {
    /**
     * Convert a value to the specified type {@code T}.
     * @param value The object to convert.
     * @return The converted object.
     * @throws InvalidConversionException If the value can't be converted to the specified type.
     */
    T convert(Object value) throws InvalidConversionException;
}
