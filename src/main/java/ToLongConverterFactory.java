
import exceptions.InvalidConversionException;
import exceptions.UnsupportedTypeException;

/**
 * Created by Alex on 05/12/2017.
 */
public class ToLongConverterFactory implements ConverterFactory {

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

    public class FromStringConverter implements Converter<String, Long> {
        @Override
        public Long convert(String value) throws InvalidConversionException {
            if(value == null) return null;

            try {
                Double d = Double.valueOf(value);
                return d.longValue();
            } catch (NumberFormatException e) {
                throw new InvalidConversionException();
            }
        }
    }

    public class FromBooleanConverter implements Converter<Boolean, Long> {
        @Override
        public Long convert(Boolean value) throws InvalidConversionException {
            if(value == null) return null;

            if(value) return 1L;
            else return 0L;
        }
    }

    public class FromNumberConverter implements Converter<Number, Long> {
        @Override
        public Long convert(Number value) throws InvalidConversionException {
            if(value == null) return null;

            return value.longValue();
        }
    }
}
