package map.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import map.dao.MapDAO;
import map.dto.MapDTO;

import map.service.MapService;
import user.dto.AuthInfo;

// http://localhost:8090/myapp/my/map

@Controller
public class drawMapController {
	private MapService mapService;
	SqlSession sqlSession;

	public drawMapController() {
		// TODO Auto-generated constructor stub
	}

	public void setMapService(MapService mapService) {
		this.mapService = mapService;
	}

	@RequestMapping(value = "/my/map")
	public String drawmap(Model model, HttpSession session) {
		System.out.println("컨트롤러 실행");
		AuthInfo authinfo = (AuthInfo) session.getAttribute("authInfo");
		String a = authinfo.getUser_id();
		System.out.println(a);
		List<MapDTO> mapdtos = mapService.getlibs(a);

		model.addAttribute("mapdtos", mapdtos);

		List<String> xy = mapService.GetCenterX(a);
		if (xy.size() != 0) {
			String x = xy.get(0);
			String y = xy.get(1);
			model.addAttribute("x", x);
			model.addAttribute("y", y);

		} else {
			model.addAttribute("x", 126.570667);
			model.addAttribute("y", 33.450701);
		}

		return "/my/map";
	}

}
