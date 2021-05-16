package com.mvc.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.spring.model.Cliente;
import com.mvc.spring.model.Proyecto;
import com.mvc.spring.service.ClientesServiceImpl;
import com.mvc.spring.service.ProyectosServiceImpl;
/**
 * <p><b> Nombre </b> ProyectoController</p>
 * 
 * <p><strong>Descripcion </strong> controlador del proyecto MVC que recibe json</p>
 * 
 * @author	Toni Blanche
 * 
 * @version	v1
 * 
 * @since	13/05/2021
 */

@Controller
public class ProyectoController {

	@Autowired
	ProyectosServiceImpl serviceProyecto;
	
	@Autowired
	ClientesServiceImpl serviceCliente;

	@GetMapping("proyectos")
	public String listaProyectos(Model m) {
		m.addAttribute("proyectoslista", serviceProyecto.getProyectos());
		m.addAttribute("listaClientes", serviceCliente.getClientes());
		m.addAttribute("cliente", new Cliente());
		return "proyectos";
	}

	@GetMapping("proyectos/admin/list")
	public String listaProyectosAdmin(Model m) {
		List<Proyecto> proyectos = new ArrayList<>();
		proyectos.addAll(serviceProyecto.getProyectos());
		m.addAttribute("proyectoslista", proyectos);
		return "admin/proyectosadmin";
	}
	
	@GetMapping("proyectos/admin/add")
	public ModelAndView newProyecto() {
		ModelAndView m = new ModelAndView("/admin/addProyecto");
		m.addObject("proyecto", new Proyecto());
		m.addObject("listaClientes", serviceCliente.getClientes());
		return m;
	}

	@PostMapping("proyectos/admin/post")
	public ModelAndView addProyecto(@ModelAttribute Proyecto proyecto) {
		System.out.println("IMPRIMIENDO PROYECTO-------" + proyecto);
		serviceProyecto.addProyectos(proyecto);
		return new ModelAndView("redirect:/proyectos/admin/list");
	}
	
	@GetMapping("proyectos/admin/select")
	public ModelAndView selectProyecto(@RequestParam Integer id) {
		System.out.println("--------------------------------------ProyectoControllerMVC" + id);
		ModelAndView m = new ModelAndView("/admin/updateProyecto");
		m.addObject("proyecto", serviceProyecto.selectProyecto(id));
		m.addObject("listaClientes", serviceCliente.getClientes());
		return m;
	}
	
	@GetMapping("proyectos/detalle")
	public ModelAndView selectProyectoDetalle(@RequestParam Integer id) {
		System.out.println("--------------------------------------ProyectoControllerMVC" + id);
		ModelAndView m = new ModelAndView("/proyectodetalle");
		m.addObject("proyecto", serviceProyecto.selectProyecto(id));
		m.addObject("listaClientes", serviceCliente.getClientes());
		return m;
	}
	
	@GetMapping("proyectos/empresa")
	public String selectProyectoEmpresa(@RequestParam Integer idcliente, Model m) {
		List<Proyecto> todosproyectos = new ArrayList<>();
		List<Proyecto> proyectos = new ArrayList<>();
		todosproyectos.addAll(serviceProyecto.getProyectos());
		for (Proyecto p: todosproyectos) {
			if (idcliente == 0){
				proyectos = todosproyectos;
			}
			else if (p.getCliente().getIdcliente() == idcliente) {
				proyectos.add(p);
			}
		}
		m.addAttribute("proyectoslista", proyectos);
		m.addAttribute("listaClientes", serviceCliente.getClientes());
		m.addAttribute("cliente", new Cliente());
		return "proyectos";
	}
	
	@PutMapping("proyectos/admin/update")
	public ModelAndView updateProyecto(@RequestParam Proyecto proyecto) {
		System.out.println("IMPRIMIENDO PROYECTO-------" + proyecto);
		serviceProyecto.updateProyectos(proyecto);
		return new ModelAndView("redirect:/proyectos/admin/list");
	}
}
