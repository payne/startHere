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

		List<PersonForm> peopleList = new ArrayList<>();
		peopleList.add(personForm);
		peopleList.add(new PersonForm("Matt",54));
		peopleList.add(new PersonForm("Dustin",51));
		peopleList.add(new PersonForm("Dave", 52));
		model.addAttribute("people",  peopleList);
		return "results";
	}
	@GetMapping("/kb")
	public String kbForm() {
		return "addKB";
	}

	@PostMapping("/kb")
	public String receiveArticle(ArticleForm articleForm) {
		System.out.println(articleForm.getTitle());
		System.out.println(articleForm.getSummary());
		System.out.println(articleForm.getDescription());
		return "addKB";
	}
	
	
}
