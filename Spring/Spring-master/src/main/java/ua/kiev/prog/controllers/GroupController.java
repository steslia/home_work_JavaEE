package ua.kiev.prog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kiev.prog.model.Contact;
import ua.kiev.prog.model.Group;
import ua.kiev.prog.services.ContactService;
import ua.kiev.prog.services.ContactServiceImpl;

//@Controller указывает что это взаимодействие с пользователем через наш сервис
//Она производная от Component, автоматом создает объект, этого класа и кладет его в контекст
//Такие классы можем дольше инжектить, без необходимости описывать их в конфиге
//Взаимодействие с пользователем через наш сервис
@Controller
public class GroupController {
    static final int DEFAULT_GROUP_ID = -1;

    @Autowired
    private ContactService contactService;

    @RequestMapping("/group_add_page")
    public String groupAddPage() {
        return "group_add_page";
    }

    @RequestMapping(value="/group/add", method = RequestMethod.POST)
    //  @PathVariable обозначает что туда нужно передать параметр с POST запроса с вот таким именем
    //  и он будет конвертирован в нужный нам тип
    public String groupAdd(@RequestParam String name) {
        contactService.addGroup(new Group(name));
        return "redirect:/"; // перенаправление в корень
    }

    @RequestMapping("/group/{id}")
    // @PathVariable говорит что часть URL по этому пути нужно передать
    // в этот метод(в нашем случаии id) по сути спринг врезает сюда конвертит в long
    // и переедает в метод в виде long примитива
    public String listGroup(@PathVariable(value = "id") long groupId, Model model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? contactService.findGroup(groupId) : null;

        model.addAttribute("groups", contactService.listGroups());
        model.addAttribute("contacts", contactService.listContacts(group));

        return "index";
    }
}
