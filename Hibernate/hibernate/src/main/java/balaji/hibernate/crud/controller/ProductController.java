package balaji.hibernate.crud.controller;

import balaji.hibernate.crud.entity.Product;
import balaji.hibernate.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("{id}")
    public Product get(@PathVariable(required = false) Long id) {
        return productService.get(id);
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/search/{name}")
    public List<Product> getAll(@PathVariable(required = false) String name) {
        return productService.getAll(name);
    }

}
