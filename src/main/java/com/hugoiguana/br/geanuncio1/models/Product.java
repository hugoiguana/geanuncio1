package com.hugoiguana.br.geanuncio1.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@AttributeOverride(name="id", column=@Column(name="product_id"))
public class Product extends MyEntity {

    @Column(nullable=false)
    String description;

    @Column(nullable=false)
    BigDecimal value;

    String urlImage1;
    String urlImage2;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    Ad ad;

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
