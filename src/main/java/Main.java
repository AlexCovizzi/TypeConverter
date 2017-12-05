

/**
 * Created by Alex on 05/12/2017.
 */
public class Main {
    public static void main(String[] args) {
        String v = null;

        String bl = (String) TypeConverter.convert(v, "", "");

        System.out.println(bl);
    }
}
