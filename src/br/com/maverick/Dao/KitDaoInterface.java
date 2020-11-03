package br.com.maverick.Dao;

import java.util.List;

import br.com.maverick.model.KitModel;

public interface KitDaoInterface {
	
	List<KitModel> getKits();
	
	KitModel getKitById(Integer codKit);

}
