/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Iordi
 */
@Entity
@Table(name = "APPOINTMENTS")
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a"),
    @NamedQuery(name = "Appointment.findById", query = "SELECT a FROM Appointment a WHERE a.id = :id"),
    @NamedQuery(name = "Appointment.countAllByCnpDoctor", query = "SELECT COUNT(a) FROM Appointment a WHERE a.cnpDoctor = :cnpDoctor"),
    @NamedQuery(name = "Appointment.countAllByCnpPacient", query = "SELECT COUNT(a) FROM Appointment a WHERE a.cnpPacient = :cnpPacient"),
    @NamedQuery(name = "Appointment.findAllByCnpDoctor", query = "SELECT a FROM Appointment a WHERE a.cnpDoctor = :cnpDoctor"),
    @NamedQuery(name = "Appointment.findAllByCnpPacient", query = "SELECT a FROM Appointment a WHERE a.cnpPacient = :cnpPacient"),
    @NamedQuery(name = "Appointment.deleteById", query = "DELETE FROM Appointment a WHERE a.id = :id"),
    @NamedQuery(name = "Appointment.maxId", query = "SELECT MAX(a.id) FROM Appointment a")})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    @JoinColumn(name = "CNP_DOCTOR", referencedColumnName = "CNP")
    @ManyToOne(optional = false)
    private Doctor cnpDoctor;
    @JoinColumn(name = "CNP_PACIENT", referencedColumnName = "CNP")
    @ManyToOne(optional = false)
    private Pacient cnpPacient;

    public Appointment() {
    }

    public Appointment(int id) {
        this.id = id;
    }

    public Appointment(int appointmentsNo, Pacient pacient, Doctor doctor) {
        this.id = appointmentsNo;
        this.cnpPacient = pacient;
        this.cnpDoctor = doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getCnpDoctor() {
        return cnpDoctor;
    }

    public void setCnpDoctor(Doctor cnpDoctor) {
        this.cnpDoctor = cnpDoctor;
    }

    public Pacient getCnpPacient() {
        return cnpPacient;
    }

    public void setCnpPacient(Pacient cnpPacient) {
        this.cnpPacient = cnpPacient;
    }

    @Override
    public String toString() {
        return "clinic_manager.entities.Appointment[ id=" + id + " ]";
    }
    
}
