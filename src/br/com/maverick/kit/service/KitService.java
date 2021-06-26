package br.com.maverick.kit.service;

import java.util.List;

import javax.inject.Inject;

import br.com.maverick.kit.model.KitModel;
import br.com.maverick.kit.repository.KitDao;

public class KitService {

	@Inject
	private KitDao kitDao;
	
	public List<KitModel> getKits() {
		return kitDao.getKits();
	}

	public KitModel getKitById(Integer codKit) {
		return kitDao.getKitById(codKit);
	}
}
