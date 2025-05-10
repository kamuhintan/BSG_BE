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
public class ResponsePkKur {
    private BigInteger number;
    private String name;
    private String requestNumber;
    private Date pkDate;
    private BigInteger plafond;

    private String businessType;
    private String guarantee;
    private String bindingType;
    private String notary;
    private String note;
    private String id;
    private CreditTypeEnum type;
    private String createdBy;
    private Long createdDate;
}
