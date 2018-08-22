package crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.Fazenda;
import DAO.Usuario;
import banco.Conexao;

public class CrudFazenda {

	public boolean addFazenda(Fazenda fazenda) {
		String sql = "INSERT INTO fazenda (nome, tamanho, proprietario, escritura, "
				+ "descri, img, idusuario) VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, fazenda.getNome());
			stmt.setString(2, fazenda.getTamanho());
			stmt.setString(3, fazenda.getProprietario());
			stmt.setString(4, fazenda.getEscritura());
			stmt.setString(5, fazenda.getDescricao());
			stmt.setBytes(6, fazenda.getImg());
			stmt.setInt(7, fazenda.getIdUsuario());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
			
	}
	
	public boolean deletaFazenda(Fazenda fazenda) {
		String sql = "DELETE FROM fazenda WHERE idfazenda=?";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, fazenda.getIdFazenda());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean updFazenda(Fazenda fazenda) {
		String sql = "UPDATE fazenda SET nome=?, tamanho=?, proprietario=?, "
				+ "escritura=?, descri=?, img=? WHERE idfazenda=?";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, fazenda.getNome());
			stmt.setString(2, fazenda.getTamanho());
			stmt.setString(3, fazenda.getProprietario());
			stmt.setString(4, fazenda.getEscritura());
			stmt.setString(5, fazenda.getDescricao());
			stmt.setBytes(6, fazenda.getImg());
			stmt.setInt(7, fazenda.getIdFazenda());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
	}
	
	public static ResultSet selecionaFazenda(Usuario usuario){
		ResultSet tabela = null;
		String sql = "SELECT * FROM fazenda where idusuario=? order by nome";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, usuario.getIdUsuario());
			tabela=stmt.executeQuery();
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return tabela = null;
		}
		return tabela;
	}
	
	public ResultSet selecionaFazendaEspecifica(int idFazenda){
		ResultSet tabela = null;
		String sql = "SELECT * FROM fazenda where idfazenda=? ";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, idFazenda);
			tabela=stmt.executeQuery();
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return tabela = null;
		}
		return tabela;
	}
	
	public ResultSet procuraFazenda(String letra,Fazenda fazenda) {
		ResultSet tabela = null;
		String sql= "SELECT * FROM fazenda WHERE nome LIKE ? and idusuario=? order by nome";
		 try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, "%"+letra+"%");
			stmt.setInt(2, fazenda.getIdUsuario());
			tabela = stmt.executeQuery();
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return tabela = null;
		}
		 return tabela;
	}
}