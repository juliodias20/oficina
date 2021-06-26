package br.com.maverick.osItem.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.osItem.model.SubOsModel;
import br.com.maverick.osItem.repository.SubOsDao;

public class SubOsService {

	@Inject
	private SubOsDao subOsDao;
	
	@Transactional
	public SubOsModel salvarSubOs(SubOsModel subOsModel) {
		subOsDao.salvarSubOs(subOsModel);
		return subOsModel;
	}

	@Transactional
	public void alterar(SubOsModel subOsModel) {
		subOsDao.alterar(subOsModel);
	}

	@Transactional
	public void excluir(Integer numOs, Integer codProduto) {
		subOsDao.excluir(numOs, codProduto);
	}

	public List<SubOsModel> getSubOs() {
		return subOsDao.getSubOs();
	}

	public List<SubOsModel> getSubOs(Integer numOs){
		return subOsDao.getSubOs(numOs);
	}
	
	public List<SubOsModel> getSubOs(Integer numOs, Integer codProduto) {
		return subOsDao.getSubOs(numOs, codProduto);
	}

}
