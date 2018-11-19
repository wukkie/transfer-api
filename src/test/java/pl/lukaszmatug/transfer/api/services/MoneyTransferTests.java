package pl.lukaszmatug.transfer.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.ClassRule;
import org.junit.Test;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import pl.lukaszmatug.transfer.api.App;
import pl.lukaszmatug.transfer.api.AppConfiguration;
import pl.lukaszmatug.transfer.api.domain.TransferSummary;

import org.junit.Assert.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoneyTransferTests {
	
    @ClassRule
    public static final DropwizardAppRule<AppConfiguration> RULE =
            new DropwizardAppRule<>(App.class, ResourceHelpers.resourceFilePath("AppConfiguration.yml"));

    
	private Response generateResponse(String from, String to, String amount, String feeStrategy) {
        Client client = RULE.client();
        return client.target(String.format("http://127.0.0.1:%d/transfer", RULE.getLocalPort()))
       		.queryParam("from", from)
       		.queryParam("to", to)
       		.queryParam("amount", amount)
       		.queryParam("feeStrategy", feeStrategy)
            .request()
            .put(Entity.entity(TransferSummary.class, MediaType.APPLICATION_JSON_TYPE));		
	}
	
    @Test
    public void AmountOfTransferedMoneyIsHigherThanBalance() {
        TransferRequestEntity tre = new TransferRequestEntity("ZERO_BALANCE_IN_GBP_FROM", "ZERO_BALANCE_IN_GBP_TO", "1000", "STANDARD_FEE");
        Response response = generateResponse(tre.getFrom(), tre.getTo(), tre.getAmount(), tre.getFeeStrategy());
        
        HashMap ts = response.readEntity(HashMap.class);        
        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(ts.get("message")).isEqualTo("Amount of money is bigger than account balance");
    }
    
    @Test
    public void standardTransferInThisSameCurrencySuccessPath() {
        TransferRequestEntity tre = new TransferRequestEntity("STANDARD_TRASFER_IN_GBP_FROM", "STANDARD_TRASFER_IN_GBP_TO", "100", "STANDARD_FEE");
        Response response = generateResponse(tre.getFrom(), tre.getTo(), tre.getAmount(), tre.getFeeStrategy());
        TransferSummary ts = response.readEntity(TransferSummary.class);
        
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(BigDecimal.valueOf(100)).isEqualByComparingTo(ts.getAmount());
        assertThat(BigDecimal.valueOf(100)).isEqualByComparingTo(ts.getAmountAfterExchange());
        assertThat(BigDecimal.valueOf(900)).isEqualByComparingTo(ts.getFrom().getBalance());
        assertThat(BigDecimal.valueOf(100)).isEqualByComparingTo(ts.getTo().getBalance());
    }

    @Test
    public void standardTransferFromGBPtoUSDSuccessPath() {
        TransferRequestEntity tre = new TransferRequestEntity("STANDARD_TRASFER_IN_GBP_TO_USD_FROM", "STANDARD_TRASFER_IN_GBP_TO_USD_TO", "100", "STANDARD_FEE");
        Response response = generateResponse(tre.getFrom(), tre.getTo(), tre.getAmount(), tre.getFeeStrategy());
        TransferSummary ts = response.readEntity(TransferSummary.class);
        
        BigDecimal amountAfterExchange = BigDecimal.valueOf(100).multiply(BigDecimal.valueOf(1.32325));
        
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(BigDecimal.valueOf(100)).isEqualByComparingTo(ts.getAmount());
        assertThat(amountAfterExchange).isEqualByComparingTo(ts.getAmountAfterExchange());
        assertThat(BigDecimal.valueOf(1000).subtract(BigDecimal.valueOf(100))).isEqualByComparingTo(ts.getFrom().getBalance());
        assertThat(amountAfterExchange).isEqualByComparingTo(ts.getTo().getBalance());
    }
    
    @Test
    public void AccountFromDontExists() {
        TransferRequestEntity tre = new TransferRequestEntity("ACCOUNT_NOT_EXIST_FROM", "STANDARD_TRASFER_IN_GBP_TO_USD_TO", "100", "STANDARD_FEE");
        Response response = generateResponse(tre.getFrom(), tre.getTo(), tre.getAmount(), tre.getFeeStrategy());      
        HashMap ts = response.readEntity(HashMap.class);        
       
        assertThat(response.getStatus()).isEqualTo(404);
        assertThat(ts.get("message")).isEqualTo("Account ACCOUNT_NOT_EXIST_FROM from you like get money for transfer was not found");
    }

    
    @Test
    public void AccountToDontExists() {
        TransferRequestEntity tre = new TransferRequestEntity("STANDARD_TRASFER_IN_GBP_TO_USD_TO", "ACCOUNT_NOT_EXIST_TO", "100", "STANDARD_FEE");
        Response response = generateResponse(tre.getFrom(), tre.getTo(), tre.getAmount(), tre.getFeeStrategy());      
        HashMap ts = response.readEntity(HashMap.class);        
        
        assertThat(response.getStatus()).isEqualTo(404);
        assertThat(ts.get("message")).isEqualTo("Account ACCOUNT_NOT_EXIST_TO to you like transfer money was not found");
    }

  
  
}
