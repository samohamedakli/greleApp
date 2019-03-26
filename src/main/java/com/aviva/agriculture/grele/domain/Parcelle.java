package com.aviva.agriculture.grele.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * The Parcelle entity.
 */
@ApiModel(description = "The Parcelle entity.")
@Entity
@Table(name = "parcelle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Parcelle implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_risque")
    private Long idRisque;

    @Column(name = "id_parcelle")
    private Long idParcelle;

    @Column(name = "num_ordre")
    private Integer numOrdre;

    @Column(name = "code_postal")
    private Long codePostal;

    @Column(name = "libelle_commune")
    private String libelleCommune;

    @Column(name = "lieudit")
    private String lieudit;

    @Column(name = "superficie")
    private Long superficie;

    @Column(name = "rendement", precision = 10, scale = 2)
    private BigDecimal rendement;

    @Column(name = "prix_unitaire")
    private Double prixUnitaire;

    @Column(name = "taux_cotisation_grele", precision = 10, scale = 2)
    private BigDecimal tauxCotisationGrele;

    @Column(name = "taux_cotisation_arc", precision = 10, scale = 2)
    private BigDecimal tauxCotisationArc;

    @ManyToOne
    @JsonIgnoreProperties("parcelles")
    private Culture culture;

    @OneToOne
    @JoinColumn(unique = true)
    private Localisation localisation;

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

    public Parcelle idRisque(Long idRisque) {
        this.idRisque = idRisque;
        return this;
    }

    public void setIdRisque(Long idRisque) {
        this.idRisque = idRisque;
    }

    public Long getIdParcelle() {
        return idParcelle;
    }

    public Parcelle idParcelle(Long idParcelle) {
        this.idParcelle = idParcelle;
        return this;
    }

    public void setIdParcelle(Long idParcelle) {
        this.idParcelle = idParcelle;
    }

    public Integer getNumOrdre() {
        return numOrdre;
    }

    public Parcelle numOrdre(Integer numOrdre) {
        this.numOrdre = numOrdre;
        return this;
    }

    public void setNumOrdre(Integer numOrdre) {
        this.numOrdre = numOrdre;
    }

    public Long getCodePostal() {
        return codePostal;
    }

    public Parcelle codePostal(Long codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public void setCodePostal(Long codePostal) {
        this.codePostal = codePostal;
    }

    public String getLibelleCommune() {
        return libelleCommune;
    }

    public Parcelle libelleCommune(String libelleCommune) {
        this.libelleCommune = libelleCommune;
        return this;
    }

    public void setLibelleCommune(String libelleCommune) {
        this.libelleCommune = libelleCommune;
    }

    public String getLieudit() {
        return lieudit;
    }

    public Parcelle lieudit(String lieudit) {
        this.lieudit = lieudit;
        return this;
    }

    public void setLieudit(String lieudit) {
        this.lieudit = lieudit;
    }

    public Long getSuperficie() {
        return superficie;
    }

    public Parcelle superficie(Long superficie) {
        this.superficie = superficie;
        return this;
    }

    public void setSuperficie(Long superficie) {
        this.superficie = superficie;
    }

    public BigDecimal getRendement() {
        return rendement;
    }

    public Parcelle rendement(BigDecimal rendement) {
        this.rendement = rendement;
        return this;
    }

    public void setRendement(BigDecimal rendement) {
        this.rendement = rendement;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public Parcelle prixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
        return this;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public BigDecimal getTauxCotisationGrele() {
        return tauxCotisationGrele;
    }

    public Parcelle tauxCotisationGrele(BigDecimal tauxCotisationGrele) {
        this.tauxCotisationGrele = tauxCotisationGrele;
        return this;
    }

    public void setTauxCotisationGrele(BigDecimal tauxCotisationGrele) {
        this.tauxCotisationGrele = tauxCotisationGrele;
    }

    public BigDecimal getTauxCotisationArc() {
        return tauxCotisationArc;
    }

    public Parcelle tauxCotisationArc(BigDecimal tauxCotisationArc) {
        this.tauxCotisationArc = tauxCotisationArc;
        return this;
    }

    public void setTauxCotisationArc(BigDecimal tauxCotisationArc) {
        this.tauxCotisationArc = tauxCotisationArc;
    }

    public Culture getCulture() {
        return culture;
    }

    public Parcelle culture(Culture culture) {
        this.culture = culture;
        return this;
    }

    public void setCulture(Culture culture) {
        this.culture = culture;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public Parcelle localisation(Localisation localisation) {
        this.localisation = localisation;
        return this;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
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
        Parcelle parcelle = (Parcelle) o;
        if (parcelle.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), parcelle.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Parcelle{" +
            "id=" + getId() +
            ", idRisque=" + getIdRisque() +
            ", idParcelle=" + getIdParcelle() +
            ", numOrdre=" + getNumOrdre() +
            ", codePostal=" + getCodePostal() +
            ", libelleCommune='" + getLibelleCommune() + "'" +
            ", lieudit='" + getLieudit() + "'" +
            ", superficie=" + getSuperficie() +
            ", rendement=" + getRendement() +
            ", prixUnitaire=" + getPrixUnitaire() +
            ", tauxCotisationGrele=" + getTauxCotisationGrele() +
            ", tauxCotisationArc=" + getTauxCotisationArc() +
            "}";
    }
}
