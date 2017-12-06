
import exceptions.InvalidConversionException;
import exceptions.UnsupportedTypeException;

/**
 * Created by Alex on 05/12/2017.
 */
public class ToDoubleConverterFactory implements ConverterFactory {


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

    public class FromStringConverter implements Converter<String, Double> {
        @Override
        public Double convert(String value) throws InvalidConversionException {
            if(value == null) return null;

            try {
                return Double.valueOf(value);
            } catch (NumberFormatException e) {
                throw new InvalidConversionException();
            }
        }
    }

    public class FromBooleanConverter implements Converter<Boolean, Double> {
        @Override
        public Double convert(Boolean value) throws InvalidConversionException {
            if(value == null) return null;

            if(value) return 1D;
            else return 0D;
        }
    }

    public class FromNumberConverter implements Converter<Number, Double> {
        @Override
        public Double convert(Number value) throws InvalidConversionException {
            if(value == null) return null;

            return value.doubleValue();
        }
    }

}
