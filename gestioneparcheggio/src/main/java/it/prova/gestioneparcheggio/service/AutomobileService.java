package it.prova.gestioneparcheggio.service;

import java.util.List;

import it.prova.gestioneparcheggio.dao.AutomobileDAO;
import it.prova.gestioneparcheggio.model.Automobile;

public interface AutomobileService {
	public List<Automobile> listAllElements() throws Exception;

	public Automobile caricaSingoloElementoById(Long id) throws Exception;

	public Automobile caricaSingoloElementoByIdEager(Long id) throws Exception;

	public void aggiorna(Automobile automobileInstance) throws Exception;

	public void inserisciNuovo(Automobile automobileInstance) throws Exception;

	public void rimuovi(Automobile automobileInstance) throws Exception;

	public List<Automobile> findByExample(Automobile example) throws Exception;

	public void setAutomobileDAO(AutomobileDAO automobileDAO);
}
