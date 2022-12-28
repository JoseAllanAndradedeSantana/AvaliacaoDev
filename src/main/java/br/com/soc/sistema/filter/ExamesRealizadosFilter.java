package br.com.soc.sistema.filter;

import br.com.soc.sistema.infra.OpcoesComboBuscarExames;
import br.com.soc.sistema.infra.OpcoesComboBuscarExamesRealizados;

public class ExamesRealizadosFilter {
	private OpcoesComboBuscarExamesRealizados opcoesCombo;
	private String valorBusca;

	public String getValorBusca() {
		return valorBusca;
	}

	public ExamesRealizadosFilter setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
		return this;
	}

	public OpcoesComboBuscarExamesRealizados getOpcoesCombo() {
		return opcoesCombo;
	}

	public ExamesRealizadosFilter setOpcoesCombo(String codigo) {
		this.opcoesCombo = OpcoesComboBuscarExamesRealizados.buscarPor(codigo);
		return this;
	}	
	
	public boolean isNullOpcoesCombo() {
		return this.getOpcoesCombo() == null;
	}
	
	public static ExameFilter builder() {
		return new ExameFilter();
	}
}
