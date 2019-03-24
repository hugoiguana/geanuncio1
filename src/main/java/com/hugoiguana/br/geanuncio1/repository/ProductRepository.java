package com.hugoiguana.br.geanuncio1.repository;

import org.springframework.data.repository.CrudRepository;

import com.hugoiguana.br.geanuncio1.models.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>, CustomizedProductRepository {

}
