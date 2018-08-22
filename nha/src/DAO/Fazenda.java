package DAO;

public class Fazenda {
	private int idFazenda;
	private String nome;
	private String tamanho;
	private String proprietario;
	private String escritura;
	private String descricao;
	private byte[] img;
	private int idUsuario;
	
	public int getIdFazenda() {
		return idFazenda;
	}
	public void setIdFazenda(int idFazenda) {
		this.idFazenda = idFazenda;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getProprietario() {
		return proprietario;
	}
	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}
	public String getEscritura() {
		return escritura;
	}
	public void setEscritura(String escritura) {
		this.escritura = escritura;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}
