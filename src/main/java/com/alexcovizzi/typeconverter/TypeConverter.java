package com.alexcovizzi.typeconverter;

import com.alexcovizzi.typeconverter.converters.*;
import com.alexcovizzi.typeconverter.exceptions.ConverterNotFoundException;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Easily convert any given value to a new type.
 */
public final class TypeConverter {

    /* Available types */
    public static final String TYPE_BOOLEAN = "java.lang.Boolean";
    public static final String TYPE_BYTE = "java.lang.Byte";
    public static final String TYPE_CHAR = "java.lang.Character";
    public static final String TYPE_DOUBLE = "java.lang.Double";
    public static final String TYPE_FLOAT = "java.lang.Float";
    public static final String TYPE_INTEGER = "java.lang.Integer";
    public static final String TYPE_LONG = "java.lang.Long";
    public static final String TYPE_NUMBER = "java.lang.Number";
    public static final String TYPE_SHORT = "java.lang.Short";
    public static final String TYPE_STRING = "java.lang.String";

    private static final Map<String, Converter> converterMap = new ConcurrentHashMap<String, Converter>();

    /**
     * Use a new mapping when converting.<br/>
     * You can substitute the current mapping or append your new mapping to the current one, in this
     * last case if there are entries with the same key, they are substituted.<br/>
     * The mapping is used when converting with {@code to(String)}
     * (e.g. {@code TypeConverter.convert(value).to(key); } ) <br/>
     * Note: It's suggested to use as key the canonical name of the class you are converting to.
     * Note: Don't use primitive type names as keys.
     *
     * @param map The new mapping to append or substitute.
     * @param substitute If true, substitute the current mapping
     *                   If false, append the new mapping.
     */
    public static void use(Map<String, Converter> map, boolean substitute) {
        if(substitute) converterMap.clear();
        converterMap.putAll(map);
    }

    /**
     * Convert the given object.
     * <p>
     *     This method will return an instance of TypeConverter to build your conversion.<br/>
     *     You can select a default value: {@link TypeConverter#def(Object)}.<br/>
     *     You can select which converter(s) to use: {@link TypeConverter#with(Converter[])}.<br/>
     *     You can convert to a new type given a string: {@link TypeConverter#to(String)}<br/>
     * </p>
     *
     * @param value The object to convert.
     * @return An instance of TypeConverter to build the conversion.
     */
    public static TypeConverter convert(Object value) {
        return new TypeConverter(value);
    }

    private Object value;
    private Object defValue;
    private boolean hasDef = false;

    private TypeConverter(Object value) {
        this.value = value;
    }

    /**
     * Default value to use when the result is null or an InvalidConversionException is thrown.<br/>
     * Note: if a converter is not found ConverterNotFoundException is still thrown.
     *
     * @param defaultValue The default value.
     * @return The TypeConverter instance.
     */
    public TypeConverter def(Object defaultValue) {
        this.defValue = defaultValue;
        this.hasDef = true;

        return this;
    }

    /**
     * Convert the value to the class specified. <br/>
     * If no converter is found for this class ConverterNotFoundException is thrown.<br/>
     * Note: To find the converter for this class it's used the canonical name of the class.
     *
     * @param cls The class to convert to.
     * @param <T>
     * @return The result of this conversion already casted to the class type.
     *
     * @throws NullPointerException If class is null.
     * @throws InvalidConversionException If a conversion failed and there is no default value.
     */
    public <T>T to(Class<T> cls) {
        String resultType = cls.getCanonicalName();

        return (T) to(resultType);
    }

    /**
     * Convert the value to the type specified. (Usually the type is the canonical name of the class) <br/>
     * If no converter is found for this class ConverterNotFoundException is thrown.<br/>
     * Note: primitive types are automatically changed to their respective wrapper class name (e.g int -> Integer)
     *
     * @param type The key to use to find the converter inside the map.
     * @return The result of this conversion as an Object. Can be null if there is no default value.
     *
     * @throws NullPointerException If type is null.
     * @throws InvalidConversionException If a conversion failed and there is no default value.
     */
    public Object to(String type) {
        type = primitiveToWrapperType(type);
        Converter converter = converterMap.get(type);
        if(converter == null) throw new ConverterNotFoundException(type);
        return with(converter);
    }

    /**
     * Use a specific converter for the conversion.<br/>
     *
     * @param converter Converter to use for the conversion.
     * @return The result of this conversion already casted to the converter type. Can be null if there is no default value.
     *
     * @throws NullPointerException If the converter is null.
     * @throws InvalidConversionException If a conversion failed and there is no default value.
     */
    public <T>T with(Converter<T> converter) {
        try {
            Object result = converter.convert(value);
            if(result == null && hasDef) result = defValue;
            return (T) result;
        } catch (InvalidConversionException e) {
            if(hasDef) return (T) defValue;
            else throw e;
        }
    }

    /**
     * Converters to use for the conversion.<br/>
     * If more than one converter is specified, the converters are used one after the other in the order they are given.<br/>
     *
     * @param converters Converters to use for the conversion.
     * @return The result of this conversion as an Object. Can be null if there is no default value.
     *
     * @throws IllegalArgumentException If no converter is given.
     * @throws InvalidConversionException If a conversion failed and there is no default value.
     */
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

    /* Populate the converter map */
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

    /* Transform the given primitive type to its wrapper. If the type is not primitive return the same type. */
    public static String primitiveToWrapperType(String type) {
        if(type.equalsIgnoreCase("boolean")) return "java.lang.Boolean";
        else if(type.equalsIgnoreCase("byte")) return "java.lang.Byte";
        else if(type.equalsIgnoreCase("char")) return "java.lang.Character";
        else if(type.equalsIgnoreCase("float")) return "java.lang.Float";
        else if(type.equalsIgnoreCase("int")) return "java.lang.Integer";
        else if(type.equalsIgnoreCase("long")) return "java.lang.Long";
        else if(type.equalsIgnoreCase("short")) return "java.lang.Short";
        else if(type.equalsIgnoreCase("double")) return "java.lang.Double";
        else return type;
    }
}
