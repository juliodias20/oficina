package br.com.maverick.model.model;

import java.util.Date;

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
@Table(name="tbos")
public class OsModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="numos")
	Integer numOS;
	
	@Column(name="dhabertura")
	Date dhAbertura;
	
	@Column(name="dhencerramento")
	Date dhEncerramento;
	
	@Column(name="status")
	String status;
	
	@Column(name="descricao")
	String descricao;
	
	@Column(name="placacarro")
	String placaCarro;
	
	@Column(name="valortotal")
	float valorTotal;
	
	@Column(name="tipoos")
	String tipoOs;
	
	@ManyToOne
	@JoinColumn(name="codmodelo", referencedColumnName="codmodelo", nullable=false)
	ModeloModel modeloModel;
	
	@ManyToOne
	@JoinColumn(name="codcliente", referencedColumnName="codcliente", nullable=false)
	ClienteModel clienteModel;

	public OsModel() {
	}

	public OsModel(Integer numOS, Date dhAbertura, Date dhEncerramento, String status, String descricao,
			String placaCarro, float valorTotal, String tipoOs, ModeloModel modeloModel, ClienteModel clienteModel) {
		this.numOS = numOS;
		this.dhAbertura = dhAbertura;
		this.dhEncerramento = dhEncerramento;
		this.status = status;
		this.descricao = descricao;
		this.placaCarro = placaCarro;
		this.valorTotal = valorTotal;
		this.tipoOs = tipoOs;
		this.modeloModel = modeloModel;
		this.clienteModel = clienteModel;
	}

	public Integer getNumOS() {
		return numOS;
	}

	public void setNumOS(Integer numOS) {
		this.numOS = numOS;
	}

	public Date getDhAbertura() {
		return dhAbertura;
	}

	public void setDhAbertura(Date dhAbertura) {
		this.dhAbertura = dhAbertura;
	}

	public Date getDhEncerramento() {
		return dhEncerramento;
	}

	public void setDhEncerramento(Date dhEncerramento) {
		this.dhEncerramento = dhEncerramento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPlacaCarro() {
		return placaCarro;
	}

	public void setPlacaCarro(String placaCarro) {
		this.placaCarro = placaCarro;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getTipoOs() {
		return tipoOs;
	}

	public void setTipoOs(String tipoOs) {
		this.tipoOs = tipoOs;
	}

	public ModeloModel getModeloModel() {
		return modeloModel;
	}

	public void setModeloModel(ModeloModel modeloModel) {
		this.modeloModel = modeloModel;
	}

	public ClienteModel getClienteModel() {
		return clienteModel;
	}

	public void setClienteModel(ClienteModel clienteModel) {
		this.clienteModel = clienteModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numOS == null) ? 0 : numOS.hashCode());
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
		OsModel other = (OsModel) obj;
		if (numOS == null) {
			if (other.numOS != null)
				return false;
		} else if (!numOS.equals(other.numOS))
			return false;
		return true;
	}
	
}
