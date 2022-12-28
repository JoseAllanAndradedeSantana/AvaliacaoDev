package br.com.soc.sistema.dao.examesrealizados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.ExamesRealizadosVo;
import br.com.soc.sistema.vo.FuncionarioVo;
import br.com.soc.sistema.vo.dtos.ExamesRealizadosDto;

public class ExamesRealizadosDao extends Dao {

	public boolean validaExamesRealizados(ExamesRealizadosDto examesRealizadosVo) {
		
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM exames_realizados WHERE id_func = ? AND id_exame = ? AND data =?");
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(sql.toString())) {
			ps.setString(1, examesRealizadosVo.getCodFunc());
			ps.setString(2, examesRealizadosVo.getCodExame());
			ps.setObject(3, examesRealizadosVo.getData());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public void insert(ExamesRealizadosDto examesRealizadosVo) {
		StringBuilder query = new StringBuilder(
				"INSERT INTO exames_realizados (id_exame,id_func,data) VALUES (?,?,?)");
		
		try (Connection con = getConexao(); 
			 PreparedStatement ps = con.prepareStatement(query.toString())) {
			
			ps.setString(1, examesRealizadosVo.getCodFunc());
			ps.setString(2, examesRealizadosVo.getCodExame());
			ps.setObject(3, examesRealizadosVo.getData());
			ps.executeUpdate();

		} catch (SQLException e) {
			//e.printStackTrace();
			String.format("ENTREI AQUI NO CATCH ====== [%s] {%s}", e.getMessage(),query);
		}
	}

	public void delete(String rowId) {
		StringBuilder sql = new StringBuilder("DELETE FROM exames_realizados WHERE (rowid = ?);");
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(sql.toString())) {
			ps.setString(1, rowId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(ExamesRealizadosDto examesRealizadosVo) {
		StringBuilder update = new StringBuilder(
				"UPDATE exames_realizados SET id_exame = ? ,id_func = ? ,data = ? WHERE (rowid = ?);");
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(update.toString())) {
			ps.setString(1, examesRealizadosVo.getCodExame());
			ps.setString(2, examesRealizadosVo.getCodFunc());
			ps.setString(3, examesRealizadosVo.getData());
			ps.setString(4, examesRealizadosVo.getCod());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<ExamesRealizadosVo> findAllExamesRealizados() throws SQLException {
		StringBuilder query = new StringBuilder();
		
		query
			.append("SELECT  e.rowid exameRowid, e.nm_exame exameNome, f.rowid funcRowid,f.nm_func funcNome, er.data erData,er.rowid erId ")
			.append("FROM exames_realizados er ")
			.append("INNER JOIN funcionario f ")
			.append("ON e.rowid = er.id_exame ")
			.append("INNER JOIN exame e ")
			.append("ON er.id_func = f.rowid");
		
		
		
		try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString());
				ResultSet rs = ps.executeQuery()){

			List<ExamesRealizadosVo> examesRealizados = new ArrayList<>();

			while (rs.next()) {
				
				ExamesRealizadosVo er = new ExamesRealizadosVo();
				ExameVo exame = new ExameVo();
				FuncionarioVo funcionario = new FuncionarioVo();
				
				
				exame.setRowid(rs.getString("exameRowid"));
				exame.setNome(rs.getString("exameNome"));
				
				funcionario.setRowid(rs.getString("funcRowid"));
				funcionario.setNome(rs.getString("funcNome"));
				
				er.setRowid(rs.getString("erId"));
				er.setFuncionarioVo(funcionario);
				er.setExameVo(exame);
				er.setDataExame(rs.getDate("erData").toLocalDate());

				examesRealizados.add(er);
				
			}
			
			return examesRealizados;
		} catch (SQLException e) {
			//e.printStackTrace();
			String.format("ENTREI AQUI NO CATCH ====== [%s] {%s}", e.getMessage(),query);
		}

		return Collections.emptyList();
	}

	public List<ExamesRealizadosDto> findAllByNome(String nome) {
		StringBuilder query = new StringBuilder(
				"SELECT rowid id, cod_func codF,nm_funcionario nomeF,cod_exame codE,nm_exame nomeE,dt_exame dt FROM exames_realizados ")
						.append("WHERE lower(nm_funcionario) like lower(?)");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;

			ps.setString(i, "%" + nome + "%");

			try (ResultSet rs = ps.executeQuery()) {
				ExamesRealizadosDto er = null;
				List<ExamesRealizadosDto> examesRealizados = new ArrayList<>();

				while (rs.next()) {
					er = new ExamesRealizadosDto();
					ExameVo exame = new ExameVo();
					FuncionarioVo funcionario = new FuncionarioVo();
//					exame.setRowid(rs.getString("codE"));
//					exame.setNome(rs.getString("nomeE"));
//					funcionario.setRowid(rs.getString("codF"));
//					funcionario.setNome(rs.getString("nomeF"));
//					er.setRowId(rs.getString("id"));
//					er.setFuncionarioVo(funcionario);
//					er.setExameVo(exame);
//					er.setDataExame(rs.getDate("dt"));

					examesRealizados.add(er);
				}
				return examesRealizados;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public ExamesRealizadosDto findByCodigo(Integer codigo) {
		StringBuilder query = new StringBuilder();
		
		query
			.append("SELECT  e.rowid exameRowid, e.nm_exame exameNome, f.rowid funcRowid,f.nm_func funcNome, er.data erData,er.rowid erId ")
			.append("FROM exames_realizados er ")
			.append("INNER JOIN funcionario f ")
			.append("ON e.rowid = er.id_exame ")
			.append("INNER JOIN exame e ")
			.append("ON er.id_func = f.rowid ")
			.append("WHERE er.rowid = ?;");
	

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;

			ps.setInt(i, codigo);

			try (ResultSet rs = ps.executeQuery()) {
				ExamesRealizadosDto er = new ExamesRealizadosDto();

				while (rs.next()) {
					er.setCod(rs.getString("erId"));
					er.setCodFunc(rs.getString("id_func"));
					er.setCodExame(rs.getString("id_exame"));
					er.setData(rs.getString("data"));

				}
				return er;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ExamesRealizadosVo> findByDate(String dtInicio, String dtFim) {
		
		StringBuilder sql = new StringBuilder();
		
		sql
			.append("SELECT e.rowid exameRowid, e.nm_exame exameNome, f.rowid funcRowid,f.nm_func funcNome, er.data erData,er.rowid erId ")
			.append("FROM exames_realizados er ")
			.append("INNER JOIN funcionario f ")
			.append("ON e.rowid = er.id_exame ")
			.append("INNER JOIN exame e ")
			.append("ON er.id_func = f.rowid ")
			.append("WHERE data ")
			.append("BETWEEN (?) AND (?) ")
			.append("ORDER BY er.data DESC");


		ExamesRealizadosVo er = null;
		
		try (Connection con = getConexao();PreparedStatement ps = con.prepareStatement(sql.toString())) {
			
			ps.setObject(1, dtInicio);
			ps.setObject(2, dtFim);
			
			ResultSet rs = ps.executeQuery();
				
			List<ExamesRealizadosVo> examesRealizadosList = new ArrayList<>();

				while (rs.next()) {
					
					er = new ExamesRealizadosVo();
					ExameVo exame = new ExameVo();
					FuncionarioVo funcionario = new FuncionarioVo();
					
					
					exame.setRowid(rs.getString("exameRowid"));
					exame.setNome(rs.getString("exameNome"));
					
					funcionario.setRowid(rs.getString("funcRowid"));
					funcionario.setNome(rs.getString("funcNome"));
					
					er.setRowid(rs.getString("erId"));
					er.setFuncionarioVo(funcionario);
					er.setExameVo(exame);
					er.setDataExame(rs.getDate("erData").toLocalDate());

					examesRealizadosList.add(er);
				}
				
				return examesRealizadosList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

}
