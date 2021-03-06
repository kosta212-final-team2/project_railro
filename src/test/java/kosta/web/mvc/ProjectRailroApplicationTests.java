package kosta.web.mvc;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import kosta.web.mvc.board.domain.InfoBoard;
import kosta.web.mvc.board.repository.InfoBoardRepository;
import org.springframework.security.test.context.support.WithMockUser;

import kosta.web.mvc.map.service.DetailedPlanService;
import kosta.web.mvc.map.service.DetailedServiceImpl;


@SpringBootTest
@Transactional
@Commit
class ProjectRailroApplicationTests {

	@Autowired
	private InfoBoardRepository rep;
	
	@Test
	@WithMockUser(roles = "USER")
	void contextLoads() {
		DetailedPlanService service=new DetailedServiceImpl();
		service.findByStaionPlan(99);
	}
	
	@Test
	void insert() {
		for(int i=0; i<=100; i++) {
			//rep.save(new InfoBoard(null, "제목"+i, "내용"+i, "sup", null, 0, 0));
		}
	}

}
