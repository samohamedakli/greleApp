package com.aviva.agriculture.grele.web.rest;

import com.aviva.agriculture.grele.GreleApplicationApp;

import com.aviva.agriculture.grele.domain.Parcelle;
import com.aviva.agriculture.grele.repository.ParcelleRepository;
import com.aviva.agriculture.grele.service.ParcelleService;
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
import java.math.BigDecimal;
import java.util.List;


import static com.aviva.agriculture.grele.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ParcelleResource REST controller.
 *
 * @see ParcelleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GreleApplicationApp.class)
public class ParcelleResourceIntTest {

    private static final Long DEFAULT_ID_RISQUE = 1L;
    private static final Long UPDATED_ID_RISQUE = 2L;

    private static final Long DEFAULT_ID_PARCELLE = 1L;
    private static final Long UPDATED_ID_PARCELLE = 2L;

    private static final Integer DEFAULT_NUM_ORDRE = 1;
    private static final Integer UPDATED_NUM_ORDRE = 2;

    private static final Long DEFAULT_CODE_POSTAL = 1L;
    private static final Long UPDATED_CODE_POSTAL = 2L;

    private static final String DEFAULT_LIBELLE_COMMUNE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_COMMUNE = "BBBBBBBBBB";

    private static final String DEFAULT_LIEUDIT = "AAAAAAAAAA";
    private static final String UPDATED_LIEUDIT = "BBBBBBBBBB";

    private static final Long DEFAULT_SUPERFICIE = 1L;
    private static final Long UPDATED_SUPERFICIE = 2L;

    private static final BigDecimal DEFAULT_RENDEMENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_RENDEMENT = new BigDecimal(2);

    private static final Double DEFAULT_PRIX_UNITAIRE = 1D;
    private static final Double UPDATED_PRIX_UNITAIRE = 2D;

    private static final BigDecimal DEFAULT_TAUX_COTISATION_GRELE = new BigDecimal(1);
    private static final BigDecimal UPDATED_TAUX_COTISATION_GRELE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TAUX_COTISATION_ARC = new BigDecimal(1);
    private static final BigDecimal UPDATED_TAUX_COTISATION_ARC = new BigDecimal(2);

    @Autowired
    private ParcelleRepository parcelleRepository;

    @Autowired
    private ParcelleService parcelleService;

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

    private MockMvc restParcelleMockMvc;

    private Parcelle parcelle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ParcelleResource parcelleResource = new ParcelleResource(parcelleService);
        this.restParcelleMockMvc = MockMvcBuilders.standaloneSetup(parcelleResource)
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
    public static Parcelle createEntity(EntityManager em) {
        Parcelle parcelle = new Parcelle()
            .idRisque(DEFAULT_ID_RISQUE)
            .idParcelle(DEFAULT_ID_PARCELLE)
            .numOrdre(DEFAULT_NUM_ORDRE)
            .codePostal(DEFAULT_CODE_POSTAL)
            .libelleCommune(DEFAULT_LIBELLE_COMMUNE)
            .lieudit(DEFAULT_LIEUDIT)
            .superficie(DEFAULT_SUPERFICIE)
            .rendement(DEFAULT_RENDEMENT)
            .prixUnitaire(DEFAULT_PRIX_UNITAIRE)
            .tauxCotisationGrele(DEFAULT_TAUX_COTISATION_GRELE)
            .tauxCotisationArc(DEFAULT_TAUX_COTISATION_ARC);
        return parcelle;
    }

    @Before
    public void initTest() {
        parcelle = createEntity(em);
    }

    @Test
    @Transactional
    public void createParcelle() throws Exception {
        int databaseSizeBeforeCreate = parcelleRepository.findAll().size();

        // Create the Parcelle
        restParcelleMockMvc.perform(post("/api/parcelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parcelle)))
            .andExpect(status().isCreated());

        // Validate the Parcelle in the database
        List<Parcelle> parcelleList = parcelleRepository.findAll();
        assertThat(parcelleList).hasSize(databaseSizeBeforeCreate + 1);
        Parcelle testParcelle = parcelleList.get(parcelleList.size() - 1);
        assertThat(testParcelle.getIdRisque()).isEqualTo(DEFAULT_ID_RISQUE);
        assertThat(testParcelle.getIdParcelle()).isEqualTo(DEFAULT_ID_PARCELLE);
        assertThat(testParcelle.getNumOrdre()).isEqualTo(DEFAULT_NUM_ORDRE);
        assertThat(testParcelle.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testParcelle.getLibelleCommune()).isEqualTo(DEFAULT_LIBELLE_COMMUNE);
        assertThat(testParcelle.getLieudit()).isEqualTo(DEFAULT_LIEUDIT);
        assertThat(testParcelle.getSuperficie()).isEqualTo(DEFAULT_SUPERFICIE);
        assertThat(testParcelle.getRendement()).isEqualTo(DEFAULT_RENDEMENT);
        assertThat(testParcelle.getPrixUnitaire()).isEqualTo(DEFAULT_PRIX_UNITAIRE);
        assertThat(testParcelle.getTauxCotisationGrele()).isEqualTo(DEFAULT_TAUX_COTISATION_GRELE);
        assertThat(testParcelle.getTauxCotisationArc()).isEqualTo(DEFAULT_TAUX_COTISATION_ARC);
    }

    @Test
    @Transactional
    public void createParcelleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = parcelleRepository.findAll().size();

        // Create the Parcelle with an existing ID
        parcelle.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParcelleMockMvc.perform(post("/api/parcelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parcelle)))
            .andExpect(status().isBadRequest());

        // Validate the Parcelle in the database
        List<Parcelle> parcelleList = parcelleRepository.findAll();
        assertThat(parcelleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllParcelles() throws Exception {
        // Initialize the database
        parcelleRepository.saveAndFlush(parcelle);

        // Get all the parcelleList
        restParcelleMockMvc.perform(get("/api/parcelles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(parcelle.getId().intValue())))
            .andExpect(jsonPath("$.[*].idRisque").value(hasItem(DEFAULT_ID_RISQUE.intValue())))
            .andExpect(jsonPath("$.[*].idParcelle").value(hasItem(DEFAULT_ID_PARCELLE.intValue())))
            .andExpect(jsonPath("$.[*].numOrdre").value(hasItem(DEFAULT_NUM_ORDRE)))
            .andExpect(jsonPath("$.[*].codePostal").value(hasItem(DEFAULT_CODE_POSTAL.intValue())))
            .andExpect(jsonPath("$.[*].libelleCommune").value(hasItem(DEFAULT_LIBELLE_COMMUNE.toString())))
            .andExpect(jsonPath("$.[*].lieudit").value(hasItem(DEFAULT_LIEUDIT.toString())))
            .andExpect(jsonPath("$.[*].superficie").value(hasItem(DEFAULT_SUPERFICIE.intValue())))
            .andExpect(jsonPath("$.[*].rendement").value(hasItem(DEFAULT_RENDEMENT.intValue())))
            .andExpect(jsonPath("$.[*].prixUnitaire").value(hasItem(DEFAULT_PRIX_UNITAIRE.doubleValue())))
            .andExpect(jsonPath("$.[*].tauxCotisationGrele").value(hasItem(DEFAULT_TAUX_COTISATION_GRELE.intValue())))
            .andExpect(jsonPath("$.[*].tauxCotisationArc").value(hasItem(DEFAULT_TAUX_COTISATION_ARC.intValue())));
    }
    
    @Test
    @Transactional
    public void getParcelle() throws Exception {
        // Initialize the database
        parcelleRepository.saveAndFlush(parcelle);

        // Get the parcelle
        restParcelleMockMvc.perform(get("/api/parcelles/{id}", parcelle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(parcelle.getId().intValue()))
            .andExpect(jsonPath("$.idRisque").value(DEFAULT_ID_RISQUE.intValue()))
            .andExpect(jsonPath("$.idParcelle").value(DEFAULT_ID_PARCELLE.intValue()))
            .andExpect(jsonPath("$.numOrdre").value(DEFAULT_NUM_ORDRE))
            .andExpect(jsonPath("$.codePostal").value(DEFAULT_CODE_POSTAL.intValue()))
            .andExpect(jsonPath("$.libelleCommune").value(DEFAULT_LIBELLE_COMMUNE.toString()))
            .andExpect(jsonPath("$.lieudit").value(DEFAULT_LIEUDIT.toString()))
            .andExpect(jsonPath("$.superficie").value(DEFAULT_SUPERFICIE.intValue()))
            .andExpect(jsonPath("$.rendement").value(DEFAULT_RENDEMENT.intValue()))
            .andExpect(jsonPath("$.prixUnitaire").value(DEFAULT_PRIX_UNITAIRE.doubleValue()))
            .andExpect(jsonPath("$.tauxCotisationGrele").value(DEFAULT_TAUX_COTISATION_GRELE.intValue()))
            .andExpect(jsonPath("$.tauxCotisationArc").value(DEFAULT_TAUX_COTISATION_ARC.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingParcelle() throws Exception {
        // Get the parcelle
        restParcelleMockMvc.perform(get("/api/parcelles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParcelle() throws Exception {
        // Initialize the database
        parcelleService.save(parcelle);

        int databaseSizeBeforeUpdate = parcelleRepository.findAll().size();

        // Update the parcelle
        Parcelle updatedParcelle = parcelleRepository.findById(parcelle.getId()).get();
        // Disconnect from session so that the updates on updatedParcelle are not directly saved in db
        em.detach(updatedParcelle);
        updatedParcelle
            .idRisque(UPDATED_ID_RISQUE)
            .idParcelle(UPDATED_ID_PARCELLE)
            .numOrdre(UPDATED_NUM_ORDRE)
            .codePostal(UPDATED_CODE_POSTAL)
            .libelleCommune(UPDATED_LIBELLE_COMMUNE)
            .lieudit(UPDATED_LIEUDIT)
            .superficie(UPDATED_SUPERFICIE)
            .rendement(UPDATED_RENDEMENT)
            .prixUnitaire(UPDATED_PRIX_UNITAIRE)
            .tauxCotisationGrele(UPDATED_TAUX_COTISATION_GRELE)
            .tauxCotisationArc(UPDATED_TAUX_COTISATION_ARC);

        restParcelleMockMvc.perform(put("/api/parcelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedParcelle)))
            .andExpect(status().isOk());

        // Validate the Parcelle in the database
        List<Parcelle> parcelleList = parcelleRepository.findAll();
        assertThat(parcelleList).hasSize(databaseSizeBeforeUpdate);
        Parcelle testParcelle = parcelleList.get(parcelleList.size() - 1);
        assertThat(testParcelle.getIdRisque()).isEqualTo(UPDATED_ID_RISQUE);
        assertThat(testParcelle.getIdParcelle()).isEqualTo(UPDATED_ID_PARCELLE);
        assertThat(testParcelle.getNumOrdre()).isEqualTo(UPDATED_NUM_ORDRE);
        assertThat(testParcelle.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testParcelle.getLibelleCommune()).isEqualTo(UPDATED_LIBELLE_COMMUNE);
        assertThat(testParcelle.getLieudit()).isEqualTo(UPDATED_LIEUDIT);
        assertThat(testParcelle.getSuperficie()).isEqualTo(UPDATED_SUPERFICIE);
        assertThat(testParcelle.getRendement()).isEqualTo(UPDATED_RENDEMENT);
        assertThat(testParcelle.getPrixUnitaire()).isEqualTo(UPDATED_PRIX_UNITAIRE);
        assertThat(testParcelle.getTauxCotisationGrele()).isEqualTo(UPDATED_TAUX_COTISATION_GRELE);
        assertThat(testParcelle.getTauxCotisationArc()).isEqualTo(UPDATED_TAUX_COTISATION_ARC);
    }

    @Test
    @Transactional
    public void updateNonExistingParcelle() throws Exception {
        int databaseSizeBeforeUpdate = parcelleRepository.findAll().size();

        // Create the Parcelle

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParcelleMockMvc.perform(put("/api/parcelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parcelle)))
            .andExpect(status().isBadRequest());

        // Validate the Parcelle in the database
        List<Parcelle> parcelleList = parcelleRepository.findAll();
        assertThat(parcelleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParcelle() throws Exception {
        // Initialize the database
        parcelleService.save(parcelle);

        int databaseSizeBeforeDelete = parcelleRepository.findAll().size();

        // Delete the parcelle
        restParcelleMockMvc.perform(delete("/api/parcelles/{id}", parcelle.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Parcelle> parcelleList = parcelleRepository.findAll();
        assertThat(parcelleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Parcelle.class);
        Parcelle parcelle1 = new Parcelle();
        parcelle1.setId(1L);
        Parcelle parcelle2 = new Parcelle();
        parcelle2.setId(parcelle1.getId());
        assertThat(parcelle1).isEqualTo(parcelle2);
        parcelle2.setId(2L);
        assertThat(parcelle1).isNotEqualTo(parcelle2);
        parcelle1.setId(null);
        assertThat(parcelle1).isNotEqualTo(parcelle2);
    }
}
