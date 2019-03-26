package com.aviva.agriculture.grele.service.impl;

import com.aviva.agriculture.grele.service.ParcelleService;
import com.aviva.agriculture.grele.domain.Parcelle;
import com.aviva.agriculture.grele.repository.ParcelleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Parcelle.
 */
@Service
@Transactional
public class ParcelleServiceImpl implements ParcelleService {

    private final Logger log = LoggerFactory.getLogger(ParcelleServiceImpl.class);

    private final ParcelleRepository parcelleRepository;

    public ParcelleServiceImpl(ParcelleRepository parcelleRepository) {
        this.parcelleRepository = parcelleRepository;
    }

    /**
     * Save a parcelle.
     *
     * @param parcelle the entity to save
     * @return the persisted entity
     */
    @Override
    public Parcelle save(Parcelle parcelle) {
        log.debug("Request to save Parcelle : {}", parcelle);
        return parcelleRepository.save(parcelle);
    }

    /**
     * Get all the parcelles.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Parcelle> findAll() {
        log.debug("Request to get all Parcelles");
        return parcelleRepository.findAll();
    }


    /**
     * Get one parcelle by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Parcelle> findOne(Long id) {
        log.debug("Request to get Parcelle : {}", id);
        return parcelleRepository.findById(id);
    }

    /**
     * Delete the parcelle by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Parcelle : {}", id);
        parcelleRepository.deleteById(id);
    }
}
