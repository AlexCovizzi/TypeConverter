import exceptions.InvalidConversionException;
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
            Assert.assertNull(stringConverter.convert(null));
        }

        @Test
        public void convert_ValueStringNumber_ReturnsNumber() throws Exception {
            String value = "2.5";
            Float result = 2.5F;
            Assert.assertEquals(result, stringConverter.convert(value));
        }

        @Test
        public void convert_ValueStringNotNumber_ThrowsInvalidConversionException() throws Exception {
            String value = "abc";
            try {
                stringConverter.convert(value);
                Assert.fail("Expected InvalidConversionException was not occurred.");
            } catch(InvalidConversionException e) {
                //
            }
        }
    }

    public static class FromBooleanTest {
        ToFloatConverterFactory.FromBooleanConverter stringConverter;

        @Before
        public void setUp() throws Exception {
            stringConverter = (new ToFloatConverterFactory()).new FromBooleanConverter();
        }

        @Test
        public void convert_ValueNull_ReturnsNull() throws Exception {
            Assert.assertNull(stringConverter.convert(null));
        }

        @Test
        public void convert_ValueBooleanTrue_ReturnsOne() throws Exception {
            Boolean value = true;
            Float result = 1F;
            Assert.assertEquals(result, stringConverter.convert(value));
        }

        @Test
        public void convert_ValueBooleanFalse_ReturnsZero() throws Exception {
            Boolean value = false;
            Float result = 0F;
            Assert.assertEquals(result, stringConverter.convert(value));
        }
    }
}