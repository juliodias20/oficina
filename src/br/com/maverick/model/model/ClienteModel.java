package br.com.maverick.model.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="cliente")

public class ClienteModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codcliente")
	Integer codCliente;
	
	@Column(name="tipoPessoa",nullable=false)
	String tipoPessoa;
	
	@Column(name="nomecliente", length=255, nullable=false)
	String nomeCliente;
	
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

	public ClienteModel() {
	}

	/*construtor pessoa juridica*/
	public ClienteModel(Integer codCliente, String tipoPessoa, String nomeCliente, String razaoSocial, String telefone,
			String cnpj, String cep, String logradouro, Integer numero, String cidade, String bairro, String estado,
			String email) {
		this.codCliente = codCliente;
		this.tipoPessoa = tipoPessoa;
		this.nomeCliente = nomeCliente;
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
	public ClienteModel(Integer codCliente, String tipoPessoa, String nomeCliente, String telefone, String cpf,
			String cep, String logradouro, Integer numero, String cidade, String bairro, String estado, String email) {
		this.codCliente = codCliente;
		this.tipoPessoa = tipoPessoa;
		this.nomeCliente = nomeCliente;
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

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
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
		this.logradouro = logradouro;
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
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
		result = prime * result + ((codCliente == null) ? 0 : codCliente.hashCode());
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
		ClienteModel other = (ClienteModel) obj;
		if (codCliente == null) {
			if (other.codCliente != null)
				return false;
		} else if (!codCliente.equals(other.codCliente))
			return false;
		return true;
	}
	
	
	
	
	
}
