package it.prova.gestioneparcheggio.dao;

public class MyDAOFactory {
	private static ParcheggioDAO parcheggioDaoInstance = null;
	private static AutomobileDAO automobileDaoInstance = null;

	public static ParcheggioDAO getParcheggioDAOInstance() {
		if (parcheggioDaoInstance == null)
			parcheggioDaoInstance = new ParcheggioDAOImpl();

		return parcheggioDaoInstance;
	}

	public static AutomobileDAO getAutomobileDAOInstance() {
		if (automobileDaoInstance == null)
			automobileDaoInstance = new AutomobileDAOImpl();

		return automobileDaoInstance;
	}

}
