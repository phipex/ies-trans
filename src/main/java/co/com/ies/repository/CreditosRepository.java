package co.com.ies.repository;

import co.com.ies.domain.Creditos;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Creditos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreditosRepository extends JpaRepository<Creditos, Long> {

}
