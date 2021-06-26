package br.com.maverick.Dao.model;

import java.util.List;

import br.com.maverick.model.model.MarcaModel;

public interface MarcaDaoInterface {
	
	MarcaModel salvarMarca(MarcaModel marcaModel);
	
	void alterar(MarcaModel marcaModel);
	
	void excluir(MarcaModel marcaModel);
	
	List<MarcaModel> getMarcas();
	

}
