package com.mercadolibre.dnaapi.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe que representa um DNA armazenado no banco de dados.
 */
@Entity
public class DnaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isSimian;
    private Integer dnaHash;
    private LocalDateTime dataHoraCriacao;

    /**
     * Construtor padrao para o framework spring poder trabalhar....
     */
    public DnaEntity() {

    }

    public DnaEntity(Boolean isSimian, Integer dnaHash, LocalDateTime dataHoraCriacao) {
        this.isSimian = isSimian;
        this.dnaHash = dnaHash;
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsSimian() {
        return isSimian;
    }

    public void setIsSimian(Boolean isSimian) {
        this.isSimian = isSimian;
    }

    public Integer getDnaHash() {
        return dnaHash;
    }

    public void setDnaHash(Integer dnaHash) {
        this.dnaHash = dnaHash;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dataHoraCriacao == null) ? 0 : dataHoraCriacao.hashCode());
        result = prime * result + ((dnaHash == null) ? 0 : dnaHash.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isSimian == null) ? 0 : isSimian.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DnaEntity other = (DnaEntity) obj;
        if (dataHoraCriacao == null) {
            if (other.dataHoraCriacao != null)
                return false;
        } else if (!dataHoraCriacao.equals(other.dataHoraCriacao))
            return false;
        if (dnaHash == null) {
            if (other.dnaHash != null)
                return false;
        } else if (!dnaHash.equals(other.dnaHash))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isSimian == null) {
            if (other.isSimian != null)
                return false;
        } else if (!isSimian.equals(other.isSimian))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DnaEntity [dataHoraCriacao=" + dataHoraCriacao + ", dnaHash=" + dnaHash + ", id=" + id + ", isSimian="
                + isSimian + "]";
    }

}