package Object;

import Object.Answer;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-20T16:08:24")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Integer> score;
    public static volatile CollectionAttribute<User, Answer> answerCollection;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile SingularAttribute<User, Integer> userID;

}