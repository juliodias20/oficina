package br.com.maverick.service.model;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.Dao.model.BairroDaoInterface;
import br.com.maverick.model.model.BairroModel;


public class BairroServiceImplem implements BairroServiceInterface{

	@Inject
	private BairroDaoInterface bairroDaoInterface;

	@Override
	@Transactional
	public BairroModel salvarBairro(BairroModel bairroModel) {
		return bairroDaoInterface.salvarBairro(bairroModel);
	}

	@Override
	@Transactional
	public void alterar(BairroModel bairroModel) {
		bairroDaoInterface.alterar(bairroModel);		
	}

	@Override
	@Transactional
	public void excluir(BairroModel bairroModel) {
		bairroDaoInterface.excluir(bairroModel);		
	}

	@Override
	@Transactional
	public List<BairroModel> getBairros() {
		return bairroDaoInterface.getBairros();
	}
	
}
