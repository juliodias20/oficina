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
@Table(name="tbproduto")
public class ProdutoModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codproduto")
	Integer codProduto;
	
	@Column(name="nomeproduto")
	String nomeProduto;
	
	@Column(name="porta")
	String porta;
	
	@Column(name="novo_semi")
	String tipoProduto;
	
	@Column(name="observacao")
	String observacao;
	
	@ManyToOne
	@JoinColumn(name="codmodelo",referencedColumnName="codmodelo", nullable=false)
	ModeloModel modeloModel;
	
	@OneToMany(mappedBy="produtoModel")
	private List<SubOsModel> subOsModel;
	
	@XmlTransient
	public List<SubOsModel> getOs(){
		return subOsModel;
	}
	
	
	public ProdutoModel() {
	}

	public ProdutoModel(Integer codProduto, String nomeProduto, String porta, String tipoProduto, String observacao,
			Integer qtdEstoque, double vlrPago, ModeloModel modeloModel, double vlrVenda) {
		this.codProduto = codProduto;
		this.nomeProduto = nomeProduto;
		this.porta = porta;
		this.tipoProduto = tipoProduto;
		this.observacao = observacao;
		this.modeloModel = modeloModel;
	}



	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto.toUpperCase();
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao.toUpperCase();
	}

	public ModeloModel getModeloModel() {
		return modeloModel;
	}

	public void setModeloModel(ModeloModel modeloModel) {
		this.modeloModel = modeloModel;
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
		ProdutoModel other = (ProdutoModel) obj;
		if (codProduto == null) {
			if (other.codProduto != null)
				return false;
		} else if (!codProduto.equals(other.codProduto))
			return false;
		return true;
	}
	
	
	
}
