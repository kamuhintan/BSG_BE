package com.bsg.app.repository;

import com.bsg.app.entities.Credit;
import com.bsg.app.enums.CreditTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

public interface CreditRepository extends JpaRepository<Credit, String> {

    @Query(value = "select c from Credit  as c " +
            "where c.type = :type " +
            "and (:query is null or :query = '' OR lower(c.name) LIKE LOWER(CONCAT('%', :query, '%') ) )" +
            "and (:consumerCreditType is null or :consumerCreditType = c.consumerCreditType  )" +
            "and (:subType is null or :subType = c.consumerCreditSubType  )" +
            "ORDER BY c.createdDate desc "
    )
    Page<Credit> findAllAndFilter(Pageable pageable, String query, String consumerCreditType, CreditTypeEnum type, String subType);

    @Query(value = "select c from Credit  as c " +
            "where c.type = :type " +
            "and (:query is null or :query = '' OR lower(c.name) LIKE LOWER(CONCAT('%', :query, '%') ) )" +
            "ORDER BY c.createdDate desc "
    )
    Page<Credit> findAllAndFilter(Pageable pageable, String query, CreditTypeEnum type);

    @Query(value = "select coalesce(SUM(c.plafond) ,0 ) from Credit  as c  where c.type = :type")
    BigInteger sumTotalPlafondConsumer(CreditTypeEnum type);

    boolean existsAllByNumberAndType(String number, CreditTypeEnum type);

}

