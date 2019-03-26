package com.aviva.agriculture.grele.service.impl;

import com.aviva.agriculture.grele.service.LocalisationService;
import com.aviva.agriculture.grele.domain.Localisation;
import com.aviva.agriculture.grele.repository.LocalisationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Localisation.
 */
@Service
@Transactional
public class LocalisationServiceImpl implements LocalisationService {

    private final Logger log = LoggerFactory.getLogger(LocalisationServiceImpl.class);

    private final LocalisationRepository localisationRepository;

    public LocalisationServiceImpl(LocalisationRepository localisationRepository) {
        this.localisationRepository = localisationRepository;
    }

    /**
     * Save a localisation.
     *
     * @param localisation the entity to save
     * @return the persisted entity
     */
    @Override
    public Localisation save(Localisation localisation) {
        log.debug("Request to save Localisation : {}", localisation);
        return localisationRepository.save(localisation);
    }

    /**
     * Get all the localisations.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Localisation> findAll() {
        log.debug("Request to get all Localisations");
        return localisationRepository.findAll();
    }


    /**
     * Get one localisation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Localisation> findOne(Long id) {
        log.debug("Request to get Localisation : {}", id);
        return localisationRepository.findById(id);
    }

    /**
     * Delete the localisation by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Localisation : {}", id);
        localisationRepository.deleteById(id);
    }
}
