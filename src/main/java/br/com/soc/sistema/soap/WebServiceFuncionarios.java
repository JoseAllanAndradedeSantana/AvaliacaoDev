package br.com.soc.sistema.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.FuncionarioVo;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceFuncionarios {
	@WebMethod
	public String buscarFuncionario(String codigo);

	@WebMethod
	public String salvarFuncionario(FuncionarioVo name);
	
	@WebMethod
	public String excluirFuncionario(String codigo);
	
	@WebMethod
	public String editarFuncionario(FuncionarioVo name);

}
