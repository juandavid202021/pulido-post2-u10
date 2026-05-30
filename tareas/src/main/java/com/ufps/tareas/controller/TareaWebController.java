package com.ufps.tareas.controller;

import com.ufps.tareas.entity.Tarea;
import com.ufps.tareas.service.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tareas")
public class TareaWebController {

    private final TareaService service;

    public TareaWebController(TareaService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tareas", service.listarTodas());
        return "tareas";
    }

    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("tarea", new Tarea());
        return "nueva-tarea";
    }

    @PostMapping
    public String crear(@ModelAttribute Tarea tarea) {
        service.crear(tarea);
        return "redirect:/tareas";
    }
}