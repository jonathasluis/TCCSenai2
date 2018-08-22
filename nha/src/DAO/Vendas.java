package DAO;

public class Vendas {
	
	private int id;
	private String produto;
	private int idanimal;
	private double preco;
	private String  cliente;
	private int quantidade;
	private int tipoDoProduto;
	private String numeroDaNota;
	
	public String getNumeroDaNota() {
		return numeroDaNota;
	}
	public void setNumeroDaNota(String numeroDaNota) {
		this.numeroDaNota = numeroDaNota;
	}
	public int getTipoDoProduto() {
		return tipoDoProduto;
	}
	public void setTipoDoProduto(int tipoDoProduto) {
		this.tipoDoProduto = tipoDoProduto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public int getIdanimal() {
		return idanimal;
	}
	public void setIdanimal(int idanimal) {
		this.idanimal = idanimal;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getIdFazenda() {
		return idFazenda;
	}
	public void setIdFazenda(int idFazenda) {
		this.idFazenda = idFazenda;
	}
	public String getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
	private int idFazenda;
	private String dataVenda;
	
}
