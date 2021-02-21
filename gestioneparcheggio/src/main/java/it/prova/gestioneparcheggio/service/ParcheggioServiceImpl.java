package it.prova.gestioneparcheggio.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneparcheggio.dao.AutomobileDAO;
import it.prova.gestioneparcheggio.dao.MyDAOFactory;
import it.prova.gestioneparcheggio.dao.ParcheggioDAO;
import it.prova.gestioneparcheggio.model.Automobile;
import it.prova.gestioneparcheggio.model.Parcheggio;
import it.prova.gestioneparcheggio.web.listener.LocalEntityManagerFactoryListener;


public class ParcheggioServiceImpl implements ParcheggioService {

	private AutomobileDAO automobileDAO;
	
	private ParcheggioDAO parcheggioDAO;

	@Override
	public void setParcheggioDAO(ParcheggioDAO parcheggioDAO) {
		this.parcheggioDAO = parcheggioDAO;
	}

	@Override
	public List<Parcheggio> listAllElements() throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			parcheggioDAO.setEntityManager(entityManager);

			return parcheggioDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Parcheggio caricaSingoloElementoById(Long id) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			parcheggioDAO.setEntityManager(entityManager);

			return parcheggioDAO.findById(id).get();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Parcheggio caricaSingoloElementoByIdConAutomobili(Long id) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			parcheggioDAO.setEntityManager(entityManager);

			return parcheggioDAO.findParkingBycars(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Parcheggio parcheggioInstance) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			parcheggioDAO.setEntityManager(entityManager);

			parcheggioDAO.update(parcheggioInstance);

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
	public void inserisciNuovo(Parcheggio parcheggioInstance) throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			parcheggioDAO.setEntityManager(entityManager);

			parcheggioDAO.insert(parcheggioInstance);

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
	public void rimuovi(Parcheggio parcheggioInstance) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			
			entityManager.getTransaction().begin();

			automobileDAO = MyDAOFactory.getAutomobileDAOInstance();
			
			automobileDAO.setEntityManager(entityManager);

			List<Automobile> listaAutomobili = automobileDAO.findAutomobiliByParcheggio(parcheggioInstance);

			for (Automobile automobileItem : listaAutomobili) {
				automobileItem.setParcheggio(null);
				automobileDAO.delete(automobileItem);
			}
			parcheggioDAO.setEntityManager(entityManager);

			parcheggioDAO.delete(parcheggioInstance);

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
	public List<Parcheggio> findByExample(Parcheggio example) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			parcheggioDAO.setEntityManager(entityManager);

			return parcheggioDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
