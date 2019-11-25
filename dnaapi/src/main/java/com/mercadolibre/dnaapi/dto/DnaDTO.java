package com.mercadolibre.dnaapi.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.mercadolibre.dnaapi.entity.DnaEntity;

/**
 * POJO que representa o dado obtido do banco.
 * @author Brunno Silva
 */
public class DnaDTO {

    private Long id;
    private Boolean isSimian;
    private Integer dnaHash;
    private LocalDateTime dataHoraCriacao;

    public DnaDTO (DnaEntity dnaEntity){
        this.id = dnaEntity.getId();
        this.dataHoraCriacao = dnaEntity.getDataHoraCriacao();
        this.dnaHash = dnaEntity.getDnaHash();
        this.isSimian = dnaEntity.getIsSimian();
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

    public List<DnaDTO> converter(List<DnaEntity> dnas){
        return dnas.stream().map(DnaDTO::new).collect(Collectors.toList());
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
        DnaDTO other = (DnaDTO) obj;
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

}