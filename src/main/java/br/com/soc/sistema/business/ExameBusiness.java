package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.exames.ExameDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.vo.ExameVo;

public class ExameBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private ExameDao dao;

	public ExameBusiness() {
		this.dao = new ExameDao();
	}

	public List<ExameVo> trazerTodosOsExames() {
		return dao.findAllExames();
	}
	
	public void apagar(String id) {
		try {
			boolean validaExamesRealizados = dao.validaExamesRealizados(id);
			
			if(validaExamesRealizados) {
				throw new Exception("Exame consta agendado");
			}
			
			//throw new Exception("ENTREI AQUI NO ----->>>>>>>"+id);
			dao.delete(id);
			
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel excluir o registro: " + e.getMessage());
		}

	}

	public void atualizar(ExameVo exameVo) {
		try {

			if (exameVo.getRowid().isEmpty()) {
				throw new IllegalArgumentException("ID INVALIDO");
			}

			dao.update(exameVo);

		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel atualizar o registro:" + e.getMessage());
		}
	}

	public void salvarExame(ExameVo exameVo) {
		try {
			if (exameVo.getNome().isEmpty()) {
				
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			}

			dao.insertExame(exameVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro " + e.getMessage());
		}

	}

	public List<ExameVo> filtrarExames(ExameFilter filter) {
		List<ExameVo> exames = new ArrayList<>();

		switch (filter.getOpcoesCombo()) {
		case ID:
			try {
				Integer codigo = Integer.parseInt(filter.getValorBusca());
				exames.add(dao.findByCodigo(codigo));
			} catch (NumberFormatException e) {
				throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
			}
			break;

		case NOME:
			exames.addAll(dao.findAllByNome(filter.getValorBusca()));
			break;
		}

		return exames;
	}

	public ExameVo buscarExamePor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			ExameVo e = dao.findByCodigo(cod);
			if(null == e) {
				throw new Exception("Exame Not Found");
			}
			return e;
		} catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		} catch (Exception ex) {
			throw new BusinessException(ex.getMessage());
		}
	}
}
