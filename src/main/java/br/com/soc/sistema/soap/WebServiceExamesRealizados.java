package br.com.soc.sistema.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.com.soc.sistema.vo.FuncionarioVo;
import br.com.soc.sistema.vo.dtos.ExamesRealizadosDto;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceExamesRealizados {
	
	@WebMethod
	public String salvarExamesRealizados(ExamesRealizadosDto name);
	
	@WebMethod
	public String buscarExamesRealizados(String codigo);
	
	@WebMethod
	public String excluirExamesRealizados(String codigo);
	
	@WebMethod
	public String editarExamesRealizados(ExamesRealizadosDto name);

}
