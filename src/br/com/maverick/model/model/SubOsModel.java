package br.com.maverick.model.model;

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
@Table(name="tbsubos")
public class SubOsModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="item")
	Integer Item;
	
	@Column(name="numos", nullable=false)
	Integer numOs;

	@Column(name="codproduto",nullable=false)
	Integer codProduto;
	
	@Column(name="qtd")
	Integer qtd;
	
	@Column(name="vlrunit")
	float valorUnit;
	
	@Column(name="vlrtotal")
	float vlrTotal;
	
	@Column(name="produto")
	String produto;
	
	/*
	@OneToMany(mappedBy="subOsModel")
	private List<ProdutoModel> produtoModel;
	
	@XmlTransient
	public List<ProdutoModel> getProdutos(){
		return produtoModel;
	}
	*/

	public SubOsModel() {
	}

	public SubOsModel(Integer numOs, Integer codProduto, Integer qtd, float valorUnit, float vlrTotal) {
		this.numOs = numOs;
		this.codProduto = codProduto;
		this.qtd = qtd;
		this.valorUnit = valorUnit;
		this.vlrTotal = vlrTotal;
	}

	public Integer getNumOs() {
		return numOs;
	}

	public void setNumOs(Integer numOs) {
		this.numOs = numOs;
	}

	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public float getValorUnit() {
		return valorUnit;
	}

	public void setValorUnit(float valorUnit) {
		this.valorUnit = valorUnit;
	}

	public float getVlrTotal() {
		return vlrTotal;
	}

	public void setVlrTotal(float vlrTotal) {
		this.vlrTotal = vlrTotal;
	}
	
	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codProduto == null) ? 0 : codProduto.hashCode());
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
		SubOsModel other = (SubOsModel) obj;
		if (codProduto == null) {
			if (other.codProduto != null)
				return false;
		} else if (!codProduto.equals(other.codProduto))
			return false;
		return true;
	}
	
}
