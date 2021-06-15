package kosta.web.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import kosta.web.mvc.map.service.DetailedPlanService;
import kosta.web.mvc.map.service.DetailedServiceImpl;

@SpringBootTest
class ProjectRailroApplicationTests {

	@Test
	@WithMockUser(roles = "USER")
	void contextLoads() {
		DetailedPlanService service=new DetailedServiceImpl();
		service.findByStaionPlan(99);
	}

}
