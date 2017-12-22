package co.com.ies.service;

import co.com.ies.service.dto.PlayRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing PlayRequest.
 */
public interface PlayRequestService {

    /**
     * Save a playRequest.
     *
     * @param playRequestDTO the entity to save
     * @return the persisted entity
     */
    PlayRequestDTO save(PlayRequestDTO playRequestDTO);

    /**
     * Get all the playRequests.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlayRequestDTO> findAll(Pageable pageable);

    /**
     * Get the "id" playRequest.
     *
     * @param id the id of the entity
     * @return the entity
     */
    PlayRequestDTO findOne(Long id);

    /**
     * Delete the "id" playRequest.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
