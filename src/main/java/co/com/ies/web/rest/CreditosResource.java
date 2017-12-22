package co.com.ies.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.com.ies.domain.Creditos;
import co.com.ies.service.CreditosService;
import co.com.ies.web.rest.errors.BadRequestAlertException;
import co.com.ies.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Creditos.
 */
@RestController
@RequestMapping("/api")
public class CreditosResource {

    private final Logger log = LoggerFactory.getLogger(CreditosResource.class);

    private static final String ENTITY_NAME = "creditos";

    private final CreditosService creditosService;

    public CreditosResource(CreditosService creditosService) {
        this.creditosService = creditosService;
    }

    /**
     * POST  /creditos : Create a new creditos.
     *
     * @param creditos the creditos to create
     * @return the ResponseEntity with status 201 (Created) and with body the new creditos, or with status 400 (Bad Request) if the creditos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/creditos")
    @Timed
    public ResponseEntity<Creditos> createCreditos(@Valid @RequestBody Creditos creditos) throws URISyntaxException {
        log.debug("REST request to save Creditos : {}", creditos);
        if (creditos.getId() != null) {
            throw new BadRequestAlertException("A new creditos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Creditos result = creditosService.save(creditos);
        return ResponseEntity.created(new URI("/api/creditos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

   /* *//**
     * PUT  /creditos : Updates an existing creditos.
     *
     * @param creditos the creditos to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated creditos,
     * or with status 400 (Bad Request) if the creditos is not valid,
     * or with status 500 (Internal Server Error) if the creditos couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     *//*
    @PutMapping("/creditos")
    @Timed
    public ResponseEntity<Creditos> updateCreditos(@Valid @RequestBody Creditos creditos) throws URISyntaxException {
        log.debug("REST request to update Creditos : {}", creditos);
        if (creditos.getId() == null) {
            return createCreditos(creditos);
        }
        Creditos result = creditosService.save(creditos);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, creditos.getId().toString()))
            .body(result);
    }*/

    /**
     * GET  /creditos : get all the creditos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of creditos in body
     */
    @GetMapping("/creditos")
    @Timed
    public List<Creditos> getAllCreditos() {
        log.debug("REST request to get all Creditos");
        return creditosService.findAll();
        }

    /**
     * GET  /creditos/:id : get the "id" creditos.
     *
     * @param id the id of the creditos to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the creditos, or with status 404 (Not Found)
     */
    @GetMapping("/creditos/{id}")
    @Timed
    public ResponseEntity<Creditos> getCreditos(@PathVariable Long id) {
        log.debug("REST request to get Creditos : {}", id);
        Creditos creditos = creditosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(creditos));
    }

  /*  *//**
     * DELETE  /creditos/:id : delete the "id" creditos.
     *
     * @param id the id of the creditos to delete
     * @return the ResponseEntity with status 200 (OK)
     *//*
    @DeleteMapping("/creditos/{id}")
    @Timed
    public ResponseEntity<Void> deleteCreditos(@PathVariable Long id) {
        log.debug("REST request to delete Creditos : {}", id);
        creditosService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }*/
}
