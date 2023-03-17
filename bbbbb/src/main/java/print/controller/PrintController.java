package print.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import admin.bookmanage.dto.BookmanageDTO;
import print.dto.PageDTO;
import print.service.PrintService;

// http://localhost:8090/myapp/books/list
@Controller
public class PrintController {
	PrintService printService;
	private PageDTO pdto;

	public void setPrintService(PrintService printService) {
		this.printService = printService;
	}

	public void setPdto(PageDTO pdto) {
		this.pdto = pdto;
	}

	@RequestMapping(value = "/books/list")
	public ModelAndView printbook(PageDTO pv, ModelAndView mav) {

		int totalCount = printService.countAll();
		System.out.println(totalCount);
		if (totalCount >= 1) {
			if (pv.getCurrentPage() == 0)
				pv.setCurrentPage(1);
			this.pdto = new PageDTO(pv.getCurrentPage(), totalCount);
			mav.addObject("pv", this.pdto);

		}
		List<BookmanageDTO> printdtos = printService.printAll(this.pdto);
		System.out.println("시작" + pdto.getStartRow() + "끝" + pdto.getEndRow());
		mav.addObject("printdtos", printdtos);

		mav.setViewName("books/list");
		return mav;

	}

	@RequestMapping(value = "/books/search", method = RequestMethod.POST)
	public String searchbook(Model model, @RequestParam("search_item") String option,
			@RequestParam("query") String query) {
		System.out.println(option);
		List<BookmanageDTO> search_result = null;
		if (option.charAt(7) == 't') {
			System.out.println("타이틀");
			System.out.println(query);
			search_result = printService.searchByTitleService(query);
			System.out.println(printService.check(query));
			System.out.println(search_result.size());
			model.addAttribute("search_result", search_result);
		} else if (option.charAt(7) == 'a') {
			System.out.println("작가");
			System.out.println(query);
			search_result = printService.searchByAuthorService(query);
			System.out.println(search_result.size());
			model.addAttribute("search_result", printService.searchByAuthorService(query));

		} else {
			System.out.println("출판");
			System.out.println(query);
			search_result = printService.searchByPubService(query);
			model.addAttribute("search_result", printService.searchByPubService(query));
		}

		return "books/list";

	}

	@RequestMapping(value = "/books/searchByCategories", method = RequestMethod.POST)
	public String searchByCategories(ModelAndView mav, PageDTO pv, @RequestParam("selectedValue") String selected) {
		List<BookmanageDTO> catedtos = null;
		String cate = "";
		if (selected.charAt(0) == 'a') {
			System.out.println("전체");
			int totalCount = printService.countAll();
			System.out.println(totalCount);
			if (totalCount >= 1) {
				if (pv.getCurrentPage() == 0)
					pv.setCurrentPage(1);
				this.pdto = new PageDTO(pv.getCurrentPage(), totalCount);
				mav.addObject("pv", this.pdto);

			}
			List<BookmanageDTO> printdtos = printService.printAll(this.pdto);
			System.out.println("시작" + pdto.getStartRow() + "끝" + pdto.getEndRow());
			mav.addObject("printdtos", printdtos);
		}
		if (selected.charAt(0) == 'b') {
			System.out.println("총류");
			cate = "0";
			System.out.println(cate);
			catedtos = printService.searchByCatService(cate);
			
			mav.addObject("catedtos", catedtos);
		}
		if (selected.charAt(0) == 'c') {
			System.out.println("철학");
			cate = "1";
			System.out.println(cate);
			catedtos = printService.searchByCatService(cate);
			mav.addObject("catedtos", catedtos);
		}

		if (selected.charAt(0) == 'd') {

			System.out.println("종교");
			cate = "2";
			System.out.println(cate);
			catedtos = printService.searchByCatService(cate);
			mav.addObject("catedtos", catedtos);
		}
		if (selected.charAt(0) == 'e') {
			System.out.println("사회과학");
			cate = "3";
			System.out.println(cate);
			catedtos = printService.searchByCatService(cate);
			mav.addObject("catedtos", catedtos);
		}
		if (selected.charAt(0) == 'f') {

			System.out.println("순수과학");
			cate = "4";
			System.out.println(cate);
			catedtos = printService.searchByCatService(cate);
			mav.addObject("catedtos", catedtos);
		}
		if (selected.charAt(0) == 'g') {

			System.out.println("기술과학");
			cate = "5";
			System.out.println(cate);
			catedtos = printService.searchByCatService(cate);
			mav.addObject("catedtos", catedtos);
		}
		if (selected.charAt(0) == 'h') {

			System.out.println("예술");
			cate = "6";
			System.out.println(cate);
			catedtos = printService.searchByCatService(cate);
			mav.addObject("catedtos", catedtos);
		}
		if (selected.charAt(0) == 'i') {

			System.out.println("언어");
			cate = "7";
			System.out.println(cate);
			catedtos = printService.searchByCatService(cate);
			mav.addObject("catedtos", catedtos);
		}
		if (selected.charAt(0) == 'j') {

			System.out.println("문학");
			cate = "8";
			System.out.println(cate);
			catedtos = printService.searchByCatService(cate);
			mav.addObject("catedtos", catedtos);
		}
		if (selected.charAt(0) == 'k') {

			System.out.println("역사");
			cate = "9";
			System.out.println(cate);
			catedtos = printService.searchByCatService(cate);
			
			mav.addObject("catedtos", catedtos);
		}

		return "books/list";
	}
}
