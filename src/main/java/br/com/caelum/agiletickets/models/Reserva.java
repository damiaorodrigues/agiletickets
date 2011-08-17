package br.com.caelum.agiletickets.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reserva {

	@Id
	@GeneratedValue
	private Long id;

	private String cadeira;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCadeira() {
		return cadeira;
	}

	public void setCadeira(String cadeira) {
		this.cadeira = cadeira;
	}

	public Espetaculo getEspetaculo() {
		return espetaculo;
	}

	public void setEspetaculo(Espetaculo espetaculo) {
		this.espetaculo = espetaculo;
	}

	@ManyToOne
	private Espetaculo espetaculo;
}
