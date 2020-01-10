
package acme.features.administrator.creditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCard.CreditCard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorCreditCardShowService implements AbstractShowService<Administrator, CreditCard> {

	@Autowired
	AdministratorCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		CreditCard card;
		Integer cardId;
		Integer principalId;

		principalId = request.getPrincipal().getActiveRoleId();
		cardId = Integer.parseInt(request.getServletRequest().getParameter("id"));
		card = this.repository.findOneCardById(cardId);

		if (card.getSponsor().getId() == principalId) {
			return false;
		}

		return true;
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ownerName", "number", "number", "deadline", "cvv");
	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result;
		String id;

		id = request.getServletRequest().getParameter("id");
		result = this.repository.findOneCardById(Integer.parseInt(id));

		return result;
	}

}
