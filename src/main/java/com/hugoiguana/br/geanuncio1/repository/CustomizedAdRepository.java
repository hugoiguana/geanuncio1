package com.hugoiguana.br.geanuncio1.repository;

import com.hugoiguana.br.geanuncio1.models.Ad;

import java.math.BigDecimal;
import java.util.List;

public interface CustomizedAdRepository {

    List<Ad> findByUserIdAndProductValueBiggerThen(Integer userId, BigDecimal value);
}
