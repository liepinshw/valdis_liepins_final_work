package lv.lu.finalwork2.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProductServiceTest {

    private ProductService service;

    @Rule
    public ExpectedException exception = ExpectedException.none(); //none nosvītrots 2:30:32

    @Before
    public void setUp() throws Exception {
        service = new ProductService();
    }

    @Test
    public void shouldThrowErrorWhenFindByNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Product Id cannot be null");
        service.findById(null);
    }
}