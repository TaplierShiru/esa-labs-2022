package gribanov.lab2.repository;

import gribanov.lab2.models.SessionData;
import org.springframework.data.repository.CrudRepository;

public interface SessionDataRepository extends CrudRepository<SessionData, Integer> { }