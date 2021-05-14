package com.rest.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.spring.model.Equipo;
import com.rest.spring.service.EquipoServiceImpl;

@RestController
public class EquipoController {

	 private final Logger log = LoggerFactory.getLogger(EquipoController.class);
		
		@Autowired
	    EquipoServiceImpl service;
		
		@GetMapping("/equipo")
		public List<Equipo> getEquipo() {
			log.info("----Entrando en método listar miembros del equipo REST --------");
			return service.getEquipo();	
		}
		@PostMapping("/equipo/admin/post") 
		public Equipo addEquipo(@RequestBody Equipo equipo) {
			log.info("---- Entrando en método guardar equipo REST ------- "+equipo.getIdpersona()+" -------");
			Equipo e = service.addEquipo(equipo);
			log.info("Equipo a añadir: "+e);
			return e;
		}
		
		@GetMapping("/equipo/admin/select/{id}")
		public Equipo selectProyecto(@PathVariable Integer id) {
			log.info("---- Entrando en método seleccionar proyecto "+id+" REST -------");
			return service.getEquipoById(id);
		}
}