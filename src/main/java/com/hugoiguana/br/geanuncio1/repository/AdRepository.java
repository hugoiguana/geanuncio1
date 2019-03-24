package com.hugoiguana.br.geanuncio1.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hugoiguana.br.geanuncio1.models.Ad;

public interface AdRepository extends CrudRepository<Ad, Integer>, CustomizedAdRepository {

    Ad findFirstByOrderByDescriptionAsc();
    
    List<Ad> findAllByOrderByDescription();

}
