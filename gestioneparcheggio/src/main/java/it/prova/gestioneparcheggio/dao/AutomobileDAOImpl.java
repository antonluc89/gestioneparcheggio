package it.prova.gestioneparcheggio.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestioneparcheggio.model.Automobile;
import it.prova.gestioneparcheggio.model.Parcheggio;

public class AutomobileDAOImpl implements AutomobileDAO {

	private EntityManager entityManager;

	@Override
	public List<Automobile> list() throws Exception {
		return entityManager.createQuery("from Automobile", Automobile.class).getResultList();
	}

	@Override
	public Optional<Automobile> findById(Long idInput) throws Exception {
		Automobile resultAutomobile = entityManager.find(Automobile.class, idInput);

		if (resultAutomobile == null)
			return Optional.empty();

		return Optional.of(resultAutomobile);
	}

	@Override
	public void update(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}
		automobileInstance = entityManager.merge(automobileInstance);
	}

	@Override
	public void insert(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(automobileInstance);
	}

	@Override
	public void delete(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(automobileInstance));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Automobile> findAutomobiliByParcheggio(Parcheggio parcheggioInstance) throws Exception {
		if (parcheggioInstance == null) {
			throw new Exception("Problema valore in input");
		}

		TypedQuery<Automobile> query = entityManager.createQuery("select a from Automobile a where a.parcheggio = ?1",
				Automobile.class);
		query.setParameter(1, parcheggioInstance);
		return query.getResultList();
	}

	@Override
	public Optional<Automobile> findByIdEager(Long idInput) throws Exception {
		return entityManager.createQuery("from Automobile a left join fetch a.parcheggio where a.id=:idAutomobile",
				Automobile.class).setParameter("idAutomobile", idInput).getResultList().stream().findFirst();
	}

	@Override
	public List<Automobile> findByExample(Automobile automobileExample) throws Exception {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select a from Automobile a where a.id = a.id ");

		if (StringUtils.isNotEmpty(automobileExample.getModello())) {
			whereClauses.add(" a.modello  like :modello ");
			paramaterMap.put("modello", "%" + automobileExample.getModello() + "%");
		}
		if (StringUtils.isNotEmpty(automobileExample.getMarca())) {
			whereClauses.add(" a.marca like :marca ");
			paramaterMap.put("marca", "%" + automobileExample.getMarca() + "%");
		}
		if (StringUtils.isNotEmpty(automobileExample.getTarga())) {
			whereClauses.add(" a.targa like :targa ");
			paramaterMap.put("genere", "%" + automobileExample.getMarca() + "%");
		}
		if (automobileExample.getOrarioStampaTicket() != null) {
			whereClauses.add(" a.orarioStampaTicket like :orarioStampaTicket ");
			paramaterMap.put("orarioStampaTicket", "%" + automobileExample.getOrarioStampaTicket() + "%");
		}
		if (automobileExample.getMinutiDurataTicket() != 0) {
			whereClauses.add(" a.minutiDurataTicket =:minutiDurataTicket ");
			paramaterMap.put("minutiDurataTicket", automobileExample.getMinutiDurataTicket());
		}
		if (automobileExample.getParcheggio() != null) {
			whereClauses.add("a.parcheggio =:parcheggio ");
			paramaterMap.put("parcheggio", automobileExample.getParcheggio());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Automobile> typedQuery = entityManager.createQuery(queryBuilder.toString(), Automobile.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();

	}

}