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
@Table(name="tbmodelo")
public class ModeloModel {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codmodelo")
	Integer codModelo;
	
	@Column(name="nomemodelo", nullable=false)
	String nomeModelo;
	
	@Column(name="qtdportas", nullable=false)
	Integer qtdPortas;
	
	@Column(name="ano", nullable=false)
	Integer ano;
	
	@ManyToOne
	@JoinColumn(name="codmarca", referencedColumnName="codmarca", nullable=false)
	MarcaModel marcaModel;

	@OneToMany(mappedBy="modeloModel")
	private List<ProdutoModel> produtoModel;
		
	@XmlTransient
	public List<ProdutoModel> getProdutos(){
		return produtoModel;
	}
	
	public ModeloModel() {
	}

	

	public ModeloModel(Integer codModelo, String nomeModelo, Integer qtdPortas, MarcaModel marcaModel, Integer ano) {
		this.codModelo = codModelo;
		this.nomeModelo = nomeModelo;
		this.qtdPortas = qtdPortas;
		this.marcaModel = marcaModel;
		this.ano = ano;
	}

	public Integer getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(Integer codModelo) {
		this.codModelo = codModelo;
	}

	public String getNomeModelo() {
		return nomeModelo;
	}

	public void setNomeModelo(String nomeModelo) {
		this.nomeModelo = nomeModelo.toUpperCase();
	}

	public Integer getQtdPortas() {
		return qtdPortas;
	}

	public void setQtdPortas(Integer qtdPortas) {
		this.qtdPortas = qtdPortas;
	}

	public MarcaModel getMarcaModel() {
		return marcaModel;
	}

	public void setMarcaModel(MarcaModel marcaModel) {
		this.marcaModel = marcaModel;
	}
	
	public Integer getAno() {
		return ano;
	}
	
	public void setAno(Integer ano) {
		this.ano = ano;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codModelo == null) ? 0 : codModelo.hashCode());
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
		ModeloModel other = (ModeloModel) obj;
		if (codModelo == null) {
			if (other.codModelo != null)
				return false;
		} else if (!codModelo.equals(other.codModelo))
			return false;
		return true;
	}
	
}
