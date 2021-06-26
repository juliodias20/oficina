<<<<<<< Updated upstream:src/br/com/maverick/model/model/SubOsModel.java
package br.com.maverick.model.model;
=======
package br.com.maverick.osItem.model;
>>>>>>> Stashed changes:src/br/com/maverick/osItem/model/SubOsModel.java

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.maverick.kit.model.KitModel;
import br.com.maverick.produto.model.ProdutoModel;


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
	
	@Column(name="qtd")
	Integer qtd;
	
	@Column(name="vlrunit")
	float valorUnit;
	
	@Column(name="vlrtotal")
	float vlrTotal;
	
	@OneToOne
	@JoinColumn(name="codproduto", referencedColumnName="codproduto", nullable=false)
	ProdutoModel produtoModel;
	
	@OneToOne
	@JoinColumn(name="codkit", referencedColumnName="codkit")
	KitModel kitModel;
	
	public SubOsModel() {
	}

	public SubOsModel(Integer item, Integer numOs, Integer qtd, float valorUnit, float vlrTotal,
			ProdutoModel produtoModel, KitModel kitModel) {
		super();
		Item = item;
		this.numOs = numOs;
		this.qtd = qtd;
		this.valorUnit = valorUnit;
		this.vlrTotal = vlrTotal;
		this.produtoModel = produtoModel;
		this.kitModel = kitModel;
		//this.codKit = codKit;
	}

	public Integer getNumOs() {
		return numOs;
	}

	public void setNumOs(Integer numOs) {
		this.numOs = numOs;
	}

	public ProdutoModel getProdutoModel() {
		return produtoModel;
	}

	public void setProdutoModel(ProdutoModel produtoModel) {
		this.produtoModel = produtoModel;
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
	
	/*
	public Integer getCodKit() {
		return codKit;
	}
	
	public void setCodKit(Integer codKit) {
		this.codKit = codKit;
	}
	*/

	public KitModel getKitModel() {
		return kitModel;
	}

	public void setKitModel(KitModel kitModel) {
		this.kitModel = kitModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numOs == null) ? 0 : numOs.hashCode());
		result = prime * result + ((produtoModel == null) ? 0 : produtoModel.hashCode());
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
		if (numOs == null) {
			if (other.numOs != null)
				return false;
		} else if (!numOs.equals(other.numOs))
			return false;
		if (produtoModel == null) {
			if (other.produtoModel != null)
				return false;
		} else if (!produtoModel.equals(other.produtoModel))
			return false;
		return true;
	}


	
}
