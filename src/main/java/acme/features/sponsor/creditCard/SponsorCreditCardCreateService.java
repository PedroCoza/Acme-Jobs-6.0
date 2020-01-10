
package acme.features.sponsor.creditCard;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCreditCardCreateService implements AbstractCreateService<Sponsor, CreditCard> {

	@Autowired
	SponsorCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		Boolean res = false;
		Sponsor sp = this.repository.findOneSponsorId(request.getPrincipal().getActiveRoleId());

		if (request.getPrincipal().hasRole(Sponsor.class) && sp.getCard() == null) {
			res = true;
		}

		return res;
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ownerName", "number", "deadline", "cvv");
	}

	@Override
	public CreditCard instantiate(final Request<CreditCard> request) {
		CreditCard result;
		Integer id;
		Sponsor sponsor;

		result = new CreditCard();
		id = request.getPrincipal().getActiveRoleId();
		sponsor = this.repository.findOneSponsorId(id);

		result.setSponsor(sponsor);

		return result;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Integer id;
		Sponsor sponsor;

		id = request.getPrincipal().getActiveRoleId();
		sponsor = this.repository.findOneSponsorId(id);

		if (!errors.hasErrors()) {
			errors.state(request, sponsor.getCard() == null, "ownerName", "sponsor.credit-card.form.error.exist");
		}

		String number = request.getModel().getString("number");
		String deadline = request.getModel().getString("deadline");
		String cvv = request.getModel().getString("cvv");

		//A credit card number must have between 13 and 16 digits.
		if (!errors.hasErrors("number")) {
			errors.state(request, number.length() <= 16 && number.length() >= 13, "number", "sponsor.credit-card.form.error.number", number);
		}

		/*
		 * A credit card number must start with:
		 * 4 for Visa cards
		 * 5 for Master cards
		 * 3 for American Express cards
		 * 6 for Discover cards
		 */
		if (!errors.hasErrors("number")) {
			errors.state(request, number.startsWith("4") || number.startsWith("5") || number.startsWith("3") || number.startsWith("6"), "number", "sponsor.credit-card.form.error.invalidStart", number);
		}

		/*
		 * Program for credit card number validation:
		 * The problem can be solved by using Luhn algorithm.
		 * Luhn check or the Mod 10 check, which can be described as follows (for illustration,
		 * consider the card number 4388576018402626):
		 *
		 * Step 1. Double every second digit from right to left. If doubling of a digit results in a
		 * two-digit number, add up the two digits to get a single-digit number (like for 12:1+2,
		 * 18=1+8).
		 *
		 * Step 2. Now add all single-digit numbers from Step 1.
		 * 4 + 4 + 8 + 2 + 3 + 1 + 7 + 8 = 37
		 *
		 * Step 3. Add all digits in the odd places from right to left in the card number.
		 * 6 + 6 + 0 + 8 + 0 + 7 + 8 + 3 = 38
		 *
		 * Step 4. Sum the results from Step 2 and Step 3.
		 * 37 + 38 = 75
		 *
		 * Step 5. If the result from Step 4 is divisible by 10, the card number is valid; otherwise, it is
		 * invalid.
		 */
		if (!errors.hasErrors("number")) {
			String reverse = IntStream.rangeClosed(0, number.length() - 1).boxed().map(x -> number.charAt(number.length() - 1 - x)).collect(Collectors.toList()).toString();
			reverse = reverse.replace(",", "");
			reverse = reverse.replace("[", "");
			reverse = reverse.replace("]", "");
			reverse = reverse.replace(" ", "");
			String[] numbers = reverse.split("");

			Integer i = 0;
			Integer sum1 = 0;
			for (String a : numbers) {
				Integer aux = Integer.parseInt(a);
				if (i % 2 == 1) {
					aux = aux * 2;
					String aux2 = aux.toString();
					if (aux2.length() > 1) {
						Integer i1 = Integer.parseInt(aux2.substring(0, 1));
						Integer i2 = Integer.parseInt(aux2.substring(1));
						aux = i1 + i2;
					}
					sum1 = sum1 + aux;
				}
				i++;
			}

			i = 0;
			Integer sum2 = 0;
			for (String a : numbers) {
				Integer aux = Integer.parseInt(a);
				if (i % 2 == 0) {
					sum2 = sum2 + aux;
				}
				i++;
			}

			Integer result = sum1 + sum2;
			errors.state(request, result % 10 == 0, "number", "sponsor.credit-card.form.error.numberInvalid", number);

		}

		if (!errors.hasErrors("cvv")) {
			errors.state(request, cvv.length() == 3, "cvv", "sponsor.credit-card.form.error.cvv", cvv);
		}

		if (!errors.hasErrors("deadline")) {
			errors.state(request, Pattern.matches("[0-9]{2}/[0-9]{4}", deadline), "deadline", "sponsor.credit-card.form.error.deadline", deadline);
		}

		if (!errors.hasErrors("deadline")) {
			String[] monthYear = deadline.split("/");
			Integer month = Integer.parseInt(monthYear[0]);
			errors.state(request, month >= 1 && month <= 12, "deadline", "sponsor.credit-card.form.error.month", deadline);
		}
	}

	@Override
	public void create(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;

		this.repository.save(entity);
	}

}
