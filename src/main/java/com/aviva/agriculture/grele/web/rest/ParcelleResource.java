package com.aviva.agriculture.grele.web.rest;
import com.aviva.agriculture.grele.domain.Parcelle;
import com.aviva.agriculture.grele.service.ParcelleService;
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
 * REST controller for managing Parcelle.
 */
@RestController
@RequestMapping("/api")
public class ParcelleResource {

    private final Logger log = LoggerFactory.getLogger(ParcelleResource.class);

    private static final String ENTITY_NAME = "parcelle";

    private final ParcelleService parcelleService;

    public ParcelleResource(ParcelleService parcelleService) {
        this.parcelleService = parcelleService;
    }

    /**
     * POST  /parcelles : Create a new parcelle.
     *
     * @param parcelle the parcelle to create
     * @return the ResponseEntity with status 201 (Created) and with body the new parcelle, or with status 400 (Bad Request) if the parcelle has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/parcelles")
    public ResponseEntity<Parcelle> createParcelle(@RequestBody Parcelle parcelle) throws URISyntaxException {
        log.debug("REST request to save Parcelle : {}", parcelle);
        if (parcelle.getId() != null) {
            throw new BadRequestAlertException("A new parcelle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Parcelle result = parcelleService.save(parcelle);
        return ResponseEntity.created(new URI("/api/parcelles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /parcelles : Updates an existing parcelle.
     *
     * @param parcelle the parcelle to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated parcelle,
     * or with status 400 (Bad Request) if the parcelle is not valid,
     * or with status 500 (Internal Server Error) if the parcelle couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/parcelles")
    public ResponseEntity<Parcelle> updateParcelle(@RequestBody Parcelle parcelle) throws URISyntaxException {
        log.debug("REST request to update Parcelle : {}", parcelle);
        if (parcelle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Parcelle result = parcelleService.save(parcelle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, parcelle.getId().toString()))
            .body(result);
    }

    /**
     * GET  /parcelles : get all the parcelles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of parcelles in body
     */
    @GetMapping("/parcelles")
    public List<Parcelle> getAllParcelles() {
        log.debug("REST request to get all Parcelles");
        return parcelleService.findAll();
    }

    /**
     * GET  /parcelles/:id : get the "id" parcelle.
     *
     * @param id the id of the parcelle to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the parcelle, or with status 404 (Not Found)
     */
    @GetMapping("/parcelles/{id}")
    public ResponseEntity<Parcelle> getParcelle(@PathVariable Long id) {
        log.debug("REST request to get Parcelle : {}", id);
        Optional<Parcelle> parcelle = parcelleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(parcelle);
    }

    /**
     * DELETE  /parcelles/:id : delete the "id" parcelle.
     *
     * @param id the id of the parcelle to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/parcelles/{id}")
    public ResponseEntity<Void> deleteParcelle(@PathVariable Long id) {
        log.debug("REST request to delete Parcelle : {}", id);
        parcelleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
