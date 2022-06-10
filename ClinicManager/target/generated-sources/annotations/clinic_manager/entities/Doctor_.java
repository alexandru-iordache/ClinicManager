package clinic_manager.entities;

import clinic_manager.entities.Appointment;
import clinic_manager.entities.User;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-06-09T21:18:01", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Doctor.class)
public class Doctor_ { 

    public static volatile SingularAttribute<Doctor, String> firstName;
    public static volatile SingularAttribute<Doctor, String> lastName;
    public static volatile SingularAttribute<Doctor, Long> cnp;
    public static volatile SingularAttribute<Doctor, String> phone;
    public static volatile SingularAttribute<Doctor, User> doctorId;
    public static volatile ListAttribute<Doctor, Appointment> appointmentList;
    public static volatile SingularAttribute<Doctor, String> adress;
    public static volatile SingularAttribute<Doctor, String> email;

}