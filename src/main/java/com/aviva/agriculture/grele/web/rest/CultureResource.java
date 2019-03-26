package com.aviva.agriculture.grele.web.rest;
import com.aviva.agriculture.grele.domain.Culture;
import com.aviva.agriculture.grele.service.CultureService;
import com.aviva.agriculture.grele.web.rest.errors.BadRequestAlertException;
import com.aviva.agriculture.grele.web.rest.util.HeaderUtil;
import com.aviva.agriculture.grele.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Culture.
 */
@RestController
@RequestMapping("/api")
public class CultureResource {

    private final Logger log = LoggerFactory.getLogger(CultureResource.class);

    private static final String ENTITY_NAME = "culture";

    private final CultureService cultureService;

    public CultureResource(CultureService cultureService) {
        this.cultureService = cultureService;
    }

    /**
     * POST  /cultures : Create a new culture.
     *
     * @param culture the culture to create
     * @return the ResponseEntity with status 201 (Created) and with body the new culture, or with status 400 (Bad Request) if the culture has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cultures")
    public ResponseEntity<Culture> createCulture(@RequestBody Culture culture) throws URISyntaxException {
        log.debug("REST request to save Culture : {}", culture);
        if (culture.getId() != null) {
            throw new BadRequestAlertException("A new culture cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Culture result = cultureService.save(culture);
        return ResponseEntity.created(new URI("/api/cultures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cultures : Updates an existing culture.
     *
     * @param culture the culture to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated culture,
     * or with status 400 (Bad Request) if the culture is not valid,
     * or with status 500 (Internal Server Error) if the culture couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cultures")
    public ResponseEntity<Culture> updateCulture(@RequestBody Culture culture) throws URISyntaxException {
        log.debug("REST request to update Culture : {}", culture);
        if (culture.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Culture result = cultureService.save(culture);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, culture.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cultures : get all the cultures.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cultures in body
     */
    @GetMapping("/cultures")
    public ResponseEntity<List<Culture>> getAllCultures(Pageable pageable) {
        log.debug("REST request to get a page of Cultures");
        Page<Culture> page = cultureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cultures");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /cultures/:id : get the "id" culture.
     *
     * @param id the id of the culture to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the culture, or with status 404 (Not Found)
     */
    @GetMapping("/cultures/{id}")
    public ResponseEntity<Culture> getCulture(@PathVariable Long id) {
        log.debug("REST request to get Culture : {}", id);
        Optional<Culture> culture = cultureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(culture);
    }

    /**
     * DELETE  /cultures/:id : delete the "id" culture.
     *
     * @param id the id of the culture to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cultures/{id}")
    public ResponseEntity<Void> deleteCulture(@PathVariable Long id) {
        log.debug("REST request to delete Culture : {}", id);
        cultureService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
