/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.entities;

import java.io.Serializable;
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

/**
 *
 * @author Iordi
 */
@Entity
@Table(name = "DOCTORS")
@NamedQueries({
    @NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM Doctor d"),
    @NamedQuery(name = "Doctor.findByCnp", query = "SELECT d FROM Doctor d WHERE d.cnp = :cnp"),
    @NamedQuery(name = "Doctor.findByFirstName", query = "SELECT d FROM Doctor d WHERE d.firstName = :firstName"),
    @NamedQuery(name = "Doctor.findByLastName", query = "SELECT d FROM Doctor d WHERE d.lastName = :lastName"),
    @NamedQuery(name = "Doctor.findByPhone", query = "SELECT d FROM Doctor d WHERE d.phone = :phone"),
    @NamedQuery(name = "Doctor.findByEmail", query = "SELECT d FROM Doctor d WHERE d.email = :email"),
    @NamedQuery(name = "Doctor.findByAdress", query = "SELECT d FROM Doctor d WHERE d.adress = :adress"),
    @NamedQuery(name = "Doctor.findByUserId", query = "SELECT d FROM Doctor d WHERE d.doctorId = :id"),
    @NamedQuery(name = "Doctor.countAllByCnp", query = "SELECT COUNT(d) FROM Doctor d WHERE d.cnp = :cnp"),
    @NamedQuery(name = "Doctor.countAllByEmail", query = "SELECT COUNT(d) FROM Doctor d WHERE d.email = :email"),
    @NamedQuery(name = "Doctor.deleteByUserId", query = "DELETE FROM Doctor d WHERE d.doctorId = :id")})
public class Doctor implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cnpDoctor")
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
    @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User doctorId;

    public Doctor() {
    }

    public Doctor(long cnp) {
        this.cnp = cnp;
    }

    public Doctor(long cnp, String firstName, String lastName, String phone, String email, String address, User doctorId) {
        this.cnp = cnp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.adress = address;
        this.doctorId = doctorId;
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

    public User getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(User doctorId) {
        this.doctorId = doctorId;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Doctor other = (Doctor) obj;
        if (this.cnp != other.cnp) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.adress, other.adress)) {
            return false;
        }
        if (!Objects.equals(this.appointmentList, other.appointmentList)) {
            return false;
        }
        return Objects.equals(this.doctorId, other.doctorId);
    }

    
    
    @Override
    public String toString() {
        return "Doctor{" + "cnp=" + cnp + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", email=" + email + ", adress=" + adress + ", doctorId=" + doctorId + '}';
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    

   
    
}
