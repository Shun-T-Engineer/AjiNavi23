package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Review;
import com.example.demo.form.ReviewEditForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditController {


	@PostMapping("/show-edit-form")
	public String showEditForm(@ModelAttribute ReviewEditForm form) {
		return "edit-review";
	}


	@PostMapping("/edit-review")
	public String editReview(
			@Validated @ModelAttribute ReviewEditForm form,
			BindingResult result) {

		if (result.hasErrors()) {
			return "edit-review";
		}
		
		return "confirm-edit-review";
	}

	@PostMapping("/confirm-edit-review")
	public String confirmEditReview(
			@Validated ReviewEditForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "edit-review";
		}

		Review r = new Review();
		r.setReviewId(form.getReviewId());
		r.setRestaurantId(form.getRestaurantId());
		r.setUserId(form.getUserId());
		r.setVisitDate(form.getVisitDate());
		r.setRating(form.getRating());
		r.setComment(form.getComment());
		
		System.out.println("レビュー更新");
		System.out.println(r);
		
		redirectAttributes.addFlashAttribute("msg", "レビュー更新");
		
		return "redirect:/complete";
	}

}
