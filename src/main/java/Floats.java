import annotations.Nullable;
import exceptions.InvalidConversionException;
import exceptions.UnsupportedTypeException;

/**
 * Created by Alex on 05/12/2017.
 */
public class Floats {

    public static Float valueOf(@Nullable Object o, @Nullable Float defValue) {
        Float result = defValue;
        try {
            result = valueOf(o);
            if(result == null) result = defValue;
        } catch (InvalidConversionException e) {
            //
        } finally {
            return result;
        }
    }

    public static Float valueOf(@Nullable Object o) {
        if(o == null) return null;

        String type = o.getClass().getCanonicalName();

        if(type.equals(Type.TYPE_FLOAT)) return (Float)o;
        else if(type.equals(Type.TYPE_STRING)) return valueOf((String)o);
        else if(type.equals(Type.TYPE_BOOLEAN)) return valueOf((Boolean)o);
        else if(type.equals(Type.TYPE_INTEGER)) return valueOf((Integer)o);
        else if(type.equals(Type.TYPE_LONG)) return valueOf((Long)o);
        else if(type.equals(Type.TYPE_DOUBLE)) return valueOf((Double)o);
        else throw new UnsupportedTypeException(type);
    }


    private static Float valueOf(String s) {
        StringConverter stringConverter = (new Floats()).new StringConverter();
        return stringConverter.convert(s);
    }

    private static Float valueOf(Boolean i) {
        BooleanConverter booleanConverter = (new Floats()).new BooleanConverter();
        return booleanConverter.convert(i);
    }

    private static Float valueOf(Number n) {
        NumberConverter numberConverter = (new Floats()).new NumberConverter();
        return numberConverter.convert(n);
    }


    /* Converters */

    public class StringConverter implements Converter<String, Float> {
        @Override
        public Float convert(@Nullable String value) throws InvalidConversionException {
            if(value == null) return null;

            try {
                Double d = Double.valueOf(value);
                return d.floatValue();
            } catch (NumberFormatException e) {
                throw new InvalidConversionException();
            }
        }
    }

    public class BooleanConverter implements Converter<Boolean, Float> {
        @Override
        public Float convert(Boolean value) throws InvalidConversionException {
            if(value == null) return null;

            if(value) return 1F;
            else return 0F;
        }
    }

    public class NumberConverter implements Converter<Number, Float> {
        @Override
        public Float convert(Number value) throws InvalidConversionException {
            if(value == null) return null;

            return value.floatValue();
        }
    }
}
