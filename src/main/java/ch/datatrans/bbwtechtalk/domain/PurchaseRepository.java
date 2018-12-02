package ch.datatrans.bbwtechtalk.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dominik.mengelt@gmail.com
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Purchase findByRefno(String refno);

}
