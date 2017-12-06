
import exceptions.InvalidConversionException;

/**
 * Created by Alex on 05/12/2017.
 */
public class ToStringConverterFactory implements ConverterFactory {

    @Override
    public Converter getConverter(String valueType) {
        return new FromObjectConverter();
    }

    /* Converters */
    public class FromObjectConverter implements Converter<Object, String> {
        @Override
        public String convert(Object value) throws InvalidConversionException {
            if(value == null) return null;

            return value.toString();
        }
    }
}
