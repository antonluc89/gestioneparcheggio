package it.prova.gestioneparcheggio.service;

import it.prova.gestioneparcheggio.dao.AutomobileDAO;
import it.prova.gestioneparcheggio.dao.AutomobileDAOImpl;
import it.prova.gestioneparcheggio.dao.ParcheggioDAO;
import it.prova.gestioneparcheggio.dao.ParcheggioDAOImpl;
import it.prova.gestioneparcheggio.dao.RuoloDAO;
import it.prova.gestioneparcheggio.dao.RuoloDAOImpl;
import it.prova.gestioneparcheggio.dao.UtenteDAO;
import it.prova.gestioneparcheggio.dao.UtenteDAOImpl;

public class MyServiceFactory {

	private static ParcheggioService PARCHEGGIO_SERVICE_INSTANCE;
	private static AutomobileService AUTOMOBILE_SERVICE_INSTANCE = null;
	private static RuoloService RUOLO_SERVICE_INSTANCE;
	private static UtenteService UTENTE_SERVICE_INSTANCE;
	private static ParcheggioDAO PARCHEGGIO_DAO_INSTANCE;
	private static AutomobileDAO AUTOMOBILE_DAO_INSTANCE = null;
	private static RuoloDAO RUOLO_DAO_INSTANCE = null;
	private static UtenteDAO UTENTE_DAO_INSTANCE = null;

	public static ParcheggioService getParcheggioServiceInstance() {
		if (PARCHEGGIO_SERVICE_INSTANCE == null)
			PARCHEGGIO_SERVICE_INSTANCE = new ParcheggioServiceImpl();

		if (PARCHEGGIO_DAO_INSTANCE == null)
			PARCHEGGIO_DAO_INSTANCE = new ParcheggioDAOImpl();

		PARCHEGGIO_SERVICE_INSTANCE.setParcheggioDAO(PARCHEGGIO_DAO_INSTANCE);

		return PARCHEGGIO_SERVICE_INSTANCE;
	}

	public static AutomobileService getAutomobileServiceInstance() {
		if (AUTOMOBILE_SERVICE_INSTANCE == null)
			AUTOMOBILE_SERVICE_INSTANCE = new AutomobileServiceImpl();

		if (AUTOMOBILE_DAO_INSTANCE == null)
			AUTOMOBILE_DAO_INSTANCE = new AutomobileDAOImpl();

		AUTOMOBILE_SERVICE_INSTANCE.setAutomobileDAO(AUTOMOBILE_DAO_INSTANCE);

		return AUTOMOBILE_SERVICE_INSTANCE;
	}

	public static UtenteService getUtenteServiceInstance() {
		if (UTENTE_SERVICE_INSTANCE == null)
			UTENTE_SERVICE_INSTANCE = new UtenteServiceImpl();

		if (UTENTE_DAO_INSTANCE == null)
			UTENTE_DAO_INSTANCE = new UtenteDAOImpl();

		UTENTE_SERVICE_INSTANCE.setUtenteDAO(UTENTE_DAO_INSTANCE);
		return UTENTE_SERVICE_INSTANCE;
	}

	public static RuoloService getRuoloServiceInstance() {
		if (RUOLO_SERVICE_INSTANCE == null)
			RUOLO_SERVICE_INSTANCE = new RuoloServiceImpl();

		if (RUOLO_DAO_INSTANCE == null)
			RUOLO_DAO_INSTANCE = new RuoloDAOImpl();

		RUOLO_SERVICE_INSTANCE.setRuoloDAO(RUOLO_DAO_INSTANCE);
		return RUOLO_SERVICE_INSTANCE;
	}

}