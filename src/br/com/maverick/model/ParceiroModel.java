package br.com.maverick.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="tbparceiro")

public class ParceiroModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codparceiro")
	Integer codParceiro;
	
	@Column(name="tipopessoa",nullable=false)
	String tipoPessoa;
	
	@Column(name="nomeparceiro", length=255, nullable=false)
	String nomeParceiro;
	
	@Column(name="razaosocial", length=255)
	String razaoSocial;
	
	@Column(name="telefone", nullable=false)
	String telefone;

	@Column(name="cpf")
	String cpf;
	
	@Column(name="cnpj")
	String cnpj;
	
	@Column(name="cep", nullable=false)
	String cep;
	
	@Column(name="logradouro", nullable=false)
	String logradouro;
	
	@Column(name="numero", nullable=false)
	Integer numero;
	
	@Column(name="cidade", nullable=false)
	String cidade;
	
	@Column(name="bairro", nullable=false)
	String bairro;
	
	@Column(name="estado", nullable=false)
	String estado;
	
	@Column(name="email", nullable=false)
	String email;

	public ParceiroModel() {
	}

	/*construtor pessoa juridica*/
	public ParceiroModel(Integer codParceiro, String tipoPessoa, String nomeParceiro, String razaoSocial, String telefone,
			String cnpj, String cep, String logradouro, Integer numero, String cidade, String bairro, String estado,
			String email) {
		this.codParceiro = codParceiro;
		this.tipoPessoa = tipoPessoa;
		this.nomeParceiro = nomeParceiro;
		this.razaoSocial = razaoSocial;
		this.telefone = telefone;
		this.cnpj = cnpj;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cidade = cidade;
		this.bairro = bairro;
		this.estado = estado;
		this.email = email;
	}

	/*construtor pessoa fisica*/
	public ParceiroModel(Integer codParceiro, String tipoPessoa, String nomeParceiro, String telefone, String cpf,
			String cep, String logradouro, Integer numero, String cidade, String bairro, String estado, String email) {
		this.codParceiro = codParceiro;
		this.tipoPessoa = tipoPessoa;
		this.nomeParceiro = nomeParceiro;
		this.telefone = telefone;
		this.cpf = cpf;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cidade = cidade;
		this.bairro = bairro;
		this.estado = estado;
		this.email = email;
	}

	public Integer getCodParceiro() {
		return codParceiro;
	}

	public void setCodParceiro(Integer codParceiro) {
		this.codParceiro = codParceiro;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNomeParceiro() {
		return nomeParceiro;
	}

	public void setNomeParceiro(String nomeParceiro) {
		this.nomeParceiro = nomeParceiro.toUpperCase();
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial.toUpperCase();
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro.toUpperCase();
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade.toUpperCase();
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro.toUpperCase();
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado.toUpperCase();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codParceiro == null) ? 0 : codParceiro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParceiroModel other = (ParceiroModel) obj;
		if (codParceiro == null) {
			if (other.codParceiro != null)
				return false;
		} else if (!codParceiro.equals(other.codParceiro))
			return false;
		return true;
	}
	
	
	
	
	
}
