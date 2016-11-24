/**
 * Class Answer.java
 * -----------------------------------------------------------------------------
 * Author : Laura
 * Last modification : 17/08/2016
 * -----------------------------------------------------------------------------
 * Entity Class form the database : represent the answer of a user
 */
package Object;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class Answer. Representation of a user evaluation
 *
 * @author Laura
 */
@Entity
@Table(name = "answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a"),
    @NamedQuery(name = "Answer.findByAnswerID", query = "SELECT a FROM Answer a WHERE a.answerID = :answerID"),
    @NamedQuery(name = "Answer.findByQuestion", query = "SELECT a FROM Answer a WHERE a.question = :question"),
    @NamedQuery(name = "Answer.findByResponse", query = "SELECT a FROM Answer a WHERE a.response = :response")})
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AnswerID")
    private Integer answerID;
    @Column(name = "Question")
    private Integer question;
    @Column(name = "Response")
    private String response;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne(optional = false)
    private User userID;

    /**
     * Constructor
     */
    public Answer() {
    }

    /**
     * Constructor
     *
     * @param answerID AnswerID
     */
    public Answer(Integer answerID) {
        this.answerID = answerID;
    }

    /**
     * Constructor
     *
     * @param u User
     * @param q Question index
     * @param a Answer ("E" for equivalent or "K" for killed)
     */
    public Answer(User u, int q, String a) {
        this.question = q;
        this.userID = u;
        this.response = a;
    }

    /**
     * Get the answer ID
     *
     * @return AnswerID
     */
    public Integer getAnswerID() {
        return answerID;
    }

    /**
     * Set the answer ID
     *
     * @param answerID answer ID
     */
    public void setAnswerID(Integer answerID) {
        this.answerID = answerID;
    }

    /**
     * Get the question index
     *
     * @return Question index (integer between 1 and 10)
     */
    public Integer getQuestion() {
        return question;
    }

    /**
     * Set the question index
     *
     * @param question Question index (integer between 1 and 10)
     */
    public void setQuestion(Integer question) {
        this.question = question;
    }

    /**
     * Get an answer
     *
     * @return Answer
     */
    public String getResponse() {
        return response;
    }

    /**
     * Set an answer
     *
     * @param response Answer
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Get the user ID
     *
     * @return user ID
     */
    public User getUserID() {
        return userID;
    }

    /**
     * Set the user ID
     *
     * @param userID user ID
     */
    public void setUserID(User userID) {
        this.userID = userID;
    }

    /**
     * Hash an answer
     *
     * @return Integer representing the answer
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (answerID != null ? answerID.hashCode() : 0);
        return hash;
    }

    /**
     * Method equals. Test the equality of two answer
     *
     * @param object Answer
     * @return Boolean
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.answerID == null && other.answerID != null) || (this.answerID != null && !this.answerID.equals(other.answerID))) {
            return false;
        }
        return true;
    }

    /**
     * Method toString
     *
     * @return String representing an answer
     */
    @Override
    public String toString() {
        return "Object.Answer[ answerID=" + answerID + " ]";
    }

}
