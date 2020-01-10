
package acme.features.auditor.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duty.Duty;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.id = ?1")
	Duty findOneJobById(int id);

	@Query("select d from Duty d where d.descriptor.id = ?1")
	Collection<Duty> findManyByJobId(int id);

	@Query("select a from Auditor a where a.id = ?1")
	Auditor findOneAuditorById(int id);
}
