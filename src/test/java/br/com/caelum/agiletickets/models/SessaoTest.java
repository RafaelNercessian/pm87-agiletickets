package br.com.caelum.agiletickets.models;

import java.util.List;


import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class SessaoTest {

	@Test
	public void deveVender1ingressoSeHa2vagas() throws Exception {
		Sessao sessao = new Sessao();
        sessao.setTotalIngressos(2);

        Assert.assertTrue(sessao.podeReservar(1));
	}

	@Test
	public void naoDeveVender3ingressoSeHa2vagas() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(2);

		Assert.assertFalse(sessao.podeReservar(3));
	}
	
	@Test
	public void deveVender5ingressoSeHa10vagas() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(10);

		Assert.assertTrue(sessao.podeReservar(5));
	}

	@Test
	public void reservarIngressosDeveDiminuirONumeroDeIngressosDisponiveis() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(5);

		sessao.reserva(3);
		Assert.assertEquals(2, sessao.getIngressosDisponiveis().intValue());
	}
	
	@Test
	public void deveVender2IngressosSeHa2Vagas() throws Exception{
		Sessao sessao=new Sessao();
		sessao.setTotalIngressos(2);
		Assert.assertTrue(sessao.podeReservar(2));
		
	}
	
	@Test
	public void cadastrandoListaDeSessoesPeriodicidadeSemanal() throws Exception{
		Espetaculo espetaculo=new Espetaculo();
		espetaculo.setNome("Chuck");
		espetaculo.setDescricao("Chuck");
		espetaculo.setTipo(TipoDeEspetaculo.CINEMA);
		Sessao sessao=new Sessao();
		sessao.setEspetaculo(espetaculo);
		LocalDate inicio=new LocalDate(2016, 1, 9);
		LocalDate fim=new LocalDate(2016, 1, 23);
		LocalTime horario=new LocalTime(17, 0);
		List<Sessao>sessoes=espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		Assert.assertEquals(10, sessoes.get(0).getInicio().getDayOfYear());
		Assert.assertEquals(16, sessoes.get(1).getInicio().getDayOfYear());
		Assert.assertEquals(23, sessoes.get(2).getInicio().getDayOfYear());
	}


	@Test
	public void cadastrandoListaDeSessoesPeriodicidadeDiaria() throws Exception{
		Espetaculo espetaculo=new Espetaculo();
		espetaculo.setNome("Chuck");
		espetaculo.setDescricao("Chuck");
		espetaculo.setTipo(TipoDeEspetaculo.CINEMA);
		Sessao sessao=new Sessao();
		sessao.setEspetaculo(espetaculo);
		LocalDate inicio=new LocalDate(2016, 1, 9);
		LocalDate fim=new LocalDate(2016, 1, 23);
		LocalTime horario=new LocalTime(17, 0);
		List<Sessao>sessoes=espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		Assert.assertEquals(9, sessoes.get(0).getInicio().getDayOfYear());
		Assert.assertEquals(10, sessoes.get(1).getInicio().getDayOfYear());
		Assert.assertEquals(11, sessoes.get(2).getInicio().getDayOfYear());
	}
}
