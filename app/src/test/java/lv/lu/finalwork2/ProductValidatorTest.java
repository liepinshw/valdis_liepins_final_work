package lv.lu.finalwork2;

import lv.lu.finalwork2.domain.ProductCategory;
import lv.lu.finalwork2.model.ProductValidationException;
import lv.lu.finalwork2.model.ui.ProductInputData;
import lv.lu.finalwork2.validation.ProductValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProductValidatorTest {

    private ProductValidator validator;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        validator = new ProductValidator();
    }

    @Test
    public void shouldFailWhenNameIsEmpty() {
        ProductInputData inputData = new ProductInputData();
        inputData.setName("");

        exception.expect(ProductValidationException.class);
        exception.expectMessage("Field 'name' should not be empty");

        validator.validate(inputData);
    }

    @Test
    public void shouldFailWhenNameIsNull() {
        ProductInputData inputData = new ProductInputData();
        inputData.setName(null);

        exception.expect(ProductValidationException.class);
        exception.expectMessage("Field 'name' should not be empty");

        validator.validate(inputData);
    }

    @Test
    public void shouldFailWhenPriceIsNegative() {
        ProductInputData inputData = new ProductInputData();
        inputData.setName("Test");
        inputData.setPrice(-1d);

        exception.expect(ProductValidationException.class);
        exception.expectMessage("Field 'price' should not be negative");

        validator.validate(inputData);
    }

    @Test
    public void shouldFailWhenCategoryIsUndefined() {
        ProductInputData inputData = new ProductInputData();
        inputData.setName("Test");
        inputData.setPrice(12d);
        inputData.setCategory("Error");

        exception.expect(ProductValidationException.class);
        exception.expectMessage("Field 'productCategory' is unrecognized");

        validator.validate(inputData);
    }

    @Test
    public void shouldFailWhenDiscountIsNegative() {
        ProductInputData inputData = new ProductInputData();
        inputData.setName("Test");
        inputData.setPrice(12d);
        inputData.setCategory(ProductCategory.FISH.name());
        inputData.setDiscount(-1d);

        exception.expect(ProductValidationException.class);
        exception.expectMessage("Field 'discount' should not be negative");

        validator.validate(inputData);
    }

}