/**
 * Class User.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 17/08/2016
 * -----------------------------------------------------------------------------
 * Entity Class form the database : represent a user
 */
package Object;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Class User. Representation of a user
 *
 * @author Laura
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserID", query = "SELECT u FROM User u WHERE u.userID = :userID"),
    @NamedQuery(name = "User.findByScore", query = "SELECT u FROM User u WHERE u.score = :score"),
    @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UserID")
    private Integer userID;
    @Column(name = "Score")
    private Integer score;
    @Basic(optional = false)
    @Column(name = "UserName")
    private String userName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Answer> answerCollection;

    /**
     * Constructor
     */
    public User() {

    }

    /**
     * Constructor
     *
     * @param n User name
     */
    public User(String n) {
        this.userName = n;
    }

    /**
     * Constructor
     *
     * @param userID user ID
     */
    public User(Integer userID) {
        this.userID = userID;
    }

    /**
     * Constructor
     *
     * @param userID user ID
     * @param userName user Name
     */
    public User(Integer userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    /**
     * Get the user ID
     *
     * @return User ID
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * Set the user ID
     *
     * @param userID user ID
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * Get the user score
     *
     * @return User score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Set the user score
     *
     * @param score user score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * Get the user Name
     *
     * @return user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the user name
     *
     * @param userName User name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the answers of a user
     *
     * @return Collection of answers
     */
    @XmlTransient
    public Collection<Answer> getAnswerCollection() {
        return answerCollection;
    }

    /**
     * Set the answers of a user
     *
     * @param answerCollection Answers collection
     */
    public void setAnswerCollection(Collection<Answer> answerCollection) {
        this.answerCollection = answerCollection;
    }

    /**
     * Hash a user
     *
     * @return Integer representing the user
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    /**
     * Method equals. Test the equality of two users
     *
     * @param object A user
     * @return Boolean
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    /**
     * Method to String
     *
     * @return A String representing the user
     */
    @Override
    public String toString() {
        return "Object.User[ userID=" + userID + " ]";
    }

}
