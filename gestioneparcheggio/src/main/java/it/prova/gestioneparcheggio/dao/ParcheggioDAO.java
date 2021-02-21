package it.prova.gestioneparcheggio.dao;

import java.util.List;

import it.prova.gestioneparcheggio.model.Parcheggio;

public interface ParcheggioDAO extends IBaseDAO<Parcheggio> {
	public Parcheggio findParkingBycars(Long idInput) throws Exception;

	public List<Parcheggio> findByExample(Parcheggio example) throws Exception;
}
