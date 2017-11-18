package com.bootcamp.resources;

import java.sql.SQLException;
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
import com.bootcamp.repository.BailleurRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/bailleurs")
@Api(value="bailleurs")
public class BailleurService {
	private BailleurRepository bailleurRepository = new BailleurRepository("Tpjpa");
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="Get All the Bailleurs",notes="A list of bailleurs can be filter by their country or by the bailleur's name",
	responseContainer="List")
	public Response getAll(
			@ApiParam(value="a bailleur name that need to view",required=false) @QueryParam("nom") String nom,
			@ApiParam(value="Give a country to sort the list of bailleur by the giving country", required=false) @QueryParam("pays") String pays
			) {
		if(nom==null && pays==null) {
		try {
				return Response.status(200).entity(bailleurRepository.findAll()).build();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(404).entity("OCNTENT NOT FOUND").build();
			}
		
		}else if(nom!=null && pays==null) {
			try {
				return Response.status(Status.ACCEPTED).entity(bailleurRepository.findByName(nom)).build();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(Status.BAD_REQUEST).build();
			}
		}else if(nom==null && pays!=null) {
			try {
				return Response.status(Status.ACCEPTED).entity(bailleurRepository.findByPays(pays)).build();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					return Response.status(Status.BAD_REQUEST).entity(bailleurRepository.findAll()).build();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return Response.status(Status.BAD_REQUEST).entity("votre requete n'est pqs prise en compte").build();
	}
	
	@GET
	@Path("/{id}")
	@ApiOperation(value="Get one single realy bailleur",notes="A bailleur can be get by the specifique id of this bailleur",
	responseContainer="Bailleur")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSingle(
			@ApiParam(value="The bailleur's id you need to view",required=false) @PathParam("id") Long id){
		try {
			return Response.status(200).entity(bailleurRepository.findById(id)).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		}
			
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="Create a particular Bailleur",notes="A bailleur can be created by involving it's name and his country")
	public Response create(Bailleur bailleur) {
		try {
			bailleurRepository.create(bailleur);
			return Response.status(Status.CREATED).entity(bailleur).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
		
	}
	
	@DELETE
	@ApiOperation(value="Delete a particular bailleur",notes="Delete a bailleur by giving it's id")
	public Response delete(Long id) {
		try {
			Bailleur bailleur = bailleurRepository.findById(id);
			bailleurRepository.delete(bailleur);
			return Response.status(Status.GONE).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	@ApiOperation(value="Update a particular bailleur",notes="To update a bailleur, give to id of the particular bailleur and the other field desired")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Bailleur bailleur) {
		try {
			bailleurRepository.update(bailleur);
			return Response.status(Status.GONE).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
}
