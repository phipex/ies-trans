package co.com.ies.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.com.ies.service.PlayRequestService;
import co.com.ies.web.rest.errors.BadRequestAlertException;
import co.com.ies.web.rest.util.HeaderUtil;
import co.com.ies.web.rest.util.PaginationUtil;
import co.com.ies.service.dto.PlayRequestDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PlayRequest.
 */
@RestController
@RequestMapping("/api")
public class PlayRequestResource {

    private final Logger log = LoggerFactory.getLogger(PlayRequestResource.class);

    private static final String ENTITY_NAME = "playRequest";

    private final PlayRequestService playRequestService;

    public PlayRequestResource(PlayRequestService playRequestService) {
        this.playRequestService = playRequestService;
    }

    /**
     * POST  /play-requests : Create a new playRequest.
     *
     * @param playRequestDTO the playRequestDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new playRequestDTO, or with status 400 (Bad Request) if the playRequest has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/play-requests")
    @Timed
    public ResponseEntity<PlayRequestDTO> createPlayRequest(@Valid @RequestBody PlayRequestDTO playRequestDTO) throws URISyntaxException {
        log.debug("REST request to save PlayRequest : {}", playRequestDTO);
        if (playRequestDTO.getId() != null) {
            throw new BadRequestAlertException("A new playRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlayRequestDTO result = playRequestService.save(playRequestDTO);
        return ResponseEntity.created(new URI("/api/play-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /playpay : Informa que se realizar un pago de dinero.
     *
     * 
     * @return the ResponseEntity with status 201 (Created) and with body the new playRequestDTO, or with status 400 (Bad Request) if the playRequest has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/play-requests/playpay")
    @Timed
    public ResponseEntity<PlayRequestDTO> createPayRequest() throws URISyntaxException {
        log.debug("REST request to save pay PlayRequest : {}");
        
        PlayRequestDTO result = playRequestService.pay();
        return ResponseEntity.created(new URI("/api/play-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /playpay : Informa que se realizar un pago de dinero.
     *
     * 
     * @return the ResponseEntity with status 201 (Created) and with body the new playRequestDTO, or with status 400 (Bad Request) if the playRequest has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/play-requests/addcredit/{valor}")
    @Timed
    public ResponseEntity<PlayRequestDTO> createAddCreditRequest(@PathVariable Double valor) throws URISyntaxException {
        log.debug("REST request to save pay PlayRequest : {}");
        BigDecimal bdValor = new BigDecimal(valor);
        PlayRequestDTO result = playRequestService.addCredit(bdValor);
        return ResponseEntity.created(new URI("/api/play-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    
    
/*
    *//**
     * PUT  /play-requests : Updates an existing playRequest.
     *
     * @param playRequestDTO the playRequestDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated playRequestDTO,
     * or with status 400 (Bad Request) if the playRequestDTO is not valid,
     * or with status 500 (Internal Server Error) if the playRequestDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     *//*
    @PutMapping("/play-requests")
    @Timed
    public ResponseEntity<PlayRequestDTO> updatePlayRequest(@Valid @RequestBody PlayRequestDTO playRequestDTO) throws URISyntaxException {
        log.debug("REST request to update PlayRequest : {}", playRequestDTO);
        if (playRequestDTO.getId() == null) {
            return createPlayRequest(playRequestDTO);
        }
        PlayRequestDTO result = playRequestService.save(playRequestDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, playRequestDTO.getId().toString()))
            .body(result);
    }*/

    /**
     * GET  /play-requests : get all the playRequests.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of playRequests in body
     */
    @GetMapping("/play-requests")
    @Timed
    public ResponseEntity<List<PlayRequestDTO>> getAllPlayRequests(Pageable pageable) {
        log.debug("REST request to get a page of PlayRequests");
        Page<PlayRequestDTO> page = playRequestService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/play-requests");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /play-requests/:id : get the "id" playRequest.
     *
     * @param id the id of the playRequestDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the playRequestDTO, or with status 404 (Not Found)
     */
    @GetMapping("/play-requests/{id}")
    @Timed
    public ResponseEntity<PlayRequestDTO> getPlayRequest(@PathVariable Long id) {
        log.debug("REST request to get PlayRequest : {}", id);
        PlayRequestDTO playRequestDTO = playRequestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(playRequestDTO));
    }

    /**
     * DELETE  /play-requests/:id : delete the "id" playRequest.
     *
     * @param id the id of the playRequestDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     *//*
    @DeleteMapping("/play-requests/{id}")
    @Timed
    public ResponseEntity<Void> deletePlayRequest(@PathVariable Long id) {
        log.debug("REST request to delete PlayRequest : {}", id);
        playRequestService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }*/
}
