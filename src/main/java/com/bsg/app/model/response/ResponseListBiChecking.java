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
public class ResponseListBiChecking  {
    private String id;
    private String createdBy;
    private Long createdDate;
    private String name;
    private BigInteger number;
    private Date requestDate;
    private String ktpNumber;
    private String placeOfBirth;
    private Date dateOfBirth;
    private String objective;
    private String note;
    private CreditTypeEnum type;
}

