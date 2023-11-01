package com.duocode.webscrapping.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateLinkRequest {

    @Schema(example = "http://www.google.com/")
    @NotBlank
    private String url;

    @Schema(example = "Sample description of the URL")
    @NotBlank
    private String description;
}
