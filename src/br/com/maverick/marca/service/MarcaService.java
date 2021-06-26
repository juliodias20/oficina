package br.com.maverick.marca.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.marca.model.MarcaModel;
import br.com.maverick.marca.repository.MarcaDao;

public class MarcaService {

	@Inject
	private MarcaDao marcaDao;
	
	@Transactional
	public MarcaModel salvarMarca(MarcaModel marcaModel) {
		return marcaDao.salvarMarca(marcaModel);
	}

	@Transactional
	public void alterar(MarcaModel marcaModel) {
		marcaDao.alterar(marcaModel);		
	}

	@Transactional
	public void excluir(MarcaModel marcaModel) {
		marcaDao.excluir(marcaModel);		
	}

	public List<MarcaModel> getMarcas() {
		return marcaDao.getMarcas();
	}

}
