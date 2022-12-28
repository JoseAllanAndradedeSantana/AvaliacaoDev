package br.com.soc.sistema.action.relatorios;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.ExamesRealizadosBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.dtos.ExamesRealizadosDto;

public class RelatoriosAction extends Action {
	
	private List<ExamesRealizadosDto> examesRealizados = new ArrayList<>();
	private String dtInicio;
	private String dtFim;
	private ExamesRealizadosBusiness business = new ExamesRealizadosBusiness();
	
	public String buscar() {
		return INPUT;
	}
	
	public String relatorios() throws Exception {
		
		
		examesRealizados.addAll(business.buscarExamesRealizadosBy(dtInicio, dtFim));
	
		return SUCCESS;
	}

	public List<ExamesRealizadosDto> getExamesRealizados() {
		return examesRealizados;
	}

	public void setExamesRealizados(List<ExamesRealizadosDto> examesRealizados) {
		this.examesRealizados = examesRealizados;
	}

	public String getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(String dtInicio) {
		this.dtInicio = dtInicio;
	}

	public String getDtFim() {
		return dtFim;
	}

	public void setDtFim(String dtFim) {
		this.dtFim = dtFim;
	}

	public ExamesRealizadosBusiness getBusiness() {
		return business;
	}

	public void setBusiness(ExamesRealizadosBusiness business) {
		this.business = business;
	}
	
	

}
