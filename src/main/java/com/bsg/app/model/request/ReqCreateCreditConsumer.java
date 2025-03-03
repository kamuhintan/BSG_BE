package com.bsg.app.model.request;


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
public class ReqCreateCreditConsumer {
    private String name;
    private Date date;
    private String insurance;
    private BigInteger plafond;
    private String agency;
    private String phoneNumber;
    private String address;
    private String consumerCreditType;
    private String number;
    private String consumerCreditSubType;
}