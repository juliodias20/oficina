package br.com.maverick.service.model;

import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import br.com.maverick.Dao.model.OsDaoInterface;
import br.com.maverick.model.model.OsModel;

public class OsServiceImplem implements OsServiceInterface {

	@Inject
	private OsDaoInterface osDaoInterface;
	
	@Override
	@Transactional
	public OsModel salvarOs(OsModel osModel) {
		osDaoInterface.salvarOs(osModel);
		return osModel;
	}

	@Override
	@Transactional
	public void alterar(OsModel osModel) {
		osDaoInterface.alterar(osModel);
	}

	@Override
	@Transactional
	public void excluir(OsModel osModel) {
		osDaoInterface.excluir(osModel);
		
	}

	@Override
	public List<OsModel> getOs() {
		return osDaoInterface.getOs();
	}

	@Override
	public List<OsModel> getOs(Integer numOs) {
		return osDaoInterface.getOs(numOs);
	}

}
