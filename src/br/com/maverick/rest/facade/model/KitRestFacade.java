package br.com.maverick.rest.facade.model;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.maverick.model.model.KitModel;
import br.com.maverick.service.model.KitServiceInterface;

@Path("/kits")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class KitRestFacade {
	
	@Inject
	private KitServiceInterface kitServiceInterface;
	
	@GET
	public List<KitModel> getKits(){
		return kitServiceInterface.getKits();
	}
	
	@GET
	@Path("/{codkit}")
	public KitModel getKitById(@PathParam("codkit") Integer codKit) {
		return kitServiceInterface.getKitById(codKit);
	}
}
