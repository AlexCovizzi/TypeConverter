package com.alexcovizzi.typeconverter;

import com.alexcovizzi.typeconverter.converters.*;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import com.alexcovizzi.typeconverter.exceptions.UnsupportedTypeException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Alex on 05/12/2017.
 */
public class TypeConverter {

    public static Builder convert(@Nullable Object value) {
        return (new TypeConverter()).new Builder(value);
    }

    public class Builder {
        private Object value;
        private Object defValue;
        private String resultType;
        private Converter converter;

        public Builder(@Nullable Object value) {
            this.value = value;
        }

        public Builder def(@NotNull Object defaultValue) {
            if(defaultValue == null) throw new IllegalArgumentException("Default value can't be null.");
            this.defValue = defaultValue;

            return this;
        }

        public Object to(@NotNull String type) {
            if(type == null) throw new IllegalArgumentException("String type can't be null.");
            this.resultType = type;

            // Find converter based on value type and result type
            String valueType = value.getClass().getCanonicalName();
            ConverterFactory converterFactory = getConverterFactory(resultType);
            Converter converter = converterFactory.getConverter(valueType);

            return with(converter);
        }

        public Object with(@NotNull Converter converter) {
            this.converter = converter;

            Object result;
            if(defValue != null) {
                try {
                    result = convert();
                    if(result == null) result = defValue;
                } catch (Exception e) {
                    result = defValue;
                }
            } else {
                result = convert();
            }
            return result;
        }

        private Object convert() {
            try {
                return converter.convert(value);
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("Invalid parameter type ("+value.getClass().getCanonicalName()+")");
            }
        }

        public Boolean toBoolean() {
            return (Boolean) to(Type.BOOLEAN);
        }

        public String toString() {
            return (String) to(Type.STRING);
        }

        public Number toNumber() {
            return (Number) to(Type.NUMBER);
        }

        public Integer toInteger() {
            return (Integer) to(Type.INTEGER);
        }

        public Long toLong() {
            return (Long) to(Type.LONG);
        }

        public Float toFloat() {
            return (Float) to(Type.FLOAT);
        }

        public Double toDouble() {
            return (Double) to(Type.DOUBLE);
        }

        public Float toShort() {
            return (Float) to(Type.SHORT);
        }

        public Double toByte() {
            return (Double) to(Type.BYTE);
        }

        private ConverterFactory getConverterFactory(String resultType) {
            if(resultType.equals(Type.STRING)) return new ToStringConverterFactory();
            else if(resultType.equals(Type.NUMBER)) return new ToNumberConverterFactory();
            else if(resultType.equals(Type.BOOLEAN)) return new ToBooleanConverterFactory();
            else if(resultType.equals(Type.INTEGER)) return new ToIntegerConverterFactory();
            else if(resultType.equals(Type.LONG)) return new ToLongConverterFactory();
            else if(resultType.equals(Type.FLOAT)) return new ToFloatConverterFactory();
            else if(resultType.equals(Type.DOUBLE)) return new ToDoubleConverterFactory();
            else if(resultType.equals(Type.SHORT)) return new ToShortConverterFactory();
            else if(resultType.equals(Type.BYTE)) return new ToByteConverterFactory();

            throw new UnsupportedTypeException(resultType);
        }
    }
}
