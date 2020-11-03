package br.com.maverick.Dao;

import java.util.List;

import br.com.maverick.model.ModeloModel;

public interface ModeloDaoInterface {

	ModeloModel salvarModelo(ModeloModel modeloModel);
	
	void alterar(ModeloModel modeloModel);
	
	void excluir(ModeloModel modeloModel);
	
	List<ModeloModel> getModelos();
	
	List<ModeloModel> getModelos(Integer codModelo);
	
}
