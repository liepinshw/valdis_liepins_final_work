package lv.lu.finalwork2.repository;

import lv.lu.finalwork2.domain.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class ProductOrmRepository implements lv.lu.finalwork2.repository.Repository<Product> {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductOrmRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long save(Product productEntity) {
        return (Long) this.sessionFactory.getCurrentSession().save(productEntity);
    }

    @Override
    public List<Product> findAll() {
//        return sessionFactory.getCurrentSession().createCriteria(Product.class).list();
        //Criteria API
//        Session session = sessionFactory.getCurrentSession();
//        CriteriaQuery<Product> criteriaQuery = session.getCriteriaBuilder().createQuery(Product.class);
//        criteriaQuery.from(Product.class);
//        return session.createQuery(criteriaQuery).getResultList();
        //JPQL
        return sessionFactory.getCurrentSession()
                .createQuery("FROM PRODUCTS p", Product.class)
                .getResultList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
