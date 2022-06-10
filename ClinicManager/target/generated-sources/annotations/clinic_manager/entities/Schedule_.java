package clinic_manager.entities;

import clinic_manager.entities.Appointment;
import clinic_manager.entities.Room;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-06-09T21:18:01", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Schedule.class)
public class Schedule_ { 

    public static volatile SingularAttribute<Schedule, Integer> hour;
    public static volatile SingularAttribute<Schedule, Room> roomNo;
    public static volatile SingularAttribute<Schedule, Appointment> appointmentId;
    public static volatile SingularAttribute<Schedule, Integer> id;
    public static volatile SingularAttribute<Schedule, Date> day;

}