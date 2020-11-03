package br.com.maverick.model;

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
	Integer numOs;
	
	@Column(name="dhabertura")
	Date dhAbertura;
	
	@Column(name="dhencerramento")
	Date dhEncerramento;
	
	@Column(name="status")
	String status;
	
	@Column(name="problema",columnDefinition = "LONGTEXT")
	String problema;
	
	@Column(name="solucao", columnDefinition = "LONGTEXT")
	String solucao;
	
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
	@JoinColumn(name="codcliente", referencedColumnName="codparceiro", nullable=false)
	ParceiroModel parceiroModel;

	public OsModel() {
	}

	public OsModel(Integer numOs, Date dhAbertura, Date dhEncerramento, String status, String problema, String solucao,
			String placaCarro, float valorTotal, String tipoOs, ModeloModel modeloModel, ParceiroModel parceiroModel) {
		this.numOs = numOs;
		this.dhAbertura = dhAbertura;
		this.dhEncerramento = dhEncerramento;
		this.status = status;
		this.problema = problema;
		this.solucao = solucao;
		this.placaCarro = placaCarro;
		this.valorTotal = valorTotal;
		this.tipoOs = tipoOs;
		this.modeloModel = modeloModel;
		this.parceiroModel = parceiroModel;
	}

	public Integer getNumOs() {
		return numOs;
	}

	public void setNumOS(Integer numOs) {
		this.numOs = numOs;
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
		this.status = status.toUpperCase();
	}

	public String getProblema() {
		return problema;
	}

	public void setProblema(String problema) {
		this.problema = problema.toUpperCase();
	}

	public String getPlacaCarro() {
		return placaCarro;
	}

	public void setPlacaCarro(String placaCarro) {
		this.placaCarro = placaCarro.toUpperCase();
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
		this.tipoOs = tipoOs.toUpperCase();
	}

	public ModeloModel getModeloModel() {
		return modeloModel;
	}

	public void setModeloModel(ModeloModel modeloModel) {
		this.modeloModel = modeloModel;
	}

	public ParceiroModel getParceiroModel() {
		return parceiroModel;
	}

	public void setParceiroModel(ParceiroModel parceiroModel) {
		this.parceiroModel = parceiroModel;
	}
	

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao.toUpperCase();
	}

	public void setNumOs(Integer numOs) {
		this.numOs = numOs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numOs == null) ? 0 : numOs.hashCode());
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
		if (numOs == null) {
			if (other.numOs != null)
				return false;
		} else if (!numOs.equals(other.numOs))
			return false;
		return true;
	}
	
}
