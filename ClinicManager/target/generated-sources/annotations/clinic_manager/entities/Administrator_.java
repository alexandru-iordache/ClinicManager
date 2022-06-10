package clinic_manager.entities;

import clinic_manager.entities.User;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-06-09T21:18:01", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Administrator.class)
public class Administrator_ { 

    public static volatile SingularAttribute<Administrator, String> firstName;
    public static volatile SingularAttribute<Administrator, String> lastName;
    public static volatile SingularAttribute<Administrator, String> phone;
    public static volatile SingularAttribute<Administrator, User> adminId;
    public static volatile SingularAttribute<Administrator, Integer> id;
    public static volatile SingularAttribute<Administrator, String> email;

}