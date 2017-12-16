package com.alexcovizzi.typeconverter;

import com.alexcovizzi.typeconverter.converters.*;
import com.alexcovizzi.typeconverter.exceptions.ConverterNotFoundException;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Easily convert any given value to a new type.
 *
 */
public final class TypeConverter {

    public static final String TYPE_BOOLEAN = Boolean.class.getCanonicalName();
    public static final String TYPE_BYTE = Byte.class.getCanonicalName();
    public static final String TYPE_CHAR = Character.class.getCanonicalName();
    public static final String TYPE_DOUBLE = Double.class.getCanonicalName();
    public static final String TYPE_FLOAT = Float.class.getCanonicalName();
    public static final String TYPE_INTEGER = Integer.class.getCanonicalName();
    public static final String TYPE_LONG = Long.class.getCanonicalName();
    public static final String TYPE_NUMBER = Number.class.getCanonicalName();
    public static final String TYPE_SHORT = Short.class.getCanonicalName();
    public static final String TYPE_STRING = String.class.getCanonicalName();

    private static final Map<String, Converter> converterMap = new ConcurrentHashMap<String, Converter>();

    public static void use(Map<String, Converter> map, boolean clear) {
        if(clear) converterMap.clear();
        converterMap.putAll(map);
    }

    public static TypeConverter convert(Object value) {
        return new TypeConverter(value);
    }


    private Object value;
    private Object defValue;
    private boolean hasDef = false;

    private TypeConverter(Object value) {
        this.value = value;
    }

    public TypeConverter def(Object defaultValue) {
        this.defValue = defaultValue;
        this.hasDef = true;

        return this;
    }

    public <T>T to(Class<T> cls) {
        String resultType = cls.getCanonicalName();

        return (T) to(resultType);
    }

    public Object to(String type) {
        type = Utils.primitiveToWrapperType(type);
        Converter converter = converterMap.get(type);
        if(converter == null) throw new ConverterNotFoundException(type);
        return with(converter);
    }

    public Object with(Converter...converters) {
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

    public Byte toByte() {
        return to(Byte.class);
    }

    public Character toChar() {
        return to(Character.class);
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

    static {
        Map<String, Converter> standardConverterMap = new HashMap<String, Converter>();
        standardConverterMap.put(TYPE_STRING, new StringConverter());
        standardConverterMap.put(TYPE_BOOLEAN, new BooleanConverter());
        standardConverterMap.put(TYPE_CHAR, new CharConverter());
        standardConverterMap.put(TYPE_NUMBER, new NumberConverter());
        standardConverterMap.put(TYPE_BYTE, new ByteConverter());
        standardConverterMap.put(TYPE_SHORT, new ShortConverter());
        standardConverterMap.put(TYPE_INTEGER, new IntegerConverter());
        standardConverterMap.put(TYPE_LONG, new LongConverter());
        standardConverterMap.put(TYPE_FLOAT, new FloatConverter());
        standardConverterMap.put(TYPE_DOUBLE, new DoubleConverter());

        use(standardConverterMap, true);
    }
}
