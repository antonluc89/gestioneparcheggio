package it.prova.gestioneparcheggio.service;

import java.util.List;

import it.prova.gestioneparcheggio.dao.UtenteDAO;
import it.prova.gestioneparcheggio.model.Ruolo;
import it.prova.gestioneparcheggio.model.Utente;

public interface UtenteService {
	public List<Utente> listAll() throws Exception;

	public Utente caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Utente utenteInstance) throws Exception;

	public void inserisciNuovo(Utente utenteInstance) throws Exception;

	public void rimuovi(Utente utenteInstance) throws Exception;
	
	public void aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloInstance) throws Exception;
	
	public Utente trovaUtenteTramiteUsernameEPassword(String username, String password) throws Exception;
	
	public Utente accedi(String username, String password) throws Exception;
	
	public List<Utente> trovaUtentiTramiteRuolo(Ruolo ruoloInstance) throws Exception;

	public void setUtenteDAO(UtenteDAO utenteDAO);
}
