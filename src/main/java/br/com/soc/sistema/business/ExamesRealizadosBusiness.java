package br.com.soc.sistema.business;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import br.com.soc.sistema.dao.exames.ExameDao;
import br.com.soc.sistema.dao.examesrealizados.ExamesRealizadosDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.filter.ExamesRealizadosFilter;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.ExamesRealizadosVo;
import br.com.soc.sistema.vo.dtos.ExamesRealizadosDto;

public class ExamesRealizadosBusiness {
	
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private ExamesRealizadosDao dao;

	public ExamesRealizadosBusiness() {
		this.dao = new ExamesRealizadosDao();
	}

	public List<ExamesRealizadosVo> trazerTodosOsExamesRealizados() throws SQLException {
		return dao.findAllExamesRealizados();
	}
	
	public List<ExamesRealizadosDto> findAll() throws SQLException{
		List<ExamesRealizadosVo> list1 = dao.findAllExamesRealizados();
		List<ExamesRealizadosDto> list = new ArrayList<>();
		ExamesRealizadosDto ed = null;
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		for(ExamesRealizadosVo e : list1) {
			
			ed = new ExamesRealizadosDto();
			ed.setCod(e.getRowid());
			ed.setExame(e.getExameVo().getNome());
			ed.setFunc(e.getFuncionarioVo().getNome());
			ed.setData(e.getDataExame().format(sdf));
			
			list.add(ed);
			
		}
		return list;
	}
	
	public void apagar(String id) {
		try {
			
			dao.delete(id);
			
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar excluir o registro" + e.getMessage());
		}

	}

	public void atualizar(ExamesRealizadosDto examesRealizadosVo) {
		try {

			if (examesRealizadosVo.getCod() == null) {
				throw new IllegalArgumentException("ID INVALIDO");
			}

			dao.update(examesRealizadosVo);

		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel atualizar o registro:" + e.getMessage());
		}
	}

	public void salvarExame(ExamesRealizadosDto examesRealizadosVo) {
		try {		
			
			
			if(null == examesRealizadosVo.getCodExame()) {
				throw new BusinessException("O código do Exame não pode ser vazio");
			}
			
			if(examesRealizadosVo.getCodFunc() == null) {
				throw new BusinessException("O código do Funcionário não pode ser vazio");
			}
			
			if(dao.validaExamesRealizados(examesRealizadosVo)) {
				throw new BusinessException(
						String.format(
								"Agenda já cadastrada para esse funcionario cod:{%s} e exame cod{%s} na data:{%s} ", 
								examesRealizadosVo.getFunc(),
								examesRealizadosVo.getExame(),
								examesRealizadosVo.getData()));
			}
			
			dao.insert(examesRealizadosVo);
			
			
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro" + e.getMessage());
		}

	}

	public List<ExamesRealizadosDto> filtrarExamesRealizados(ExamesRealizadosFilter filter) {
		List<ExamesRealizadosDto> examesRealizadosList = new ArrayList<>();

		switch (filter.getOpcoesCombo()) {
		case ID:
			try {
				Integer codigo = Integer.parseInt(filter.getValorBusca());
				examesRealizadosList.add(dao.findByCodigo(codigo));
			} catch (NumberFormatException e) {
				throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
			}
			break;

		case NOME:
			examesRealizadosList.addAll(dao.findAllByNome(filter.getValorBusca()));
			break;
		}

		return examesRealizadosList;
	}
	
	public List<ExamesRealizadosDto> buscarExamesRealizadosBy(String DtInicio, String DtFfim){
		try {
		//  MOCK DE DATAS PARA TESTE
		//	DtInicio = LocalDate.of(2022, Month.DECEMBER, 13).toString();
		//	DtFfim = LocalDate.of(2022, Month.DECEMBER, 16).toString();
			
			
			if(DtInicio == null || DtFfim == null) {
				throw new BusinessException("not null " + DtInicio + " " +DtFfim);
			}
			List<ExamesRealizadosVo> list = dao.findByDate(DtInicio, DtFfim);
			List<ExamesRealizadosDto> list2 = new ArrayList<>();
			ExamesRealizadosDto ed = null;
			DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
			for(ExamesRealizadosVo e : list) {
				
				ed = new ExamesRealizadosDto();
				ed.setCod(e.getRowid());
				ed.setCodFunc(e.getFuncionarioVo().getRowid());
				ed.setCodExame(e.getExameVo().getRowid());
				ed.setExame(e.getExameVo().getNome());
				ed.setFunc(e.getFuncionarioVo().getNome());
				ed.setData(e.getDataExame().format(sdf));
				
				list2.add(ed);
				
			}
			
			return list2;
		}catch (Exception e) {
			throw new BusinessException("Data Invalida" + e.getMessage());
		}
		
	}

	public ExamesRealizadosDto buscarExamePor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		} catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
}
