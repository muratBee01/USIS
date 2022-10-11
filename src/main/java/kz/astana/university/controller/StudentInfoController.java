package kz.astana.university.controller;

import jakarta.validation.Valid;
import kz.astana.university.dao.StudentDAO;
import kz.astana.university.model.StudentInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller                         //Компонент с дополнительными возможностями
@RequestMapping("/studentCase")  //????
public class StudentInfoController {
    private final StudentDAO studentDAO;

    @Autowired
    public StudentInfoController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping()
    public String commonPage(Model model){
        // Получим всех студентов из DAO и передадим на отображение в представление
        model.addAttribute("studentCase", studentDAO.commonPage());

        return "studentCase/commonPage";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        // Получим одного студента по id из DAO и передадим на отображение в представление
        model.addAttribute("studentData", studentDAO.show(id));

        return "studentCase/show";
    }

    @GetMapping("/new")
    public String newStudent(Model model){
        model.addAttribute("studentData", new StudentInformation());
        return "studentCase/new";
    }

    @PostMapping
    public String create(@ModelAttribute("studentData") @Valid StudentInformation studentInformation, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "studentCase/new";
        }
        studentDAO.save(studentInformation);
        return "redirect:/studentCase";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("studentData", studentDAO.show(id));
        return "studentCase/edit";
    }

    @PatchMapping("{/id}")
    public String update(@ModelAttribute("studentData") @Valid StudentInformation studentInformation, BindingResult bindingResult,
                         @PathVariable("id") int id){

        if(bindingResult.hasErrors()){
            return "studentCase/edit";
        }
        studentDAO.update(id, studentInformation);

        return "studentCase/edit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        studentDAO.delete(id);

        return "redirect:/studentCase";
    }
}
