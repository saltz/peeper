package com.dane.peeper.domain.models.viewModels;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TokenViewModel {
    public String accessToken;
    public long expiresIn;
    public String tokenType;

    public TokenViewModel(String accessToken, long expiresIn, String tokenType) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.tokenType = tokenType;
    }
}
