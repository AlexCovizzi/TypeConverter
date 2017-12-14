package com.alexcovizzi.typeconverter;

import com.alexcovizzi.typeconverter.converters.*;
import com.alexcovizzi.typeconverter.exceptions.ConverterNotFoundException;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class TypeConverter {
    private static final Map<String, Converter<?>> converterMap = new ConcurrentHashMap();

    public static Builder convert(@Nullable Object value) {
        return (new TypeConverter()).new Builder(value);
    }

    public static <T> void addConverter(Class<T> cls, Converter<T> converter) {
        String key = cls.getCanonicalName();
        addConverter(key, converter);
    }

    public static void addConverter(String type, Converter<?> converter) {
        converterMap.put(type, converter);
    }

    @Nullable
    public static <T>Converter<T> getConverter(Class<T> cls) {
        String key = cls.getCanonicalName();
        return (Converter<T>) getConverter(key);
    }

    @Nullable
    public static Converter<?> getConverter(String type) {
        return converterMap.get(type);
    }

    public static void removeConverter(Class<?> cls) {
        String key = cls.getCanonicalName();
        removeConverter(key);
    }

    public static void removeConverter(String type) {
        converterMap.remove(type);
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
            return with(getConverter(type));
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

    static {
        addConverter(String.class, new StringConverter());
        addConverter(Boolean.class, new BooleanConverter());
        addConverter(Character.class, new CharConverter());
        addConverter(Number.class, new NumberConverter());
        addConverter(Byte.class, new ByteConverter());
        addConverter(Short.class, new ShortConverter());
        addConverter(Integer.class, new IntegerConverter());
        addConverter(Long.class, new LongConverter());
        addConverter(Float.class, new FloatConverter());
        addConverter(Double.class, new DoubleConverter());
    }
}
