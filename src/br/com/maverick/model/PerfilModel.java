package br.com.maverick.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement
@Entity
@Table(name="tbperfilacesso")
public class PerfilModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codperfil")
	Integer codPerfil;
	
	@Column(name="nomeperfil")
	String nomePerfil;
	
	@OneToMany(mappedBy="perfilModel")
	private List<UsuarioModel> usuarioModel;
	
	@XmlTransient
	public List<UsuarioModel> getUsuarios(){
		return usuarioModel;
	}

	public PerfilModel() {
	}

	public PerfilModel(Integer codPerfil, String nomePerfil) {
		this.codPerfil = codPerfil;
		this.nomePerfil = nomePerfil;
	}

	public Integer getCodPerfil() {
		return codPerfil;
	}

	public void setCodPerfil(Integer codPerfil) {
		this.codPerfil = codPerfil;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codPerfil == null) ? 0 : codPerfil.hashCode());
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
		PerfilModel other = (PerfilModel) obj;
		if (codPerfil == null) {
			if (other.codPerfil != null)
				return false;
		} else if (!codPerfil.equals(other.codPerfil))
			return false;
		return true;
	}
	
	
}
