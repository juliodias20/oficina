package br.com.maverick.service.model;

import java.util.List;

import javax.inject.Inject;

import br.com.maverick.Dao.model.KitDaoInterface;
import br.com.maverick.model.model.KitModel;

public class KitServiceImplem implements KitServiceInterface {

	@Inject
	private KitDaoInterface kitDaoInterface;
	
	@Override
	public List<KitModel> getKits() {
		return kitDaoInterface.getKits();
	}

	@Override
	public KitModel getKitById(Integer codKit) {
		return kitDaoInterface.getKitById(codKit);
	}
}
