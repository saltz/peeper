package com.dane.peeper.domain.models.viewModels;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class ErrorViewModel {
    public String errorType;
    public String message;

    public ErrorViewModel(String errorType, String message) {
        this.errorType = errorType;
        this.message = message;
    }
}
