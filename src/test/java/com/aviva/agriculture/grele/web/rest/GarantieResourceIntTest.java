package com.aviva.agriculture.grele.web.rest;

import com.aviva.agriculture.grele.GreleApplicationApp;

import com.aviva.agriculture.grele.domain.Garantie;
import com.aviva.agriculture.grele.repository.GarantieRepository;
import com.aviva.agriculture.grele.service.GarantieService;
import com.aviva.agriculture.grele.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.aviva.agriculture.grele.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GarantieResource REST controller.
 *
 * @see GarantieResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GreleApplicationApp.class)
public class GarantieResourceIntTest {

    private static final Integer DEFAULT_CODE_GARANTIE = 1;
    private static final Integer UPDATED_CODE_GARANTIE = 2;

    private static final String DEFAULT_LIBELLE_GARANTIE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_GARANTIE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_FRANCHISE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_FRANCHISE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TAUX_FRANCHISE = 1;
    private static final Integer UPDATED_TAUX_FRANCHISE = 2;

    @Autowired
    private GarantieRepository garantieRepository;

    @Autowired
    private GarantieService garantieService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restGarantieMockMvc;

    private Garantie garantie;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GarantieResource garantieResource = new GarantieResource(garantieService);
        this.restGarantieMockMvc = MockMvcBuilders.standaloneSetup(garantieResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Garantie createEntity(EntityManager em) {
        Garantie garantie = new Garantie()
            .codeGarantie(DEFAULT_CODE_GARANTIE)
            .libelleGarantie(DEFAULT_LIBELLE_GARANTIE)
            .typeFranchise(DEFAULT_TYPE_FRANCHISE)
            .tauxFranchise(DEFAULT_TAUX_FRANCHISE);
        return garantie;
    }

    @Before
    public void initTest() {
        garantie = createEntity(em);
    }

    @Test
    @Transactional
    public void createGarantie() throws Exception {
        int databaseSizeBeforeCreate = garantieRepository.findAll().size();

        // Create the Garantie
        restGarantieMockMvc.perform(post("/api/garanties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garantie)))
            .andExpect(status().isCreated());

        // Validate the Garantie in the database
        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeCreate + 1);
        Garantie testGarantie = garantieList.get(garantieList.size() - 1);
        assertThat(testGarantie.getCodeGarantie()).isEqualTo(DEFAULT_CODE_GARANTIE);
        assertThat(testGarantie.getLibelleGarantie()).isEqualTo(DEFAULT_LIBELLE_GARANTIE);
        assertThat(testGarantie.getTypeFranchise()).isEqualTo(DEFAULT_TYPE_FRANCHISE);
        assertThat(testGarantie.getTauxFranchise()).isEqualTo(DEFAULT_TAUX_FRANCHISE);
    }

    @Test
    @Transactional
    public void createGarantieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = garantieRepository.findAll().size();

        // Create the Garantie with an existing ID
        garantie.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGarantieMockMvc.perform(post("/api/garanties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garantie)))
            .andExpect(status().isBadRequest());

        // Validate the Garantie in the database
        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGaranties() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList
        restGarantieMockMvc.perform(get("/api/garanties?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(garantie.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeGarantie").value(hasItem(DEFAULT_CODE_GARANTIE)))
            .andExpect(jsonPath("$.[*].libelleGarantie").value(hasItem(DEFAULT_LIBELLE_GARANTIE.toString())))
            .andExpect(jsonPath("$.[*].typeFranchise").value(hasItem(DEFAULT_TYPE_FRANCHISE.toString())))
            .andExpect(jsonPath("$.[*].tauxFranchise").value(hasItem(DEFAULT_TAUX_FRANCHISE)));
    }
    
    @Test
    @Transactional
    public void getGarantie() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get the garantie
        restGarantieMockMvc.perform(get("/api/garanties/{id}", garantie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(garantie.getId().intValue()))
            .andExpect(jsonPath("$.codeGarantie").value(DEFAULT_CODE_GARANTIE))
            .andExpect(jsonPath("$.libelleGarantie").value(DEFAULT_LIBELLE_GARANTIE.toString()))
            .andExpect(jsonPath("$.typeFranchise").value(DEFAULT_TYPE_FRANCHISE.toString()))
            .andExpect(jsonPath("$.tauxFranchise").value(DEFAULT_TAUX_FRANCHISE));
    }

    @Test
    @Transactional
    public void getNonExistingGarantie() throws Exception {
        // Get the garantie
        restGarantieMockMvc.perform(get("/api/garanties/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGarantie() throws Exception {
        // Initialize the database
        garantieService.save(garantie);

        int databaseSizeBeforeUpdate = garantieRepository.findAll().size();

        // Update the garantie
        Garantie updatedGarantie = garantieRepository.findById(garantie.getId()).get();
        // Disconnect from session so that the updates on updatedGarantie are not directly saved in db
        em.detach(updatedGarantie);
        updatedGarantie
            .codeGarantie(UPDATED_CODE_GARANTIE)
            .libelleGarantie(UPDATED_LIBELLE_GARANTIE)
            .typeFranchise(UPDATED_TYPE_FRANCHISE)
            .tauxFranchise(UPDATED_TAUX_FRANCHISE);

        restGarantieMockMvc.perform(put("/api/garanties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGarantie)))
            .andExpect(status().isOk());

        // Validate the Garantie in the database
        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeUpdate);
        Garantie testGarantie = garantieList.get(garantieList.size() - 1);
        assertThat(testGarantie.getCodeGarantie()).isEqualTo(UPDATED_CODE_GARANTIE);
        assertThat(testGarantie.getLibelleGarantie()).isEqualTo(UPDATED_LIBELLE_GARANTIE);
        assertThat(testGarantie.getTypeFranchise()).isEqualTo(UPDATED_TYPE_FRANCHISE);
        assertThat(testGarantie.getTauxFranchise()).isEqualTo(UPDATED_TAUX_FRANCHISE);
    }

    @Test
    @Transactional
    public void updateNonExistingGarantie() throws Exception {
        int databaseSizeBeforeUpdate = garantieRepository.findAll().size();

        // Create the Garantie

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGarantieMockMvc.perform(put("/api/garanties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garantie)))
            .andExpect(status().isBadRequest());

        // Validate the Garantie in the database
        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGarantie() throws Exception {
        // Initialize the database
        garantieService.save(garantie);

        int databaseSizeBeforeDelete = garantieRepository.findAll().size();

        // Delete the garantie
        restGarantieMockMvc.perform(delete("/api/garanties/{id}", garantie.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Garantie.class);
        Garantie garantie1 = new Garantie();
        garantie1.setId(1L);
        Garantie garantie2 = new Garantie();
        garantie2.setId(garantie1.getId());
        assertThat(garantie1).isEqualTo(garantie2);
        garantie2.setId(2L);
        assertThat(garantie1).isNotEqualTo(garantie2);
        garantie1.setId(null);
        assertThat(garantie1).isNotEqualTo(garantie2);
    }
}
