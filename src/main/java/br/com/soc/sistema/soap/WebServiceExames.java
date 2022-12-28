package br.com.soc.sistema.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.com.soc.sistema.vo.ExameVo;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceExames {
	
	
	@WebMethod
	public String buscarExames();
	
	@WebMethod
	public String buscarExame(String codigo);
	
	@WebMethod
	public String salvarExame(ExameVo name);
	
	@WebMethod
	public String excluirExame(String codigo);
	
	@WebMethod
	public String editarExame(ExameVo name);
	
}
