package it.prova.gestioneparcheggio.service;

import java.util.List;

import it.prova.gestioneparcheggio.dao.RuoloDAO;
import it.prova.gestioneparcheggio.model.Ruolo;

public interface RuoloService {
	public List<Ruolo> listAll() throws Exception;

	public Ruolo caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Ruolo ruoloInstance) throws Exception;

	public void inserisciNuovo(Ruolo ruoloInstance) throws Exception;

	public void rimuovi(Ruolo ruoloInstance) throws Exception;

	public Ruolo cercaRuoloPerCodiceEDescrizione(String codice, String descrizione) throws Exception;

	public void setRuoloDAO(RuoloDAO ruoloDAO);
}
