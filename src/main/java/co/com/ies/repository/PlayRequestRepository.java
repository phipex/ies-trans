package co.com.ies.repository;

import co.com.ies.domain.PlayRequest;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PlayRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlayRequestRepository extends JpaRepository<PlayRequest, Long> {

}
