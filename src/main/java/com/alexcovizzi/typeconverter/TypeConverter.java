package com.alexcovizzi.typeconverter;

import com.alexcovizzi.typeconverter.converters.*;
import com.alexcovizzi.typeconverter.exceptions.ConverterNotFoundException;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class TypeConverter {
    private static final ConverterMap converterMap = new ConverterMap();

    public static <T> Converter<T> addConverter(@NotNull Class<T> cls, @NotNull Converter<T> converter) {
        return converterMap.put(cls, converter);
    }

    public static Converter<?> addConverter(@NotNull String type, @NotNull Converter<?> converter) {
        return converterMap.put(type, converter);
    }

    @Nullable
    public static <T>Converter<T> getConverter(Class<T> cls) {
        return converterMap.get(cls);
    }

    @Nullable
    public static Converter<?> getConverter(String type) {
        return converterMap.get(type);
    }

    public static <T> Converter<T> removeConverter(Class<T> cls) {
        return converterMap.remove(cls);
    }

    public static Converter<?> removeConverter(String type) {
        return converterMap.remove(type);
    }

    public static Builder convert(@Nullable Object value) {
        return (new TypeConverter()).new Builder(value);
    }

    public class Builder {
        private Object value;
        private Object defValue;
        private boolean hasDef = false;

        public Builder(@Nullable Object value) {
            this.value = value;
        }

        public Builder def(@NotNull Object defaultValue) {
            if(defaultValue == null) throw new IllegalArgumentException("Argument defaultValue can't be null.");
            this.defValue = defaultValue;
            this.hasDef = true;

            return this;
        }

        public <T>T to(@NotNull Class<T> cls) {
            if(cls == null) throw new IllegalArgumentException("Argument cls can't be null.");
            String resultType = cls.getCanonicalName();

            return (T) to(resultType);
        }

        public Object to(@NotNull String type) {
            if(type == null) throw new IllegalArgumentException("Argument type can't be null.");
            type = Utils.primitiveToWrapperType(type);
            Converter converter = getConverter(type);
            if(converter == null) throw new ConverterNotFoundException(type);
            return with(converter);
        }

        public Object with(@NotNull Converter...converters) {
            if(converters == null) throw new IllegalArgumentException("Argument converters can't be null.");
            if(converters.length == 0) throw new IllegalArgumentException("Argument converters can't be empty.");
            Converter converter = new ChainConverter(converters);

            try {
                Object result = converter.convert(value);
                if(result == null && hasDef) result = defValue;
                return result;
            } catch (InvalidConversionException e) {
                if(hasDef) return defValue;
                else throw e;
            }
        }

        public Boolean toBoolean() {
            return to(Boolean.class);
        }

        public String toString() {
            return to(String.class);
        }

        public Number toNumber() {
            return to(Number.class);
        }

        public Short toShort() {
            return to(Short.class);
        }

        public Integer toInteger() {
            return to(Integer.class);
        }

        public Long toLong() {
            return to(Long.class);
        }

        public Float toFloat() {
            return to(Float.class);
        }

        public Double toDouble() {
            return to(Double.class);
        }

        public Character toChar() {
            return to(Character.class);
        }

    }
}
