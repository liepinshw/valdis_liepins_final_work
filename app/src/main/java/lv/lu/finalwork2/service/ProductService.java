package lv.lu.finalwork2.service;

import lv.lu.finalwork2.domain.Product;
import lv.lu.finalwork2.model.ItemNotFoundException;
import lv.lu.finalwork2.model.ui.ProductData;
import lv.lu.finalwork2.model.ui.ProductInputData;
import lv.lu.finalwork2.repository.Repository;
import lv.lu.finalwork2.validation.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final Repository<Product> repository;
    private final ProductMapper mapper;
    private final ProductValidator productValidator;

    @Autowired
    public ProductService(Repository<Product> repository,
                          ProductMapper mapper,
                          ProductValidator productValidator) {
        this.repository = repository;
        this.mapper = mapper;
        this.productValidator = productValidator;
    }

    public void save(ProductInputData productInputData) {
        productValidator.validate(productInputData);
        final Product product = mapper.mapFrom(productInputData);
        repository.save(product);
    }

    public List<ProductData> findAll() {
//        List<ProductData> result = new ArrayList<>();
//        for (Product product : repository.findAll()) {
//            ProductData productData = mapper.mapFrom(product);
//            result.add(productData);
//        }
//        return result;

        return repository.findAll().stream()
                .map(mapper::mapFrom)
                .collect(Collectors.toList());
    }

    public Product findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Product Id can't be null");
        }

        Optional<Product> result = repository.findById(id);
        if (!result.isPresent()) {
            throw new ItemNotFoundException(
                    String.format("Product by id: %d is not found", id));
        }

        return result.get();
    }

    public void delete(Long id) {

    }
}
