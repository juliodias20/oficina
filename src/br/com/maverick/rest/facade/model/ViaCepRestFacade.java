package br.com.maverick.rest.facade.model;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.maverick.service.viaCep.ClienteViaCepWS;

@Path("/ceps")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public class ViaCepRestFacade {

	@GET
	@Path("/{cep}")
	public String getCep(@PathParam("cep") String cep){
		String viacep;
		viacep = ClienteViaCepWS.buscarCep(cep);
		return viacep;
	}
	
}
