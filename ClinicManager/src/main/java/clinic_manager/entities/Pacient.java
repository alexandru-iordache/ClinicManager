/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Iordi
 */
@Entity
@Table(name = "PACIENTS")
@NamedQueries({
    @NamedQuery(name = "Pacient.findAll", query = "SELECT p FROM Pacient p"),
    @NamedQuery(name = "Pacient.findByCnp", query = "SELECT p FROM Pacient p WHERE p.cnp = :cnp"),
    @NamedQuery(name = "Pacient.findByFirstName", query = "SELECT p FROM Pacient p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Pacient.findByLastName", query = "SELECT p FROM Pacient p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Pacient.findByPhone", query = "SELECT p FROM Pacient p WHERE p.phone = :phone"),
    @NamedQuery(name = "Pacient.findByEmail", query = "SELECT p FROM Pacient p WHERE p.email = :email"),
    @NamedQuery(name = "Pacient.findByAdress", query = "SELECT p FROM Pacient p WHERE p.adress = :adress"),
    @NamedQuery(name = "Pacient.findByBirthDate", query = "SELECT p FROM Pacient p WHERE p.birthDate = :birthDate"),
    @NamedQuery(name = "Pacient.findByGender", query = "SELECT p FROM Pacient p WHERE p.gender = :gender"),
    @NamedQuery(name = "Pacient.findByUserId", query = "SELECT p FROM Pacient p WHERE p.pacientId = :id"),
    @NamedQuery(name = "Pacient.countAllByCnp", query = "SELECT COUNT(p) FROM Pacient p WHERE p.cnp = :cnp"),
    @NamedQuery(name = "Pacient.countAllByEmail", query = "SELECT COUNT(p) FROM Pacient p WHERE p.email = :email"),
    @NamedQuery(name = "Pacient.deleteByUserId", query = "DELETE FROM Pacient p WHERE p.pacientId = :id")})
public class Pacient implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cnpPacient")
    private List<Appointment> appointmentList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "CNP")
    private long cnp;
    @Basic(optional = false)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "PHONE")
    private String phone;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ADRESS")
    private String adress;
    @Basic(optional = false)
    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    @Basic(optional = false)
    @Column(name = "GENDER")
    private String gender;
    @JoinColumn(name = "PACIENT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User pacientId;

    public Pacient() {
    }

    public Pacient(long cnp) {
        this.cnp = cnp;
    }

    public Pacient(long cnp, String firstName, String lastName, String phone, String email, String address, Date birthDate, String gender, User pacientId) {
        this.cnp = cnp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.adress = address;
        this.birthDate = birthDate;
        this.gender = gender;
        this.pacientId = pacientId;
    }

    public long getCnp() {
        return cnp;
    }

    public void setCnp(long cnp) {
        this.cnp = cnp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User getPacientId() {
        return pacientId;
    }

    public void setPacientId(User pacientId) {
        this.pacientId = pacientId;
    }

   
    @Override
    public String toString() {
        return "Pacient{" + "cnp=" + cnp + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", email=" + email + ", adress=" + adress + ", birthDate=" + birthDate + ", gender=" + gender + ", pacientId=" + pacientId + '}';
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    
    
}
