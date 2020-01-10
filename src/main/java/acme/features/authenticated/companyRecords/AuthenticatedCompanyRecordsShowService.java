
package acme.features.authenticated.companyRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyRecords.CompanyRecords;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedCompanyRecordsShowService implements AbstractShowService<Authenticated, CompanyRecords> {

	@Autowired
	private AuthenticatedCompanyRecordsRepository repository;


	@Override
	public boolean authorise(final Request<CompanyRecords> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<CompanyRecords> request, final CompanyRecords entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector", "CEOname", "description", "web", "phone", "email", "incorporated", "stars");

	}

	@Override
	public CompanyRecords findOne(final Request<CompanyRecords> request) {
		assert request != null;

		CompanyRecords result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
