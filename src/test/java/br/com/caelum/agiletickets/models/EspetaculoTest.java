package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {
	Espetaculo espetaculo;
	LocalDate inicio;
	LocalDate fim;
	LocalTime horario;
	Periodicidade periodicidade;
	List<Sessao> sessoes;

	@Test
	public void adicionarSessaoUnica() {
		// CEN�RIO
		espetaculo = new Espetaculo();
		inicio = new LocalDate(2011, 01, 01);
		fim = new LocalDate(2011, 01, 01);
		horario = new LocalTime(18, 00);
		periodicidade = Periodicidade.DIARIA;

		// A��O
		sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);

		// VALIDA��O
		// Verifica se est� retornando uma sessao
		assertEquals(1, sessoes.size());
		// Verifica se est� retornando uma sess�o com data e hora informados
		assertEquals(inicio.toDateTime(horario), sessoes.get(0).getInicio());

	}

	@Test
	public void adicionarSessoesDiarias() {

		// CEN�RIO
		espetaculo = new Espetaculo();
		inicio = new LocalDate(2011, 01, 01);
		fim = new LocalDate(2011, 01, 03);
		horario = new LocalTime(18, 00);
		periodicidade = Periodicidade.DIARIA;

		// A��O
		sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);

		// VALIDA��O
		assertEquals(3, sessoes.size());

		for (int i = 0; i < sessoes.size(); i++) {
			assertEquals(inicio.plusDays(i).toDateTime(horario), sessoes.get(i)
					.getInicio());
		}

	}

	@Test
	public void adicionarSessaoSemanalUnica() {
		// CEN�RIO
		espetaculo = new Espetaculo();
		inicio = new LocalDate(2011, 01, 01);
		fim = new LocalDate(2011, 01, 06);
		horario = new LocalTime(15, 00);
		periodicidade = Periodicidade.SEMANAL;

		// A��O
		sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);

		// VALIDA��O
		assertEquals(1, sessoes.size());
	}

	@Test
	public void adicionarSessaoSemanalInformandoUmM�s() {
		// CEN�RIO
		espetaculo = new Espetaculo();
		inicio = new LocalDate(2011, 01, 01);
		fim = new LocalDate(2011, 01, 31);
		horario = new LocalTime(15, 00);
		periodicidade = Periodicidade.SEMANAL;
		int semanas = 0;

		// A��O
		sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);

		// VALIDA��O
		assertEquals(5, sessoes.size());

		for (int i = 0; i < sessoes.size(); i++) {
			assertEquals(inicio.plusDays(semanas).toDateTime(horario), sessoes
					.get(i).getInicio());
			semanas = semanas + 7;
		}
	}
}
