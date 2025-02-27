package com.bsg.app.model.request;

import com.bsg.app.enums.MailSubTypeEnum;
import com.bsg.app.enums.MailTypeEnum;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RequestCreateMail {
    private String count;
    private String name;

    private String sender;
    private String recipient;
    private MailTypeEnum type;
    private String note;
    private MailSubTypeEnum subType;
    private String date;


}
