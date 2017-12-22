package co.com.ies.service;

import co.com.ies.domain.Creditos;
import java.util.List;

/**
 * Service Interface for managing Creditos.
 */
public interface CreditosService {

    /**
     * Save a creditos.
     *
     * @param creditos the entity to save
     * @return the persisted entity
     */
    Creditos save(Creditos creditos);

    /**
     * Get all the creditos.
     *
     * @return the list of entities
     */
    List<Creditos> findAll();

    /**
     * Get the "id" creditos.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Creditos findOne(Long id);

    /**
     * Delete the "id" creditos.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
