import annotations.Nullable;
import exceptions.InvalidConversionException;

/**
 * Created by Alex on 05/12/2017.
 */
public class Strings {

    public static String valueOf(@Nullable Object o, @Nullable String defValue) {
        String result = defValue;
        try {
            result = valueOf(o);
            if(result == null) result = defValue;
        } catch (InvalidConversionException e) {
            //
        } finally {
            return result;
        }
    }

    public static String valueOf(@Nullable Object o) {
        if(o == null) return null;

        ObjectConverter objectConverter = (new Strings()).new ObjectConverter();
        return objectConverter.convert(o);
    }

    /* Converters */

    public class ObjectConverter implements Converter<Object, String> {
        @Override
        public String convert(@Nullable Object value) throws InvalidConversionException {
            if(value == null) return null;

            return value.toString();
        }
    }
}
