package crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.Vendas;
import banco.Conexao;

public class CrudVendas {
	
	public boolean addvendas(Vendas venda) {
		String sql = "INSERT INTO vendas(produto, idanimal, preco, cliente, qtd, idfazenda, datavenda,tipodoproduto,numeronota)"
				+ " VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, venda.getProduto());
			stmt.setInt(2, venda.getIdanimal());
			stmt.setDouble(3, venda.getPreco());
			stmt.setString(4, venda.getCliente());
			stmt.setInt(5, venda.getQuantidade());
			stmt.setInt(6, venda.getIdFazenda());
			stmt.setString(7, venda.getDataVenda());
			stmt.setInt(8, venda.getTipoDoProduto());
			stmt.setString(9, venda.getNumeroDaNota());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updVendas(Vendas venda) {
		String sql = "update  vendas set produto=?, idanimal=?, preco=?, cliente=?, qtd=?,tipodoproduto=?,numeronota=? where idvendas=?";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, venda.getProduto());
			stmt.setInt(2, venda.getIdanimal());
			stmt.setDouble(3, venda.getPreco());
			stmt.setString(4, venda.getCliente());
			stmt.setInt(5, venda.getQuantidade());
			stmt.setInt(6, venda.getTipoDoProduto());
			stmt.setString(7, venda.getNumeroDaNota());
			stmt.setInt(8, venda.getId());
			stmt.execute();
			stmt.close();
			 return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static ResultSet selecionaVendas(Vendas venda) {
		ResultSet tabela = null;
		String sql = "SELECT*FROM vendas where idfazenda=? order by datavenda";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, venda.getIdFazenda());
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
	
	public static ResultSet procuraVendas(String letra,Vendas venda) {
		ResultSet tabela = null;
		String sql = "SELECT * FROM vendas WHERE produto LIKE ? and idfazenda=? order by datavenda";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, "%"+letra+"%");
			stmt.setInt(2, venda.getIdFazenda());
			tabela=stmt.executeQuery();
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return tabela =null;
		}
		return tabela;
	}
	
	public ResultSet procurarVendasDataAno(String ano,int idFazenda) {
		ResultSet tabela = null;
		String sql = "SELECT*FROM vendas WHERE idfazenda=? and year(datavenda) = ? order by produto";
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
			System.out.println("Erro selecionar Vendas Data Ano");
		}
		return tabela;
	}
	
	public ResultSet procurarVendasDataAnoMes(String ano,String mes,int idFazenda) {
		ResultSet tabela = null;
		String sql = "SELECT*FROM vendas WHERE idfazenda=? and year(datavenda) = ? and month(datavenda) = ? order by produto";
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
			System.out.println("Erro selecionar vendas Data Ano Mes");
		}
		return tabela;
	}
	
	public ResultSet procurarVendasDataAnoDia(String ano,String mes,String dia,int idFazenda) {
		ResultSet tabela = null;
		String sql = "SELECT*FROM vendas WHERE idfazenda=? and year(datavenda) = ? "
				+ "and month(datavenda) = ? and day(datavenda) = ? order by produto";
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
			System.out.println("Erro selecionar Vendas Data Ano Dia");
		}
		return tabela;
	}
	
}
