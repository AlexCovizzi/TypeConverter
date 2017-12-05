
import annotations.NotNull;
import annotations.Nullable;
import exceptions.InvalidConversionException;
import exceptions.UnsupportedTypeException;

/**
 * Created by Alex on 05/12/2017.
 */
public class Booleans {


    public static Boolean valueOf(@Nullable Object o, @Nullable Boolean defValue) {
        Boolean result = defValue;
        try {
            result = valueOf(o);
            if(result == null) result = defValue;
        } catch (InvalidConversionException e) {
            //
        } finally {
            return result;
        }
    }

    public static Boolean valueOf(@Nullable Object o) {
        if(o == null) return null;

        String type = o.getClass().getCanonicalName();

        if(type.equals(Type.TYPE_BOOLEAN)) return (Boolean)o;
        else if(type.equals(Type.TYPE_STRING)) return valueOf((String)o);
        else if(type.equals(Type.TYPE_INTEGER)) return valueOf((Integer)o);
        else if(type.equals(Type.TYPE_LONG)) return valueOf((Long)o);
        else if(type.equals(Type.TYPE_FLOAT)) return valueOf((Float)o);
        else if(type.equals(Type.TYPE_DOUBLE)) return valueOf((Double)o);
        else throw new UnsupportedTypeException(type);
    }

    private static Boolean valueOf(String s) {
        StringConverter stringConverter = (new Booleans()).new StringConverter();
        return stringConverter.convert(s);
    }

    private static Boolean valueOf(Number n) {
        NumberConverter numberConverter = (new Booleans()).new NumberConverter();
        return numberConverter.convert(n);
    }

    /* Converters */

    public class StringConverter implements Converter<String, Boolean> {
        @Override
        public Boolean convert(@Nullable String value) throws InvalidConversionException {
            if(value == null) return null;

            if(value.equalsIgnoreCase("true") ||
                    value.equalsIgnoreCase("yes") ||
                    value.equalsIgnoreCase("1")) {
                return true;
            } else if(value.equalsIgnoreCase("false") ||
                    value.equalsIgnoreCase("no") ||
                    value.equalsIgnoreCase("0")) {
                return false;
            } else {
                throw new InvalidConversionException();
            }
        }
    }

    public class NumberConverter implements Converter<Number, Boolean> {
        @Override
        public Boolean convert(Number value) throws InvalidConversionException {
            if(value == null) return null;

            if(value == 1) return true;
            else if(value == 0) return false;
            else throw new InvalidConversionException();
        }
    }
}
