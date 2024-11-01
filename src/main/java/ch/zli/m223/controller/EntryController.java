package ch.zli.m223.controller;

import java.util.List;

import javax.inject.Inject;
import javax.resource.NotSupportedException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.dto.DtoEntity;
import ch.zli.m223.model.Entry;
import ch.zli.m223.service.EntryService;

@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
public class EntryController {

    @Inject
    EntryService entryService;

    @Inject
    DtoEntity dtoEntity;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Entries.", description = "Returns a list of all entries.")
    public List<Entry> index() {
        return entryService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new entry.", description = "Creates a new entry and returns the newly added entry.")
    public Entry create(Entry entry) {
       return entryService.createEntry(entry);
    }

    @Path("/{id}")
    @DELETE
    @Operation(summary = "delete an entry.", description = "delete an entry by its id")
    public void delete(@PathParam("id") Long id) {
        entryService.deleteEntry(id);
    }

    @Path("/{id}")
    @PUT
    @Operation(
        summary = "updat a entry",
        description = "Updates an entry by its id"
    )
    public void updateEntry(@PathParam("id") Long id ,Entry entry){
        try{
            entryService.updateEntry(id, entry);
                
        }catch(NotSupportedException e ){
            
            
        }
    }

}
