package it.prova.gestioneparcheggio.dao;

import it.prova.gestioneparcheggio.model.Ruolo;

public interface RuoloDAO extends IBaseDAO<Ruolo> {

	public Ruolo findRuoloByCodiceEDescrizione(String codice, String descrizione) throws Exception;

}
