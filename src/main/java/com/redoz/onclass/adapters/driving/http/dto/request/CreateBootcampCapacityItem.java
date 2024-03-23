package com.redoz.onclass.adapters.driving.http.dto.request;

import com.redoz.onclass.adapters.driving.http.utils.RequestConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBootcampCapacityItem {
    @NotBlank(message = RequestConstants.ID_IS_MANDATORY)
    private Long id;

    @NotBlank(message = RequestConstants.NAME_IS_MANDATORY)
    private String name;
}
