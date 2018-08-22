package crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.Animal;
import banco.Conexao;

public class CrudAnimal {

	public boolean addAnimal(Animal animal) {
		String sql = "INSERT INTO animais (datadenascimento, raca, sexo, img, destino, idfazenda, quantidade, datacompra,nomeLote)"
				+ " VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, animal.getDataDeNascimento());
			stmt.setInt(2, animal.getRaca());
			stmt.setString(3, animal.getSexo());
			stmt.setBytes(4, animal.getImagem());
			stmt.setString(5, animal.getDestino());
			stmt.setInt(6, animal.getIdFazenda());
			stmt.setInt(7, animal.getQuantidade());
			stmt.setString(8, animal.getDataCompra());
			stmt.setString(9, animal.getNomeLote());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updAnimal(Animal animal) {
		String sql = "update  animais set datadenascimento=?, raca=?, sexo=?, img=?, destino=?,"
				+ " idfazenda=?, quantidade=?, datacompra=?,nomeLote=? where idanimal = ?";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, animal.getDataDeNascimento());
			stmt.setInt(2, animal.getRaca());
			stmt.setString(3, animal.getSexo());
			stmt.setBytes(4, animal.getImagem());
			stmt.setString(5, animal.getDestino());
			stmt.setInt(6, animal.getIdFazenda());
			stmt.setInt(7, animal.getQuantidade());
			stmt.setString(8, animal.getDataCompra());
			stmt.setString(9, animal.getNomeLote());
			stmt.setInt(10, animal.getIdAnimal());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean removeAnimal(Animal animal) {
		String sql = "delete from animais where idanimal = ?";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, animal.getIdAnimal());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	
	public static ResultSet selecionaAnimais(Animal animal) {
		ResultSet tabela = null;
		String sql = "SELECT * FROM animais where idfazenda = ? and idanimal>1 and quantidade>=1 order by nomeLote";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, animal.getIdFazenda());
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
	
	public static ResultSet procuraAnimal(String letra,Animal animal) {
		ResultSet tabela = null;
		String sql = "SELECT * FROM animais WHERE nomelote LIKE ? and idfazenda = ? and idanimal>1 and quantidade>=1 order by nomeLote";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, "%"+letra+"%");
			stmt.setInt(2, animal.getIdFazenda());
			tabela = stmt.executeQuery();
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erro ao selecionar animal");
			return tabela=null;
		}
		return tabela;
	}
	
}


