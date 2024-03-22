package com.redoz.onclass.adapters.driving.http.dto.request;

import com.redoz.onclass.adapters.driving.http.utils.RequestConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Getter
public class CreateBootcampRequest {
    @NotBlank(message = RequestConstants.NAME_IS_MANDATORY)
    @Size(max = RequestConstants.NAME_MAX_LENGTH_VALUE, message = RequestConstants.NAME_MAX_LENGTH)
    private final String name;

    @NotBlank(message = RequestConstants.DESCRIPTION_IS_MANDATORY)
    @Size(max = RequestConstants.DESCRIPTION_MAX_LENGTH_VALUE, message = RequestConstants.DESCRIPTION_MAX_LENGTH)
    private final String description;

    @NotEmpty(message = RequestConstants.CAPACITIES_IS_MANDATORY)
    private final List<CreateBootcampCapacityItem> capacities;
}
