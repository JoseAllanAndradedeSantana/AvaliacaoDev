package br.com.soc.sistema.soap;

import javax.jws.WebService;

import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.FuncionarioVo;

@WebService(endpointInterface = "br.com.soc.sistema.soap.WebServiceFuncionarios" )
public class WebServiceFuncionariosImpl implements WebServiceFuncionarios {
private FuncionarioBusiness business;
	
	public WebServiceFuncionariosImpl() {
		this.business = new FuncionarioBusiness();
	}
	
	@Override
	public String buscarFuncionario(String codigo) {		
		return business.buscarFuncionarioPor(codigo).toString();
	}

	@Override
	public String salvarFuncionario(FuncionarioVo name) {
		
		business.salvar(name);
		return name.toString();
	}

	@Override
	public String excluirFuncionario(String codigo) {
		
		business.apagar(codigo);
		return codigo.toString();
	}

	@Override
	public String editarFuncionario(FuncionarioVo name) {
		
		business.atualizar(name);
		return name.toString();
	}

}
