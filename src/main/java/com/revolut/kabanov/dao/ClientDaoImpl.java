package com.revolut.kabanov.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.revolut.kabanov.model.Client;

/**
 * @author Алексей
 * 
 */
public class ClientDaoImpl implements ClientDao{

    private final SessionFactory sessionFactory;

    public ClientDaoImpl() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Client.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }
    
    @Override
    public Client createOrUpdate(Client client) {
        getOpenSession().saveOrUpdate(client);
        return client;
    }

    @Override
    public List<Client> getAllClients() {
        CriteriaBuilder builder = getOpenSession().getCriteriaBuilder();
        CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
        criteria.from(Client.class);
        return getOpenSession().createQuery(criteria).getResultList();
    }

    @Override
    public Client getClient(long id) {
        return getOpenSession().get(Client.class, id);
    }

    private Session getOpenSession() {
        return sessionFactory.openSession();
    }
}
