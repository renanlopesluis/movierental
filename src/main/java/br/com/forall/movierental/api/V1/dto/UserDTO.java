package br.com.forall.movierental.api.V1.dto;

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
public class UserDTO {

	@ApiModelProperty(required = false, hidden = true)
	private Long userId;
	@ApiModelProperty(required = true, hidden = false)
    private String name;
	@ApiModelProperty(required = true, hidden = false)
    private String email;
	@ApiModelProperty(required = true, hidden = false)
    private String password;
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
