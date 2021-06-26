<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/KitRestFacade.java
package br.com.maverick.rest.facade.model;
=======
package br.com.maverick.kit.controller;
>>>>>>> Stashed changes:src/br/com/maverick/kit/controller/KitController.java

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/KitRestFacade.java
import br.com.maverick.model.model.KitModel;
import br.com.maverick.service.model.KitServiceInterface;
=======
import br.com.maverick.kit.model.KitModel;
import br.com.maverick.kit.service.KitService;
>>>>>>> Stashed changes:src/br/com/maverick/kit/controller/KitController.java

@Path("/kits")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class KitController {
	
	@Inject
	private KitService kitService;
	
	@GET
	public List<KitModel> getKits(){
		return kitService.getKits();
	}
	
	@GET
	@Path("/{codkit}")
	public KitModel getKitById(@PathParam("codkit") Integer codKit) {
		return kitService.getKitById(codKit);
	}
}
