package clinic_manager.entities;

import clinic_manager.entities.Doctor;
import clinic_manager.entities.Pacient;
import clinic_manager.entities.Schedule;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-06-09T21:18:01", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Appointment.class)
public class Appointment_ { 

    public static volatile CollectionAttribute<Appointment, Schedule> scheduleCollection;
    public static volatile SingularAttribute<Appointment, Pacient> cnpPacient;
    public static volatile SingularAttribute<Appointment, Doctor> cnpDoctor;
    public static volatile SingularAttribute<Appointment, Integer> id;

}