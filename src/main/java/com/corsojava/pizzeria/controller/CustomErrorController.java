package com.corsojava.pizzeria.controller;

import org.springframework.boot.web.servlet.error.ErrorController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

	@GetMapping
	public String handleError(HttpServletRequest request, Model model) {
		Object errCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		// ritorna a una pagina di errori
//		if (errCode != null) {
//			int code = Integer.parseInt(errCode.toString());
//			if (code == HttpStatus.NOT_FOUND.value()) {
//				return "error-404";
//			}
//		}
		model.addAttribute("errore", errCode);
		return "error";
	}
}
