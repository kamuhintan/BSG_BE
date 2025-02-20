package com.bsg.app.entities;

import com.bsg.app.enums.MailSubTypeEnum;
import com.bsg.app.enums.MailTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mail")
public class Mail {
    @Id
    private String id;

    @Column(name = "count")
    private String count;

    @Column(name = "name")
    private String name;

    @Column(name = "note")
    private String note;

    @Column(name = "sender")
    private String sender;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MailTypeEnum type;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Long createdDate;

    @Column(name = "date")
    private Date date;

    @Column(name = "sub_type")
    @Enumerated(EnumType.STRING)
    private MailSubTypeEnum subType;


    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
        this.createdDate = new Date().getTime();
    }

}

