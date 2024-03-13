package com.redoz.onclass.adapters.driving.http.dto.request;

import com.redoz.onclass.adapters.driving.http.utils.TechnologyRequestConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@AllArgsConstructor
@Getter
public class CreateTechnologyRequest {
    @NotBlank(message = TechnologyRequestConstants.NAME_IS_MANDATORY)
    @Size(max = TechnologyRequestConstants.NAME_MAX_LENGTH_VALUE, message = TechnologyRequestConstants.NAME_MAX_LENGTH)
    private final String name;

    @NotBlank(message = TechnologyRequestConstants.DESCRIPTION_IS_MANDATORY)
    @Size(max = TechnologyRequestConstants.DESCRIPTION_MAX_LENGTH_VALUE, message = TechnologyRequestConstants.DESCRIPTION_MAX_LENGTH)
    private final String description;

    @NotBlank(message = TechnologyRequestConstants.DIRECTION_IS_MANDATORY)
    private final String direction;
}
