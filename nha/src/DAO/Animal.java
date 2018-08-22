package DAO;

public class Animal {

	private int idAnimal;
	private String dataDeNascimento;
	private int raca;
	private String sexo;
	private byte[] imagem;
	private String destino;
	private int idFazenda;
	private int quantidade;
	private String dataCompra;
	private String nomeLote;
	
	public int getIdAnimal() {
		return idAnimal;
	}
	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public int getRaca() {
		return raca;
	}
	public void setRaca(int raca) {
		this.raca = raca;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public int getIdFazenda() {
		return idFazenda;
	}
	public void setIdFazenda(int idFazenda) {
		this.idFazenda = idFazenda;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}
	public String getNomeLote() {
		return nomeLote;
	}
	public void setNomeLote(String nomeLote) {
		this.nomeLote = nomeLote;
	}
}
