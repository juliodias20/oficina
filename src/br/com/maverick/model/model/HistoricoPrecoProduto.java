package br.com.maverick.model.model;

import java.sql.Timestamp;

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
@Table(name="tb_produto_historico_preco")
public class HistoricoPrecoProduto  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="item")
	Integer item;
	
	@Column(name="dhlanc")
	Timestamp dhLanc;
	
	@Column(name="vlrcompra")
	double vlrCompra;
	
	@Column(name="vlrvenda")
	double vlrVenda;
	
	@ManyToOne
	@JoinColumn(name="codproduto", referencedColumnName="codproduto", nullable=false)
	EstoqueModel estoqueModel;
	
	public HistoricoPrecoProduto() {
	}

	public HistoricoPrecoProduto(Integer item, Timestamp dhLanc, double vlrCompra, double vlrVenda,
			EstoqueModel estoqueModel) {
		this.item = item;
		this.dhLanc = dhLanc;
		this.vlrCompra = vlrCompra;
		this.vlrVenda = vlrVenda;
		this.estoqueModel = estoqueModel;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public Timestamp getDhLanc() {
		return dhLanc;
	}

	public void setDhLanc(Timestamp dhLanc) {
		this.dhLanc = dhLanc;
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

	public EstoqueModel getEstoqueModel() {
		return estoqueModel;
	}

	public void setEstoqueModel(EstoqueModel estoqueModel) {
		this.estoqueModel = estoqueModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		HistoricoPrecoProduto other = (HistoricoPrecoProduto) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HistoricoPrecoProduto [item=" + item + ", dhLanc=" + dhLanc + ", vlrCompra=" + vlrCompra + ", vlrVenda="
				+ vlrVenda + ", estoqueModel=" + estoqueModel + "]";
	}
	
	
	
}
