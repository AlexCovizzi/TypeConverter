
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * Created by Alex on 06/12/2017.
 */
@RunWith(Enclosed.class)
public class ToStringConverterTest {


    public static class FromObjectConverterTest {
        ToStringConverterFactory.FromObjectConverter fromObjectConverter;

        @Before
        public void setUp() throws Exception {
            fromObjectConverter = (new ToStringConverterFactory()).new FromObjectConverter();
        }

        @Test
        public void convert_ValueNull_ReturnsNull() throws Exception {
            Assert.assertEquals(null, fromObjectConverter.convert(null));
        }

        @Test
        public void convert_ValueObject_ReturnsToString() throws Exception {
            Integer i = 4;
            Assert.assertEquals(i.toString(), fromObjectConverter.convert(i));
        }
    }
}