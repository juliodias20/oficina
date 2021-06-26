package br.com.maverick.service.model;

import java.util.List;

import br.com.maverick.model.model.KitModel;

public interface KitServiceInterface {

List<KitModel> getKits();
	
	KitModel getKitById(Integer codKit);
	
}
