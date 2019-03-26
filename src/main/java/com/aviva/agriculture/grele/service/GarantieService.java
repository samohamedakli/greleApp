package com.aviva.agriculture.grele.service;

import com.aviva.agriculture.grele.domain.Garantie;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Garantie.
 */
public interface GarantieService {

    /**
     * Save a garantie.
     *
     * @param garantie the entity to save
     * @return the persisted entity
     */
    Garantie save(Garantie garantie);

    /**
     * Get all the garanties.
     *
     * @return the list of entities
     */
    List<Garantie> findAll();


    /**
     * Get the "id" garantie.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Garantie> findOne(Long id);

    /**
     * Delete the "id" garantie.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
