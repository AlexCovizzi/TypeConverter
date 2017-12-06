
import exceptions.InvalidConversionException;
import exceptions.UnsupportedTypeException;

/**
 * Created by Alex on 05/12/2017.
 */
public class TypeConverter {

    public static Builder convert(Object value) {
        return (new TypeConverter()).new Builder(value);
    }

    public class Builder {
        private Object value;
        private Object defValue;
        private boolean hasDef = false;
        private String resultType;
        private Converter converter;

        public Builder(Object value) {
            this.value = value;
        }

        public Builder def(Object defaultValue) {
            this.defValue = defaultValue;
            this.hasDef = true;
            return this;
        }

        public Object to(String type) {
            this.resultType = type;

            // Default value is automatically converted to the resultType if the resultType is supported
            if(hasDef && defValue != null && Type.isSupported(resultType)) {
                String defValueType = defValue.getClass().getCanonicalName();
                if(!defValueType.equals(resultType)) {
                    try {
                        defValue = TypeConverter.convert(defValue).to(resultType);
                    } catch (InvalidConversionException e) {
                        throw new IllegalArgumentException("Invalid default value.");
                    }
                }
            }

            // Find converter based on value type and result type
            String valueType = value.getClass().getCanonicalName();
            ConverterFactory converterFactory = getConverterFactory(resultType);
            Converter converter = converterFactory.getConverter(valueType);
            return with(converter);
        }

        public Object with(Converter converter) {
            this.converter = converter;

            if(hasDef) {
                Object result = defValue;
                try {
                    result = convert();
                    if(result == null) result = defValue;
                } catch (Exception e) {
                    //
                } finally {
                    return result;
                }
            } else {
                return convert();
            }
        }

        private Object convert() {
            try {
                return converter.convert(value);
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("Invalid parameter type ("+value.getClass().getCanonicalName()+")");
            }
        }

        public Boolean toBoolean() {
            return (Boolean) to(Type.BOOLEAN);
        }

        public String toString() {
            return (String) to(Type.STRING);
        }

        public Integer toInteger() {
            return (Integer) to(Type.INTEGER);
        }

        public Long toLong() {
            return (Long) to(Type.LONG);
        }

        public Float toFloat() {
            return (Float) to(Type.FLOAT);
        }

        public Double toDouble() {
            return (Double) to(Type.DOUBLE);
        }

        private ConverterFactory getConverterFactory(String resultType) {
            if(resultType.equals(Type.STRING)) return new ToStringConverterFactory();
            else if(resultType.equals(Type.BOOLEAN)) return new ToBooleanConverterFactory();
            else if(resultType.equals(Type.INTEGER)) return new ToIntegerConverterFactory();
            else if(resultType.equals(Type.LONG)) return new ToLongConverterFactory();
            else if(resultType.equals(Type.FLOAT)) return new ToFloatConverterFactory();
            else if(resultType.equals(Type.DOUBLE)) return new ToDoubleConverterFactory();

            throw new UnsupportedTypeException(resultType);
        }
    }
}
