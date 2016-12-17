package mainBean.Entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-16T16:32:13")
@StaticMetamodel(Visitor.class)
public class Visitor_ { 

    public static volatile SingularAttribute<Visitor, Integer> visits;
    public static volatile SingularAttribute<Visitor, String> clientIP;
    public static volatile SingularAttribute<Visitor, Date> lastVisit;

}