package com.aviva.agriculture.grele.service;

import com.aviva.agriculture.grele.domain.Parcelle;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Parcelle.
 */
public interface ParcelleService {

    /**
     * Save a parcelle.
     *
     * @param parcelle the entity to save
     * @return the persisted entity
     */
    Parcelle save(Parcelle parcelle);

    /**
     * Get all the parcelles.
     *
     * @return the list of entities
     */
    List<Parcelle> findAll();


    /**
     * Get the "id" parcelle.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Parcelle> findOne(Long id);

    /**
     * Delete the "id" parcelle.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
