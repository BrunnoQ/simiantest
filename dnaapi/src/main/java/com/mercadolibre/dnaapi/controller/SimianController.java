package com.mercadolibre.dnaapi.controller;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import javax.validation.Valid;
import com.mercadolibre.dnaapi.business.DefineSimianDnaBusiness;
import com.mercadolibre.dnaapi.dto.DnaDTO;
import com.mercadolibre.dnaapi.entity.DnaEntity;
import com.mercadolibre.dnaapi.exception.NoEqualLengthException;
import com.mercadolibre.dnaapi.forms.DnaForm;
import com.mercadolibre.dnaapi.forms.DnaResponse;
import com.mercadolibre.dnaapi.forms.StatsResponse;
import com.mercadolibre.dnaapi.repository.IDnaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class SimianController {

    private static final Logger LOGGER = LogManager.getLogger(SimianController.class);

    private final IDnaRepository dnaRepository;

    @Autowired
    public SimianController(IDnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    @PostMapping(path = "simian", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DnaResponse> verificarDNA(@RequestBody @Valid DnaForm input,
                                                    UriComponentsBuilder uriBuilder) {

        char[][] dna = convertInputRequestToArray2D(input);
        boolean isSimian = DefineSimianDnaBusiness.isSimian(dna, 4, 2);

        List<DnaEntity> dnas = dnaRepository.findByDnaHash(input.hashCode());
        if (dnas.isEmpty()) {
            DnaEntity dnaEntity = dnaRepository.save(new DnaEntity(isSimian, input.hashCode(), LocalDateTime.now()));
            LOGGER.debug("NOVO DNA SALVO: " + dnaEntity);
        }

        DnaResponse response = new DnaResponse();
        response.setIs_simian(isSimian);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "simian", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DnaDTO> obterDnaPorID(@RequestParam(name = "id") Long id) {
        DnaEntity dnaEntity = dnaRepository.getOne(id);
        DnaDTO dnaDTO = new DnaDTO(dnaEntity);
        return ResponseEntity.ok(dnaDTO);
    }

    @GetMapping(path = "simian/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatsResponse> obterEstatistica() {
        List<DnaEntity> dnaEntities = dnaRepository.findAll(Sort.by(Sort.Direction.DESC, "isSimian"));
        int count_simian_dna = 0;
        int count_human_dna = dnaEntities.size();

        Iterator<DnaEntity> iterator = dnaEntities.iterator();
        boolean isSimian = true;
        while (iterator.hasNext() && isSimian) {
            DnaEntity dnaEntity = iterator.next();
            if (dnaEntity.getIsSimian()) {
                count_simian_dna += 1;
            } else {
                isSimian = false;
            }
        }

        double ratio = count_human_dna != 0 ? (double) count_simian_dna / count_human_dna : 0;
        StatsResponse statsResponse = new StatsResponse(count_simian_dna, count_human_dna, ratio);
        return ResponseEntity.ok(statsResponse);
    }

    private char[][] convertInputRequestToArray2D(DnaForm input) throws NoEqualLengthException {
        int size = input.getDna().size();
        char[][] dna = new char[size][size];

        Iterator<String> iterator = input.getDna().iterator();
        for (int l = 0; l < size; l++) {
            String sequence = iterator.next();
            if (sequence.length() != size) {
                throw new NoEqualLengthException("TODOS os conjutos de nucleotidos devem possuir o MESMO TAMANHO!");
            }
            dna[l] = sequence.toCharArray();
        }

        return dna;
    }
}
