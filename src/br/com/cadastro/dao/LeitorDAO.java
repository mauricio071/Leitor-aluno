package br.com.cadastro.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.cadastro.model.Leitor;
import br.com.cadastro.util.ConnectionFactory;

public class LeitorDAO {  
	
	private Leitor leitor;
	private Connection conn;       // conecta com o banco de dados
	private java.sql.PreparedStatement ps; // permite executar querys
	private ResultSet rs; // tabela 

	public LeitorDAO() throws Exception {
		try {
			conn = ConnectionFactory.getConnection();
		}catch(Exception e) {
			throw new Exception("Erro " + e.getMessage());
		}
	}
	public void salvar(Leitor leitor) throws Exception {
		try {
			String sql = "INSERT INTO tbLeitor(codLeitor,nomeLeitor,tipoLeitor) "
					+ "values (?, ?, ?)";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, leitor.getCodLeitor());
			ps.setString(2, leitor.getNomeLeitor());
			ps.setString(3, leitor.getTipoLeitor());
			ps.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Erro ao salvar" + e.getMessage());
		}
	}	
	public void alterar(Leitor leitor) throws Exception {
		try {
			String sql = "UPDATE tbLeitor SET nomeLeitor=?,tipoLeitor=? "
					+ " WHERE codLeitor=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, leitor.getNomeLeitor());
			ps.setString(2, leitor.getTipoLeitor());
			ps.setInt(3, leitor.getCodLeitor());
			ps.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Erro ao alterar" + e.getMessage());
		}
	}	
	public void excluir(int codLeitor) throws Exception {
		try {
			String sql = "DELETE FROM tbLeitor "
					+ "WHERE codLeitor=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, codLeitor);
			ps.executeUpdate();
		}catch(Exception e) {
			throw new Exception("Erro ao excluir" + e.getMessage());
		}
	}	
	public List listarTodos() throws Exception {
		List<Leitor> lista = new ArrayList<Leitor>();
		try {
			ps = conn.prepareStatement("SELECT * FROM tbLeitor");
			rs = ps.executeQuery();
			while(rs.next()) {
				int codLeitor = rs.getInt("codLeitor");
				String nomeLeitor = rs.getString("nomeLeitor");
				String tipoLeitor = rs.getString("tipoLeitor");
				leitor = new Leitor(codLeitor, nomeLeitor, tipoLeitor);
				lista.add(leitor);
			}
			return lista;
		}catch(Exception e) {
			throw new Exception("Erro ao listar" + e.getMessage());
		}
	}
	public Leitor consultar(int codLeitor) throws Exception {
		try {
			ps = conn.prepareStatement("SELECT * FROM tbLeitor WHERE codLeitor=?");
			ps.setInt(1, codLeitor);
			rs = ps.executeQuery();
			if(rs.next()) {
				String nomeLeitor = rs.getString("nomeLeitor");
				String tipoLeitor = rs.getString("tipoLeitor");
				leitor = new Leitor(codLeitor, nomeLeitor, tipoLeitor);
			}
			return leitor;
		}catch(Exception e) {
			throw new Exception("Erro ao listar" + e.getMessage());
		}
	}
}