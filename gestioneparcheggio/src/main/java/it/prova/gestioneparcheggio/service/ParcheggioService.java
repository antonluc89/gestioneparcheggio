package it.prova.gestioneparcheggio.service;

import java.util.List;

import it.prova.gestioneparcheggio.dao.ParcheggioDAO;
import it.prova.gestioneparcheggio.model.Parcheggio;

public interface ParcheggioService {
	public List<Parcheggio> listAllElements() throws Exception;

	public Parcheggio caricaSingoloElementoById(Long id) throws Exception;
	
	public Parcheggio caricaSingoloElementoByIdConAutomobili(Long id) throws Exception;

	public void aggiorna(Parcheggio parcheggioInstance) throws Exception;

	public void inserisciNuovo(Parcheggio parcheggioInstance) throws Exception;

	public void rimuovi(Parcheggio parcheggioInstance) throws Exception;
	
	public List<Parcheggio> findByExample(Parcheggio example) throws Exception;

	public void setParcheggioDAO(ParcheggioDAO parcheggioDAO);

}
