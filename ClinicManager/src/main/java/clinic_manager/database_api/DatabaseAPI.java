/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.database_api;

import clinic_manager.entities.Administrator;
import clinic_manager.entities.Appointment;
import clinic_manager.entities.Doctor;
import clinic_manager.entities.Pacient;
import clinic_manager.entities.Room;
import clinic_manager.entities.Schedule;
import clinic_manager.entities.User;
import java.awt.desktop.UserSessionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Iordi
 */
public class DatabaseAPI {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CM_PU");
    EntityManager em;

    public DatabaseAPI() {
        this.em = emf.createEntityManager();
    }

    public User findUserById(int id) {
        em.getTransaction().begin();
        User user = (User) em.createNamedQuery("User.findById")
                .setParameter("id", id)
                .getSingleResult();
        em.getTransaction().commit();
        return user;
    }

    public User loginValidation(String username, String password, String type) {
        em.getTransaction().begin();
        try {
            User user = (User) em.createNamedQuery("User.login")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .setParameter("userType", type)
                    .getSingleResult();
            return user;
        } catch (NoResultException e) {
            System.out.println("Nu s-a gasit nimic!");
            return null;
        } finally {
            em.getTransaction().commit();
        }

    }

    public int registerAdministrator(String username, String password, String type, String firstName, String lastName, String phone, String email) {
        em.getTransaction().begin();
        try {
            int validationUser = ((Number) em.createNamedQuery("User.countAllByUsername")
                    .setParameter("username", username)
                    .getSingleResult())
                    .intValue();
            if (validationUser != 0) {
                return 1;
            }

            int count = ((Number) em.createNamedQuery("User.maxId")
                    .getSingleResult())
                    .intValue();
            count = count + 1;
            User user = new User(count, username, password, type);

            int validationAdministrator = ((Number) em.createNamedQuery("Administrator.countAllByEmail")
                    .setParameter("email", email)
                    .getSingleResult())
                    .intValue();
            if (validationAdministrator != 0) {
                return 3;
            }

            int admin_id = ((Number) em.createNamedQuery("Administrator.maxId")
                    .getSingleResult())
                    .intValue();
            admin_id = admin_id + 1;
            em.persist(user);
            Administrator administrator = new Administrator(admin_id, firstName, lastName, phone, email, user);
            em.persist(administrator);
            return 10;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            em.getTransaction().commit();
        }
    }

    public int registerPacient(String username, String password, String type, long cnp, String firstName, String lastName, String phone, String email, String address, java.util.Date birthdate, String gender) {
        em.getTransaction().begin();
        try {
            int validationUser = ((Number) em.createNamedQuery("User.countAllByUsername")
                    .setParameter("username", username)
                    .getSingleResult())
                    .intValue();
            if (validationUser != 0) {
                return 1;
            }

            int count = ((Number) em.createNamedQuery("User.maxId")
                    .getSingleResult())
                    .intValue();
            count = count + 1;
            User user = new User(count, username, password, type);

            int validationPacient = ((Number) em.createNamedQuery("Pacient.countAllByCnp")
                    .setParameter("cnp", cnp)
                    .getSingleResult())
                    .intValue();
            if (validationPacient != 0) {
                return 2;
            }

            validationPacient = ((Number) em.createNamedQuery("Pacient.countAllByEmail")
                    .setParameter("email", email)
                    .getSingleResult())
                    .intValue();
            if (validationPacient != 0) {
                return 3;
            }
            em.persist(user);

            Pacient pacient = new Pacient(cnp, firstName, lastName, phone, email, address, birthdate, gender, user);
            em.persist(pacient);
            return 10;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            em.getTransaction().commit();
        }
    }

    public int registerDoctor(String username, String password, String type, long cnp, String firstName, String lastName, String phone, String email, String address) {
        em.getTransaction().begin();

        try {
            int validationUser = ((Number) em.createNamedQuery("User.countAllByUsername")
                    .setParameter("username", username)
                    .getSingleResult())
                    .intValue();
            if (validationUser != 0) {
                return 1;
            }

            int count = ((Number) em.createNamedQuery("User.maxId")
                    .getSingleResult())
                    .intValue();
            count = count + 1;
            User user = new User(count, username, password, type);

            int validationDoctor = ((Number) em.createNamedQuery("Doctor.countAllByCnp")
                    .setParameter("cnp", cnp)
                    .getSingleResult())
                    .intValue();
            if (validationDoctor != 0) {
                return 2;
            }

            validationDoctor = ((Number) em.createNamedQuery("Doctor.countAllByEmail")
                    .setParameter("email", email)
                    .getSingleResult())
                    .intValue();
            if (validationDoctor != 0) {
                return 3;
            }
            em.persist(user);

            Doctor doctor = new Doctor(cnp, firstName, lastName, phone, email, address, user);
            em.persist(doctor);
            return 10;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;
        } finally {
            em.getTransaction().commit();
        }
    }

    public Administrator getAdministratorByUser(User user) {
        em.getTransaction().begin();
        try {
            Administrator administrator = (Administrator) em.createNamedQuery("Administrator.findByAdminId")
                    .setParameter("adminId", user)
                    .getSingleResult();
            return administrator;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.getTransaction().commit();
        }
    }

    public Doctor getDoctorByUser(User user) {
        em.getTransaction().begin();
        try {
            Doctor doctor = (Doctor) em.createNamedQuery("Doctor.findByUserId")
                    .setParameter("id", user)
                    .getSingleResult();
            return doctor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.getTransaction().commit();
        }
    }

    public Pacient getPacientByUser(User user) {
        em.getTransaction().begin();
        try {
            Pacient pacient = (Pacient) em.createNamedQuery("Pacient.findByUserId")
                    .setParameter("id", user)
                    .getSingleResult();
            return pacient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.getTransaction().commit();
        }
    }

    public String[][] retrieveDataFromUsers() {
        em.getTransaction().begin();

        int userNumber = ((Number) em.createNamedQuery("User.countAll")
                .getSingleResult())
                .intValue();

        String data[][] = new String[userNumber][4];

        List<User> userList = new ArrayList<>();
        userList = em.createNamedQuery("User.findAll")
                .getResultList();
        int i = 0;
        for (User user : userList) {
            data[i][0] = String.valueOf(user.getId());
            data[i][1] = user.getUsername();
            data[i][2] = user.getPassword();
            data[i][3] = user.getUserType();
            i += 1;
        }

        em.getTransaction().commit();
        return data;
    }

    public String[][] retrieveDataFromAppointments(User user) {
        em.getTransaction().begin();

        try {
            if (user.getUserType().equals("Doctor")) {
                Doctor doctor = (Doctor) em.createNamedQuery("Doctor.findByUserId")
                        .setParameter("id", user)
                        .getSingleResult();

                int appointmentsNumber = ((Number) em.createNamedQuery("Appointment.countAllByCnpDoctor")
                        .setParameter("cnpDoctor", doctor)
                        .getSingleResult())
                        .intValue();

                String data[][] = new String[appointmentsNumber][5];

                List<Appointment> appointmentList = new ArrayList<>();
                appointmentList = em.createNamedQuery("Appointment.findAllByCnpDoctor")
                        .setParameter("cnpDoctor", doctor)
                        .getResultList();
                int i = 0;
                for (Appointment appointment : appointmentList) {
                    data[i][0] = String.valueOf(appointment.getId());
                    Pacient pacient = (Pacient) em.createNamedQuery("Pacient.findByCnp")
                            .setParameter("cnp", appointment.getCnpPacient().getCnp())
                            .getSingleResult();
                    data[i][1] = pacient.getFirstName() + " " + pacient.getLastName();
                    Schedule schedule = (Schedule) em.createNamedQuery("Schedule.findByAppointmentId")
                            .setParameter("appointmentId", appointment)
                            .getSingleResult();
                    data[i][2] = String.valueOf(schedule.getRoomNo().getRoomNo());
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = dateFormat.format(schedule.getDay());
                    data[i][3] = String.valueOf(strDate);
                    data[i][4] = String.valueOf(schedule.getHour() + ":00");
                    i += 1;
                }
                return data;
            } else if (user.getUserType().equals("Pacient")) {
                Pacient pacient = (Pacient) em.createNamedQuery("Pacient.findByUserId")
                        .setParameter("id", user)
                        .getSingleResult();

                int appointmentsNumber = ((Number) em.createNamedQuery("Appointment.countAllByCnpPacient")
                        .setParameter("cnpPacient", pacient)
                        .getSingleResult())
                        .intValue();

                String data[][] = new String[appointmentsNumber][5];

                List<Appointment> appointmentList = new ArrayList<>();
                appointmentList = em.createNamedQuery("Appointment.findAllByCnpPacient")
                        .setParameter("cnpPacient", pacient)
                        .getResultList();
                int i = 0;
                for (Appointment appointment : appointmentList) {
                    data[i][0] = String.valueOf(appointment.getId());
                    Doctor doctor = (Doctor) em.createNamedQuery("Doctor.findByCnp")
                            .setParameter("cnp", appointment.getCnpDoctor().getCnp())
                            .getSingleResult();
                    data[i][1] = doctor.getFirstName() + " " + doctor.getLastName();
                    Schedule schedule = (Schedule) em.createNamedQuery("Schedule.findByAppointmentId")
                            .setParameter("appointmentId", appointment)
                            .getSingleResult();
                    data[i][2] = String.valueOf(schedule.getRoomNo().getRoomNo());
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = dateFormat.format(schedule.getDay());
                    data[i][3] = String.valueOf(strDate);
                    data[i][4] = String.valueOf(schedule.getHour() + ":00");
                    i += 1;
                }
                return data;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.getTransaction().commit();
        }

    }

    public boolean deleteRecordFromUsers(int id) {
        em.getTransaction().begin();
        try {
            User user = (User) em.createNamedQuery("User.findById")
                    .setParameter("id", id)
                    .getSingleResult();
            String type = user.getUserType();

            em.createNamedQuery(type + ".deleteByUserId")
                    .setParameter("id", user)
                    .executeUpdate();
            em.flush();

            em.createNamedQuery("User.deleteById")
                    .setParameter("id", id)
                    .executeUpdate();

            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.getTransaction().commit();
        }
    }

    public boolean deleteRecordFromAppointments(int id) {
        em.getTransaction().begin();
        try {
            Appointment appointment = (Appointment) em.createNamedQuery("Appointment.findById")
                    .setParameter("id", id)
                    .getSingleResult();
            Schedule schedule = (Schedule) em.createNamedQuery("Schedule.findByAppointmentId")
                    .setParameter("appointmentId", appointment)
                    .getSingleResult();
            em.createNamedQuery("Schedule.deleteById")
                    .setParameter("id", schedule.getId())
                    .executeUpdate();
            em.flush();

            em.createNamedQuery("Appointment.deleteById")
                    .setParameter("id", id)
                    .executeUpdate();
            em.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.getTransaction().commit();
        }
    }

    public boolean updateAdmin(User user, String password, String phone, String email) {
        em.getTransaction().begin();
        try {
            User newUser = (User) em.createNamedQuery("User.findById")
                    .setParameter("id", user.getId())
                    .getSingleResult();
            newUser.setPassword(password);
            em.merge(newUser);

            Administrator administrator = (Administrator) em.createNamedQuery("Administrator.findByAdminId")
                    .setParameter("adminId", user)
                    .getSingleResult();
            administrator.setPhone(phone);
            administrator.setEmail(email);
            em.merge(administrator);

            int validationAdministrator = ((Number) em.createNamedQuery("Administrator.countAllByEmail")
                    .setParameter("email", email)
                    .getSingleResult())
                    .intValue();
            if (validationAdministrator != 0 && !administrator.getEmail().equals(email)) {
                return false;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.getTransaction().commit();
        }
    }

    public boolean updateDoctor(User user, String password, String phone, String email, String address) {
        em.getTransaction().begin();
        try {
            User newUser = (User) em.createNamedQuery("User.findById")
                    .setParameter("id", user.getId())
                    .getSingleResult();
            newUser.setPassword(password);
            em.merge(newUser);

            Doctor doctor = (Doctor) em.createNamedQuery("Doctor.findByUserId")
                    .setParameter("id", user)
                    .getSingleResult();

            int validationDoctor = ((Number) em.createNamedQuery("Doctor.countAllByEmail")
                    .setParameter("email", email)
                    .getSingleResult())
                    .intValue();
            if (validationDoctor != 0 && !doctor.getEmail().equals(email)) {
                return false;
            }

            doctor.setPhone(phone);
            doctor.setEmail(email);
            doctor.setAdress(address);
            em.merge(doctor);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.getTransaction().commit();
        }
    }

    public boolean updatePacient(User user, String password, String phone, String email, String address) {
        em.getTransaction().begin();
        try {
            User newUser = (User) em.createNamedQuery("User.findById")
                    .setParameter("id", user.getId())
                    .getSingleResult();
            newUser.setPassword(password);
            em.merge(newUser);

            Pacient pacient = (Pacient) em.createNamedQuery("Pacient.findByUserId")
                    .setParameter("id", user)
                    .getSingleResult();

            int validationPacient = ((Number) em.createNamedQuery("Pacient.countAllByEmail")
                    .setParameter("email", email)
                    .getSingleResult())
                    .intValue();
            if (validationPacient != 0 && !pacient.getEmail().equals(email)) {
                return false;
            }

            pacient.setPhone(phone);
            pacient.setEmail(email);
            pacient.setAdress(address);
            em.merge(pacient);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.getTransaction().commit();
        }
    }

    public String[] getDoctorNames() {
        em.getTransaction().begin();
        try {
            List<Doctor> doctors = new ArrayList<>();
            doctors = em.createNamedQuery("Doctor.findAll")
                    .getResultList();
            String[] doctorList = new String[doctors.size()];
            int i = 0;
            for (Doctor doctor : doctors) {
                doctorList[i++] = doctor.getFirstName();
            }
            return doctorList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.getTransaction().commit();
        }
    }

    public int addAppointment(String doctorName, JFormattedTextField dateField, Pacient pacient) {
        em.getTransaction().begin();
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateField.getText());
            int dateAppointments;
            List<Schedule> schedulesOnDate = new ArrayList<>();
            schedulesOnDate = (List<Schedule>) em.createNamedQuery("Schedule.findByDay")
                    .setParameter("day", date)
                    .getResultList();

            dateAppointments = schedulesOnDate.size();
            Doctor doctor = (Doctor) em.createNamedQuery("Doctor.findByFirstName")
                    .setParameter("firstName", doctorName)
                    .getSingleResult();

            int scheduleNo = 0;
            int appointmentsNo = 0;
            
            if(dateAppointments != 0){
            scheduleNo = ((Number) em.createNamedQuery("Schedule.maxId")
                    .getSingleResult())
                    .intValue();

            appointmentsNo = ((Number) em.createNamedQuery("Appointment.maxId")
                    .getSingleResult())
                    .intValue();
            }
            appointmentsNo += 1;
            scheduleNo += 1;
            if (dateAppointments == 0) {

                Appointment appointment = new Appointment(appointmentsNo, pacient, doctor);
                em.persist(appointment);

                Room room = (Room) em.createNamedQuery("Room.findAll")
                        .setMaxResults(1)
                        .getSingleResult();

                Schedule schedule = new Schedule(scheduleNo, room, 8, appointment, date);
                em.persist(schedule);
                return 10;
            } else {
                List<Schedule> dateSchedules = new ArrayList<>();
                dateSchedules = em.createNamedQuery("Schedule.findByDay")
                        .setParameter("day", date)
                        .getResultList();
               

                int doctorCount = 0;
                for (Schedule scheduleIterator : dateSchedules) {
                    Doctor doctor2 = scheduleIterator.getAppointmentId().getCnpDoctor();
                    if (doctor.equals(doctor2)) {
                        doctorCount += 1;
                    }
                    if (doctorCount == 5) {
                        return 1;
                    }
                }
      
                List<Room> roomList = new ArrayList<>();
                roomList = (List<Room>) em.createNamedQuery("Room.findAll")
                        .getResultList();
                for (Room room : roomList) {
                    int roomCount = 0;
                    for (Schedule scheduleIterator : dateSchedules) {
                        Room compareRoom = scheduleIterator.getRoomNo();

                        if (room.equals(compareRoom)) {
                            roomCount += 1;
                        }
                    }

                    if (roomCount < 5) {

                        Appointment appointment = new Appointment(appointmentsNo, pacient, doctor);

                        List<Integer> hours = new ArrayList<>();
                        hours = (List<Integer>) em.createNamedQuery("Schedule.findHourByDayAndRoomNo")
                                .setParameter("day", date)
                                .setParameter("roomNo", room)
                                .getResultList();
                        
                        int maxHour = 8;
                        if (hours.size() != 0) {
                            maxHour = ((Number) em.createNamedQuery("Schedule.maxHour")
                                    .setParameter("day", date)
                                    .setParameter("roomNo", room)
                                    .getSingleResult())
                                    .intValue();
                            maxHour += 2;
                        }

                        em.persist(appointment);
                        Schedule schedule = new Schedule(scheduleNo, room, maxHour, appointment, date);
                        em.persist(schedule);
                        return 10;
                    }
                }
                return 2;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return 4;
        } catch (NoResultException e) {
            e.printStackTrace();
            return 3;
        } finally {
            em.getTransaction().commit();
        }
    }
}
