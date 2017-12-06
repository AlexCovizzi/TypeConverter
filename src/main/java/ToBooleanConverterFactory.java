
import exceptions.InvalidConversionException;
import exceptions.UnsupportedTypeException;

/**
 * Created by Alex on 05/12/2017.
 */
public class ToBooleanConverterFactory implements ConverterFactory {

    @Override
    public Converter getConverter(String type) {
        if(Type.isNumber(type)) {
            return new FromNumberConverter();
        } else if(type.equals(Type.BOOLEAN)) {
            return new FromBooleanConverter();
        } else if(type.equals(Type.STRING)) {
            return new FromStringConverter();
        }
        throw new UnsupportedTypeException(type);
    }

    /* Converters */

    public class FromStringConverter implements Converter<String, Boolean> {
        @Override
        public Boolean convert(String value) throws InvalidConversionException {
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

    public class FromBooleanConverter implements Converter<Boolean, Boolean> {
        @Override
        public Boolean convert(Boolean value) throws InvalidConversionException {
            if(value == null) return null;

            return value;
        }
    }

    public class FromNumberConverter implements Converter<Number, Boolean> {
        @Override
        public Boolean convert(Number value) throws InvalidConversionException {
            if(value == null) return null;

            if(value.equals(1)) return true;
            else if(value.equals(0)) return false;
            else throw new InvalidConversionException();
        }
    }
}
