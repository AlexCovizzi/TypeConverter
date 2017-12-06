/**
 * Created by Alex on 05/12/2017.
 */
public class Type {
    /* Supported types */
    public static final String STRING = "java.lang.String";
    public static final String BOOLEAN = "java.lang.Boolean";
    public static final String INTEGER = "java.lang.Integer";
    public static final String LONG = "java.lang.Long";
    public static final String FLOAT = "java.lang.Float";
    public static final String DOUBLE = "java.lang.Double";

    public static final String[] SUPPORTED_TYPES = new String[] {STRING, BOOLEAN, INTEGER, LONG, FLOAT, DOUBLE};

    public static boolean isSupported(String type) {
        if(type == null) return false;

        for(String suppType : SUPPORTED_TYPES) {
            if(type.equals(suppType)) return true;
        }

        return false;
    }
}
