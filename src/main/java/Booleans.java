
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

        if(type.equals(Type.TYPE_STRING)) return valueOf((String)o);
        else if(type.equals(Type.TYPE_INTEGER)) return valueOf((int)o);
        else if(type.equals(Type.TYPE_LONG)) return valueOf((long)o);
        else if(type.equals(Type.TYPE_FLOAT)) return valueOf((float)o);
        else if(type.equals(Type.TYPE_DOUBLE)) return valueOf((double)o);
        else throw new UnsupportedTypeException(type);
    }

    private static boolean valueOf(String s) {
        StringConverter stringConverter = (new Booleans()).new StringConverter();
        return stringConverter.convert(s);
    }

    private static boolean valueOf(int i) {
        IntegerConverter integerConverter = (new Booleans()).new IntegerConverter();
        return integerConverter.convert(i);
    }

    private static boolean valueOf(long l) {
        LongConverter longConverter = (new Booleans()).new LongConverter();
        return longConverter.convert(l);
    }

    private static boolean valueOf(float f) {
        FloatConverter floatConverter = (new Booleans()).new FloatConverter();
        return floatConverter.convert(f);
    }

    private static boolean valueOf(double d) {
        DoubleConverter doubleConverter = (new Booleans()).new DoubleConverter();
        return doubleConverter.convert(d);
    }

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

    public class IntegerConverter implements Converter<Integer, Boolean> {
        @Override
        public Boolean convert(Integer value) throws InvalidConversionException {
            if(value == null) return null;

            if(value == 1) return true;
            else if(value == 0) return false;
            else throw new InvalidConversionException();
        }
    }

    public class LongConverter implements Converter<Long, Boolean> {
        @Override
        public Boolean convert(Long value) throws InvalidConversionException {
            if(value == null) return null;

            if(value == 1) return true;
            else if(value == 0) return false;
            else throw new InvalidConversionException();
        }
    }

    public class FloatConverter implements Converter<Float, Boolean> {
        @Override
        public Boolean convert(Float value) throws InvalidConversionException {
            if(value == null) return null;

            if(value == 1) return true;
            else if(value == 0) return false;
            else throw new InvalidConversionException();
        }
    }

    public class DoubleConverter implements Converter<Double, Boolean> {
        @Override
        public Boolean convert(Double value) throws InvalidConversionException {
            if(value == null) return null;

            if(value == 1) return true;
            else if(value == 0) return false;
            else throw new InvalidConversionException();
        }
    }
}
