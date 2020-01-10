
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.job.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerJobShowService implements AbstractShowService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		Job job;
		int jobId;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = job.finalMode() || !job.finalMode() && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "status", "title", "deadline");
		request.unbind(entity, model, "salary", "moreInfo", "description");

		model.setAttribute("descriptor-description", entity.getDescriptor().getDescription());
		model.setAttribute("descriptorId", entity.getDescriptor().getId());

		Integer duties = this.repository.findDutiesByJobId(entity.getId());

		model.setAttribute("duties", duties);

		model.setAttribute("oldstatus", entity.getStatus());

		model.setAttribute("finalMode", entity.finalMode());

		boolean isApply;
		Collection<Application> a = this.repository.findManyApplicationsByJobId(entity.getId());
		if (a.size() > 0) {
			isApply = true;
		} else {
			isApply = false;
		}
		model.setAttribute("isApply", isApply);
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

}
