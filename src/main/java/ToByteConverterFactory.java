import exceptions.InvalidConversionException;
import exceptions.UnsupportedTypeException;

/**
 * Created by Alex on 06/12/2017.
 */
public class ToByteConverterFactory implements ConverterFactory {

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

    public class FromStringConverter implements Converter<String, Byte> {
        @Override
        public Byte convert(String value) throws InvalidConversionException {
            if(value == null) return null;

            try {
                Double d = Double.valueOf(value);
                return d.byteValue();
            } catch (NumberFormatException e) {
                throw new InvalidConversionException();
            }
        }
    }

    public class FromBooleanConverter implements Converter<Boolean, Byte> {
        @Override
        public Byte convert(Boolean value) throws InvalidConversionException {
            if(value == null) return null;

            if(value) return 1;
            else return 0;
        }
    }

    public class FromNumberConverter implements Converter<Number, Byte> {
        @Override
        public Byte convert(Number value) throws InvalidConversionException {
            if(value == null) return null;

            return value.byteValue();
        }
    }
}
