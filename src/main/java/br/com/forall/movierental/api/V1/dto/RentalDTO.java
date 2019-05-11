package br.com.forall.movierental.api.V1.dto;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {
	
	@ApiModelProperty(required = true, hidden = false)
	private Long rentalId;
	@ApiModelProperty(required = false, hidden = true)
	private Long copyId;
	@ApiModelProperty(required = true, hidden = false)
	private Long userId;
	@ApiModelProperty(required = false, hidden = true)
    private String userName;
	@ApiModelProperty(required = false, hidden = true)
    private String movieTitle;
	@ApiModelProperty(required = true, hidden = false)
    private Long movieId;
	@ApiModelProperty(required = false, hidden = true)
    private LocalDateTime rentalDate;
	@ApiModelProperty(required = false, hidden = true)
    private LocalDateTime foreseenGiveBackDate;
	@ApiModelProperty(required = false, hidden = true)
    private LocalDateTime executedGiveBackDate;
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
