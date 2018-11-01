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
@Table(name = "fee_strategy")
@NamedQueries({
    @NamedQuery(name = "pl.lukaszmatug.transfer.api.domain.FeeStrategy.findById",
            query = "select a from FeeStrategy a"
            + " where a.id = :id"
            )
})
public class FeeStrategy {
	
	@Id
	private String id;
	
	@OneToOne(fetch=FetchType.EAGER, targetEntity=Currency.class)
	@JoinColumn(name = "id")
	private Currency currencyFrom; 
	
	@JoinColumn(name = "id")
	@OneToOne(fetch=FetchType.EAGER, targetEntity=Currency.class) 
	private Currency currencyTo; 
	
	@Column(name="fee_method")
	private String feeMethod;
	private double fee; 
	private boolean active;
}
