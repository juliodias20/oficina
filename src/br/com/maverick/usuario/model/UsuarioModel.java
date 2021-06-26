<<<<<<< Updated upstream:src/br/com/maverick/model/model/UsuarioModel.java
package br.com.maverick.model.model;
=======
package br.com.maverick.usuario.model;
>>>>>>> Stashed changes:src/br/com/maverick/usuario/model/UsuarioModel.java

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.maverick.perfil.model.PerfilModel;


@XmlRootElement
@Entity
@Table(name="tbusuario")
public class UsuarioModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codusuario")
	Integer codUsuario;
	
	@Column(name="login", unique=true)
	String login;
	
	@Column(name="senha")
	String senha;
	
	@Column(name="dtcriacao")
	Timestamp dtCriacao;
	
	@Column(name="dtdesativacao")
	Timestamp dtDesativacao;
	
	@Column(name="ativo")
	String ativo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="codperfil", referencedColumnName="codperfil", nullable=false)
	PerfilModel perfilModel;
	
	
	public UsuarioModel() {
	}

	public UsuarioModel(Integer codUsuario, String login, String senha, Timestamp dtCriacao, Timestamp dtDesativacao,
			String ativo) {
		this.codUsuario = codUsuario;
		this.login = login;
		this.senha = senha;
		this.dtCriacao = dtCriacao;
		this.dtDesativacao = dtDesativacao;
		this.ativo = ativo;
	}

	public Integer getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Integer codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Timestamp dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtDesativacao() {
		return dtDesativacao;
	}

	public void setDtDesativacao(Timestamp dtDesativacao) {
		this.dtDesativacao = dtDesativacao;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public PerfilModel getPerfilModel() {
		return perfilModel;
	}

	public void setPerfilModel(PerfilModel perfilModel) {
		this.perfilModel = perfilModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codUsuario == null) ? 0 : codUsuario.hashCode());
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
		UsuarioModel other = (UsuarioModel) obj;
		if (codUsuario == null) {
			if (other.codUsuario != null)
				return false;
		} else if (!codUsuario.equals(other.codUsuario))
			return false;
		return true;
	}
	
}
