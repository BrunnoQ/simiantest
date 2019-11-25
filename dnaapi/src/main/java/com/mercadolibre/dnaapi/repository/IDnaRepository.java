package com.mercadolibre.dnaapi.repository;

import java.util.List;
import com.mercadolibre.dnaapi.entity.DnaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Brunno Silva
 */
public interface IDnaRepository extends JpaRepository<DnaEntity,Long>{

    List<DnaEntity> findByDnaHash(Integer dnaHash);

}