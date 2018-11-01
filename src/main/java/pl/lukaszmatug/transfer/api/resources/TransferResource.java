package pl.lukaszmatug.transfer.api.resources;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;

import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.UnitOfWork;
import pl.lukaszmatug.transfer.api.exception.BusinessLogicException;
import pl.lukaszmatug.transfer.api.services.MoneyTransferService;

@Path("/transfer")
@Produces(MediaType.APPLICATION_JSON)

public class TransferResource implements ITransferResource {

	private MoneyTransferService service; 
	private SessionFactory sessionFactory; 
    
	public TransferResource(MoneyTransferService service, SessionFactory sessionFactory) {
    	this.service = service;
    	this.sessionFactory = sessionFactory;
    }
    
	@Override
    @GET
    @Timed
    @UnitOfWork
    @Path("/forecast")
	public Response getForecast(@QueryParam("from") Optional<String> from, 
			@QueryParam("to") Optional<String> to,
			@QueryParam("amount") Optional<Double> amount, 
			@QueryParam("feeStrategy") Optional<String> feeStrategy) {
		try {
			sessionFactory.getCurrentSession().setHibernateFlushMode(FlushMode.MANUAL);
			return Response.ok().entity(service.transfer(from, to, amount, feeStrategy)).build();
		} catch(BusinessLogicException be) {
			return Response.status(be.getResponseStatus().getCode()).entity(be.getResponseStatus()).build();
		} finally {
			sessionFactory.getCurrentSession().clear();
			sessionFactory.getCurrentSession().setHibernateFlushMode(FlushMode.AUTO);
		}
	}

	@Override
    @PUT
    @Timed
    @UnitOfWork
	public Response transfer(
			@QueryParam("from") Optional<String> from, 
			@QueryParam("to") Optional<String> to,
			@QueryParam("amount") Optional<Double> amount, 
			@QueryParam("feeStrategy") Optional<String> feeStrategy) {
		try {
			return Response.ok().entity(service.transfer(from, to, amount, feeStrategy)).build();
		} catch(BusinessLogicException be) {
			return Response.status(be.getResponseStatus().getCode()).entity(be.getResponseStatus()).build();
		}
	}

	//Only for test purposes 
    @GET
    @Timed
    @UnitOfWork
	public Response transferGetBrowserTest(
			@QueryParam("from") Optional<String> from, 
			@QueryParam("to") Optional<String> to,
			@QueryParam("amount") Optional<Double> amount, 
			@QueryParam("feeStrategy") Optional<String> feeStrategy) {
		try {
			return Response.ok().entity(service.transfer(from, to, amount, feeStrategy)).build();
		} catch(BusinessLogicException be) {
			return Response.status(be.getResponseStatus().getCode()).entity(be.getResponseStatus()).build();
		}
	}

}
