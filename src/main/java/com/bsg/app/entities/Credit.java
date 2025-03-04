package com.bsg.app.entities;

import com.bsg.app.enums.CreditTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credit")
public class Credit {
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;

    @Column(name = "plafond")
    private BigInteger plafond;

    @Column(name = "insurance")
    private String insurance;

    @Column(name = "agency")
    private String agency;

    @Column(name = "phone")
    private String phone;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CreditTypeEnum type;

    @Column(name = "request_date")
    private Date requestDate;

    @Column(name = "ktp_number")
    private String ktpNumber;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "objective")
    private String objective;

    @Column(name = "note")
    private String note;

    @Column(name = "created_date")
    private Long createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "credit_type")
    private String creditType;

    @Column(name = "number")
    private String number;

    @Column(name = "consumer_credit_type")
    private String consumerCreditType;

    @Column(name = "consumer_credit_sub_type")
    private String consumerCreditSubType;

    @Column(name = "address")
    private String address;

    @Column(name = "request_number")
    private String requestNumber;

    @Column(name = "pk_date")
    private Date pkDate;

    @Column(name = "business_type")
    private String businessType;

    @Column(name = "guarantee")
    private String guarantee;

    @Column(name = "binding_type")
    private String bindingType;

    @Column(name = "notary")
    private String notary;

    @Column(name = "pk_number")
    private String pkNumber;


    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
        this.createdDate = new Date().getTime();
    }


}

