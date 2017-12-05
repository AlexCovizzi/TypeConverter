/**
 * Created by Alex on 05/12/2017.
 */
public class Type {
    /* Supported types */
    public static final String TYPE_STRING = "java.lang.String";
    public static final String TYPE_BOOLEAN = "java.lang.Boolean";
    public static final String TYPE_INTEGER = "java.lang.Integer";
    public static final String TYPE_LONG = "java.lang.Long";
    public static final String TYPE_FLOAT = "java.lang.Float";
    public static final String TYPE_DOUBLE = "java.lang.Double";

    public static boolean isSupported(String type) {
        if(type == null) return false;

        if(type.equals(TYPE_STRING) || type.equals(TYPE_BOOLEAN) ||
                type.equals(TYPE_INTEGER) || type.equals(TYPE_LONG) ||
                type.equals(TYPE_FLOAT) || type.equals(TYPE_DOUBLE)) {
            return true;
        } else {
            return false;
        }
    }
}
