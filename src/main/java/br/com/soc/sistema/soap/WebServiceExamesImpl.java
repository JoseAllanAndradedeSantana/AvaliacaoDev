package br.com.soc.sistema.soap;



import javax.jws.WebService;


import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.vo.ExameVo;

@WebService(endpointInterface = "br.com.soc.sistema.soap.WebServiceExames" )
public class WebServiceExamesImpl implements WebServiceExames {

	private ExameBusiness business;
	
	public WebServiceExamesImpl() {
		this.business = new ExameBusiness();
	}
	
	@Override
	public String buscarExames() {		
		return business.trazerTodosOsExames().toString();
	}
	@Override
	public String buscarExame(String codigo) {		
		return business.buscarExamePor(codigo).toString();
	}
	
	@Override
	public String salvarExame(ExameVo name) {
		business.salvarExame(name);
		return name.toString();
	}
	
	@Override
	public String excluirExame(String codigo) {
		
		business.apagar(codigo);
		return codigo.toString();
	}
	
	@Override
	public String editarExame(ExameVo name) {
		
		business.atualizar(name);
		return name.toString();
	}
	
	
	
}
