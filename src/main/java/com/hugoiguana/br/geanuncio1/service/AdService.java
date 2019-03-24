package com.hugoiguana.br.geanuncio1.service;

import com.hugoiguana.br.geanuncio1.models.Ad;
import com.hugoiguana.br.geanuncio1.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Component
public class AdService {

    @Autowired
    AdRepository adRepository;

    @PersistenceContext
    private EntityManager em;


    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    public void insertSomeAdsTestTransation() throws Exception{
        insertSomeAdsTestTransation1();
    }

    public void insertSomeAdsTestTransation1() throws Exception{

        for (int i = 0; i < 2; i++) {

            Ad ad = Ad.builder()
                    .description("Ad " + (i + 1))
                    .value(new BigDecimal(680))
                    .urlImage("https://img1.madeiramadeira.com.br/prd/imperio-estofados/192108/poltrona-do-papai-reclin-vel-gelo-198_desc.jpg")
                    .build();

            adRepository.save(ad);
                //em.persist(ad);

            if (i == 3) {
                throw new Exception("Testing rollback spring transactional");
            }
        }

        insertSomeAdsTestTransaction2();
        insertSomeAdsTestTransaction3();
        //insertSomeAdsTestTransaction4();
    }

    private void insertSomeAdsTestTransaction4() {
        try {
            insertSomeAdsTestTransaction5();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    public void insertSomeAdsTestTransaction5() throws Exception {
        Ad ad = Ad.builder()
                .description("Ad 1.2 nao")
                .value(new BigDecimal(680))
                .urlImage("https://img1.madeiramadeira.com.br/prd/imperio-estofados/192108/poltrona-do-papai-reclin-vel-gelo-198_desc.jpg")
                .build();

        adRepository.save(ad);
        throw new Exception("Testing rollback spring transactional");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Throwable.class})
    public void insertSomeAdsTestTransaction2() throws Exception {
    //public void insertSomeAdsTestTransaction2() {

        Ad ad = Ad.builder()
                .description("Ad 1.3")
                .value(new BigDecimal(680))
                .urlImage("https://img1.madeiramadeira.com.br/prd/imperio-estofados/192108/poltrona-do-papai-reclin-vel-gelo-198_desc.jpg")
                .build();

        adRepository.save(ad);
        throw new Exception("Testing rollback spring transactional");
    }

    private void insertSomeAdsTestTransaction3() throws Exception {
        //public void insertSomeAdsTestTransaction2() {

        Ad ad = Ad.builder()
                .description("Ad 1.4")
                .value(new BigDecimal(680))
                .urlImage("https://img1.madeiramadeira.com.br/prd/imperio-estofados/192108/poltrona-do-papai-reclin-vel-gelo-198_desc.jpg")
                .build();

        adRepository.save(ad);
        throw new Exception("Testing rollback spring transactional");
    }

}
