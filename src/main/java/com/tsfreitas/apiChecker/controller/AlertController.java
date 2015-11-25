package com.tsfreitas.apiChecker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AlertController {

	// -----
	// Página de avisa

	// Se aviso de versão estiver ativo, ativa aviso visual
	// Se aviso de tag estiver ativo, ativa aviso visual

	// Quando usuário clicar nos avisos, desligar flag no arquivo.

	@RequestMapping(value = "/")
	public String alertHome(Model model) {

		model.addAttribute("name", "Thiiii");
		
		model.addAttribute("api_tag","v1238");
		model.addAttribute("lib_tag","2.0.9");
		model.addAttribute("api_alert","true");
		model.addAttribute("lib_alert","true");

		return "index";
	}

}
