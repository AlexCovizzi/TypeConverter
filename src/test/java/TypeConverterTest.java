import com.alexcovizzi.typeconverter.TypeConverter;
import com.alexcovizzi.typeconverter.converters.BooleanConverter;
import com.alexcovizzi.typeconverter.exceptions.ConverterNotFoundException;
import com.alexcovizzi.typeconverter.exceptions.InvalidConversionException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Alex on 09/12/2017.
 */

public class TypeConverterTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void convert_ToTypeSupported_ReturnsResult() throws Exception {
        Integer value = 0;
        String result = "0";

        Assert.assertEquals(result, TypeConverter.convert(value).to(result.getClass().getCanonicalName()));
    }

    @Test
    public void convert_ToTypeUnsupported_ThrowsConverterNotFoundException() throws Exception {
        String value = "abc";

        try {
            TypeConverter.convert(value).to(Integer[].class);
            Assert.fail("Expected ConverterNotFoundException was not occurred.");
        } catch(ConverterNotFoundException e) {
            //
        }
    }

    @Test
    public void convert_ValueNull_ToTypeSupported_ReturnsNull() throws Exception {
        Integer value = null;

        Assert.assertEquals(null, TypeConverter.convert(value).to(String.class));
    }

    @Test
    public void convert_ConversionImpossible_ThrowsInvalidConversionException() throws Exception {
        String value = "abc";

        try {
            TypeConverter.convert(value).to(Number.class);
            Assert.fail("Expected InvalidConversionException was not occurred.");
        } catch(InvalidConversionException e) {
            //
        }
    }

    @Test
    public void convert_ConversionImpossible_WithDefValue_ReturnsDefValue() throws Exception {
        String value = "abc";
        Number defValue = 0;

        Assert.assertEquals(defValue, TypeConverter.convert(value).def(defValue).to(Number.class));
    }

    @Test
    public void convert_ValueNull_WithDefValue_ToTypeSupported_ReturnsDefValue() throws Exception {
        Integer value = null;
        String defValue = "abc";

        Assert.assertEquals(defValue, TypeConverter.convert(value).def(defValue).to(String.class));
    }
}
