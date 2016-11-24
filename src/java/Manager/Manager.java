/**
 * Interface Manager.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 17/08/2016
 * -----------------------------------------------------------------------------
 * Management of the database objects
 */


package Manager;

import Object.User;

/**
 * Interface Manager. Management of the database objects
 * @author Laura
 */
public interface Manager {

    /**
     * Add a user in the database
     *
     * @param name User name
     * @return boolean true if the user has been added and false if he is
     * already added
     */
    public boolean add(String name);

    /**
     * Increase the score of a user
     *
     * @param u User name
     */
    public void incrScore(String u);

    /**
     * Save the answer of a user
     *
     * @param u User name
     * @param q Number of the question
     * @param a Answer of the user (E for equivalent or K for killed)
     * @return Boolean true if the answer is added and false if it is already
     * registered
     */
    public boolean saveAnswer(String u, int q, String a);

    /**
     * Find a user by his name
     *
     * @param u User name
     * @return User
     */
    public User findUser(String u);
}
