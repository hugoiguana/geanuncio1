package com.hugoiguana.br.geanuncio1.models;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@AttributeOverride(name="id", column=@Column(name="ad_id"))
public class Ad extends MyEntity {

    @Column(nullable=false)
    String description;

    @Column(nullable=false)
    BigDecimal value;

    @Column(nullable=true)
    String urlImage;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    List<Product> products;
    
    public void addProduct(Product product) {
    	if (products == null) {
    		products = new ArrayList<>();
    	}
    	if (product != null) {
    		product.setAd(this);
    		products.add(product);
    	}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyEntity entity = (MyEntity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
