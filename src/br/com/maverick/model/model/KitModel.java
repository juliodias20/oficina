package br.com.maverick.model.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="tb_kit_produto")
public class KitModel {

	@Id
	@Column(name="codkit")
	private Integer codKit;
	
	@Column(name="nomekit")
	private String nomeKit;

	public KitModel() {
	}

	public KitModel(Integer codKit, String nomeKit) {
		this.codKit = codKit;
		this.nomeKit = nomeKit;
	}

	public Integer getCodKit() {
		return codKit;
	}

	public void setCodKit(Integer codKit) {
		this.codKit = codKit;
	}

	public String getNomeKit() {
		return nomeKit;
	}

	public void setNomeKit(String nomeKit) {
		this.nomeKit = nomeKit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codKit == null) ? 0 : codKit.hashCode());
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
		KitModel other = (KitModel) obj;
		if (codKit == null) {
			if (other.codKit != null)
				return false;
		} else if (!codKit.equals(other.codKit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KitModel [codKit=" + codKit + ", nomeKit=" + nomeKit + "]";
	}
	
	
	
}
