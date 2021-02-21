package it.prova.gestioneparcheggio.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneparcheggio.dao.RuoloDAO;
import it.prova.gestioneparcheggio.model.Ruolo;
import it.prova.gestioneparcheggio.model.Utente;
import it.prova.gestioneparcheggio.web.listener.LocalEntityManagerFactoryListener;

public class RuoloServiceImpl implements RuoloService {

	private RuoloDAO ruoloDAO;

	private UtenteService utenteService = MyServiceFactory.getUtenteServiceInstance();

	@Override
	public List<Ruolo> listAll() throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			ruoloDAO.setEntityManager(entityManager);

			return ruoloDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Ruolo caricaSingoloElemento(Long id) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			ruoloDAO.setEntityManager(entityManager);

			return ruoloDAO.findById(id).get();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Ruolo ruoloInstance) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ruoloDAO.setEntityManager(entityManager);

			ruoloDAO.update(ruoloInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Ruolo ruoloInstance) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ruoloDAO.setEntityManager(entityManager);

			ruoloDAO.insert(ruoloInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Ruolo ruoloInstance) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ruoloDAO.setEntityManager(entityManager);

			List<Utente> listaUtentiPresentiNeldB = utenteService.trovaUtentiTramiteRuolo(ruoloInstance);
			if (!listaUtentiPresentiNeldB.isEmpty())
				for (Utente utenteItem : listaUtentiPresentiNeldB) {
					utenteItem.setRuoli(null);

					utenteService.aggiorna(utenteItem);
				}
			ruoloDAO.delete(ruoloInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ruolo cercaRuoloPerCodiceEDescrizione(String codice, String descrizione) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			ruoloDAO.setEntityManager(entityManager);

			return ruoloDAO.findRuoloByCodiceEDescrizione(codice, descrizione);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void setRuoloDAO(RuoloDAO ruoloDAO) {
		this.ruoloDAO = ruoloDAO;
	}
}
