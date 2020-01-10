
package acme.entities.descriptor;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import acme.entities.duty.Duty;
import acme.entities.job.Job;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Descriptor extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	//Atributes----------------------------------------------
	private String				description;

	@OneToMany(mappedBy = "descriptor")
	private Collection<Duty>	duty;

	//RelationShips
	@OneToOne(optional = true)
	private Job					job;

}
