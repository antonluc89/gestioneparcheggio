package it.prova.gestioneparcheggio.dao;

import java.util.List;
import java.util.Optional;

import it.prova.gestioneparcheggio.model.Automobile;
import it.prova.gestioneparcheggio.model.Parcheggio;

public interface AutomobileDAO extends IBaseDAO<Automobile> {
	public List<Automobile> findAutomobiliByParcheggio(Parcheggio parcheggioInput) throws Exception;

	public Optional<Automobile> findByIdEager(Long idInput) throws Exception;

	public List<Automobile> findByExample(Automobile automobileExample) throws Exception;
}
