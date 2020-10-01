package fr.eletutour.kataSG.repository;

import fr.eletutour.kataSG.model.Operation;
import org.springframework.data.repository.CrudRepository;

/**
 * repository for account operation
 */
public interface OperationRepository extends CrudRepository<Operation, Long> {
}
