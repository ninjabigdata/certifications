package balaji.hibernate.crud.service;

import balaji.hibernate.crud.entity.Product;
import balaji.hibernate.crud.repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product save(Product product) {
        return productRepo.saveAndFlush(product);
    }

    public Product get(long id) {
        return  productRepo.findById(id).orElse(null);
    }

    public Product update(Product product) {
        Product productToBeUpdated = get(product.getId());

        log.debug("Retrieved product is {}", productToBeUpdated);

        if (productToBeUpdated == null) {
            return null;
        }

        productToBeUpdated.setName(StringUtils.hasText(product.getName()) ? product.getName() : productToBeUpdated.getName());
        productToBeUpdated.setPrice(product.getPrice() != null ? product.getPrice() : productToBeUpdated.getPrice());
        productToBeUpdated.setDescription(StringUtils.hasText(product.getDescription()) ? product.getDescription() : productToBeUpdated.getDescription());

        return save(productToBeUpdated);
    }

    public void delete(Long id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
        }
    }

    public List<Product> getAll(String name) {
        return productRepo.findAllByName(name, PageRequest.of(0, 1, Sort.by("id")));
    }

}
