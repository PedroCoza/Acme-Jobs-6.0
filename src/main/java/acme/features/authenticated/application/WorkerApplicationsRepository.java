
package acme.features.authenticated.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.application.Application;
import acme.entities.job.Job;
import acme.entities.roles.Worker;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerApplicationsRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select a from Application a where a.worker.id = ?1")
	Collection<Application> findManyByWorkerId(int workerid);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select w from Worker w where w.id = ?1")
	Worker findOneWorkerById(int id);

	@Query("select a from Application a where a.worker.id = ?1 and a.job.id=?2")
	Application findAppByWorkerId(int id, int jobId);
}
