
package acme.components;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.commercial.Commercial;
import acme.entities.nonCommercial.NonCommercial;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BannerRepository extends AbstractRepository {

	@Query("select count(b) from Commercial b")
	int countBannersCommercial();

	@Query("select count(b) from NonCommercial b")
	int countBannersNonCommercial();

	@Query("select b from Commercial b")
	List<Commercial> findManyBannersCommercial(PageRequest pageRequest);

	@Query("select b from NonCommercial b")
	List<NonCommercial> findManyBannersNonCommercial(PageRequest pageRequest);

	default Object findRandomBanner() {
		Object result;
		int bannerCount, bannerIndex, bannerCount2, bannerIndex2;
		ThreadLocalRandom random;
		PageRequest page, page2;
		List<Object> listBanners = new ArrayList<Object>();

		bannerCount = this.countBannersCommercial();
		bannerCount2 = this.countBannersNonCommercial();

		random = ThreadLocalRandom.current();
		bannerIndex = random.nextInt(0, bannerCount);
		bannerIndex2 = random.nextInt(0, bannerCount2);

		page = PageRequest.of(bannerIndex, 1);
		page2 = PageRequest.of(bannerIndex2, 1);

		List<Commercial> list = this.findManyBannersCommercial(page);
		List<NonCommercial> list2 = this.findManyBannersNonCommercial(page2);

		listBanners.addAll(list2);
		listBanners.addAll(list);

		result = listBanners.isEmpty() ? null : listBanners.get(0);

		return result;
	}

}
