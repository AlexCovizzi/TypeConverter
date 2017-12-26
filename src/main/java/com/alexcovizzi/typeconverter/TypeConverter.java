package com.alexcovizzi.typeconverter;

import com.alexcovizzi.typeconverter.converters.*;
import com.alexcovizzi.typeconverter.exceptions.ConverterNotFoundException;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Easily convert any value to a new type.<br>
 * To convert to a new type use the method {@link com.alexcovizzi.typeconverter.TypeConverter#convert(Object)} specifying the object to convert.<br>
 * You can specify a default value using the method {@link com.alexcovizzi.typeconverter.TypeConverter#def(Object)}, the default value is returned if the conversion is invalid
 * ({@link com.alexcovizzi.typeconverter.exceptions.InvalidConversionException} is not thrown) or if the value returned is null.<br>
 * You can choose which converter to use for the conversion specifying the result type/class ({@link com.alexcovizzi.typeconverter.TypeConverter#to(String)})
 * or the converter/s to use ({@link com.alexcovizzi.typeconverter.TypeConverter#with(Converter)}).
 * <br>
 * <br>
 * Example - Conversion without default value <br>
 * <code>TypeConverter.convert(value).to(String.class)</code>
 * <br>
 * <br>
 * Example - Conversion with default value <br>
 * <code>TypeConverter.convert(value).def(defValue).to(String.class)</code>
 * <br>
 * The supported types right now are:
 * <ul>
 *     <li>{@code java.lang.Boolean}</li>
 *     <li>{@code java.lang.Byte}</li>
 *     <li>{@code java.lang.Character}</li>
 *     <li>{@code java.lang.Double}</li>
 *     <li>{@code java.lang.Float}</li>
 *     <li>{@code java.lang.Integer}</li>
 *     <li>{@code java.lang.Long}</li>
 *     <li>{@code java.lang.Number}</li>
 *     <li>{@code java.lang.Short}</li>
 *     <li>{@code java.lang.String}</li>
 * </ul>
 * <br>
 * You can extend/change the supported types by using the methods:
 * <ul>
 *     <li>{@code TypeConverter.add}: Add converters specifying type/class and the converter or a mapping.</li>
 *     <li>{@code TypeConverter.remove}: Remove converters specifying type/class.</li>
 * </ul>
 * <br>
 *
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

    public static void add(Map<String, Converter> map) {
        converterMap.putAll(map);
    }

    public static <T> void add(Class<T> cls, Converter<T> converter) {
        add(cls.getCanonicalName(), converter);
    }

    public static void add(String type, Converter converter) {
        type = primitiveToWrapperType(type);
        converterMap.put(type, converter);
    }

    public static <T> Converter<T> get(Class<T> cls) {
        return (Converter<T>) get(cls.getCanonicalName());
    }

    public static Converter get(String type) {
        type = primitiveToWrapperType(type);
        return converterMap.get(type);
    }

    public static <T> Converter<T> remove(Class<T> cls) {
        return (Converter<T>) remove(cls.getCanonicalName());
    }

    public static Converter remove(String type) {
        return converterMap.remove(type);
    }

    /**
     * Convert the given object.
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
     * Default value to use when the result is null or an InvalidConversionException is thrown.<br>
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
     * Convert the value to the class specified. <br>
     * If no converter is found for this class ConverterNotFoundException is thrown.<br>
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
     * Convert the value to the type specified. (Usually the type is the canonical name of the class) <br>
     * If no converter is found for this class ConverterNotFoundException is thrown.<br>
     * Note: primitive types are automatically changed to their respective wrapper class name (e.g int - Integer)
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
     * Use a specific converter for the conversion.<br>
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
     * Converters to use for the conversion.<br>
     * If more than one converter is specified, the converters are used one after the other in the order they are given.<br>
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
        add(TYPE_STRING, new StringConverter());
        add(TYPE_BOOLEAN, new BooleanConverter());
        add(TYPE_CHAR, new CharConverter());
        add(TYPE_NUMBER, new NumberConverter());
        add(TYPE_BYTE, new ByteConverter());
        add(TYPE_SHORT, new ShortConverter());
        add(TYPE_INTEGER, new IntegerConverter());
        add(TYPE_LONG, new LongConverter());
        add(TYPE_FLOAT, new FloatConverter());
        add(TYPE_DOUBLE, new DoubleConverter());
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
