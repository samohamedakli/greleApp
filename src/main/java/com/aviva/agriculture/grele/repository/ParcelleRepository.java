package com.aviva.agriculture.grele.repository;

import com.aviva.agriculture.grele.domain.Parcelle;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Parcelle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParcelleRepository extends JpaRepository<Parcelle, Long> {

}
