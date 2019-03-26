package com.aviva.agriculture.grele.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Garantie.
 */
@Entity
@Table(name = "garantie")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Garantie implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_garantie")
    private Integer codeGarantie;

    @Column(name = "libelle_garantie")
    private String libelleGarantie;

    @Column(name = "type_franchise")
    private String typeFranchise;

    @Column(name = "taux_franchise")
    private Integer tauxFranchise;

    @ManyToOne
    @JsonIgnoreProperties("garanties")
    private Culture culture;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodeGarantie() {
        return codeGarantie;
    }

    public Garantie codeGarantie(Integer codeGarantie) {
        this.codeGarantie = codeGarantie;
        return this;
    }

    public void setCodeGarantie(Integer codeGarantie) {
        this.codeGarantie = codeGarantie;
    }

    public String getLibelleGarantie() {
        return libelleGarantie;
    }

    public Garantie libelleGarantie(String libelleGarantie) {
        this.libelleGarantie = libelleGarantie;
        return this;
    }

    public void setLibelleGarantie(String libelleGarantie) {
        this.libelleGarantie = libelleGarantie;
    }

    public String getTypeFranchise() {
        return typeFranchise;
    }

    public Garantie typeFranchise(String typeFranchise) {
        this.typeFranchise = typeFranchise;
        return this;
    }

    public void setTypeFranchise(String typeFranchise) {
        this.typeFranchise = typeFranchise;
    }

    public Integer getTauxFranchise() {
        return tauxFranchise;
    }

    public Garantie tauxFranchise(Integer tauxFranchise) {
        this.tauxFranchise = tauxFranchise;
        return this;
    }

    public void setTauxFranchise(Integer tauxFranchise) {
        this.tauxFranchise = tauxFranchise;
    }

    public Culture getCulture() {
        return culture;
    }

    public Garantie culture(Culture culture) {
        this.culture = culture;
        return this;
    }

    public void setCulture(Culture culture) {
        this.culture = culture;
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
        Garantie garantie = (Garantie) o;
        if (garantie.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), garantie.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Garantie{" +
            "id=" + getId() +
            ", codeGarantie=" + getCodeGarantie() +
            ", libelleGarantie='" + getLibelleGarantie() + "'" +
            ", typeFranchise='" + getTypeFranchise() + "'" +
            ", tauxFranchise=" + getTauxFranchise() +
            "}";
    }
}
