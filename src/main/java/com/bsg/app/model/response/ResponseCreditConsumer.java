package com.bsg.app.model.response;

import com.bsg.app.enums.CreditTypeEnum;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseCreditConsumer {

    private String id;
    private String name;
    private Date date;
    private String insurance;
    private BigInteger plafond;
    private String agency;
    private String phone;
    private String address;
    private String createdBy;
    private Long createdDate;
    private CreditTypeEnum type;
    private String consumerCreditType;
    private String consumerCreditSubType;
    private String number;


}
