package pl.lukaszmatug.transfer.api.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="currency")
public class Currency {
	@Id
	private String id;
	private String name;
}
