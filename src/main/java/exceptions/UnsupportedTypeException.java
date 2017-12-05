package exceptions;

/**
 * Created by Alex on 05/12/2017.
 */
public class UnsupportedTypeException extends RuntimeException {

    public UnsupportedTypeException(Class cls) {
        this(cls.getCanonicalName());
    }

    public UnsupportedTypeException(String type) {
        super("Type "+type+" is not supported.");
    }
}
