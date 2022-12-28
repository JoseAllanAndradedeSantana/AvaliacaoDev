package br.com.soc.sistema.vo;

import java.time.LocalDate;

public class ExamesRealizadosVo {
	private String rowid;
	private ExameVo exameVo;
	private FuncionarioVo funcionarioVo;
	private LocalDate dataExame;
	
	
	public ExamesRealizadosVo() {
		super();
	}
	
	public ExamesRealizadosVo(String rowid, ExameVo exameVo, FuncionarioVo funcionarioVo, LocalDate dataExame) {
		super();
		this.rowid = rowid;
		this.exameVo = exameVo;
		this.funcionarioVo = funcionarioVo;
		this.dataExame = dataExame;
	}

	public String getRowid() {
		return rowid;
	}
	public void setRowid(String rowid) {
		this.rowid = rowid;
	}
	public ExameVo getExameVo() {
		return exameVo;
	}
	public void setExameVo(ExameVo exameVo) {
		this.exameVo = exameVo;
	}
	public FuncionarioVo getFuncionarioVo() {
		return funcionarioVo;
	}
	public void setFuncionarioVo(FuncionarioVo funcionarioVo) {
		this.funcionarioVo = funcionarioVo;
	}
	public LocalDate getDataExame() {
		return dataExame;
	}
	public void setDataExame(LocalDate dataExame) {
		this.dataExame = dataExame;
	}
	@Override
	public String toString() {
		return "ExamesRealizadosVo [rowid=" + rowid + ", exameVo=" + exameVo + ", funcionarioVo=" + funcionarioVo
				+ ", dataExame=" + dataExame + "]";
	}

	
	

}
