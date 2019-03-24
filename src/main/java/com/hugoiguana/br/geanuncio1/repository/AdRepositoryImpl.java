package com.hugoiguana.br.geanuncio1.repository;

import com.hugoiguana.br.geanuncio1.models.Ad;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class AdRepositoryImpl implements CustomizedAdRepository {

    @Override
    public List<Ad> findByUserIdAndProductValueBiggerThen(Integer userId, BigDecimal value) {
        return null;
    }
}
