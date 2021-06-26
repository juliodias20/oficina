<<<<<<< Updated upstream:src/br/com/maverick/model/model/MarcaModel.java
package br.com.maverick.model.model;
=======
package br.com.maverick.marca.model;
>>>>>>> Stashed changes:src/br/com/maverick/marca/model/MarcaModel.java

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

import br.com.maverick.modelo.model.ModeloModel;

@XmlRootElement
@Entity
@Table(name="tbmarca")
public class MarcaModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codmarca")
	Integer codMarca;
	
	@Column(name="nomemarca")
	String nomeMarca;

	@OneToMany(mappedBy="marcaModel")
	private List<ModeloModel> modeloModel;
	
	@XmlTransient
	public List<ModeloModel> getModelos(){
		return modeloModel;
	}

	public MarcaModel() {
	}

	public MarcaModel(Integer codMarca, String nomeMarca) {
		this.codMarca = codMarca;
		this.nomeMarca = nomeMarca;
	}

	public Integer getCodMarca() {
		return codMarca;
	}

	public void setCodMarca(Integer codMarca) {
		this.codMarca = codMarca;
	}

	public String getNomeMarca() {
		return nomeMarca;
	}

	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca.toUpperCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codMarca == null) ? 0 : codMarca.hashCode());
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
		MarcaModel other = (MarcaModel) obj;
		if (codMarca == null) {
			if (other.codMarca != null)
				return false;
		} else if (!codMarca.equals(other.codMarca))
			return false;
		return true;
	}
	
}
