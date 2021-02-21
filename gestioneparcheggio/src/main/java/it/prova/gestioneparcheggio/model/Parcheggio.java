package it.prova.gestioneparcheggio.model;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "parcheggio")
public class Parcheggio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "indirizzo")
	private String indirizzo;
	@Column(name = "orarioApertura")
	private Time orarioApertura;
	@Column(name = "orarioChiusura")
	private Time orarioChiusura;
	@Column(name = "giornoChiusura")
	private Date giornoChiusura;
	@Column(name = "capienza")
	private Integer capienza;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parcheggio")
	private Set<Automobile> automobili = new HashSet<Automobile>(0);

	public Parcheggio() {
	}

	public Parcheggio(String nome, String indirizzo, Time orarioApertura, Time orarioChiusura, Date giornoChiusura,
			Integer capienza) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.orarioApertura = orarioApertura;
		this.orarioChiusura = orarioChiusura;
		this.giornoChiusura = giornoChiusura;
		this.capienza = capienza;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Time getOrarioApertura() {
		return orarioApertura;
	}

	public void setOrarioApertura(Time orarioApertura) {
		this.orarioApertura = orarioApertura;
	}

	public Time getOrarioChiusura() {
		return orarioChiusura;
	}

	public void setOrarioChiusura(Time orarioChiusura) {
		this.orarioChiusura = orarioChiusura;
	}

	public Date getGiornoChiusura() {
		return giornoChiusura;
	}

	public void setGiornoChiusura(Date giornoChiusura) {
		this.giornoChiusura = giornoChiusura;
	}

	public Integer getCapienza() {
		return capienza;
	}

	public void setCapienza(Integer capienza) {
		this.capienza = capienza;
	}

	public Set<Automobile> getAutomobili() {
		return automobili;
	}

	public void setAutomobili(Set<Automobile> automobili) {
		this.automobili = automobili;
	}

}
