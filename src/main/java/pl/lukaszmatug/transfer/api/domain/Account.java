package pl.lukaszmatug.transfer.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "account")
@NamedQueries({

    @NamedQuery(name = "pl.lukaszmatug.transfer.api.domain.Account.findByNumber",
            query = "select a from Account a"
            + " where a.number like :number"
            )
})
public class Account {
	
	@Id
	@Column(name = "number", nullable = false, unique=true, columnDefinition="VARCHAR(50)")
	private String number;
	private String owner;
	private double balance;
	
	@OneToOne(fetch=FetchType.EAGER) 
	private Currency currency;
}
