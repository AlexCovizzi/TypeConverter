import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * Created by Alex on 06/12/2017.
 */
@RunWith(Enclosed.class)
public class ToFloatConverterTest {

    public static class FromStringTest {
        ToFloatConverterFactory.FromStringConverter stringConverter;

        @Before
        public void setUp() throws Exception {
            stringConverter = (new ToFloatConverterFactory()).new FromStringConverter();
        }

        @Test
        public void convert_ValueNull_ReturnsNull() throws Exception {
            Assert.assertEquals(null, stringConverter.convert(null));
        }

        @Test
        public void convert_ValueObject_ReturnsToString() throws Exception {

        }
    }
}