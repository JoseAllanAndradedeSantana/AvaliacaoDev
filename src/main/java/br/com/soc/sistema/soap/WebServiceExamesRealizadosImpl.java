package br.com.soc.sistema.soap;

import javax.jws.WebService;

import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.business.ExamesRealizadosBusiness;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.dtos.ExamesRealizadosDto;

@WebService(endpointInterface = "br.com.soc.sistema.soap.WebServiceExamesRealizados" )
public class WebServiceExamesRealizadosImpl implements WebServiceExamesRealizados {

	private ExamesRealizadosBusiness business;

	public WebServiceExamesRealizadosImpl() {
		this.business = new ExamesRealizadosBusiness();
	}

	@Override
	public String salvarExamesRealizados(ExamesRealizadosDto name) {
		business.salvarExame(name);
		return String.format("Agenda Salva com sucesso %s", name);
	}

	@Override
	public String buscarExamesRealizados(String codigo) {
		
		return business.buscarExamePor(codigo).toString();
	}

	@Override
	public String excluirExamesRealizados(String codigo) {
		business.apagar(codigo);
		return String.format("Agenda com o id:%s atualizada com sucesso", codigo) ;
	}

	@Override
	public String editarExamesRealizados(ExamesRealizadosDto name) {
		business.atualizar(name);
		return String.format("Agenda atualizada com sucesso : %s", name) ;
	}

}
