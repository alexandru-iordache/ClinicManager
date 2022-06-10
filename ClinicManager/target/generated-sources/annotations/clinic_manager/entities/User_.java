package clinic_manager.entities;

import clinic_manager.entities.Doctor;
import clinic_manager.entities.Pacient;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-06-09T21:18:01", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, Doctor> doctorList;
    public static volatile ListAttribute<User, Pacient> pacientList;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> userType;
    public static volatile SingularAttribute<User, String> username;

}