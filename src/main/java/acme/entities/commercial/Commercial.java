
package acme.entities.commercial;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import acme.entities.banner.Banner;
import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Commercial extends Banner {

	private static final long	serialVersionUID	= 1L;

	@Valid
	@ManyToOne(optional = true)
	private CreditCard			card;

	@Valid
	@ManyToOne(optional = true)
	private Sponsor				sponsor;

}
