
package acme.features.employer.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.job.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		int principalId = request.getPrincipal().getActiveRoleId();

		int jobId = request.getModel().getInteger("id");
		Job j = this.repository.findOneJobById(jobId);

		boolean result;
		result = j.getEmployer().getId() == principalId;
		return result;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "status", "title", "deadline", "salary", "description", "moreInfo");

	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);
		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		errors.state(request, this.repository.findManyApplicationsByJobId(entity.getId()).isEmpty(), "reference", "acme.validation.yesapps");

	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		Integer descriptorId = entity.getDescriptor().getId();

		this.repository.deleteDuties(descriptorId);
		this.repository.deleteDescriptor(descriptorId);
		this.repository.deleteAudit(entity.getId());
		this.repository.delete(entity);

	}

}
