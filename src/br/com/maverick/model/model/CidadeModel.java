package br.com.maverick.model.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@Table(name="cidade")
public class CidadeModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codcidade")
	Integer codigo;
	
	@Column(name="nomecidade", length=255, nullable=false, unique=true)
	String nomeCidade;
	
	@ManyToOne
	@JoinColumn(name="codestado", referencedColumnName="codestado", nullable=false)
	EstadoModel estadoModel;

	@OneToMany(mappedBy="cidadeModel")
	private List<BairroModel> bairroModel;
	
	@XmlTransient
	public List<BairroModel> getBairros(){
		return bairroModel;
	}
	
	
	public CidadeModel() {
	}
	
	public CidadeModel(Integer codigo, String nomeCidade, EstadoModel estadoModel) {
		this.codigo = codigo;
		this.nomeCidade = nomeCidade;
		this.estadoModel = estadoModel;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		CidadeModel other = (CidadeModel) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade.toUpperCase();
	}

	public EstadoModel getEstadoModel() {
		return estadoModel;
	}

	public void setEstadoModel(EstadoModel estadoModel) {
		this.estadoModel = estadoModel;
	}
	
	
	
}
