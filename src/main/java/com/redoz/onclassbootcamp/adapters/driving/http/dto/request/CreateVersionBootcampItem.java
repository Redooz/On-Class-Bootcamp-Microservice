package com.redoz.onclassbootcamp.adapters.driving.http.dto.request;

import com.redoz.onclassbootcamp.adapters.driving.http.utils.RequestConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateVersionBootcampItem {
    @NotBlank(message = RequestConstants.ID_IS_MANDATORY)
    private Long id;
}
