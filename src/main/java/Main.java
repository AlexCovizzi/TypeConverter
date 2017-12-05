

/**
 * Created by Alex on 05/12/2017.
 */
public class Main {
    public static void main(String[] args) {
        String v = "0";

        Boolean bl = (Boolean) TypeConverter.convert(v, true, "");

        System.out.println(bl);
    }
}
