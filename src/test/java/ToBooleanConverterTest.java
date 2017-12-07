
import com.alexcovizzi.typeconverter.converters.ToBooleanConverterFactory;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * Created by Alex on 07/12/2017.
 */
@RunWith(Enclosed.class)
public class ToBooleanConverterTest {


    public static class FromStringTest {
        ToBooleanConverterFactory.FromStringConverter stringConverter;

        @Before
        public void setUp() throws Exception {
            stringConverter = (new ToBooleanConverterFactory()).new FromStringConverter();
        }

        @Test
        public void convert_ValueNull_ReturnsNull() throws Exception {
            Assert.assertNull(stringConverter.convert(null));
        }

        @Test
        public void convert_ValueStringTrue_ReturnsTrue() throws Exception {
            String value = "TRUE";
            Boolean result = true;
            Assert.assertEquals(result, stringConverter.convert(value));
        }

        @Test
        public void convert_ValueStringYes_ReturnsTrue() throws Exception {
            String value = "YES";
            Boolean result = true;
            Assert.assertEquals(result, stringConverter.convert(value));
        }

        @Test
        public void convert_ValueStringOne_ReturnsTrue() throws Exception {
            String value = "1";
            Boolean result = true;
            Assert.assertEquals(result, stringConverter.convert(value));
        }

        @Test
        public void convert_ValueStringFalse_ReturnsFalse() throws Exception {
            String value = "FALSE";
            Boolean result = false;
            Assert.assertEquals(result, stringConverter.convert(value));
        }

        @Test
        public void convert_ValueStringNo_ReturnsFalse() throws Exception {
            String value = "NO";
            Boolean result = false;
            Assert.assertEquals(result, stringConverter.convert(value));
        }

        @Test
        public void convert_ValueStringZero_ReturnsFalse() throws Exception {
            String value = "0";
            Boolean result = false;
            Assert.assertEquals(result, stringConverter.convert(value));
        }

        @Test
        public void convert_ValueStringInvalid_ThrowsInvalidConversionException() throws Exception {
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
        ToBooleanConverterFactory.FromBooleanConverter booleanConverter;

        @Before
        public void setUp() throws Exception {
            booleanConverter = (new ToBooleanConverterFactory()).new FromBooleanConverter();
        }

        @Test
        public void convert_ValueNull_ReturnsNull() throws Exception {
            Assert.assertNull(booleanConverter.convert(null));
        }

        @Test
        public void convert_ValueBoolean_ReturnsBoolean() throws Exception {
            Boolean value = true;
            Boolean result = true;
            Assert.assertEquals(result, booleanConverter.convert(value));
        }
    }

    public static class FromNumberTest {
        ToBooleanConverterFactory.FromNumberConverter numberConverter;

        @Before
        public void setUp() throws Exception {
            numberConverter = (new ToBooleanConverterFactory()).new FromNumberConverter();
        }

        @Test
        public void convert_ValueNull_ReturnsNull() throws Exception {
            Assert.assertNull(numberConverter.convert(null));
        }

        @Test
        public void convert_ValueNumberOne_ReturnsTrue() throws Exception {
            Number value = 1;
            Boolean result = true;
            Assert.assertEquals(result, numberConverter.convert(value));
        }

        @Test
        public void convert_ValueNumberZero_ReturnsFalse() throws Exception {
            Number value = 0;
            Boolean result = false;
            Assert.assertEquals(result, numberConverter.convert(value));
        }

        @Test
        public void convert_ValueNumberInvalid_ThrowsInvalidConversionException() throws Exception {
            Number value = 2;
            try {
                numberConverter.convert(value);
                Assert.fail("Expected InvalidConversionException was not occurred.");
            } catch(InvalidConversionException e) {
                //
            }
        }
    }
}
