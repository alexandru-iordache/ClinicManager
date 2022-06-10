/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic_manager.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Iordi
 */
@Entity
@Table(name = "USERS")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByUserType", query = "SELECT u FROM User u WHERE u.userType = :userType"),
    @NamedQuery(name = "User.countAll", query = "SELECT COUNT(u) FROM User u"),
    @NamedQuery(name = "User.countAllByUsername", query = "SELECT COUNT(u) FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findUserTypeById", query = "SELECT u.userType FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.deleteById", query = "DELETE FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.maxId", query = "SELECT MAX(u.id) FROM User u"),
    @NamedQuery(name = "User.login", query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password AND u.userType = :userType")})
public class User implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacientId")
    private List<Pacient> pacientList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "USER_TYPE")
    private String userType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctorId")
    private List<Doctor> doctorList;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String username, String password, String userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    @Override
    public String toString() {
        return "clinic_manager.entities.User[ id=" + id + " ]";
    }

    public List<Pacient> getPacientList() {
        return pacientList;
    }

    public void setPacientList(List<Pacient> pacientList) {
        this.pacientList = pacientList;
    }
    
}
