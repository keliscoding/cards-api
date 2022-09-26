package io.github.zam0k.cardsapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

import static java.util.Collections.singletonList;

@Data
@AllArgsConstructor
public class ApiError {
    private OffsetDateTime timestamp;
    private List<String> message;
    private Integer status;
    private String path;
    private String error;

    public ApiError(OffsetDateTime timestamp, String message, Integer status, String path, String error) {
        this.timestamp = timestamp;
        this.message = singletonList(message);
        this.status = status;
        this.path = path;
        this.error = error;
    }
}
