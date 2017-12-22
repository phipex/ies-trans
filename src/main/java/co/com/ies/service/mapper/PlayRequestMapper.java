package co.com.ies.service.mapper;

import co.com.ies.domain.*;
import co.com.ies.service.dto.PlayRequestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PlayRequest and its DTO PlayRequestDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlayRequestMapper extends EntityMapper<PlayRequestDTO, PlayRequest> {

    

    

    default PlayRequest fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlayRequest playRequest = new PlayRequest();
        playRequest.setId(id);
        return playRequest;
    }
}
