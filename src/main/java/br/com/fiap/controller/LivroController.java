package br.com.fiap.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.model.LivroModel;
import br.com.fiap.repository.LivroRepository;

@Controller
@RequestMapping("/livro")
public class LivroController {
	
	private LivroRepository livroRepository = LivroRepository.getInstance();

	@GetMapping
	public String findAll(Model model) {
		
		List<LivroModel> listaDeLivros = livroRepository.findAll();
		
		model.addAttribute("livros", listaDeLivros);
		
		return "livros";
	}
	
	@GetMapping(value = "/{id}")
	public String findById(@PathVariable("id")long id, Model model) {
		
		LivroModel livroEncontrado = livroRepository.findById(id);
				
		model.addAttribute("livro", livroEncontrado);
		
		return "detalhe-livro";
	}
	
	@GetMapping("/form")
	public String open(@RequestParam String page,
					@RequestParam(required = false) Long id,
					Model model) {
		
		if ("editar-livro".equals(page)) {
			model.addAttribute("livro", livroRepository.findById(id));
		}
				
		return page;
	}
		
	@GetMapping("/new")
	public String form(@ModelAttribute("livroModel") LivroModel livroModel) {
		return "novo-livro";
	}
	
	@PostMapping
	public String save(@Valid LivroModel livroModel,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return "novo-livro";
		}
		
		livroRepository.save(livroModel);	
		
		redirectAttributes.addFlashAttribute("messages", "Livro cadastrado com sucesso.");		
		return "redirect:/livro";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") long id, Model model) {
				
		model.addAttribute("livro", livroRepository.findById(id));

		return "editar-livro";
	}
	
	@PutMapping("/{id}")
	public String updateLivro(@PathVariable("id") long id, 
			LivroModel livroModel, Model model,
			RedirectAttributes redirectAttributes) {
		
		livroModel.setId(id);
		livroRepository.update(livroModel);
		
		model.addAttribute("livros", livroRepository.findAll());
		redirectAttributes.addFlashAttribute("messages", "Livro editado com sucesso.");	
		
		return "redirect:/livro";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		
		livroRepository.deleteById(id);
		
		redirectAttributes.addFlashAttribute("messages", "Livro excluído com sucesso!");
		
		return "redirect:/livro";
		
	}

}