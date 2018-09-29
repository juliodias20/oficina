package br.com.maverick.Dao.model;

import java.util.List;

import br.com.maverick.model.model.EstadoModel;

public interface EstadoDaoInterface {

	EstadoModel salvarEstado(EstadoModel estadoModel);
	
	void alterar(EstadoModel estadoModel);
	
	void excluir(EstadoModel estadoModel);
	
	List<EstadoModel> getEstados();
	
}
