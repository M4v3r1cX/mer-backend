package com.bsodsoftware.merbackend.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.bsodsoftware.merbackend.services.UsuarioService;
import com.bsodsoftware.merbackend.services.to.LoginDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/usuario")
public class UsuarioRest {

	@Inject
	UsuarioService usuarioService;
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(String json, @Context HttpHeaders headers) {
		Gson gson = new GsonBuilder().create();
		LoginDTO loginDto = gson.fromJson(json, LoginDTO.class);
		Response resp = null;
		
		try {
			if(usuarioService.login(loginDto.getUsuario(), loginDto.getPassword())) {
				ResponseBuilder responseBuilder = Response.status(200);
				resp = responseBuilder.build();
			}
		} catch (Exception ex) {
			ResponseBuilder responseBuilder = Response.status(500);
			resp = responseBuilder.build();
		}
		
		return resp;
	}
}
