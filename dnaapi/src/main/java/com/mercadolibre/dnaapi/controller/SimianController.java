package com.mercadolibre.dnaapi.controller;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.validation.Valid;
import com.mercadolibre.dnaapi.business.DefineSimianDnaBusiness;
import com.mercadolibre.dnaapi.dto.DnaDTO;
import com.mercadolibre.dnaapi.entity.DnaEntity;
import com.mercadolibre.dnaapi.exception.NoEqualLengthException;
import com.mercadolibre.dnaapi.forms.DnaForm;
import com.mercadolibre.dnaapi.forms.DnaResponse;
import com.mercadolibre.dnaapi.forms.StatsResponse;
import com.mercadolibre.dnaapi.repository.IDnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Classe responsavel por orquestrar as regras de negocio da API.
 * 
 * @@author Brunno Silva
 * @since 23/11/2019
 */
@RestController
@RequestMapping(path = "simian")
public class SimianController {

    private static final Logger LOGGER = LogManager.getLogger(SimianController.class);

    @Autowired
    IDnaRepository dnaRepository;

    /**
     * Verifica a composição do DNA e informa se é de um humano ou de um simio e
     * salva no banco de dados em memória. -1002391728
     * 
     * @param input Dados de entrada da API
     * @return um {@code boolean} informando se o DNA é de um <b>SIMIO</b> ou não.
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DnaResponse> verificarDNA(@RequestBody @Valid final DnaForm input,
            UriComponentsBuilder uriBuilder) {

        DnaResponse response = new DnaResponse();
        char[][] dna = convertInputRequestToArray2D(input);
        boolean isSimian = DefineSimianDnaBusiness.isSimian(dna, 4, 2);
        response.setIs_simian(isSimian);

        // Unicidade garantida utilizando o hash code!!!
        List<DnaEntity> dnas = dnaRepository.findByDnaHash(input.hashCode());

        if (dnas.size() == 0) {

            DnaEntity dnaEntity = dnaRepository.save(new DnaEntity(isSimian, input.hashCode(), LocalDateTime.now()));
            // URI uri =
            // uriBuilder.path("/simian/{id}").buildAndExpand(dnaEntity.getId()).toUri();
            // responseHttp = ResponseEntity.created(uri).body(response); enunciado pede 200
            // talvez tenha robo de testes.....
            LOGGER.debug("NOVO DNA SALVO" + dnaEntity);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Obtem o DNA por ID.
     * 
     * @param id
     * @return O DNA conforme ID informado.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DnaDTO> obterDnaPorID(@RequestParam(name = "id") Long id) {
        DnaEntity dnaEntity = dnaRepository.getOne(id);
        DnaDTO dnaDTO = new DnaDTO(dnaEntity);
        return ResponseEntity.status(HttpStatus.OK).body(dnaDTO);
    }

    @GetMapping(path="stats",produces = "application/json")
    public ResponseEntity<StatsResponse> obterEstatistica(){

        int count_simian_dna = 0;
        int count_human_dna =0;
        double ratio = 0;
        boolean isSimian = true;
        //Primeiro os Simios
        List<DnaEntity> dnaEntities = dnaRepository.findAll(Sort.by(Sort.Direction.DESC, "isSimian"));
        Iterator<DnaEntity> iterator = dnaEntities.iterator();

        //Nao precisa varrer todo array soh pegar os simios, performance
        while(iterator.hasNext() && isSimian){

            DnaEntity dnaEntity = iterator.next();

            if (dnaEntity.getIsSimian()){
                count_simian_dna += 1;
            } else {
                isSimian = false; //break
            }

        }

        count_human_dna = dnaEntities.size() - count_simian_dna;
        
        if(count_human_dna != 0){
            ratio = (double) count_simian_dna / count_human_dna;
        }

        return ResponseEntity.status(HttpStatus.OK).body(new StatsResponse(count_simian_dna,count_human_dna,ratio));
    }

    /**
     * Realiza a conversao dos dados enviado para a API.
     * 
     * @param input Dados de entrada da API
     * @return {@code Array} 2D contendo os dados enviados.
     */
    private char[][] convertInputRequestToArray2D(DnaForm input) throws NoEqualLengthException {

        char[][] dna = new char[input.getDna().size()][input.getDna().size()];
        int l = 0;

        for (String s : input.getDna()) {

            char[] linha = s.toCharArray();
            if (linha.length != input.getDna().size()) {
                throw new NoEqualLengthException("TODOS os conjutos de nucleotidos devem possuir o MESMO TAMANHO!!!!!");
            } else {

                for (int c = 0; c < linha.length; c++) {
                    dna[l][c] = linha[c];
                }
            }

            l++;

        }

        return dna;
    }

}