package br.com.caelum.agiletickets.models;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

@Entity
public class Espetaculo {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;

	private String descricao;

	@Enumerated(EnumType.STRING)
	private TipoDeEspetaculo tipo;

	@OneToMany(mappedBy = "espetaculo")
	private List<Sessao> sessoes = newArrayList();

	@ManyToOne
	private Estabelecimento estabelecimento;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoDeEspetaculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeEspetaculo tipo) {
		this.tipo = tipo;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public List<Sessao> criaSessoes(LocalDate inicio, LocalDate fim,
			LocalTime horario, Periodicidade periodicidade) {
		int intervaloDeDias = 0;
		int intervaloDeSemanas = 0;

		if (periodicidade.equals(Periodicidade.DIARIA)) {
			intervaloDeDias = Days.daysBetween(inicio, fim).getDays() + 1;

			for (int i = 0; i < intervaloDeDias; i++) {
				Sessao sessao = new Sessao();
				sessao.setInicio(inicio.plusDays(i).toDateTime(horario));
				sessao.setEspetaculo(this);
				sessoes.add(sessao);
			}
		} else {

			intervaloDeDias = Days.daysBetween(inicio, fim).getDays();
			if (intervaloDeDias <= 7) {
				intervaloDeSemanas = 1;
			} else {
				intervaloDeSemanas = (intervaloDeDias / 7) + 1;
			}
			int semanas = 0;
			for (int i = 0; i < intervaloDeSemanas; i++) {

				Sessao sessao = new Sessao();
				sessao.setInicio(inicio.plusDays(semanas).toDateTime(horario));
				semanas = semanas + 7;
				sessao.setEspetaculo(this);
				sessoes.add(sessao);
			}
		}

		return sessoes;
	}

	private void adicionarSessaoNaLista(LocalDate inicio, LocalTime horario,
			int intervalo) {
		for (int i = 0; i < intervalo; i++) {
			Sessao sessao = new Sessao();
			sessao.setInicio(inicio.plusDays(i).toDateTime(horario));
			sessao.setEspetaculo(this);
			sessoes.add(sessao);
		}
	}
}
