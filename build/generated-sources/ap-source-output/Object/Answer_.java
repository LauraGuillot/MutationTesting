package Object;

import Object.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-20T16:08:24")
@StaticMetamodel(Answer.class)
public class Answer_ { 

    public static volatile SingularAttribute<Answer, Integer> answerID;
    public static volatile SingularAttribute<Answer, Integer> question;
    public static volatile SingularAttribute<Answer, String> response;
    public static volatile SingularAttribute<Answer, User> userID;

}