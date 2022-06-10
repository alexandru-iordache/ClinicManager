package clinic_manager.entities;

import clinic_manager.entities.Appointment;
import clinic_manager.entities.User;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-06-09T21:18:01", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Pacient.class)
public class Pacient_ { 

    public static volatile SingularAttribute<Pacient, String> firstName;
    public static volatile SingularAttribute<Pacient, String> lastName;
    public static volatile SingularAttribute<Pacient, Long> cnp;
    public static volatile SingularAttribute<Pacient, String> gender;
    public static volatile SingularAttribute<Pacient, User> pacientId;
    public static volatile SingularAttribute<Pacient, String> phone;
    public static volatile ListAttribute<Pacient, Appointment> appointmentList;
    public static volatile SingularAttribute<Pacient, String> adress;
    public static volatile SingularAttribute<Pacient, Date> birthDate;
    public static volatile SingularAttribute<Pacient, String> email;

}