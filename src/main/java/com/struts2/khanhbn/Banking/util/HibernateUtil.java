package com.struts2.khanhbn.Banking.util;

import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    
    private static org.hibernate.SessionFactory sessionFactory;
    
    static {
        try{
            
            Configuration configuration=  new Configuration().configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
            serviceRegistryBuilder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);        
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private HibernateUtil(){}
    
    public static Session getThreadLocalSession() {
        Session session = (Session) threadLocal.get();
         
        if (session == null) {
         session = sessionFactory.openSession();
            threadLocal.set(session); 
         }
        
        return session; 
     }
    
    
    public static void closeThreadLocalSession() {
        
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);
        if (session != null) {
        session.close();        
        }
    }
    
    public static Session getsession() {
            return sessionFactory.openSession();
    }
    
    public static void closeSession(Session session) {
        if (session != null) {
            session.close();
        }
    }
}
