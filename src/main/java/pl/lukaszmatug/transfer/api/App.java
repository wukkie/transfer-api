package pl.lukaszmatug.transfer.api;

import org.hibernate.SessionFactory;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import pl.lukaszmatug.transfer.api.dao.AccountDao;
import pl.lukaszmatug.transfer.api.dao.ExchangeRateDao;
import pl.lukaszmatug.transfer.api.dao.FeeStrategyDao;
import pl.lukaszmatug.transfer.api.dao.MoneyTransferDao;
import pl.lukaszmatug.transfer.api.domain.Account;
import pl.lukaszmatug.transfer.api.domain.Currency;
import pl.lukaszmatug.transfer.api.domain.ExchangeRate;
import pl.lukaszmatug.transfer.api.domain.FeeStrategy;
import pl.lukaszmatug.transfer.api.resources.AccountResource;
import pl.lukaszmatug.transfer.api.resources.TransferResource;
import pl.lukaszmatug.transfer.api.services.MoneyTransferService;
import pl.lukaszmatug.transfer.api.services.MoneyTransferValidation;

public class App extends Application<AppConfiguration> { 

	private final static HibernateBundle<AppConfiguration> hibernate = new HibernateBundle<AppConfiguration>(Account.class, Currency.class, FeeStrategy.class, ExchangeRate.class) {
		@Override
		public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
	        return configuration.getDataSourceFactory();
	    }
	};
	
    public static void main(String[] args) throws Exception {
       new App().run(args);   
    }
    
    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }
    
	@Override
	public void run(AppConfiguration configuration, Environment environment) throws Exception {
		final SessionFactory sessionFactory = hibernate.getSessionFactory(); 
	    final AccountDao accountDao = new AccountDao(sessionFactory);
	    final MoneyTransferDao transferDao = new MoneyTransferDao(sessionFactory);
	    final FeeStrategyDao feeStrategyDao = new FeeStrategyDao(sessionFactory);
	    final ExchangeRateDao exchangeRateDao = new ExchangeRateDao(sessionFactory);
	    		
	    final MoneyTransferService moneyTransferService = new MoneyTransferService(transferDao, accountDao, feeStrategyDao, exchangeRateDao, new MoneyTransferValidation());
	    
	    environment.jersey().register(new AccountResource(accountDao));	
	    environment.jersey().register(new TransferResource(moneyTransferService, sessionFactory));	
	}
}







	