import annotations.Nullable;
import exceptions.InvalidConversionException;
import exceptions.UnsupportedTypeException;

/**
 * Created by Alex on 05/12/2017.
 */
public class Doubles {

    public static Double valueOf(@Nullable Object o, @Nullable Double defValue) {
        Double result = defValue;
        try {
            result = valueOf(o);
            if(result == null) result = defValue;
        } catch (InvalidConversionException e) {
            //
        } finally {
            return result;
        }
    }

    public static Double valueOf(@Nullable Object o) {
        if(o == null) return null;

        String type = o.getClass().getCanonicalName();

        if(type.equals(Type.TYPE_DOUBLE)) return (Double) o;
        else if(type.equals(Type.TYPE_STRING)) return valueOf((String)o);
        else if(type.equals(Type.TYPE_BOOLEAN)) return valueOf((Boolean)o);
        else if(type.equals(Type.TYPE_INTEGER)) return valueOf((Integer)o);
        else if(type.equals(Type.TYPE_LONG)) return valueOf((Long)o);
        else if(type.equals(Type.TYPE_FLOAT)) return valueOf((Double)o);
        else throw new UnsupportedTypeException(type);
    }


    private static Double valueOf(String s) {
        StringConverter stringConverter = (new Doubles()).new StringConverter();
        return stringConverter.convert(s);
    }

    private static Double valueOf(Boolean i) {
        BooleanConverter booleanConverter = (new Doubles()).new BooleanConverter();
        return booleanConverter.convert(i);
    }

    private static Double valueOf(Number n) {
        NumberConverter numberConverter = (new Doubles()).new NumberConverter();
        return numberConverter.convert(n);
    }


    /* Converters */

    public class StringConverter implements Converter<String, Double> {
        @Override
        public Double convert(@Nullable String value) throws InvalidConversionException {
            if(value == null) return null;

            try {
                return Double.valueOf(value);
            } catch (NumberFormatException e) {
                throw new InvalidConversionException();
            }
        }
    }

    public class BooleanConverter implements Converter<Boolean, Double> {
        @Override
        public Double convert(Boolean value) throws InvalidConversionException {
            if(value == null) return null;

            if(value) return 1D;
            else return 0D;
        }
    }

    public class NumberConverter implements Converter<Number, Double> {
        @Override
        public Double convert(Number value) throws InvalidConversionException {
            if(value == null) return null;

            return value.doubleValue();
        }
    }

}
