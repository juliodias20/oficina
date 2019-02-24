package br.com.maverick.model.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="tbestoque")
public class EstoqueModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codestoque")
	Integer codEstoque;
	
	@Column(name="qtdestoque")
	Integer qtdEstoque;
	
	@Column(name="vlrcompra")
	double vlrCompra;
	
	@Column(name="vlrvenda")
	double vlrVenda;
	
	@OneToOne
	@JoinColumn(name="codproduto", referencedColumnName="codproduto", nullable = false)
	ProdutoModel produtoModel;
	
	/*
	@OneToMany(mappedBy="estoqueModel")
	private List<HistoricoPrecoProduto> historicoPrecoModel;
	
	@XmlTransient
	public List<HistoricoPrecoProduto> getHistoricos(){
		return historicoPrecoModel;
	}
	*/
	
	//@ManyToOne
	//@JoinColumn(name="codproduto",referencedColumnName="codproduto", nullable=false)
	//Integer codProduto;
	
	public EstoqueModel() {
	}

	public EstoqueModel(Integer codEstoque, Integer qtdEstoque, double vlrCompra, double vlrVenda,
			List<HistoricoPrecoProduto> historicoPrecoModel) {
		this.codEstoque = codEstoque;
		this.qtdEstoque = qtdEstoque;
		this.vlrCompra = vlrCompra;
		this.vlrVenda = vlrVenda;
		//this.historicoPrecoModel = historicoPrecoModel;
	}

	public Integer getCodEstoque() {
		return codEstoque;
	}

	public void setCodEstoque(Integer codEstoque) {
		this.codEstoque = codEstoque;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public double getVlrCompra() {
		return vlrCompra;
	}

	public void setVlrCompra(double vlrCompra) {
		this.vlrCompra = vlrCompra;
	}

	public double getVlrVenda() {
		return vlrVenda;
	}

	public void setVlrVenda(double vlrVenda) {
		this.vlrVenda = vlrVenda;
	}
	
	public ProdutoModel getProdutoModel() {
		return produtoModel;
	}

	public void setProdutoModel(ProdutoModel produtoModel) {
		this.produtoModel = produtoModel;
	}

	/*
	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}
	*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codEstoque == null) ? 0 : codEstoque.hashCode());
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
		EstoqueModel other = (EstoqueModel) obj;
		if (codEstoque == null) {
			if (other.codEstoque != null)
				return false;
		} else if (!codEstoque.equals(other.codEstoque))
			return false;
		return true;
	}

}
