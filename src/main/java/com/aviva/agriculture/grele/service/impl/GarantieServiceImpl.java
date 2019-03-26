package com.aviva.agriculture.grele.service.impl;

import com.aviva.agriculture.grele.service.GarantieService;
import com.aviva.agriculture.grele.domain.Garantie;
import com.aviva.agriculture.grele.repository.GarantieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Garantie.
 */
@Service
@Transactional
public class GarantieServiceImpl implements GarantieService {

    private final Logger log = LoggerFactory.getLogger(GarantieServiceImpl.class);

    private final GarantieRepository garantieRepository;

    public GarantieServiceImpl(GarantieRepository garantieRepository) {
        this.garantieRepository = garantieRepository;
    }

    /**
     * Save a garantie.
     *
     * @param garantie the entity to save
     * @return the persisted entity
     */
    @Override
    public Garantie save(Garantie garantie) {
        log.debug("Request to save Garantie : {}", garantie);
        return garantieRepository.save(garantie);
    }

    /**
     * Get all the garanties.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Garantie> findAll() {
        log.debug("Request to get all Garanties");
        return garantieRepository.findAll();
    }


    /**
     * Get one garantie by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Garantie> findOne(Long id) {
        log.debug("Request to get Garantie : {}", id);
        return garantieRepository.findById(id);
    }

    /**
     * Delete the garantie by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Garantie : {}", id);
        garantieRepository.deleteById(id);
    }
}
