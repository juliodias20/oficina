package br.com.maverick.service.model;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import br.com.maverick.Dao.model.EstadoDaoInterface;
import br.com.maverick.model.model.EstadoModel;

public class EstadoServiceImplem implements EstadoServiceInterface{

	@Inject
	private EstadoDaoInterface estadoDaoInterface;
	
	@Override
	public List<EstadoModel> getEstados() {
		return estadoDaoInterface.getEstados();
	}
	
	@Override
	@Transactional
	public EstadoModel salvarEstado(EstadoModel estadoModel) {
		return estadoDaoInterface.salvarEstado(estadoModel);
	}

	@Override
	@Transactional
	public void alterar(EstadoModel estadoModel) {
		estadoDaoInterface.alterar(estadoModel);
	}

	@Override
	@Transactional
	public void excluir(EstadoModel estadoModel) {
		estadoDaoInterface.excluir(estadoModel);
		
	}

}
