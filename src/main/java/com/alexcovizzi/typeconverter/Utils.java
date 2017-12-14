package com.alexcovizzi.typeconverter;

/**
 * Created by Alex on 13/12/2017.
 */
class Utils {

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

    public static boolean isArray(String type) {
        return type.endsWith("[]");
    }
}
