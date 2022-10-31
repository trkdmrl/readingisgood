package com.trkdmrl.readingisgood.utils.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseMessage {

    private String message;

    private long id;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public ResponseMessage(String message, long id) {
        this.message = message;
        this.id = id;
    }
}
