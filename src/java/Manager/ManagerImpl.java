/**
 * Class ManagerImpl.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 17/08/2016
 * -----------------------------------------------------------------------------
 * Management of the database objects
 */
package Manager;

import Object.Answer;
import Object.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 * Class ManagerImpl
 * Implementation of the interface Manager
 * @author Laura
 */
public class ManagerImpl implements Manager {

    /**
     * Entity manager factory
     */
    private EntityManagerFactory emf;
    
    /**
     * Manager
     */
    private static ManagerImpl theManager;

    /**
     * Constructor
     */
    private ManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("MutationPU");
        }
    }

    /**
     * Manager instanciation
     * @return Manager
     */
    public static Manager getInstance() {
        if (theManager == null) {
            theManager = new ManagerImpl();
        }
        return theManager;
    }

    /**
     * Add a user in the database
     * @param name User name
     * @return boolean true if the user has been added and false if he is already added
     */
    @Override
    public boolean add(String name) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createNamedQuery("User.findByUserName", User.class);
        q.setParameter("userName", name);
        List l = q.getResultList();

        Boolean b;
        if (l.isEmpty()) {
            b = true;
            User u = new User(name);
            u.setScore(0);
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();

        } else {
            b = false;
        }
        return b;
    }

    /**
     * Increase the score of a user
     * @param u User name
     */
    @Override
    public void incrScore(String u) {
        EntityManager em = emf.createEntityManager();
        User user = this.findUser(u);
        user.setScore(user.getScore() + 1);
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    /**
     * Find a user by his name
     * @param u User name
     * @return User
     */
    @Override
    public User findUser(String u) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("User.findByUserName", User.class);
        q.setParameter("userName", u);
        List l = q.getResultList();
        User user = (User) l.get(0);
        return user;
    }

    /**
     * Save the answer of a user
     * @param u User name
     * @param q Number of the question
     * @param a Answer of the user (E for equivalent or K for killed)
     * @return Boolean true if the answer is added and false if it is already registered
     */
    @Override
    public boolean saveAnswer(String u, int q, String a) {
        EntityManager em = emf.createEntityManager();

        Query qu = em.createQuery("SELECT a FROM Answer a WHERE(a.userID=:u AND a.question=:q)");
        qu.setParameter("u", this.findUser(u));
        qu.setParameter("q", q);
        List l = qu.getResultList();

        if (l.isEmpty()) {
            Answer ans = new Answer(this.findUser(u), q, a);
            em.getTransaction().begin();
            em.persist(ans);
            em.getTransaction().commit();
        }
        return l.isEmpty();
    }
}
