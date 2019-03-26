package com.aviva.agriculture.grele.web.rest;
import com.aviva.agriculture.grele.domain.Localisation;
import com.aviva.agriculture.grele.service.LocalisationService;
import com.aviva.agriculture.grele.web.rest.errors.BadRequestAlertException;
import com.aviva.agriculture.grele.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Localisation.
 */
@RestController
@RequestMapping("/api")
public class LocalisationResource {

    private final Logger log = LoggerFactory.getLogger(LocalisationResource.class);

    private static final String ENTITY_NAME = "localisation";

    private final LocalisationService localisationService;

    public LocalisationResource(LocalisationService localisationService) {
        this.localisationService = localisationService;
    }

    /**
     * POST  /localisations : Create a new localisation.
     *
     * @param localisation the localisation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new localisation, or with status 400 (Bad Request) if the localisation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/localisations")
    public ResponseEntity<Localisation> createLocalisation(@RequestBody Localisation localisation) throws URISyntaxException {
        log.debug("REST request to save Localisation : {}", localisation);
        if (localisation.getId() != null) {
            throw new BadRequestAlertException("A new localisation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Localisation result = localisationService.save(localisation);
        return ResponseEntity.created(new URI("/api/localisations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /localisations : Updates an existing localisation.
     *
     * @param localisation the localisation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated localisation,
     * or with status 400 (Bad Request) if the localisation is not valid,
     * or with status 500 (Internal Server Error) if the localisation couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/localisations")
    public ResponseEntity<Localisation> updateLocalisation(@RequestBody Localisation localisation) throws URISyntaxException {
        log.debug("REST request to update Localisation : {}", localisation);
        if (localisation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Localisation result = localisationService.save(localisation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, localisation.getId().toString()))
            .body(result);
    }

    /**
     * GET  /localisations : get all the localisations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of localisations in body
     */
    @GetMapping("/localisations")
    public List<Localisation> getAllLocalisations() {
        log.debug("REST request to get all Localisations");
        return localisationService.findAll();
    }

    /**
     * GET  /localisations/:id : get the "id" localisation.
     *
     * @param id the id of the localisation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the localisation, or with status 404 (Not Found)
     */
    @GetMapping("/localisations/{id}")
    public ResponseEntity<Localisation> getLocalisation(@PathVariable Long id) {
        log.debug("REST request to get Localisation : {}", id);
        Optional<Localisation> localisation = localisationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(localisation);
    }

    /**
     * DELETE  /localisations/:id : delete the "id" localisation.
     *
     * @param id the id of the localisation to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/localisations/{id}")
    public ResponseEntity<Void> deleteLocalisation(@PathVariable Long id) {
        log.debug("REST request to delete Localisation : {}", id);
        localisationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
