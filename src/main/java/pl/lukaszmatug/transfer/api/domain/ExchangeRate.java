package pl.lukaszmatug.transfer.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "exchange_rate")
@NamedQueries({
    @NamedQuery(name = "pl.lukaszmatug.transfer.api.domain.ExchangeRate.findByIds",
            query = "select er from ExchangeRate er"
            + " where er.currencyFrom =:from "
            + " and  er.currencyTo =:to"
            )
})
public class ExchangeRate {

	@Id
	@Column(name="id")
	private long id;
	
	@OneToOne(fetch=FetchType.EAGER, targetEntity=Currency.class)
	@JoinColumn(name = "currency_from")
	private Currency currencyFrom; 

	@OneToOne(fetch=FetchType.EAGER, targetEntity=Currency.class)
	@JoinColumn(name = "currency_to")
	private Currency currencyTo; 
	
	private Double rate;
	
}
