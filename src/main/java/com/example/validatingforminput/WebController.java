package com.example.validatingforminput;

import java.util.*;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.ui.Model;

@Controller
public class WebController implements WebMvcConfigurer {
	
	private ArrayList<ArticleForm> articleList;

	public WebController() {
		this.articleList = new ArrayList<ArticleForm>();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/")
	public String showForm(PersonForm personForm) {
		return "form";
	}

	@PostMapping("/")
	public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "form";
		}
		// leave out validation for my version of this.
		List<PersonForm> peopleList = new ArrayList<>(); // don't create here, create w/constuctor and add to it here.
		peopleList.add(personForm);
		peopleList.add(new PersonForm("Matt", 54));
		peopleList.add(new PersonForm("Dustin", 51));
		peopleList.add(new PersonForm("Dave", 52));
		model.addAttribute("people", peopleList);
		return "results";
	}

	@GetMapping("/kb")
	public String kbForm(Model model) {
		ArticleForm a = new ArticleForm();
		model.addAttribute("a", a);
		return "addKB";
	}

//	@PostMapping("/kb")
//	public String receiveArticle(ArticleForm articleForm, Model model) {
//		model.addAttribute("a", articleForm);
//		return "addKB";
//	}
	
	@PostMapping("/kb")
	public String receiveArticle(ArticleForm articleForm, Model model) {
		articleList.add(articleForm);
		model.addAttribute("a", articleForm);
		return "listKB";
	}
	
	
}
