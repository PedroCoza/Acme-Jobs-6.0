
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.job.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationsCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	WorkerApplicationsRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		boolean res = false;
		assert request != null;
		Job job;

		String jobid = request.getServletRequest().getParameter("id");

		Application a = this.repository.findAppByWorkerId(request.getPrincipal().getActiveRoleId(), Integer.parseInt(jobid));

		job = this.repository.findOneJobById(Integer.parseInt(jobid));
		if (job.getStatus().equals("PUBLISHED") && a == null) {
			res = true;
		}

		return res;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ref", "status", "statement", "skill", "qualification");

		model.setAttribute("id", request.getServletRequest().getParameter("id"));
		Integer workerId = request.getPrincipal().getActiveRoleId();
		Worker w = this.repository.findOneWorkerById(workerId);
		Job j = this.repository.findOneJobById(entity.getJob().getId());
		model.setAttribute("skill", w.getSkills());
		model.setAttribute("qualification", w.getQualifications());
		model.setAttribute("ref", j.getReference() + ":");
	}

	@Override
	public Application instantiate(final Request<Application> request) {

		Application result = new Application();
		Worker worker;
		int workerid;

		workerid = request.getPrincipal().getActiveRoleId();
		worker = this.repository.findOneWorkerById(workerid);
		result.setWorker(worker);

		Job job;

		String jobid = request.getServletRequest().getParameter("id");

		job = this.repository.findOneJobById(Integer.parseInt(jobid));
		result.setJob(job);

		result.setStatus("PENDING");

		Date moment;
		Date updatedStatusMoment;

		updatedStatusMoment = new Date(System.currentTimeMillis() - 1);
		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);
		result.setUpdatedStatusMoment(updatedStatusMoment);

		result.setJustification("");

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isUnique = this.repository.findAppByRef(request.getModel().getString("ref")) == null;
		errors.state(request, isUnique, "ref", "acme.error.uniqueref");

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		Job job;

		String jobid = request.getServletRequest().getParameter("id");

		job = this.repository.findOneJobById(Integer.parseInt(jobid));

		entity.setJob(job);

		this.repository.save(entity);

	}

}
