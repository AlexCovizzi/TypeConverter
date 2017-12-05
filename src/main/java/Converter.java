import exceptions.InvalidConversionException;

/**
 * Created by Alex on 05/12/2017.
 */
public interface Converter<V, R> {

    R convert(V value) throws InvalidConversionException;
}
