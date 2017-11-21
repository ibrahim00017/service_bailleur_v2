package com.bootcamp.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.bootcamp.entities.Bailleur;

import com.bootcamp.service.BailleurFS;
import com.bootcamp.service.crud.BailleurCRUD;
import io.swagger.annotations.*;

@Path("/bailleurs")
@Api(value="/bailleurs",description = "Les operations sur le service bailleur")
@Produces(MediaType.APPLICATION_JSON)
public class BailleurService {
	@GET
	@ApiOperation(value="Get All the Bailleurs",notes="A list of bailleurs can be filter by their country or by the bailleur's name",
			responseContainer="List")
	public Response getAll(
			@ApiParam(value="a bailleur name that need to view",required=false) @QueryParam("nom") String nom,
			@ApiParam(value="Give a country to sort the list of bailleur by the giving country", required=false) @QueryParam("pays") String pays
	) {
		if(nom==null && pays==null) {
			return Response.status(200).entity(BailleurFS.readAll()).build();

		}else if(nom!=null && pays==null) {

			return Response.status(Status.ACCEPTED).entity(BailleurFS.findByName(nom)).build();

		}else if(nom==null && pays!=null) {
			return Response.status(200).entity(BailleurCRUD.readAll(pays)).build();
		}
		return Response.status(Status.BAD_REQUEST).entity(BailleurCRUD.readAll()).build();
	}

	@GET
	@Path("/{id}")
	@ApiOperation(value="Get one single particular bailleur",notes="A bailleur can be get by the specifique id of this bailleur",
			responseContainer="Bailleur")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Invalid Bailleur ID supplied"),
			@ApiResponse(code = 404, message = "Bailleur not found") })
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSingle(
			@ApiParam(value="The bailleur's id you need to fetch",required=true) @PathParam("id") Long id){

		return Response.status(200).entity(BailleurFS.getById(id)).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value="Create a particular Bailleur",notes="A bailleur can be created by involving it's name and his country")
	public Response create(Bailleur bailleur) {
		if(BailleurFS.create(bailleur))
			return Response.status(200).entity(bailleur).build();
		return Response.status(Status.BAD_REQUEST).entity("Please choose another name, this name already in").build();

	}

	@DELETE
	@ApiOperation(value="Delete a particular bailleur",notes="Delete a bailleur by giving it's id")
	public Response delete(@QueryParam("id") Long id) {
		if(BailleurFS.delete(id))
			return Response.status(Status.FOUND).build();
		return Response.status(Status.NOT_FOUND).build();
	}

	@PUT
	@ApiOperation(value="Update a particular bailleur",notes="To update a bailleur, give to id of the particular bailleur and the other field desired")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Bailleur bailleur) {
		if(BailleurFS.update(bailleur))
			return Response.status(Status.OK).entity("The bailleur has been updated").build();
		return Response.status(Status.BAD_REQUEST).entity("bailleur not Found").build();
	}
}
