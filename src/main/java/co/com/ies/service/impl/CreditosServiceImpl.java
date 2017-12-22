package co.com.ies.service.impl;

import co.com.ies.service.CreditosService;
import co.com.ies.domain.Creditos;
import co.com.ies.repository.CreditosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Creditos.
 */
@Service
@Transactional
public class CreditosServiceImpl implements CreditosService{

    private final Logger log = LoggerFactory.getLogger(CreditosServiceImpl.class);

    private final CreditosRepository creditosRepository;

    public CreditosServiceImpl(CreditosRepository creditosRepository) {
        this.creditosRepository = creditosRepository;
    }

    /**
     * Save a creditos.
     *
     * @param creditos the entity to save
     * @return the persisted entity
     */
    @Override
    public Creditos save(Creditos creditos) {
        log.debug("Request to save Creditos : {}", creditos);
        return creditosRepository.save(creditos);
    }

    /**
     * Get all the creditos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Creditos> findAll() {
        log.debug("Request to get all Creditos");
        return creditosRepository.findAll();
    }

    /**
     * Get one creditos by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Creditos findOne(Long id) {
        log.debug("Request to get Creditos : {}", id);
        return creditosRepository.findOne(id);
    }

    /**
     * Delete the creditos by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Creditos : {}", id);
        creditosRepository.delete(id);
    }
}
