package com.workintech.fswebs18challengemaven.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardErrorResponse {
    private int status;
    private String message;
    private long timeStamps;


    public CardErrorResponse(HttpStatus status, long timeStamps, String message) {
        this.status = status.value();
        this.timeStamps = timeStamps;
        this.message = message;
    }

    public CardErrorResponse(String expectedMessage) {
        this.message=expectedMessage;
    }
}
