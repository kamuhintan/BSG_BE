package com.bsg.app.repository;

import com.bsg.app.entities.Mail;
import com.bsg.app.enums.MailSubTypeEnum;
import com.bsg.app.enums.MailTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Optional;

public interface MailRepository extends JpaRepository<Mail, String> {

    @Query(value = "select m from Mail as m " +
            "where (:type is null or :type = m.type) " +
            "and (:subType is null or :subType = m.subType) " +
            "and (:query is null or :query = '' OR lower(m.name) LIKE LOWER(CONCAT('%', :query, '%') ) ) " +
            "order by m.createdDate desc ")
    Page<Mail> findAllAndFilter(Pageable pageable, MailTypeEnum type, String query, MailSubTypeEnum subType);

    @Query (value="select m from Mail as m where m.type=:type and m.subType=:subTypeEnum order by m.count desc limit 1")
    Optional<Mail> getLatestMail(MailTypeEnum type,MailSubTypeEnum subTypeEnum);

    BigInteger countAllByType(MailTypeEnum type);
}
