package it.prova.gestioneparcheggio.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

public interface IBaseDAO<T> {
	public List<T> list() throws Exception;

	public Optional<T> findById(Long idInput) throws Exception;

	public void update(T input) throws Exception;

	public void insert(T input) throws Exception;

	public void delete(T input) throws Exception;

	public void setEntityManager(EntityManager entityManager);
}
