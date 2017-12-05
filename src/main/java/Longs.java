import annotations.Nullable;
import exceptions.InvalidConversionException;
import exceptions.UnsupportedTypeException;

/**
 * Created by Alex on 05/12/2017.
 */
public class Longs {

    public static Long valueOf(@Nullable Object o, @Nullable Long defValue) {
        Long result = defValue;
        try {
            result = valueOf(o);
            if(result == null) result = defValue;
        } catch (InvalidConversionException e) {
            //
        } finally {
            return result;
        }
    }

    public static Long valueOf(@Nullable Object o) {
        if(o == null) return null;

        String type = o.getClass().getCanonicalName();

        if(type.equals(Type.TYPE_LONG)) return (Long)o;
        else if(type.equals(Type.TYPE_STRING)) return valueOf((String)o);
        else if(type.equals(Type.TYPE_BOOLEAN)) return valueOf((Boolean)o);
        else if(type.equals(Type.TYPE_INTEGER)) return valueOf((Integer)o);
        else if(type.equals(Type.TYPE_FLOAT)) return valueOf((Float)o);
        else if(type.equals(Type.TYPE_DOUBLE)) return valueOf((Double)o);
        else throw new UnsupportedTypeException(type);
    }


    private static Long valueOf(String s) {
        StringConverter stringConverter = (new Longs()).new StringConverter();
        return stringConverter.convert(s);
    }

    private static Long valueOf(Boolean i) {
        BooleanConverter booleanConverter = (new Longs()).new BooleanConverter();
        return booleanConverter.convert(i);
    }

    private static Long valueOf(Number n) {
        NumberConverter numberConverter = (new Longs()).new NumberConverter();
        return numberConverter.convert(n);
    }


    /* Converters */

    public class StringConverter implements Converter<String, Long> {
        @Override
        public Long convert(@Nullable String value) throws InvalidConversionException {
            if(value == null) return null;

            try {
                Double d = Double.valueOf(value);
                return d.longValue();
            } catch (NumberFormatException e) {
                throw new InvalidConversionException();
            }
        }
    }

    public class BooleanConverter implements Converter<Boolean, Long> {
        @Override
        public Long convert(Boolean value) throws InvalidConversionException {
            if(value == null) return null;

            if(value) return 1L;
            else return 0L;
        }
    }

    public class NumberConverter implements Converter<Number, Long> {
        @Override
        public Long convert(Number value) throws InvalidConversionException {
            if(value == null) return null;

            return value.longValue();
        }
    }
}
