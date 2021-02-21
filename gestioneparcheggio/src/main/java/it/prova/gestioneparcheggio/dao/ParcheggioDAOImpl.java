package it.prova.gestioneparcheggio.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestioneparcheggio.model.Parcheggio;

public class ParcheggioDAOImpl implements ParcheggioDAO {

	private EntityManager entityManager;

	@Override
	public List<Parcheggio> list() throws Exception {
		return entityManager.createQuery("from Parcheggio", Parcheggio.class).getResultList();
	}

	@Override
	public Optional<Parcheggio> findById(Long idInput) throws Exception {
		Parcheggio resultParcheggio = entityManager.find(Parcheggio.class, idInput);
		if (resultParcheggio == null)
			return Optional.empty();

		return Optional.of(resultParcheggio);
	}

	@Override
	public void update(Parcheggio parcheggioInstance) throws Exception {
		if (parcheggioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		parcheggioInstance = entityManager.merge(parcheggioInstance);
	}

	@Override
	public void insert(Parcheggio parcheggioInstance) throws Exception {
		if (parcheggioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(parcheggioInstance);
	}

	@Override
	public void delete(Parcheggio parcheggioInstance) throws Exception {
		if (parcheggioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(parcheggioInstance));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Parcheggio findParkingBycars(Long idInput) throws Exception {
		return entityManager.createQuery("from Parcheggio p left join fetch p.automobili where r.id=:idParcheggio",
				Parcheggio.class).setParameter("idRegista", idInput).getSingleResult();
	}

	@Override
	public List<Parcheggio> findByExample(Parcheggio parcheggioExample) throws Exception {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select p from Parcheggio p where p.id = p.id ");

		if (StringUtils.isNotEmpty(parcheggioExample.getNome())) {
			whereClauses.add(" p.nome  like :nome ");
			paramaterMap.put("nome", "%" + parcheggioExample.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(parcheggioExample.getIndirizzo())) {
			whereClauses.add(" p.indirizzo like :indirizzo ");
			paramaterMap.put("indirizzo", "%" + parcheggioExample.getIndirizzo() + "%");
		}
		if (parcheggioExample.getOrarioApertura() != null) {
			whereClauses.add(" p.orarioApertura like :orarioApertura");
			paramaterMap.put("orarioApertura", "%" + parcheggioExample.getOrarioApertura() + "%");
		}
		if (parcheggioExample.getOrarioApertura() != null) {
			whereClauses.add(" p.orarioChiusura like :orarioChiusura");
			paramaterMap.put("orarioChiusura", "%" + parcheggioExample.getOrarioChiusura() + "%");
		}
		if (parcheggioExample.getGiornoChiusura() != null) {
			whereClauses.add(" p.giornoChiusura like :giornoChiusura ");
			paramaterMap.put("giornoChiusura", "%" + parcheggioExample.getGiornoChiusura() + "%");
		}
		if (parcheggioExample.getCapienza() != 0) {
			whereClauses.add(" p.capienza =:capienza ");
			paramaterMap.put("capienza", parcheggioExample.getCapienza());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Parcheggio> typedQuery = entityManager.createQuery(queryBuilder.toString(), Parcheggio.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
