package gribanov.lab2.repository;

import gribanov.lab2.models.EmailHistory;
import org.springframework.data.repository.CrudRepository;

public interface EmailHistoryRepository extends CrudRepository<EmailHistory, Integer> {
}
