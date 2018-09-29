package br.com.maverick.model.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="bairro")
public class BairroModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codbairro")
	Integer codBairro;
	
	@Column(name="nomebairro", length=255, nullable=false, unique=true)
	String nomeBairro;
	
	@ManyToOne
	@JoinColumn(name="codcidade", referencedColumnName="codcidade", nullable=false)
	CidadeModel cidadeModel;

	public BairroModel() {
	}

	public BairroModel(Integer codBairro, String nomeBairro, CidadeModel cidadeModel) {
		this.codBairro = codBairro;
		this.nomeBairro = nomeBairro;
		this.cidadeModel = cidadeModel;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidadeModel == null) ? 0 : cidadeModel.hashCode());
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
		BairroModel other = (BairroModel) obj;
		if (cidadeModel == null) {
			if (other.cidadeModel != null)
				return false;
		} else if (!cidadeModel.equals(other.cidadeModel))
			return false;
		return true;
	}

	public Integer getCodBairro() {
		return codBairro;
	}

	public void setCodBairro(Integer codBairro) {
		this.codBairro = codBairro;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro.toUpperCase();
	}

	public CidadeModel getCidadeModel() {
		return cidadeModel;
	}

	public void setCidadeModel(CidadeModel cidadeModel) {
		this.cidadeModel = cidadeModel;
	}
	
}
