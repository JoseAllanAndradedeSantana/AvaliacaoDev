package br.com.soc.sistema.action.examesrealizados;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.ExamesRealizadosBusiness;
import br.com.soc.sistema.filter.ExamesRealizadosFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarExamesRealizados;
import br.com.soc.sistema.vo.ExamesRealizadosVo;
//import br.com.soc.sistema.vo.ExamesRealizadosDto;
import br.com.soc.sistema.vo.dtos.ExamesRealizadosDto;

public class ExamesRealizadosAction extends Action {
	private List<ExamesRealizadosVo> examesRealizados2 = new ArrayList<>();
	private List<ExamesRealizadosDto> examesRealizados = new ArrayList<>();
	private ExamesRealizadosBusiness business = new ExamesRealizadosBusiness();
	private ExamesRealizadosFilter filtrar = new ExamesRealizadosFilter();
	private ExamesRealizadosDto examesRealizadosVo = new ExamesRealizadosDto();
	private String dtInicio;
	private String dtFim;
	
	

	public void setDtInicio(String dtInicio) {
		this.dtInicio = dtInicio;
	}

	public void setDtFim(String dtFim) {
		this.dtFim = dtFim;
	}

	public String todos() throws SQLException, ParseException {
		examesRealizados.addAll(business.findAll());

		return SUCCESS;
	}

	public String filtrar() {
		if (filtrar.isNullOpcoesCombo())
			return REDIRECT;

		examesRealizados = business.filtrarExamesRealizados(filtrar);

		return SUCCESS;
	}

	public String novo() {
		if (examesRealizadosVo.getCodFunc() == null) {
			return INPUT;		
		}
		
		business.salvarExame(examesRealizadosVo);
		
		return REDIRECT;
	}
	
	public String excluir() {
		if(examesRealizadosVo.getCod() == null) {
			return REDIRECT;			
		}
		business.apagar(examesRealizadosVo.getCod());
		return REDIRECT;
	}

	public String editar() {
		
		if (examesRealizadosVo.getCod() == null) {
			
			return REDIRECT;

		}

		examesRealizadosVo = business.buscarExamePor(examesRealizadosVo.getCod());
		
		
		return "editar";
	}
	
	public String buscar() {
		return "buscar";
	}
	
	public String relatorios() throws Exception {
		
		if(dtInicio == null) {
			throw new Exception(""+dtInicio);
		}
		examesRealizados.addAll(business.buscarExamesRealizadosBy(dtInicio, dtFim));
		//throw new Exception(examesRealizados2.get(0).toString());
		return "relatorios";
	}
	
	public List<ExamesRealizadosVo> getExamesRealizados2() {
		return examesRealizados2;
	}

	public void setExamesRealizados2(List<ExamesRealizadosVo> examesRealizados2) {
		this.examesRealizados2 = examesRealizados2;
	}

	public ExamesRealizadosBusiness getBusiness() {
		return business;
	}

	public void setBusiness(ExamesRealizadosBusiness business) {
		this.business = business;
	}

	public String update() {
        if (examesRealizadosVo.getCodExame() == null) {
        	return INPUT;
        }
        business.atualizar(examesRealizadosVo);
        return REDIRECT;
    }
	

	public List<OpcoesComboBuscarExamesRealizados> getListaOpcoesCombo() {
		return Arrays.asList(OpcoesComboBuscarExamesRealizados.values());
	}

	public List<ExamesRealizadosDto> getExamesRealizados() {
		return examesRealizados;
	}

	public void setExamesRealizados(List<ExamesRealizadosDto> examesRealizados) {
		this.examesRealizados = examesRealizados;
	}


	public ExamesRealizadosFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(ExamesRealizadosFilter filtrar) {
		this.filtrar = filtrar;
	}

	public ExamesRealizadosDto getExamesRealizadosVo() {
		return examesRealizadosVo;
	}

	public void setExamesRealizadosVo(ExamesRealizadosDto examesRealizadosVo) {
		this.examesRealizadosVo = examesRealizadosVo;
	}

	

}
