package com.alexcovizzi.typeconverter;

/**
 * Created by Alex on 13/12/2017.
 */
class Utils {

    /**
     * Transform the given primitive type to its wrapper. If the type is not primitive return the same type.
     * @param type Primitive type as String (boolean, byte, char, float, int, long, short, double).
     * @return Wrapper type or same type if the given type is not primitive.
     */
    public static String primitiveToWrapperType(String type) {
        if(type.equalsIgnoreCase("boolean")) return "java.lang.Boolean";
        else if(type.equalsIgnoreCase("byte")) return "java.lang.Byte";
        else if(type.equalsIgnoreCase("char")) return "java.lang.Character";
        else if(type.equalsIgnoreCase("float")) return "java.lang.Float";
        else if(type.equalsIgnoreCase("int")) return "java.lang.Integer";
        else if(type.equalsIgnoreCase("long")) return "java.lang.Long";
        else if(type.equalsIgnoreCase("short")) return "java.lang.Short";
        else if(type.equalsIgnoreCase("double")) return "java.lang.Double";
        else return type;
    }
}
