package br.com.maverick.modelo.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.modelo.model.ModeloModel;
import br.com.maverick.modelo.repository.ModeloDao;

public class ModeloService {

	@Inject
	private ModeloDao modeloDao;
	
	@Transactional
	public ModeloModel salvarModelo(ModeloModel modeloModel) {
		return modeloDao.salvarModelo(modeloModel);
	}

	@Transactional
	public void alterar(ModeloModel modeloModel) {
		modeloDao.alterar(modeloModel);
	}

	@Transactional
	public void excluir(ModeloModel modeloModel) {
		modeloDao.excluir(modeloModel);
	}

	public List<ModeloModel> getModelos() {
		return modeloDao.getModelos();
	}

	public List<ModeloModel> getModelos(Integer codModelo) {
		return modeloDao.getModelos(codModelo);
	}

}
