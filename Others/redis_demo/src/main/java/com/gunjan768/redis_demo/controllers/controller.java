package com.gunjan768.redis_demo.controllers;

import com.gunjan768.redis_demo.entity.Product;
import com.gunjan768.redis_demo.respository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gunjan768.redis_demo.respository.ProductDao.HASH_KEY;

@RestController
@RequestMapping("/product")
@EnableCaching
public class controller {
    @Autowired
    private ProductDao dao;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return dao.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = HASH_KEY, unless = "#result.price > 60")
    public Product findProduct(@PathVariable int id) {
        return dao.findProductById(id);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = HASH_KEY)
    public String remove(@PathVariable int id) {
        return dao.deleteProduct(id);
    }
}