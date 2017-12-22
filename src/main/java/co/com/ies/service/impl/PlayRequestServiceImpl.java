package co.com.ies.service.impl;

import co.com.ies.service.PlayRequestService;
import co.com.ies.domain.PlayRequest;
import co.com.ies.domain.enumeration.PlayType;
import co.com.ies.repository.PlayRequestRepository;
import co.com.ies.service.dto.PlayRequestDTO;
import co.com.ies.service.mapper.PlayRequestMapper;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing PlayRequest.
 */
@Service
@Transactional
public class PlayRequestServiceImpl implements PlayRequestService{

    private final Logger log = LoggerFactory.getLogger(PlayRequestServiceImpl.class);

    private final PlayRequestRepository playRequestRepository;

    private final PlayRequestMapper playRequestMapper;

    public PlayRequestServiceImpl(PlayRequestRepository playRequestRepository, PlayRequestMapper playRequestMapper) {
        this.playRequestRepository = playRequestRepository;
        this.playRequestMapper = playRequestMapper;
    }

    @Override
    public PlayRequestDTO addCredit(BigDecimal valor) {
		
    	//TODO traer los datos de las variables faltantes
    	
    	PlayRequest playRequest = new PlayRequest();
    	
    	playRequest.setId(0L);
    	playRequest.setPlaytype(PlayType.ADDCREDIT);
    	playRequest.setAmount(valor);
    	
    	playRequest = playRequestRepository.save(playRequest);
    	
    	
		return playRequestMapper.toDto(playRequest);
    	
    }
    
    
    @Override
    public PlayRequestDTO pay() {
		
    	PlayRequest playRequest = new PlayRequest();
    	
    	playRequest.setId(0L);
    	playRequest.setPlaytype(PlayType.PAY);
    	
    	//TODO traer los datos del ultimo playrequest
    	//TODO traer el valor a reclamar
    	//TODO crear el movimiento
    	//TODO guardar el movimiento
    	
    	return playRequestMapper.toDto(playRequest);
    	
    }
    
    /**
     * Save a playRequest.
     *
     * @param playRequestDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PlayRequestDTO save(PlayRequestDTO playRequestDTO) {
        log.debug("Request to save PlayRequest : {}", playRequestDTO);
        PlayRequest playRequest = playRequestMapper.toEntity(playRequestDTO);
        playRequest = playRequestRepository.save(playRequest);
        return playRequestMapper.toDto(playRequest);
    }

    /**
     * Get all the playRequests.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlayRequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlayRequests");
        return playRequestRepository.findAll(pageable)
            .map(playRequestMapper::toDto);
    }

    /**
     * Get one playRequest by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PlayRequestDTO findOne(Long id) {
        log.debug("Request to get PlayRequest : {}", id);
        PlayRequest playRequest = playRequestRepository.findOne(id);
        return playRequestMapper.toDto(playRequest);
    }

    /**
     * Delete the playRequest by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlayRequest : {}", id);
        playRequestRepository.delete(id);
    }
}
