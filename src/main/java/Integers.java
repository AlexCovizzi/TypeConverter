import annotations.Nullable;
import exceptions.InvalidConversionException;
import exceptions.UnsupportedTypeException;

/**
 * Created by Alex on 05/12/2017.
 */
public class Integers {

    public static Integer valueOf(@Nullable Object o, @Nullable Integer defValue) {
        Integer result = defValue;
        try {
            result = valueOf(o);
            if(result == null) result = defValue;
        } catch (InvalidConversionException e) {
            //
        } finally {
            return result;
        }
    }

    public static Integer valueOf(@Nullable Object o) {
        if(o == null) return null;

        String type = o.getClass().getCanonicalName();

        if(type.equals(Type.TYPE_INTEGER)) return (Integer)o;
        else if(type.equals(Type.TYPE_STRING)) return valueOf((String)o);
        else if(type.equals(Type.TYPE_BOOLEAN)) return valueOf((Boolean)o);
        else if(type.equals(Type.TYPE_LONG)) return valueOf((Long)o);
        else if(type.equals(Type.TYPE_FLOAT)) return valueOf((Float)o);
        else if(type.equals(Type.TYPE_DOUBLE)) return valueOf((Double)o);
        else throw new UnsupportedTypeException(type);
    }


    private static Integer valueOf(String s) {
        StringConverter stringConverter = (new Integers()).new StringConverter();
        return stringConverter.convert(s);
    }

    private static Integer valueOf(Boolean i) {
        BooleanConverter booleanConverter = (new Integers()).new BooleanConverter();
        return booleanConverter.convert(i);
    }

    private static Integer valueOf(Number n) {
        NumberConverter numberConverter = (new Integers()).new NumberConverter();
        return numberConverter.convert(n);
    }


    /* Converters */

    public class StringConverter implements Converter<String, Integer> {
        @Override
        public Integer convert(@Nullable String value) throws InvalidConversionException {
            if(value == null) return null;

            try {
                Double d = Double.valueOf(value);
                return d.intValue();
            } catch (NumberFormatException e) {
                throw new InvalidConversionException();
            }
        }
    }

    public class BooleanConverter implements Converter<Boolean, Integer> {
        @Override
        public Integer convert(Boolean value) throws InvalidConversionException {
            if(value == null) return null;

            if(value) return 1;
            else return 0;
        }
    }

    public class NumberConverter implements Converter<Number, Integer> {
        @Override
        public Integer convert(Number value) throws InvalidConversionException {
            if(value == null) return null;

            return value.intValue();
        }
    }
}
