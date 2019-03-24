package com.hugoiguana.br.geanuncio1.models;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hugoiguana.br.geanuncio1.models.Util.DateUtil;

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
@AttributeOverride(name="id", column=@Column(name="user_id"))
@Table(name = "user_ad")
public class User extends MyEntity {

    @Column(nullable=false, length=100)
    private String fullName;

    @Column(nullable=false, length=50)
    private String nickName;

    @Column(nullable=false, length=100)
    private String email;

    @Column(nullable=true, length=100)
    private String phoneNumber;
    
    @Column(nullable=false)
    private LocalDate dtAge;

    @Column(nullable=false)
    private EGender gender;

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable=false, unique=true)
    private Address address;
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((dtAge == null) ? 0 : dtAge.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
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
		User other = (User) obj;
		if (dtAge == null) {
			if (other.dtAge != null)
				return false;
		} else if (!dtAge.equals(other.dtAge))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return fullName + "(" + nickName + ") - " + DateUtil.formatDefault(dtAge) + " - " + email + " - "
				+ address.toString();
	}
}
