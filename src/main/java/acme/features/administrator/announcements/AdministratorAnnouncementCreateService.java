
package acme.features.administrator.announcements;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcements;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorAnnouncementCreateService implements AbstractCreateService<Administrator, Announcements> {

	@Autowired
	AdministratorAnnouncementRepository repository;


	@Override
	public boolean authorise(final Request<Announcements> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Announcements> request, final Announcements entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationdate");

	}

	@Override
	public void unbind(final Request<Announcements> request, final Announcements entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "text", "addinfo");
	}

	@Override
	public Announcements instantiate(final Request<Announcements> request) {
		Announcements result;

		result = new Announcements();

		return result;
	}

	@Override
	public void validate(final Request<Announcements> request, final Announcements entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Announcements> request, final Announcements entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationdate(moment);
		this.repository.save(entity);
	}

}
