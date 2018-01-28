package cn.e3mall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexCONtroller {

	@RequestMapping("/index")
	public String indexController(){
		
		System.out.println("食屎吧蛋蛋");
		
		return "index";
	}
	
}
