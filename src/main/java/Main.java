

/**
 * Created by Alex on 05/12/2017.
 */
public class Main {
    public static void main(String[] args) {
        String v = "3.54346drtfhrth";

        System.out.println(TypeConverter.convert(v).def(2).toLong());
    }
}
