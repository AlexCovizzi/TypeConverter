package com.alexcovizzi.typeconverter;

import com.alexcovizzi.typeconverter.converters.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ConcurrentHashMap;

class ConverterMap extends ConcurrentHashMap<String, Converter<?>> {

    public ConverterMap() {
        super();
        put(String.class, new StringConverter());
        put(Boolean.class, new BooleanConverter());
        put(Character.class, new CharConverter());
        put(Number.class, new NumberConverter());
        put(Byte.class, new ByteConverter());
        put(Short.class, new ShortConverter());
        put(Integer.class, new IntegerConverter());
        put(Long.class, new LongConverter());
        put(Float.class, new FloatConverter());
        put(Double.class, new DoubleConverter());
    }

    public <T>Converter<T> put(@NotNull Class<T> cls, @NotNull Converter<T> converter) {
        if(cls == null) throw new IllegalArgumentException("Argument class can't be null.");
        if(converter == null) throw new IllegalArgumentException("Argument converter can't be null.");

        String key = cls.getCanonicalName();
        return (Converter<T>) put(key, converter);
    }

    @Nullable
    public <T> Converter<T> get(@NotNull Class<T> cls) {
        if(cls == null) throw new IllegalArgumentException("Argument class can't be null.");

        String key = cls.getCanonicalName();
        return (Converter<T>) get(key);
    }

    public <T> Converter<T> remove(@NotNull Class<T> cls) {
        if(cls == null) throw new IllegalArgumentException("Argument class can't be null.");

        String key = cls.getCanonicalName();
        return (Converter<T>) remove(key);
    }
}
