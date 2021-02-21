package it.prova.gestioneparcheggio.dao;

import java.util.List;
import java.util.Optional;

import it.prova.gestioneparcheggio.model.Ruolo;
import it.prova.gestioneparcheggio.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	public Optional<Utente> findUtenteByUsernameEPassword(String username, String password);

	public Optional<Utente> loginUtente(String username, String password);

	public List<Utente> findAllUtentiByRuolo(Ruolo ruoloInput);
}
