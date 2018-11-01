package pl.lukaszmatug.transfer.api.resources;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.UnitOfWork;
import pl.lukaszmatug.transfer.api.dao.AccountDao;
import pl.lukaszmatug.transfer.api.domain.Account;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)

public class AccountResource {

	private AccountDao dao; 
	
    @GET
    @Timed
    @UnitOfWork
    public Account sayHello(@QueryParam("number") Optional<String> number) {
    	return dao.findByNumber(number.get());
    }
    
    public AccountResource(AccountDao dao) {
    	this.dao = dao;
    }
}
