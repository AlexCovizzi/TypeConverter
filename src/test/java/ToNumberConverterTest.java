import com.alexcovizzi.typeconverter.converters.ToNumberConverterFactory;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import com.alexcovizzi.typeconverter.converters.ToFloatConverterFactory;

/**
 * Created by Alex on 06/12/2017.
 */
@RunWith(Enclosed.class)
public class ToNumberConverterTest {

    public static class FromStringTest {
        ToNumberConverterFactory.FromStringConverter stringConverter;

        @Before
        public void setUp() throws Exception {
            stringConverter = (new ToNumberConverterFactory()).new FromStringConverter();
        }

        @Test
        public void convert_ValueNull_ReturnsNull() throws Exception {
            Assert.assertNull(stringConverter.convert(null));
        }

        @Test
        public void convert_ValueStringNumber_ReturnsNumber() throws Exception {
            String value = "2.5";
            Number result = 2.5;
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
        ToNumberConverterFactory.FromBooleanConverter booleanConverter;

        @Before
        public void setUp() throws Exception {
            booleanConverter = (new ToNumberConverterFactory()).new FromBooleanConverter();
        }

        @Test
        public void convert_ValueNull_ReturnsNull() throws Exception {
            Assert.assertNull(booleanConverter.convert(null));
        }

        @Test
        public void convert_ValueBooleanTrue_ReturnsOne() throws Exception {
            Boolean value = true;
            Number result = 1;
            Assert.assertEquals(result, booleanConverter.convert(value));
        }

        @Test
        public void convert_ValueBooleanFalse_ReturnsZero() throws Exception {
            Boolean value = false;
            Number result = 0;
            Assert.assertEquals(result, booleanConverter.convert(value));
        }
    }

    public static class FromNumberTest {
        ToNumberConverterFactory.FromNumberConverter numberConverter;

        @Before
        public void setUp() throws Exception {
            numberConverter = (new ToNumberConverterFactory()).new FromNumberConverter();
        }

        @Test
        public void convert_ValueNull_ReturnsNull() throws Exception {
            Assert.assertNull(numberConverter.convert(null));
        }

        @Test
        public void convert_ValueNumber_ReturnsNumber() throws Exception {
            Number value = 3.14;
            Number result = 3.14;
            Assert.assertEquals(result, numberConverter.convert(value));
        }
    }
}