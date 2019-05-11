package br.com.forall.movierental.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="MOVIECOPY")
public class MovieCopy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean available;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MOVIEID")
	@NotNull
	private Movie movie;
		
	public Boolean isAvailable() {
		return this.available;
	}
	
	@Override
    public String toString() {
       return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
