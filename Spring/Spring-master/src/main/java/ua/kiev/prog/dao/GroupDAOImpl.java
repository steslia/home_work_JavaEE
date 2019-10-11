package ua.kiev.prog.dao;

import org.springframework.stereotype.Repository;
import ua.kiev.prog.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

//Реализация DAO
//@Repository она производная от Component
//указывает что это DAO, будет создан бин этого класса и добавят его в контекст
//и мы можем такой класс инжектить дальше без необходимости описывать его в конфиге
@Repository  //сомпонент всегда на класс
public class GroupDAOImpl implements GroupDAO {
    @PersistenceContext// дает эмф - все автоматически
    private EntityManager entityManager;

    @Override
    public void add(Group group) {
        entityManager.persist(group);
    }

    @Override
    public void delete(Group group) {
        entityManager.remove(group);
    }

    @Override
    public Group findOne(long id) {
        return entityManager.getReference(Group.class, id);
    }

    @Override
    public List<Group> list() {
        TypedQuery<Group> query = entityManager.createQuery("SELECT g FROM Group g", Group.class);
        return query.getResultList();
    }
}
