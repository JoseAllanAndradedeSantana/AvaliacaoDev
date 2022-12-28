package br.com.soc.sistema.dao.funcionarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioDao extends Dao {
	
	public boolean validaExamesRealizados(String cod) {
		// TODO criar view no bd
		StringBuilder sql = new StringBuilder();
		
		sql
			.append("DELETE FROM exames_realizados ")
			.append("WHERE id_func = ?");
		
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(sql.toString())) {
			ps.setString(1, cod);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public void insert(FuncionarioVo funcionarioVo){
		StringBuilder query = new StringBuilder("INSERT INTO funcionario (nm_func) values (?)");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setString(i++, funcionarioVo.getNome());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String rowId) {
		StringBuilder sql = new StringBuilder("DELETE FROM funcionario WHERE (rowid = ?);");
		try(Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(sql.toString())){
			ps.setString(1, rowId);
			ps.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(FuncionarioVo funcionarioVo) {
		StringBuilder update = new StringBuilder("UPDATE funcionario SET nm_func = ? WHERE (rowid = ?);");
		
		try(Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(update.toString())){
			
					ps.setString(1, funcionarioVo.getNome());
					ps.setString(2, funcionarioVo.getRowid());
					ps.executeUpdate();
					
			}catch (SQLException e) {
					String.format("Erro ao atulaziar funcionario [%s]", e.getMessage());
			}
	
	}
	
	public List<FuncionarioVo> findAllFuncionarios(){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_func nome FROM funcionario");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()){
			
			FuncionarioVo func =  null;
			List<FuncionarioVo> funcs = new ArrayList<>();
			while (rs.next()) {
				func = new FuncionarioVo();
				func.setRowid(rs.getString("id"));
				func.setNome(rs.getString("nome"));	
				
				funcs.add(func);
			}
			return funcs;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}
	
	public List<FuncionarioVo> findAllByNome(String nome){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_func nome FROM funcionario ")
								.append("WHERE lower(nm_func) like lower(?)");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				FuncionarioVo func =  null;
				List<FuncionarioVo> funcs = new ArrayList<>();
				
				while (rs.next()) {
					func = new FuncionarioVo();
					func.setRowid(rs.getString("id"));
					func.setNome(rs.getString("nome"));	
					
					funcs.add(func);
				}
				return funcs;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return Collections.emptyList();
	}
	
	public FuncionarioVo findByCodigo(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_func nome FROM funcionario ")
								.append("WHERE rowid = ?");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				FuncionarioVo func =  null;
				
				while (rs.next()) {
					func = new FuncionarioVo();
					func.setRowid(rs.getString("id"));
					func.setNome(rs.getString("nome"));	
				}
				return func;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
}
