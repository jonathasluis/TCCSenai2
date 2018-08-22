package crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.Usuario;
import banco.Conexao;

public class crudUsuarios {
	
	public boolean addUsuario(Usuario usuario) {
		String sql = "INSERT usuario(usuario,senha,email) VALUES(?,?,?)";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1,usuario.getUsuario());
			stmt.setString(2,usuario.getSenha());
			stmt.setString(3,usuario.getEmail());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deletaUsuario(Usuario usuario) {
		String sql = "DELETE FROM usuario WHERE idusuario=?";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, usuario.getIdUsuario());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updUsuario(Usuario usuario) {
		String sql = "UPDATE usuario SET usuario=?,senha=?,email=? WHERE idusuario=?";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getUsuario());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getEmail());
			stmt.setInt(4, usuario.getIdUsuario());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet visualizarUsuario(Usuario usuario) {
		ResultSet tabela = null;
		String sql = "SELECT*FROM usuario where idusuario=?";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, usuario.getIdUsuario());
			tabela = stmt.executeQuery();
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return tabela=null;
		}
		return tabela;
	}
	
	public boolean updSenhaUsuario(String senha,String usuario) {
		String sql = "UPDATE usuario SET senha=? WHERE usuario=?";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1,senha);
			stmt.setString(2, usuario);
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet selectUsuarioPeloNome(String usuario) {
		ResultSet tabela = null;
		String sql = "SELECT*FROM usuario where usuario=?";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, usuario);
			tabela = stmt.executeQuery();
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return tabela=null;
		}
		return tabela;
	}
}
