package br.com.soc.sistema.infra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.soc.sistema.exception.BusinessException;

public enum OpcoesComboBuscarExamesRealizados {
	ID("1", "ID"), 
	NOME("2", "NOME");
	
	private String codigo;
	private String descricao;
	private final static Map<String, OpcoesComboBuscarExamesRealizados> opcoes = new HashMap<>();
	
	static {
		Arrays.asList(OpcoesComboBuscarExamesRealizados.values())
		.forEach(
			opcao -> opcoes.put(opcao.getCodigo(), opcao)
		);
	}
	
	private OpcoesComboBuscarExamesRealizados(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static OpcoesComboBuscarExamesRealizados buscarPor(String codigo) throws IllegalArgumentException {
		if(codigo == null)
			throw new IllegalArgumentException("informe um codigo valido");
		
		OpcoesComboBuscarExamesRealizados opcao = getOpcao(codigo)
				.orElseThrow(() -> new BusinessException("Codigo informado nao existe"));
		
		return opcao;
	}
	
	private static Optional<OpcoesComboBuscarExamesRealizados> getOpcao(String codigo){
		return Optional.ofNullable(opcoes.get(codigo));
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
