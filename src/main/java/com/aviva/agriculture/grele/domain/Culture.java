package com.aviva.agriculture.grele.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * The Culture entity.
 */
@ApiModel(description = "The Culture entity.")
@Entity
@Table(name = "culture")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Culture implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_risque")
    private Long idRisque;

    @Column(name = "id_culture")
    private Long idCulture;

    @Column(name = "num_ordre")
    private Integer numOrdre;

    @Column(name = "classe_nature_clt")
    private Integer classeNatureCLT;

    @Column(name = "nature_culture")
    private String natureCulture;

    @Column(name = "code_caracteristique")
    private String codeCaracteristique;

    @Column(name = "libelle_caracteristique")
    private String libelleCaracteristique;

    @Column(name = "prix_officiel")
    private Double prixOfficiel;

    @Column(name = "unite_mesure")
    private String uniteMesure;

    @Column(name = "superficie")
    private Long superficie;

    @Column(name = "superficie_expl")
    private Long superficieExpl;

    @Column(name = "unite_superficie")
    private String uniteSuperficie;

    @Column(name = "nb_parcelles")
    private Integer nbParcelles;

    @Column(name = "cultureirriguee")
    private Boolean cultureirriguee;

    @Column(name = "rendement_moyen")
    private Double rendementMoyen;

    @Column(name = "prix_moyen")
    private Double prixMoyen;

    @Column(name = "prix_autre")
    private Double prixAutre;

    @Column(name = "prix_retenu")
    private Double prixRetenu;

    @Column(name = "rendement_n_1", precision = 10, scale = 2)
    private BigDecimal rendementN1;

    @Column(name = "rendement_n_2", precision = 10, scale = 2)
    private BigDecimal rendementN2;

    @Column(name = "rendement_n_3", precision = 10, scale = 2)
    private BigDecimal rendementN3;

    @Column(name = "rendement_n_4", precision = 10, scale = 2)
    private BigDecimal rendementN4;

    @Column(name = "rendement_n_5", precision = 10, scale = 2)
    private BigDecimal rendementN5;

    @Column(name = "prix_unitaire_1")
    private Double prixUnitaire1;

    @Column(name = "prix_unitaire_2")
    private Double prixUnitaire2;

    @Column(name = "prix_unitaire_3")
    private Double prixUnitaire3;

    @Column(name = "prix_unitaire_4")
    private Double prixUnitaire4;

    @Column(name = "prix_unitaire_5")
    private Double prixUnitaire5;

    @Column(name = "motif_autre_prix")
    private String motifAutrePrix;

    @Column(name = "maj_en_attente")
    private Boolean majEnAttente;

    @Column(name = "absence_culture_avenir")
    private Boolean absenceCultureAvenir;

    @OneToMany(mappedBy = "culture")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Parcelle> parcelles = new HashSet<>();
    @OneToMany(mappedBy = "culture")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Garantie> garanties = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdRisque() {
        return idRisque;
    }

    public Culture idRisque(Long idRisque) {
        this.idRisque = idRisque;
        return this;
    }

    public void setIdRisque(Long idRisque) {
        this.idRisque = idRisque;
    }

    public Long getIdCulture() {
        return idCulture;
    }

    public Culture idCulture(Long idCulture) {
        this.idCulture = idCulture;
        return this;
    }

    public void setIdCulture(Long idCulture) {
        this.idCulture = idCulture;
    }

    public Integer getNumOrdre() {
        return numOrdre;
    }

    public Culture numOrdre(Integer numOrdre) {
        this.numOrdre = numOrdre;
        return this;
    }

    public void setNumOrdre(Integer numOrdre) {
        this.numOrdre = numOrdre;
    }

    public Integer getClasseNatureCLT() {
        return classeNatureCLT;
    }

    public Culture classeNatureCLT(Integer classeNatureCLT) {
        this.classeNatureCLT = classeNatureCLT;
        return this;
    }

    public void setClasseNatureCLT(Integer classeNatureCLT) {
        this.classeNatureCLT = classeNatureCLT;
    }

    public String getNatureCulture() {
        return natureCulture;
    }

    public Culture natureCulture(String natureCulture) {
        this.natureCulture = natureCulture;
        return this;
    }

    public void setNatureCulture(String natureCulture) {
        this.natureCulture = natureCulture;
    }

    public String getCodeCaracteristique() {
        return codeCaracteristique;
    }

    public Culture codeCaracteristique(String codeCaracteristique) {
        this.codeCaracteristique = codeCaracteristique;
        return this;
    }

    public void setCodeCaracteristique(String codeCaracteristique) {
        this.codeCaracteristique = codeCaracteristique;
    }

    public String getLibelleCaracteristique() {
        return libelleCaracteristique;
    }

    public Culture libelleCaracteristique(String libelleCaracteristique) {
        this.libelleCaracteristique = libelleCaracteristique;
        return this;
    }

    public void setLibelleCaracteristique(String libelleCaracteristique) {
        this.libelleCaracteristique = libelleCaracteristique;
    }

    public Double getPrixOfficiel() {
        return prixOfficiel;
    }

    public Culture prixOfficiel(Double prixOfficiel) {
        this.prixOfficiel = prixOfficiel;
        return this;
    }

    public void setPrixOfficiel(Double prixOfficiel) {
        this.prixOfficiel = prixOfficiel;
    }

    public String getUniteMesure() {
        return uniteMesure;
    }

    public Culture uniteMesure(String uniteMesure) {
        this.uniteMesure = uniteMesure;
        return this;
    }

    public void setUniteMesure(String uniteMesure) {
        this.uniteMesure = uniteMesure;
    }

    public Long getSuperficie() {
        return superficie;
    }

    public Culture superficie(Long superficie) {
        this.superficie = superficie;
        return this;
    }

    public void setSuperficie(Long superficie) {
        this.superficie = superficie;
    }

    public Long getSuperficieExpl() {
        return superficieExpl;
    }

    public Culture superficieExpl(Long superficieExpl) {
        this.superficieExpl = superficieExpl;
        return this;
    }

    public void setSuperficieExpl(Long superficieExpl) {
        this.superficieExpl = superficieExpl;
    }

    public String getUniteSuperficie() {
        return uniteSuperficie;
    }

    public Culture uniteSuperficie(String uniteSuperficie) {
        this.uniteSuperficie = uniteSuperficie;
        return this;
    }

    public void setUniteSuperficie(String uniteSuperficie) {
        this.uniteSuperficie = uniteSuperficie;
    }

    public Integer getNbParcelles() {
        return nbParcelles;
    }

    public Culture nbParcelles(Integer nbParcelles) {
        this.nbParcelles = nbParcelles;
        return this;
    }

    public void setNbParcelles(Integer nbParcelles) {
        this.nbParcelles = nbParcelles;
    }

    public Boolean isCultureirriguee() {
        return cultureirriguee;
    }

    public Culture cultureirriguee(Boolean cultureirriguee) {
        this.cultureirriguee = cultureirriguee;
        return this;
    }

    public void setCultureirriguee(Boolean cultureirriguee) {
        this.cultureirriguee = cultureirriguee;
    }

    public Double getRendementMoyen() {
        return rendementMoyen;
    }

    public Culture rendementMoyen(Double rendementMoyen) {
        this.rendementMoyen = rendementMoyen;
        return this;
    }

    public void setRendementMoyen(Double rendementMoyen) {
        this.rendementMoyen = rendementMoyen;
    }

    public Double getPrixMoyen() {
        return prixMoyen;
    }

    public Culture prixMoyen(Double prixMoyen) {
        this.prixMoyen = prixMoyen;
        return this;
    }

    public void setPrixMoyen(Double prixMoyen) {
        this.prixMoyen = prixMoyen;
    }

    public Double getPrixAutre() {
        return prixAutre;
    }

    public Culture prixAutre(Double prixAutre) {
        this.prixAutre = prixAutre;
        return this;
    }

    public void setPrixAutre(Double prixAutre) {
        this.prixAutre = prixAutre;
    }

    public Double getPrixRetenu() {
        return prixRetenu;
    }

    public Culture prixRetenu(Double prixRetenu) {
        this.prixRetenu = prixRetenu;
        return this;
    }

    public void setPrixRetenu(Double prixRetenu) {
        this.prixRetenu = prixRetenu;
    }

    public BigDecimal getRendementN1() {
        return rendementN1;
    }

    public Culture rendementN1(BigDecimal rendementN1) {
        this.rendementN1 = rendementN1;
        return this;
    }

    public void setRendementN1(BigDecimal rendementN1) {
        this.rendementN1 = rendementN1;
    }

    public BigDecimal getRendementN2() {
        return rendementN2;
    }

    public Culture rendementN2(BigDecimal rendementN2) {
        this.rendementN2 = rendementN2;
        return this;
    }

    public void setRendementN2(BigDecimal rendementN2) {
        this.rendementN2 = rendementN2;
    }

    public BigDecimal getRendementN3() {
        return rendementN3;
    }

    public Culture rendementN3(BigDecimal rendementN3) {
        this.rendementN3 = rendementN3;
        return this;
    }

    public void setRendementN3(BigDecimal rendementN3) {
        this.rendementN3 = rendementN3;
    }

    public BigDecimal getRendementN4() {
        return rendementN4;
    }

    public Culture rendementN4(BigDecimal rendementN4) {
        this.rendementN4 = rendementN4;
        return this;
    }

    public void setRendementN4(BigDecimal rendementN4) {
        this.rendementN4 = rendementN4;
    }

    public BigDecimal getRendementN5() {
        return rendementN5;
    }

    public Culture rendementN5(BigDecimal rendementN5) {
        this.rendementN5 = rendementN5;
        return this;
    }

    public void setRendementN5(BigDecimal rendementN5) {
        this.rendementN5 = rendementN5;
    }

    public Double getPrixUnitaire1() {
        return prixUnitaire1;
    }

    public Culture prixUnitaire1(Double prixUnitaire1) {
        this.prixUnitaire1 = prixUnitaire1;
        return this;
    }

    public void setPrixUnitaire1(Double prixUnitaire1) {
        this.prixUnitaire1 = prixUnitaire1;
    }

    public Double getPrixUnitaire2() {
        return prixUnitaire2;
    }

    public Culture prixUnitaire2(Double prixUnitaire2) {
        this.prixUnitaire2 = prixUnitaire2;
        return this;
    }

    public void setPrixUnitaire2(Double prixUnitaire2) {
        this.prixUnitaire2 = prixUnitaire2;
    }

    public Double getPrixUnitaire3() {
        return prixUnitaire3;
    }

    public Culture prixUnitaire3(Double prixUnitaire3) {
        this.prixUnitaire3 = prixUnitaire3;
        return this;
    }

    public void setPrixUnitaire3(Double prixUnitaire3) {
        this.prixUnitaire3 = prixUnitaire3;
    }

    public Double getPrixUnitaire4() {
        return prixUnitaire4;
    }

    public Culture prixUnitaire4(Double prixUnitaire4) {
        this.prixUnitaire4 = prixUnitaire4;
        return this;
    }

    public void setPrixUnitaire4(Double prixUnitaire4) {
        this.prixUnitaire4 = prixUnitaire4;
    }

    public Double getPrixUnitaire5() {
        return prixUnitaire5;
    }

    public Culture prixUnitaire5(Double prixUnitaire5) {
        this.prixUnitaire5 = prixUnitaire5;
        return this;
    }

    public void setPrixUnitaire5(Double prixUnitaire5) {
        this.prixUnitaire5 = prixUnitaire5;
    }

    public String getMotifAutrePrix() {
        return motifAutrePrix;
    }

    public Culture motifAutrePrix(String motifAutrePrix) {
        this.motifAutrePrix = motifAutrePrix;
        return this;
    }

    public void setMotifAutrePrix(String motifAutrePrix) {
        this.motifAutrePrix = motifAutrePrix;
    }

    public Boolean isMajEnAttente() {
        return majEnAttente;
    }

    public Culture majEnAttente(Boolean majEnAttente) {
        this.majEnAttente = majEnAttente;
        return this;
    }

    public void setMajEnAttente(Boolean majEnAttente) {
        this.majEnAttente = majEnAttente;
    }

    public Boolean isAbsenceCultureAvenir() {
        return absenceCultureAvenir;
    }

    public Culture absenceCultureAvenir(Boolean absenceCultureAvenir) {
        this.absenceCultureAvenir = absenceCultureAvenir;
        return this;
    }

    public void setAbsenceCultureAvenir(Boolean absenceCultureAvenir) {
        this.absenceCultureAvenir = absenceCultureAvenir;
    }

    public Set<Parcelle> getParcelles() {
        return parcelles;
    }

    public Culture parcelles(Set<Parcelle> parcelles) {
        this.parcelles = parcelles;
        return this;
    }

    public Culture addParcelle(Parcelle parcelle) {
        this.parcelles.add(parcelle);
        parcelle.setCulture(this);
        return this;
    }

    public Culture removeParcelle(Parcelle parcelle) {
        this.parcelles.remove(parcelle);
        parcelle.setCulture(null);
        return this;
    }

    public void setParcelles(Set<Parcelle> parcelles) {
        this.parcelles = parcelles;
    }

    public Set<Garantie> getGaranties() {
        return garanties;
    }

    public Culture garanties(Set<Garantie> garanties) {
        this.garanties = garanties;
        return this;
    }

    public Culture addGarantie(Garantie garantie) {
        this.garanties.add(garantie);
        garantie.setCulture(this);
        return this;
    }

    public Culture removeGarantie(Garantie garantie) {
        this.garanties.remove(garantie);
        garantie.setCulture(null);
        return this;
    }

    public void setGaranties(Set<Garantie> garanties) {
        this.garanties = garanties;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Culture culture = (Culture) o;
        if (culture.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), culture.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Culture{" +
            "id=" + getId() +
            ", idRisque=" + getIdRisque() +
            ", idCulture=" + getIdCulture() +
            ", numOrdre=" + getNumOrdre() +
            ", classeNatureCLT=" + getClasseNatureCLT() +
            ", natureCulture='" + getNatureCulture() + "'" +
            ", codeCaracteristique='" + getCodeCaracteristique() + "'" +
            ", libelleCaracteristique='" + getLibelleCaracteristique() + "'" +
            ", prixOfficiel=" + getPrixOfficiel() +
            ", uniteMesure='" + getUniteMesure() + "'" +
            ", superficie=" + getSuperficie() +
            ", superficieExpl=" + getSuperficieExpl() +
            ", uniteSuperficie='" + getUniteSuperficie() + "'" +
            ", nbParcelles=" + getNbParcelles() +
            ", cultureirriguee='" + isCultureirriguee() + "'" +
            ", rendementMoyen=" + getRendementMoyen() +
            ", prixMoyen=" + getPrixMoyen() +
            ", prixAutre=" + getPrixAutre() +
            ", prixRetenu=" + getPrixRetenu() +
            ", rendementN1=" + getRendementN1() +
            ", rendementN2=" + getRendementN2() +
            ", rendementN3=" + getRendementN3() +
            ", rendementN4=" + getRendementN4() +
            ", rendementN5=" + getRendementN5() +
            ", prixUnitaire1=" + getPrixUnitaire1() +
            ", prixUnitaire2=" + getPrixUnitaire2() +
            ", prixUnitaire3=" + getPrixUnitaire3() +
            ", prixUnitaire4=" + getPrixUnitaire4() +
            ", prixUnitaire5=" + getPrixUnitaire5() +
            ", motifAutrePrix='" + getMotifAutrePrix() + "'" +
            ", majEnAttente='" + isMajEnAttente() + "'" +
            ", absenceCultureAvenir='" + isAbsenceCultureAvenir() + "'" +
            "}";
    }
}
