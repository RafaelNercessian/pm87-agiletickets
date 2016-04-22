package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;
import br.com.caelum.agiletickets.*;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		boolean sessaoEhCinema=sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA) ;
		boolean sessaoEhShow=sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW);
		boolean sessaoEhBallet=sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET);
		boolean sessaoEhOruqestra=sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA);
		BigDecimal preco=new BigDecimal(0);
		
		if(sessaoEhCinema || sessaoEhShow) {
			preco=calculaPrecoCinemaOuShow(sessao);		
		} else if(sessaoEhBallet) {
			preco=calculaPrecoBallet(sessao);
		} else if(sessaoEhOruqestra) {
			preco=calculaPrecoOrquestra(sessao);
		}  else {
			preco = sessao.getPreco();
		} 
		
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal calculaPrecoOrquestra(Sessao sessao) {
		BigDecimal preco=new BigDecimal(0);
		if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.50) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
		} else {
			preco = sessao.getPreco();
		}

		if(sessao.getDuracaoEmMinutos() > 60){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		}
		return preco;
	}

	private static BigDecimal calculaPrecoBallet(Sessao sessao) {
		BigDecimal preco=new BigDecimal(0);
		if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.50) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
		} else {
			preco = sessao.getPreco();
		}
		
		if(sessao.getDuracaoEmMinutos() > 60){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		}
		
		return preco;
		
	}

	private static BigDecimal calculaPrecoCinemaOuShow(Sessao sessao) {
		BigDecimal preco=new BigDecimal(0);
		if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.05) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		} else {
			preco = sessao.getPreco();
		}
		return preco;
	}

	

}