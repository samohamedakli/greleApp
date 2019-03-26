package com.aviva.agriculture.grele.service.impl;

import com.aviva.agriculture.grele.service.CultureService;
import com.aviva.agriculture.grele.domain.Culture;
import com.aviva.agriculture.grele.repository.CultureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Culture.
 */
@Service
@Transactional
public class CultureServiceImpl implements CultureService {

    private final Logger log = LoggerFactory.getLogger(CultureServiceImpl.class);

    private final CultureRepository cultureRepository;

    public CultureServiceImpl(CultureRepository cultureRepository) {
        this.cultureRepository = cultureRepository;
    }

    /**
     * Save a culture.
     *
     * @param culture the entity to save
     * @return the persisted entity
     */
    @Override
    public Culture save(Culture culture) {
        log.debug("Request to save Culture : {}", culture);
        return cultureRepository.save(culture);
    }

    /**
     * Get all the cultures.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Culture> findAll(Pageable pageable) {
        log.debug("Request to get all Cultures");
        return cultureRepository.findAll(pageable);
    }


    /**
     * Get one culture by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Culture> findOne(Long id) {
        log.debug("Request to get Culture : {}", id);
        return cultureRepository.findById(id);
    }

    /**
     * Delete the culture by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Culture : {}", id);
        cultureRepository.deleteById(id);
    }
}
