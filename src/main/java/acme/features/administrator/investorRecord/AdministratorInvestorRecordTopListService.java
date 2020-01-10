
package acme.features.administrator.investorRecord;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investorRecord.InvestorRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorInvestorRecordTopListService implements AbstractListService<Administrator, InvestorRecord> {

	//Internal State ---------------------------------------
	@Autowired
	AdministratorInvestorRecordRepository repository;


	@Override
	public boolean authorise(final Request<InvestorRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<InvestorRecord> request, final InvestorRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector", "statement", "stars");
	}

	@Override
	public Collection<InvestorRecord> findMany(final Request<InvestorRecord> request) {
		assert request != null;

		Collection<InvestorRecord> result;
		Collection<InvestorRecord> res = new ArrayList<>();
		result = this.repository.findManyAll();
		for (InvestorRecord e : result) {
			if (e.getStars() == 5) {
				res.add(e);
			}
		}

		return res;
	}

}
