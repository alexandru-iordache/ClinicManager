/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Iordi
 */
@Entity
@Table(name = "SCHEDULE")
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s"),
    @NamedQuery(name = "Schedule.findById", query = "SELECT s FROM Schedule s WHERE s.id = :id"),
    @NamedQuery(name = "Schedule.findByHour", query = "SELECT s FROM Schedule s WHERE s.hour = :hour"),
    @NamedQuery(name = "Schedule.findByDay", query = "SELECT s FROM Schedule s WHERE s.day = :day"),
    @NamedQuery(name = "Schedule.findByAppointmentId", query = "SELECT s FROM Schedule s WHERE s.appointmentId = :appointmentId"),
    @NamedQuery(name = "Schedule.findHourByDayAndRoomNo", query = "SELECT s.hour FROM Schedule s WHERE s.day = :day AND s.roomNo = :roomNo"),
    @NamedQuery(name = "Schedule.deleteById", query = "DELETE FROM Schedule s WHERE s.id = :id"),
    @NamedQuery(name = "Schedule.countAllByAppointmentDate", query = "SELECT COUNT(s) FROM Schedule s WHERE s.day = :appointmentDate"),
    @NamedQuery(name = "Schedule.maxId", query = "SELECT MAX(s.id) FROM Schedule s"),
    @NamedQuery(name = "Schedule.maxHour", query = "SELECT MAX (s.hour) FROM Schedule s WHERE s.day = :day AND s.roomNo = :roomNo")})
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    @Basic(optional = false)
    @Column(name = "HOUR")
    private int hour;
    @Basic(optional = false)
    @Column(name = "DAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date day;
    @JoinColumn(name = "APPOINTMENT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Appointment appointmentId;
    @JoinColumn(name = "ROOM_NO", referencedColumnName = "ROOM_NO")
    @ManyToOne(optional = false)
    private Room roomNo;

    public Schedule() {
    }

    public Schedule(int id) {
        this.id = id;
    }

    public Schedule(int id, int hour, Date day) {
        this.id = id;
        this.hour = hour;
        this.day = day;
    }

    public Schedule(int scheduleNo, Room roomNo, int hour, Appointment appointment, Date date) {
        this.id = scheduleNo;
        this.roomNo = roomNo;
        this.hour = hour;
        this.appointmentId = appointment;
        this.day = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Appointment getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Appointment appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Room getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Room roomNo) {
        this.roomNo = roomNo;
    }

    @Override
    public String toString() {
        return "clinic_manager.entities.Schedule[ id=" + id + " ]";
    }
    
}
