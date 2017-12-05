
import annotations.NotNull;
import exceptions.InvalidConversionException;
import exceptions.UnsupportedTypeException;

/**
 * Created by Alex on 05/12/2017.
 */
public class TypeConverter {

    public static Object convert(Object value, Object defValue, Class targetClass) {
        return convert(value, defValue, targetClass.getCanonicalName());
    }

    public static Object convert(Object value, Object defValue, String targetType) {
        if(defValue != null && Type.isSupported(targetType)) {
            String defValueType = defValue.getClass().getCanonicalName();
            if(!defValueType.equals(targetType)) {
                try {
                    defValue = convert(defValue, targetType);
                } catch (InvalidConversionException e) {
                    throw new IllegalArgumentException("Invalid default value.");
                }
            }
        }

        Object result = defValue;
        try {
            result = convert(value, targetType);
            if(result == null) result = defValue;
        } catch (InvalidConversionException | UnsupportedTypeException | IllegalArgumentException e) {
            //
        } finally {
            return result;
        }
    }

    public static Object convert(Object value, @NotNull Class targetClass) {
        if(targetClass == null) throw new IllegalArgumentException("Parameter targetClass can't be null.");
        if(value == null) return null;

        return convert(value, targetClass.getCanonicalName());
    }

    public static Object convert(Object value, @NotNull String targetType) {
        if(targetType == null) throw new IllegalArgumentException("Parameter targetType can't be null.");
        if(value == null) return null;

        if(targetType.equals(Type.TYPE_BOOLEAN)) return Booleans.valueOf(value);
        else if(targetType.equals(Type.TYPE_STRING)) return Strings.valueOf(value);
        else if(targetType.equals(Type.TYPE_INTEGER)) return Integers.valueOf(value);
        else if(targetType.equals(Type.TYPE_LONG)) return Longs.valueOf(value);
        else if(targetType.equals(Type.TYPE_FLOAT)) return Floats.valueOf(value);
        else if(targetType.equals(Type.TYPE_DOUBLE)) return Doubles.valueOf(value);
        else throw new UnsupportedTypeException(targetType);
    }

    public static Boolean toBoolean(Object value, Boolean defValue) {
        return Booleans.valueOf(value, defValue);
    }

    public static Boolean toBoolean(Object value) {
        return Booleans.valueOf(value);
    }

    public static String toString(Object value, String defValue) {
        return Strings.valueOf(value, defValue);
    }

    public static String toString(Object value) {
        return Strings.valueOf(value);
    }

    public static Integer toInteger(Object value, Integer defValue) {
        return Integers.valueOf(value, defValue);
    }

    public static Integer toInteger(Object value) {
        return Integers.valueOf(value);
    }

    public static Long toLong(Object value, Long defValue) {
        return Longs.valueOf(value, defValue);
    }

    public static Long toLong(Object value) {
        return Longs.valueOf(value);
    }

    public static Float toFloat(Object value, Float defValue) {
        return Floats.valueOf(value, defValue);
    }

    public static Float toFloat(Object value) {
        return Floats.valueOf(value);
    }

    public static Double toDouble(Object value, Double defValue) {
        return Doubles.valueOf(value, defValue);
    }

    public static Double toDouble(Object value) {
        return Doubles.valueOf(value);
    }

    public static Object convert(Object value, Converter converter) {
        return converter.convert(value);
    }
}
