package br.com.maverick.Dao.model;

import java.util.List;

import br.com.maverick.model.model.KitModel;

public interface KitDaoInterface {
	
	List<KitModel> getKits();
	
	KitModel getKitById(Integer codKit);

}
