package com.bsg.app.repository;

import com.bsg.app.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByUsername(String username);


    boolean existsAccountByUsername(String username);

    @Query("SELECT a FROM Account a " +
            "WHERE (:query IS NULL OR :query = '' OR LOWER(a.name) LIKE LOWER(CONCAT('%', :query, '%'))) " +
            "ORDER BY a.createdDate DESC")
    Page<Account> findAllAndFilter(@Param("query") String query, Pageable pageable);

    @Modifying
    @Query("UPDATE Account a SET a.createdBy = :newCreatedBy WHERE a.createdBy = :oldCreatedBy")
    void updateCreatedByReferences(@Param("oldCreatedBy") String oldCreatedBy, @Param("newCreatedBy") String newCreatedBy);
}
