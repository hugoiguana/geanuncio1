package com.hugoiguana.br.geanuncio1.models;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@AttributeOverride(name="id", column=@Column(name="address_id"))
public class Address extends MyEntity {

    @Column(nullable=false)
    private ECountry country;

    @Column(nullable=false)
    private EState state;

    @Column(nullable=false)
    private ECity city;

    @Column(nullable=false)
    private String street;

    @Column(nullable=true)
    private String zip_code;
    
    @Column(nullable=false)
    private String Number;

    @OneToOne(mappedBy = "address", cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
    private User user;
    
    
    
	@Override
	public String toString() {
		return "Address [country=" + country.name() + ", state=" + state.name() + ", city=" + city.name() + ", street="
				+ street + ", zip_code=" + zip_code + ", Number=" + Number + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((Number == null) ? 0 : Number.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zip_code == null) ? 0 : zip_code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (Number == null) {
			if (other.Number != null)
				return false;
		} else if (!Number.equals(other.Number))
			return false;
		if (city != other.city)
			return false;
		if (country != other.country)
			return false;
		if (state != other.state)
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zip_code == null) {
			if (other.zip_code != null)
				return false;
		} else if (!zip_code.equals(other.zip_code))
			return false;
		return true;
	}

    
}
