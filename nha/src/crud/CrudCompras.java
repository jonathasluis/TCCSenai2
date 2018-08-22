package crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.Compras;
import banco.Conexao;

public class CrudCompras {
	
	public boolean addCompras(Compras compras) {
		String sql = "INSERT compras_insumos(fornecedor, cnpj,"
				+ " numero_nota, qtd, preco, produto, data_compra, id_fazenda) VALUES (?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, compras.getFornecedor());
			stmt.setString(2, compras.getCnpj());
			stmt.setString(3, compras.getNumeroNota());
			stmt.setInt(4, compras.getQuantidade());
			stmt.setDouble(5, compras.getPreco());
			stmt.setString(6, compras.getProduto());
			stmt.setString(7, compras.getDataCompra());
			stmt.setInt(8, compras.getIdFazenda());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updCompras(Compras compras) {
		String sql = "UPDATE compras_insumos SET fornecedor=?, cnpj=?,"
					+ "numero_nota=?, produto=?,preco=?,qtd=? where id_fazenda=? and id_compras = ?";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, compras.getFornecedor());
			stmt.setString(2, compras.getCnpj());
			stmt.setString(3, compras.getNumeroNota());
			stmt.setString(4, compras.getProduto());
			stmt.setDouble(5, compras.getPreco());
			stmt.setInt(6, compras.getQuantidade());
			stmt.setInt(7, compras.getIdFazenda());
			stmt.setInt(8, compras.getId());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static ResultSet selecionaCompras(Compras compras) {
		ResultSet tabela = null;
		String sql = "SELECT*FROM compras_insumos where id_fazenda=? order by data_compra";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, compras.getIdFazenda());
			tabela=stmt.executeQuery();
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return tabela=null;
		}
		return tabela;
	}
	
	public ResultSet procurarCompra(String letra,int idFazenda) {
		ResultSet tabela = null;
		String sql = "SELECT*FROM compras_insumos WHERE produto LIKE ? and id_fazenda=? order by data_compra";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1,"%"+letra+"%");
			stmt.setInt(2, idFazenda);
			tabela = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erro selecionar compra");
		}
		return tabela;
	}
	
	public ResultSet procurarCompraDataAno(String ano,int idFazenda) {//seleciona dados de um ano especifico
		ResultSet tabela = null;
		String sql = "SELECT*FROM compras_insumos WHERE id_fazenda=? and year(data_compra) = ? order by produto";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1,idFazenda);
			stmt.setString(2, ano);
			tabela = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erro selecionar Compra Data Ano");
		}
		return tabela;
	}
	
	public ResultSet procurarCompraDataAnoMes(String ano,String mes,int idFazenda) {//seleciona dados de um mes do ano especifico
		ResultSet tabela = null;
		String sql = "SELECT*FROM compras_insumos WHERE id_fazenda=? and year(data_compra) = ? and month(data_compra) = ? order by produto";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1,idFazenda);
			stmt.setString(2, ano);
			stmt.setString(3, mes);
			tabela = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erro selecionar compra Data Ano Mes");
		}
		return tabela;
	}
	
	public ResultSet procurarCompraDataAnoDia(String ano,String mes,String dia,int idFazenda) {//seleciona dados de um dia especifico
		ResultSet tabela = null;
		String sql = "SELECT*FROM compras_insumos WHERE id_fazenda=? and year(data_compra) = ? "
				+ "and month(data_compra) = ? and day(data_compra) = ? order by produto";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1,idFazenda);
			stmt.setString(2, ano);
			stmt.setString(3, mes);
			stmt.setString(4, dia);
			tabela = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erro selecionar compra Data ano Dia");
		}
		return tabela;
	}
}


