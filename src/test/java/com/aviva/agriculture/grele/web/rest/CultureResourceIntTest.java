package com.aviva.agriculture.grele.web.rest;

import com.aviva.agriculture.grele.GreleApplicationApp;

import com.aviva.agriculture.grele.domain.Culture;
import com.aviva.agriculture.grele.repository.CultureRepository;
import com.aviva.agriculture.grele.service.CultureService;
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
 * Test class for the CultureResource REST controller.
 *
 * @see CultureResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GreleApplicationApp.class)
public class CultureResourceIntTest {

    private static final Long DEFAULT_ID_RISQUE = 1L;
    private static final Long UPDATED_ID_RISQUE = 2L;

    private static final Long DEFAULT_ID_CULTURE = 1L;
    private static final Long UPDATED_ID_CULTURE = 2L;

    private static final Integer DEFAULT_NUM_ORDRE = 1;
    private static final Integer UPDATED_NUM_ORDRE = 2;

    private static final Integer DEFAULT_CLASSE_NATURE_CLT = 1;
    private static final Integer UPDATED_CLASSE_NATURE_CLT = 2;

    private static final String DEFAULT_NATURE_CULTURE = "AAAAAAAAAA";
    private static final String UPDATED_NATURE_CULTURE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_CARACTERISTIQUE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CARACTERISTIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE_CARACTERISTIQUE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_CARACTERISTIQUE = "BBBBBBBBBB";

    private static final Double DEFAULT_PRIX_OFFICIEL = 1D;
    private static final Double UPDATED_PRIX_OFFICIEL = 2D;

    private static final String DEFAULT_UNITE_MESURE = "AAAAAAAAAA";
    private static final String UPDATED_UNITE_MESURE = "BBBBBBBBBB";

    private static final Long DEFAULT_SUPERFICIE = 1L;
    private static final Long UPDATED_SUPERFICIE = 2L;

    private static final Long DEFAULT_SUPERFICIE_EXPL = 1L;
    private static final Long UPDATED_SUPERFICIE_EXPL = 2L;

    private static final String DEFAULT_UNITE_SUPERFICIE = "AAAAAAAAAA";
    private static final String UPDATED_UNITE_SUPERFICIE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NB_PARCELLES = 1;
    private static final Integer UPDATED_NB_PARCELLES = 2;

    private static final Boolean DEFAULT_CULTUREIRRIGUEE = false;
    private static final Boolean UPDATED_CULTUREIRRIGUEE = true;

    private static final Double DEFAULT_RENDEMENT_MOYEN = 1D;
    private static final Double UPDATED_RENDEMENT_MOYEN = 2D;

    private static final Double DEFAULT_PRIX_MOYEN = 1D;
    private static final Double UPDATED_PRIX_MOYEN = 2D;

    private static final Double DEFAULT_PRIX_AUTRE = 1D;
    private static final Double UPDATED_PRIX_AUTRE = 2D;

    private static final Double DEFAULT_PRIX_RETENU = 1D;
    private static final Double UPDATED_PRIX_RETENU = 2D;

    private static final BigDecimal DEFAULT_RENDEMENT_N_1 = new BigDecimal(1);
    private static final BigDecimal UPDATED_RENDEMENT_N_1 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_RENDEMENT_N_2 = new BigDecimal(1);
    private static final BigDecimal UPDATED_RENDEMENT_N_2 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_RENDEMENT_N_3 = new BigDecimal(1);
    private static final BigDecimal UPDATED_RENDEMENT_N_3 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_RENDEMENT_N_4 = new BigDecimal(1);
    private static final BigDecimal UPDATED_RENDEMENT_N_4 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_RENDEMENT_N_5 = new BigDecimal(1);
    private static final BigDecimal UPDATED_RENDEMENT_N_5 = new BigDecimal(2);

    private static final Double DEFAULT_PRIX_UNITAIRE_1 = 1D;
    private static final Double UPDATED_PRIX_UNITAIRE_1 = 2D;

    private static final Double DEFAULT_PRIX_UNITAIRE_2 = 1D;
    private static final Double UPDATED_PRIX_UNITAIRE_2 = 2D;

    private static final Double DEFAULT_PRIX_UNITAIRE_3 = 1D;
    private static final Double UPDATED_PRIX_UNITAIRE_3 = 2D;

    private static final Double DEFAULT_PRIX_UNITAIRE_4 = 1D;
    private static final Double UPDATED_PRIX_UNITAIRE_4 = 2D;

    private static final Double DEFAULT_PRIX_UNITAIRE_5 = 1D;
    private static final Double UPDATED_PRIX_UNITAIRE_5 = 2D;

    private static final String DEFAULT_MOTIF_AUTRE_PRIX = "AAAAAAAAAA";
    private static final String UPDATED_MOTIF_AUTRE_PRIX = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MAJ_EN_ATTENTE = false;
    private static final Boolean UPDATED_MAJ_EN_ATTENTE = true;

    private static final Boolean DEFAULT_ABSENCE_CULTURE_AVENIR = false;
    private static final Boolean UPDATED_ABSENCE_CULTURE_AVENIR = true;

    @Autowired
    private CultureRepository cultureRepository;

    @Autowired
    private CultureService cultureService;

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

    private MockMvc restCultureMockMvc;

    private Culture culture;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CultureResource cultureResource = new CultureResource(cultureService);
        this.restCultureMockMvc = MockMvcBuilders.standaloneSetup(cultureResource)
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
    public static Culture createEntity(EntityManager em) {
        Culture culture = new Culture()
            .idRisque(DEFAULT_ID_RISQUE)
            .idCulture(DEFAULT_ID_CULTURE)
            .numOrdre(DEFAULT_NUM_ORDRE)
            .classeNatureCLT(DEFAULT_CLASSE_NATURE_CLT)
            .natureCulture(DEFAULT_NATURE_CULTURE)
            .codeCaracteristique(DEFAULT_CODE_CARACTERISTIQUE)
            .libelleCaracteristique(DEFAULT_LIBELLE_CARACTERISTIQUE)
            .prixOfficiel(DEFAULT_PRIX_OFFICIEL)
            .uniteMesure(DEFAULT_UNITE_MESURE)
            .superficie(DEFAULT_SUPERFICIE)
            .superficieExpl(DEFAULT_SUPERFICIE_EXPL)
            .uniteSuperficie(DEFAULT_UNITE_SUPERFICIE)
            .nbParcelles(DEFAULT_NB_PARCELLES)
            .cultureirriguee(DEFAULT_CULTUREIRRIGUEE)
            .rendementMoyen(DEFAULT_RENDEMENT_MOYEN)
            .prixMoyen(DEFAULT_PRIX_MOYEN)
            .prixAutre(DEFAULT_PRIX_AUTRE)
            .prixRetenu(DEFAULT_PRIX_RETENU)
            .rendementN1(DEFAULT_RENDEMENT_N_1)
            .rendementN2(DEFAULT_RENDEMENT_N_2)
            .rendementN3(DEFAULT_RENDEMENT_N_3)
            .rendementN4(DEFAULT_RENDEMENT_N_4)
            .rendementN5(DEFAULT_RENDEMENT_N_5)
            .prixUnitaire1(DEFAULT_PRIX_UNITAIRE_1)
            .prixUnitaire2(DEFAULT_PRIX_UNITAIRE_2)
            .prixUnitaire3(DEFAULT_PRIX_UNITAIRE_3)
            .prixUnitaire4(DEFAULT_PRIX_UNITAIRE_4)
            .prixUnitaire5(DEFAULT_PRIX_UNITAIRE_5)
            .motifAutrePrix(DEFAULT_MOTIF_AUTRE_PRIX)
            .majEnAttente(DEFAULT_MAJ_EN_ATTENTE)
            .absenceCultureAvenir(DEFAULT_ABSENCE_CULTURE_AVENIR);
        return culture;
    }

    @Before
    public void initTest() {
        culture = createEntity(em);
    }

    @Test
    @Transactional
    public void createCulture() throws Exception {
        int databaseSizeBeforeCreate = cultureRepository.findAll().size();

        // Create the Culture
        restCultureMockMvc.perform(post("/api/cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(culture)))
            .andExpect(status().isCreated());

        // Validate the Culture in the database
        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeCreate + 1);
        Culture testCulture = cultureList.get(cultureList.size() - 1);
        assertThat(testCulture.getIdRisque()).isEqualTo(DEFAULT_ID_RISQUE);
        assertThat(testCulture.getIdCulture()).isEqualTo(DEFAULT_ID_CULTURE);
        assertThat(testCulture.getNumOrdre()).isEqualTo(DEFAULT_NUM_ORDRE);
        assertThat(testCulture.getClasseNatureCLT()).isEqualTo(DEFAULT_CLASSE_NATURE_CLT);
        assertThat(testCulture.getNatureCulture()).isEqualTo(DEFAULT_NATURE_CULTURE);
        assertThat(testCulture.getCodeCaracteristique()).isEqualTo(DEFAULT_CODE_CARACTERISTIQUE);
        assertThat(testCulture.getLibelleCaracteristique()).isEqualTo(DEFAULT_LIBELLE_CARACTERISTIQUE);
        assertThat(testCulture.getPrixOfficiel()).isEqualTo(DEFAULT_PRIX_OFFICIEL);
        assertThat(testCulture.getUniteMesure()).isEqualTo(DEFAULT_UNITE_MESURE);
        assertThat(testCulture.getSuperficie()).isEqualTo(DEFAULT_SUPERFICIE);
        assertThat(testCulture.getSuperficieExpl()).isEqualTo(DEFAULT_SUPERFICIE_EXPL);
        assertThat(testCulture.getUniteSuperficie()).isEqualTo(DEFAULT_UNITE_SUPERFICIE);
        assertThat(testCulture.getNbParcelles()).isEqualTo(DEFAULT_NB_PARCELLES);
        assertThat(testCulture.isCultureirriguee()).isEqualTo(DEFAULT_CULTUREIRRIGUEE);
        assertThat(testCulture.getRendementMoyen()).isEqualTo(DEFAULT_RENDEMENT_MOYEN);
        assertThat(testCulture.getPrixMoyen()).isEqualTo(DEFAULT_PRIX_MOYEN);
        assertThat(testCulture.getPrixAutre()).isEqualTo(DEFAULT_PRIX_AUTRE);
        assertThat(testCulture.getPrixRetenu()).isEqualTo(DEFAULT_PRIX_RETENU);
        assertThat(testCulture.getRendementN1()).isEqualTo(DEFAULT_RENDEMENT_N_1);
        assertThat(testCulture.getRendementN2()).isEqualTo(DEFAULT_RENDEMENT_N_2);
        assertThat(testCulture.getRendementN3()).isEqualTo(DEFAULT_RENDEMENT_N_3);
        assertThat(testCulture.getRendementN4()).isEqualTo(DEFAULT_RENDEMENT_N_4);
        assertThat(testCulture.getRendementN5()).isEqualTo(DEFAULT_RENDEMENT_N_5);
        assertThat(testCulture.getPrixUnitaire1()).isEqualTo(DEFAULT_PRIX_UNITAIRE_1);
        assertThat(testCulture.getPrixUnitaire2()).isEqualTo(DEFAULT_PRIX_UNITAIRE_2);
        assertThat(testCulture.getPrixUnitaire3()).isEqualTo(DEFAULT_PRIX_UNITAIRE_3);
        assertThat(testCulture.getPrixUnitaire4()).isEqualTo(DEFAULT_PRIX_UNITAIRE_4);
        assertThat(testCulture.getPrixUnitaire5()).isEqualTo(DEFAULT_PRIX_UNITAIRE_5);
        assertThat(testCulture.getMotifAutrePrix()).isEqualTo(DEFAULT_MOTIF_AUTRE_PRIX);
        assertThat(testCulture.isMajEnAttente()).isEqualTo(DEFAULT_MAJ_EN_ATTENTE);
        assertThat(testCulture.isAbsenceCultureAvenir()).isEqualTo(DEFAULT_ABSENCE_CULTURE_AVENIR);
    }

    @Test
    @Transactional
    public void createCultureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cultureRepository.findAll().size();

        // Create the Culture with an existing ID
        culture.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCultureMockMvc.perform(post("/api/cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(culture)))
            .andExpect(status().isBadRequest());

        // Validate the Culture in the database
        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCultures() throws Exception {
        // Initialize the database
        cultureRepository.saveAndFlush(culture);

        // Get all the cultureList
        restCultureMockMvc.perform(get("/api/cultures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(culture.getId().intValue())))
            .andExpect(jsonPath("$.[*].idRisque").value(hasItem(DEFAULT_ID_RISQUE.intValue())))
            .andExpect(jsonPath("$.[*].idCulture").value(hasItem(DEFAULT_ID_CULTURE.intValue())))
            .andExpect(jsonPath("$.[*].numOrdre").value(hasItem(DEFAULT_NUM_ORDRE)))
            .andExpect(jsonPath("$.[*].classeNatureCLT").value(hasItem(DEFAULT_CLASSE_NATURE_CLT)))
            .andExpect(jsonPath("$.[*].natureCulture").value(hasItem(DEFAULT_NATURE_CULTURE.toString())))
            .andExpect(jsonPath("$.[*].codeCaracteristique").value(hasItem(DEFAULT_CODE_CARACTERISTIQUE.toString())))
            .andExpect(jsonPath("$.[*].libelleCaracteristique").value(hasItem(DEFAULT_LIBELLE_CARACTERISTIQUE.toString())))
            .andExpect(jsonPath("$.[*].prixOfficiel").value(hasItem(DEFAULT_PRIX_OFFICIEL.doubleValue())))
            .andExpect(jsonPath("$.[*].uniteMesure").value(hasItem(DEFAULT_UNITE_MESURE.toString())))
            .andExpect(jsonPath("$.[*].superficie").value(hasItem(DEFAULT_SUPERFICIE.intValue())))
            .andExpect(jsonPath("$.[*].superficieExpl").value(hasItem(DEFAULT_SUPERFICIE_EXPL.intValue())))
            .andExpect(jsonPath("$.[*].uniteSuperficie").value(hasItem(DEFAULT_UNITE_SUPERFICIE.toString())))
            .andExpect(jsonPath("$.[*].nbParcelles").value(hasItem(DEFAULT_NB_PARCELLES)))
            .andExpect(jsonPath("$.[*].cultureirriguee").value(hasItem(DEFAULT_CULTUREIRRIGUEE.booleanValue())))
            .andExpect(jsonPath("$.[*].rendementMoyen").value(hasItem(DEFAULT_RENDEMENT_MOYEN.doubleValue())))
            .andExpect(jsonPath("$.[*].prixMoyen").value(hasItem(DEFAULT_PRIX_MOYEN.doubleValue())))
            .andExpect(jsonPath("$.[*].prixAutre").value(hasItem(DEFAULT_PRIX_AUTRE.doubleValue())))
            .andExpect(jsonPath("$.[*].prixRetenu").value(hasItem(DEFAULT_PRIX_RETENU.doubleValue())))
            .andExpect(jsonPath("$.[*].rendementN1").value(hasItem(DEFAULT_RENDEMENT_N_1.intValue())))
            .andExpect(jsonPath("$.[*].rendementN2").value(hasItem(DEFAULT_RENDEMENT_N_2.intValue())))
            .andExpect(jsonPath("$.[*].rendementN3").value(hasItem(DEFAULT_RENDEMENT_N_3.intValue())))
            .andExpect(jsonPath("$.[*].rendementN4").value(hasItem(DEFAULT_RENDEMENT_N_4.intValue())))
            .andExpect(jsonPath("$.[*].rendementN5").value(hasItem(DEFAULT_RENDEMENT_N_5.intValue())))
            .andExpect(jsonPath("$.[*].prixUnitaire1").value(hasItem(DEFAULT_PRIX_UNITAIRE_1.doubleValue())))
            .andExpect(jsonPath("$.[*].prixUnitaire2").value(hasItem(DEFAULT_PRIX_UNITAIRE_2.doubleValue())))
            .andExpect(jsonPath("$.[*].prixUnitaire3").value(hasItem(DEFAULT_PRIX_UNITAIRE_3.doubleValue())))
            .andExpect(jsonPath("$.[*].prixUnitaire4").value(hasItem(DEFAULT_PRIX_UNITAIRE_4.doubleValue())))
            .andExpect(jsonPath("$.[*].prixUnitaire5").value(hasItem(DEFAULT_PRIX_UNITAIRE_5.doubleValue())))
            .andExpect(jsonPath("$.[*].motifAutrePrix").value(hasItem(DEFAULT_MOTIF_AUTRE_PRIX.toString())))
            .andExpect(jsonPath("$.[*].majEnAttente").value(hasItem(DEFAULT_MAJ_EN_ATTENTE.booleanValue())))
            .andExpect(jsonPath("$.[*].absenceCultureAvenir").value(hasItem(DEFAULT_ABSENCE_CULTURE_AVENIR.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCulture() throws Exception {
        // Initialize the database
        cultureRepository.saveAndFlush(culture);

        // Get the culture
        restCultureMockMvc.perform(get("/api/cultures/{id}", culture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(culture.getId().intValue()))
            .andExpect(jsonPath("$.idRisque").value(DEFAULT_ID_RISQUE.intValue()))
            .andExpect(jsonPath("$.idCulture").value(DEFAULT_ID_CULTURE.intValue()))
            .andExpect(jsonPath("$.numOrdre").value(DEFAULT_NUM_ORDRE))
            .andExpect(jsonPath("$.classeNatureCLT").value(DEFAULT_CLASSE_NATURE_CLT))
            .andExpect(jsonPath("$.natureCulture").value(DEFAULT_NATURE_CULTURE.toString()))
            .andExpect(jsonPath("$.codeCaracteristique").value(DEFAULT_CODE_CARACTERISTIQUE.toString()))
            .andExpect(jsonPath("$.libelleCaracteristique").value(DEFAULT_LIBELLE_CARACTERISTIQUE.toString()))
            .andExpect(jsonPath("$.prixOfficiel").value(DEFAULT_PRIX_OFFICIEL.doubleValue()))
            .andExpect(jsonPath("$.uniteMesure").value(DEFAULT_UNITE_MESURE.toString()))
            .andExpect(jsonPath("$.superficie").value(DEFAULT_SUPERFICIE.intValue()))
            .andExpect(jsonPath("$.superficieExpl").value(DEFAULT_SUPERFICIE_EXPL.intValue()))
            .andExpect(jsonPath("$.uniteSuperficie").value(DEFAULT_UNITE_SUPERFICIE.toString()))
            .andExpect(jsonPath("$.nbParcelles").value(DEFAULT_NB_PARCELLES))
            .andExpect(jsonPath("$.cultureirriguee").value(DEFAULT_CULTUREIRRIGUEE.booleanValue()))
            .andExpect(jsonPath("$.rendementMoyen").value(DEFAULT_RENDEMENT_MOYEN.doubleValue()))
            .andExpect(jsonPath("$.prixMoyen").value(DEFAULT_PRIX_MOYEN.doubleValue()))
            .andExpect(jsonPath("$.prixAutre").value(DEFAULT_PRIX_AUTRE.doubleValue()))
            .andExpect(jsonPath("$.prixRetenu").value(DEFAULT_PRIX_RETENU.doubleValue()))
            .andExpect(jsonPath("$.rendementN1").value(DEFAULT_RENDEMENT_N_1.intValue()))
            .andExpect(jsonPath("$.rendementN2").value(DEFAULT_RENDEMENT_N_2.intValue()))
            .andExpect(jsonPath("$.rendementN3").value(DEFAULT_RENDEMENT_N_3.intValue()))
            .andExpect(jsonPath("$.rendementN4").value(DEFAULT_RENDEMENT_N_4.intValue()))
            .andExpect(jsonPath("$.rendementN5").value(DEFAULT_RENDEMENT_N_5.intValue()))
            .andExpect(jsonPath("$.prixUnitaire1").value(DEFAULT_PRIX_UNITAIRE_1.doubleValue()))
            .andExpect(jsonPath("$.prixUnitaire2").value(DEFAULT_PRIX_UNITAIRE_2.doubleValue()))
            .andExpect(jsonPath("$.prixUnitaire3").value(DEFAULT_PRIX_UNITAIRE_3.doubleValue()))
            .andExpect(jsonPath("$.prixUnitaire4").value(DEFAULT_PRIX_UNITAIRE_4.doubleValue()))
            .andExpect(jsonPath("$.prixUnitaire5").value(DEFAULT_PRIX_UNITAIRE_5.doubleValue()))
            .andExpect(jsonPath("$.motifAutrePrix").value(DEFAULT_MOTIF_AUTRE_PRIX.toString()))
            .andExpect(jsonPath("$.majEnAttente").value(DEFAULT_MAJ_EN_ATTENTE.booleanValue()))
            .andExpect(jsonPath("$.absenceCultureAvenir").value(DEFAULT_ABSENCE_CULTURE_AVENIR.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCulture() throws Exception {
        // Get the culture
        restCultureMockMvc.perform(get("/api/cultures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCulture() throws Exception {
        // Initialize the database
        cultureService.save(culture);

        int databaseSizeBeforeUpdate = cultureRepository.findAll().size();

        // Update the culture
        Culture updatedCulture = cultureRepository.findById(culture.getId()).get();
        // Disconnect from session so that the updates on updatedCulture are not directly saved in db
        em.detach(updatedCulture);
        updatedCulture
            .idRisque(UPDATED_ID_RISQUE)
            .idCulture(UPDATED_ID_CULTURE)
            .numOrdre(UPDATED_NUM_ORDRE)
            .classeNatureCLT(UPDATED_CLASSE_NATURE_CLT)
            .natureCulture(UPDATED_NATURE_CULTURE)
            .codeCaracteristique(UPDATED_CODE_CARACTERISTIQUE)
            .libelleCaracteristique(UPDATED_LIBELLE_CARACTERISTIQUE)
            .prixOfficiel(UPDATED_PRIX_OFFICIEL)
            .uniteMesure(UPDATED_UNITE_MESURE)
            .superficie(UPDATED_SUPERFICIE)
            .superficieExpl(UPDATED_SUPERFICIE_EXPL)
            .uniteSuperficie(UPDATED_UNITE_SUPERFICIE)
            .nbParcelles(UPDATED_NB_PARCELLES)
            .cultureirriguee(UPDATED_CULTUREIRRIGUEE)
            .rendementMoyen(UPDATED_RENDEMENT_MOYEN)
            .prixMoyen(UPDATED_PRIX_MOYEN)
            .prixAutre(UPDATED_PRIX_AUTRE)
            .prixRetenu(UPDATED_PRIX_RETENU)
            .rendementN1(UPDATED_RENDEMENT_N_1)
            .rendementN2(UPDATED_RENDEMENT_N_2)
            .rendementN3(UPDATED_RENDEMENT_N_3)
            .rendementN4(UPDATED_RENDEMENT_N_4)
            .rendementN5(UPDATED_RENDEMENT_N_5)
            .prixUnitaire1(UPDATED_PRIX_UNITAIRE_1)
            .prixUnitaire2(UPDATED_PRIX_UNITAIRE_2)
            .prixUnitaire3(UPDATED_PRIX_UNITAIRE_3)
            .prixUnitaire4(UPDATED_PRIX_UNITAIRE_4)
            .prixUnitaire5(UPDATED_PRIX_UNITAIRE_5)
            .motifAutrePrix(UPDATED_MOTIF_AUTRE_PRIX)
            .majEnAttente(UPDATED_MAJ_EN_ATTENTE)
            .absenceCultureAvenir(UPDATED_ABSENCE_CULTURE_AVENIR);

        restCultureMockMvc.perform(put("/api/cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCulture)))
            .andExpect(status().isOk());

        // Validate the Culture in the database
        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeUpdate);
        Culture testCulture = cultureList.get(cultureList.size() - 1);
        assertThat(testCulture.getIdRisque()).isEqualTo(UPDATED_ID_RISQUE);
        assertThat(testCulture.getIdCulture()).isEqualTo(UPDATED_ID_CULTURE);
        assertThat(testCulture.getNumOrdre()).isEqualTo(UPDATED_NUM_ORDRE);
        assertThat(testCulture.getClasseNatureCLT()).isEqualTo(UPDATED_CLASSE_NATURE_CLT);
        assertThat(testCulture.getNatureCulture()).isEqualTo(UPDATED_NATURE_CULTURE);
        assertThat(testCulture.getCodeCaracteristique()).isEqualTo(UPDATED_CODE_CARACTERISTIQUE);
        assertThat(testCulture.getLibelleCaracteristique()).isEqualTo(UPDATED_LIBELLE_CARACTERISTIQUE);
        assertThat(testCulture.getPrixOfficiel()).isEqualTo(UPDATED_PRIX_OFFICIEL);
        assertThat(testCulture.getUniteMesure()).isEqualTo(UPDATED_UNITE_MESURE);
        assertThat(testCulture.getSuperficie()).isEqualTo(UPDATED_SUPERFICIE);
        assertThat(testCulture.getSuperficieExpl()).isEqualTo(UPDATED_SUPERFICIE_EXPL);
        assertThat(testCulture.getUniteSuperficie()).isEqualTo(UPDATED_UNITE_SUPERFICIE);
        assertThat(testCulture.getNbParcelles()).isEqualTo(UPDATED_NB_PARCELLES);
        assertThat(testCulture.isCultureirriguee()).isEqualTo(UPDATED_CULTUREIRRIGUEE);
        assertThat(testCulture.getRendementMoyen()).isEqualTo(UPDATED_RENDEMENT_MOYEN);
        assertThat(testCulture.getPrixMoyen()).isEqualTo(UPDATED_PRIX_MOYEN);
        assertThat(testCulture.getPrixAutre()).isEqualTo(UPDATED_PRIX_AUTRE);
        assertThat(testCulture.getPrixRetenu()).isEqualTo(UPDATED_PRIX_RETENU);
        assertThat(testCulture.getRendementN1()).isEqualTo(UPDATED_RENDEMENT_N_1);
        assertThat(testCulture.getRendementN2()).isEqualTo(UPDATED_RENDEMENT_N_2);
        assertThat(testCulture.getRendementN3()).isEqualTo(UPDATED_RENDEMENT_N_3);
        assertThat(testCulture.getRendementN4()).isEqualTo(UPDATED_RENDEMENT_N_4);
        assertThat(testCulture.getRendementN5()).isEqualTo(UPDATED_RENDEMENT_N_5);
        assertThat(testCulture.getPrixUnitaire1()).isEqualTo(UPDATED_PRIX_UNITAIRE_1);
        assertThat(testCulture.getPrixUnitaire2()).isEqualTo(UPDATED_PRIX_UNITAIRE_2);
        assertThat(testCulture.getPrixUnitaire3()).isEqualTo(UPDATED_PRIX_UNITAIRE_3);
        assertThat(testCulture.getPrixUnitaire4()).isEqualTo(UPDATED_PRIX_UNITAIRE_4);
        assertThat(testCulture.getPrixUnitaire5()).isEqualTo(UPDATED_PRIX_UNITAIRE_5);
        assertThat(testCulture.getMotifAutrePrix()).isEqualTo(UPDATED_MOTIF_AUTRE_PRIX);
        assertThat(testCulture.isMajEnAttente()).isEqualTo(UPDATED_MAJ_EN_ATTENTE);
        assertThat(testCulture.isAbsenceCultureAvenir()).isEqualTo(UPDATED_ABSENCE_CULTURE_AVENIR);
    }

    @Test
    @Transactional
    public void updateNonExistingCulture() throws Exception {
        int databaseSizeBeforeUpdate = cultureRepository.findAll().size();

        // Create the Culture

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCultureMockMvc.perform(put("/api/cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(culture)))
            .andExpect(status().isBadRequest());

        // Validate the Culture in the database
        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCulture() throws Exception {
        // Initialize the database
        cultureService.save(culture);

        int databaseSizeBeforeDelete = cultureRepository.findAll().size();

        // Delete the culture
        restCultureMockMvc.perform(delete("/api/cultures/{id}", culture.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Culture.class);
        Culture culture1 = new Culture();
        culture1.setId(1L);
        Culture culture2 = new Culture();
        culture2.setId(culture1.getId());
        assertThat(culture1).isEqualTo(culture2);
        culture2.setId(2L);
        assertThat(culture1).isNotEqualTo(culture2);
        culture1.setId(null);
        assertThat(culture1).isNotEqualTo(culture2);
    }
}
