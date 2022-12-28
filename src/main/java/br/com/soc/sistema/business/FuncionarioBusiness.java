package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.funcionarios.FuncionarioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioBusiness {
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private FuncionarioDao dao;

	public FuncionarioBusiness() {
		this.dao = new FuncionarioDao();
	}

	public List<FuncionarioVo> trazerTodosOsFuncionarios() {
		return dao.findAllFuncionarios();
	}
	
	public void apagar(String id) {
		try {
			
			dao.validaExamesRealizados(id);				
			
			dao.delete(id);
			
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel excluir o registro" + e.getMessage());
		}

	}

	public void atualizar(FuncionarioVo funcionarioVo) {
		try {

			if (funcionarioVo.getRowid().isEmpty()) {
				throw new IllegalArgumentException("ID INVALIDO");
			}

			dao.update(funcionarioVo);

		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel atualizar o registro:" + e.getMessage());
		}
	}

	public void salvar(FuncionarioVo funcionarioVo) {
		try {
			if (funcionarioVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");

			dao.insert(funcionarioVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro " + e.getMessage());
		}

	}

	public List<FuncionarioVo> filtrarFuncionarios(FuncionarioFilter filter) {
		List<FuncionarioVo> funcs = new ArrayList<>();

		switch (filter.getOpcoesCombo()) {
		case ID:
			try {
				Integer codigo = Integer.parseInt(filter.getValorBusca());
				funcs.add(dao.findByCodigo(codigo));
			} catch (NumberFormatException e) {
				throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
			}
			break;

		case NOME:
			funcs.addAll(dao.findAllByNome(filter.getValorBusca()));
			break;
		}

		return funcs;
	}

	public FuncionarioVo buscarFuncionarioPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		} catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
}
