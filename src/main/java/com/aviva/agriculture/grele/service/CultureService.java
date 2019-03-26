package com.aviva.agriculture.grele.service;

import com.aviva.agriculture.grele.domain.Culture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Culture.
 */
public interface CultureService {

    /**
     * Save a culture.
     *
     * @param culture the entity to save
     * @return the persisted entity
     */
    Culture save(Culture culture);

    /**
     * Get all the cultures.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Culture> findAll(Pageable pageable);


    /**
     * Get the "id" culture.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Culture> findOne(Long id);

    /**
     * Delete the "id" culture.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
