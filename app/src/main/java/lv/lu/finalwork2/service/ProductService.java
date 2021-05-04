package lv.lu.finalwork2.service;

import lv.lu.finalwork2.model.ItemNotFoundException;
import lv.lu.finalwork2.model.repository.Product;
import lv.lu.finalwork2.model.repository.ProductCategory;
import lv.lu.finalwork2.model.ui.ProductInputData;
import lv.lu.finalwork2.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private ProductRepository repository;

    public ProductService() {
        this.repository = new ProductRepository();
    }

    public void save(ProductInputData productInputData) {
        Product product = new Product();
        product.setName(productInputData.getName());
        product.setPrice(BigDecimal.valueOf(productInputData.getPrice()));
        product.setCategory(ProductCategory.valueOf(productInputData.getCategory()));
        if (productInputData.getDiscount()!= null) {
            product.setDiscount(BigDecimal.valueOf(productInputData.getDiscount()));
        }
        if (productInputData.getDescription()!= null) {
            product.setDescription(productInputData.getDescription());
        }

        repository.save(product);

    }

    public Product findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Product Id cannot be null");
        }

        Optional<Product> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new ItemNotFoundException(String.format("Product by id: %d is not found", id));
        }
        return result.get();
    }

    public List<Product> findAll() {
        return null;

    }

    public void delete(Long id) {
    }
}

