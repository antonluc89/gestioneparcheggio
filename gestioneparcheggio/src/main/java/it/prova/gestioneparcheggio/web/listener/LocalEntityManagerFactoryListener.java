package it.prova.gestioneparcheggio.web.listener;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import it.prova.gestioneparcheggio.model.Ruolo;
import it.prova.gestioneparcheggio.model.StatoUtente;
import it.prova.gestioneparcheggio.model.Utente;
import it.prova.gestioneparcheggio.service.MyServiceFactory;
import it.prova.gestioneparcheggio.service.RuoloService;
import it.prova.gestioneparcheggio.service.UtenteService;

@WebListener
public class LocalEntityManagerFactoryListener implements ServletContextListener {

	private static EntityManagerFactory entityManagerFactory;

	public void contextDestroyed(ServletContextEvent sce) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("gestioneparcheggio_unit");
			initAdminUserAndRuoli();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return entityManagerFactory.createEntityManager();

	}

	public static void closeEntityManager(EntityManager em) {
		if (em != null) {
			try {
				if (em.isOpen()) {
					em.close();
				}
			} catch (PersistenceException ex) {
				System.err.println("Could not close JPA EntityManager" + ex);
			} catch (Throwable ex) {
				System.err.println("Unexpected exception on closing JPA EntityManager" + ex);
			}
		}
	}

	private void initAdminUserAndRuoli() throws Exception {
		RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();
		UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();

		if (ruoloServiceInstance.cercaRuoloPerCodiceEDescrizione("ROLE_ADMIN_USER", "Administrator") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("ROLE_ADMIN_USER", "Administrator"));
		}

		if (ruoloServiceInstance.cercaRuoloPerCodiceEDescrizione("ROLE_CLASSIC_USER", "Classic User") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("ROLE_CLASSIC_USER", "Classic User"));
		}

		if (utenteServiceInstance.trovaUtenteTramiteUsernameEPassword("admin", "admin") == null) {
			Utente admin = new Utente("admin", "admin", "Luigi", "Bianchi", new Date());
			admin.setStato(StatoUtente.ATTIVO);
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.aggiungiRuolo(admin,
					ruoloServiceInstance.cercaRuoloPerCodiceEDescrizione("ROLE_ADMIN_USER", "Administrator"));
		}
	}

}
