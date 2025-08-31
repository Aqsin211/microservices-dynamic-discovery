package az.company.msproduct.exception;

public class ValidationMessages {
    public static final String QUANTITY_IS_REQUIRED = "Quantity is required";
    public static final String QUANTITY_MUST_AT_LEAST_BE_1 = "Quantity must be at least 1";
    public static final String QUANTITY_CANNOT_EXCEED_100 = "Quantity cannot exceed 100";

    public static final String PRODUCT_ID_CANNOT_BE_NULL = "Product ID cannot be null";
    public static final String PRODUCT_NAME_CANNOT_BE_BLANK = "Product name cannot be blank";
    public static final String PRODUCT_NAME_MUST_NOT_EXCEED_30 = "Product name must not exceed 30 characters";

    public static final String PRICE_IS_REQUIRED = "Price is required";
    public static final String PRICE_MUST_BE_GREATER_THAN_0= "Price must be greater than 0";
    public static final String PRICE_MUST_NOT_EXCEED_100000 = "Price must not exceed 100,000";

    public static final String STOCK_IS_REQUIRED = "Stock is required";
    public static final String STOCK_CANNOT_BE_NEGATIVE = "Stock cannot be negative";
    public static final String STOCK_CANNOT_EXCEED_10000 = "Stock cannot exceed 10,000";

    public static final String DESCRIPTION_CANNOT_BE_BLANK = "Description cannot be blank";
    public static final String DESCRIPTION_MUST_NOT_EXCEED_255 = "Description must not exceed 255 characters";
}
