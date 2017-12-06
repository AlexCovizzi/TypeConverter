
import exceptions.InvalidConversionException;
import exceptions.UnsupportedTypeException;

/**
 * Created by Alex on 05/12/2017.
 */
public class ToIntegerConverterFactory implements ConverterFactory {

    @Override
    public Converter getConverter(String type) {
        if(type.equals(Type.INTEGER) || type.equals(Type.LONG) || type.equals(Type.FLOAT) ||
                type.equals(Type.DOUBLE)) {
            return new FromNumberConverter();
        } else if(type.equals(Type.BOOLEAN)) {
            return new FromBooleanConverter();
        } else if(type.equals(Type.STRING)) {
            return new FromStringConverter();
        }
        throw new UnsupportedTypeException(type);
    }

    /* Converters */

    public class FromStringConverter implements Converter<String, Integer> {
        @Override
        public Integer convert(String value) throws InvalidConversionException {
            if(value == null) return null;

            try {
                Double d = Double.valueOf(value);
                return d.intValue();
            } catch (NumberFormatException e) {
                throw new InvalidConversionException();
            }
        }
    }

    public class FromBooleanConverter implements Converter<Boolean, Integer> {
        @Override
        public Integer convert(Boolean value) throws InvalidConversionException {
            if(value == null) return null;

            if(value) return 1;
            else return 0;
        }
    }

    public class FromNumberConverter implements Converter<Number, Integer> {
        @Override
        public Integer convert(Number value) throws InvalidConversionException {
            if(value == null) return null;

            return value.intValue();
        }
    }
}
