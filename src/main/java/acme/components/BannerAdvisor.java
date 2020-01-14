
package acme.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import acme.entities.commercial.Commercial;

@ControllerAdvice
public class BannerAdvisor {

	@Autowired
	private BannerRepository repository;


	@ModelAttribute("banner1")
	public Object getBanner() {
		Object result;

		result = this.repository.findRandomBanner();

		this.getType(result);

		return result;
	}
	//true-->commercial
	@ModelAttribute("type")
	public Boolean getType(final Object c) {
		Boolean res = true;

		if (!c.getClass().equals(Commercial.class)) {
			res = false;
		}

		return res;
	}

}
