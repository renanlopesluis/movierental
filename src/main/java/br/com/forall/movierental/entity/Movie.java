package br.com.forall.movierental.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
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
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String title;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DIRECTORID")
	@NotNull
	private Director director;
	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<MovieCopy> copies;
	
	@Override
    public String toString() {
       return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
