<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/ViaCepRestFacade.java
package br.com.maverick.rest.facade.model;
=======
package br.com.maverick.viaCep;
>>>>>>> Stashed changes:src/br/com/maverick/viaCep/ViaCepController.java

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ceps")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public class ViaCepController {

	@GET
	@Path("/{cep}")
	public String getCep(@PathParam("cep") String cep){
		String viacep;
		viacep = ClienteViaCepWS.buscarCep(cep);
		return viacep;
	}
	
}
