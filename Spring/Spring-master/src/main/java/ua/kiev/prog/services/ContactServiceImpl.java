package ua.kiev.prog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.model.Contact;
import ua.kiev.prog.model.Group;
import ua.kiev.prog.dao.ContactDAO;
import ua.kiev.prog.dao.GroupDAO;

import java.util.List;

//@Service указывает что это сервис, будет создан бин этого класса и добавят его в контекст
//На уровне сервиса управляем транзациями, можно несолько DAO засовывать в одну транзакцию
//Методы сервиса написаны через DAO методы
@Service
public class ContactServiceImpl implements ContactService {
    //Инжектим наш DAO
    @Autowired
    private ContactDAO contactDAO;
    //Инжектим наш DAO
    @Autowired
    private GroupDAO groupDAO;

    @Transactional
    public void addContact(Contact contact) {
        contactDAO.add(contact);
    }

    @Transactional
    public void addGroup(Group group) {
        groupDAO.add(group);
    }

    //Транзакция указывает, что этот метод будет транзакцией, нам теперь не нужно ею управлять
    //При возникновении исключения будет ролбэк, можем указать при каких исключения делать ролбэк
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteContact(long[] ids) {
        contactDAO.delete(ids);
    }

    @Transactional(readOnly=true)
    //readOnly=true указывает что розрешено ток чтение, этот метод не сможет изменить БД
    public List<Group> listGroups() {
        return groupDAO.list();
    }

    @Transactional(readOnly=true)
    public List<Contact> listContacts(Group group, int start, int count) {
        return contactDAO.list(group, start, count);
    }

    @Transactional(readOnly=true)
    public List<Contact> listContacts(Group group) {
        return contactDAO.list(group, 0, 0);
    }

    @Transactional(readOnly = true)
    public long count() {
        return contactDAO.count();
    }

    @Transactional(readOnly=true)
    public Group findGroup(long id) {
        return groupDAO.findOne(id);
    }

    @Transactional(readOnly=true)
    public List<Contact> searchContacts(String pattern) {
        return contactDAO.list(pattern);
    }
}
