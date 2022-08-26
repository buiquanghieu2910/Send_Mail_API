// Generated with g9.

package com.hieubq.entities;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="users")
public class User implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    
    @NotBlank
    @Column(name="full_name", nullable=false, length=45)
    private String fullName;
    
    @NotBlank
    @Column(nullable=false, length=45)
    private String password;
    
    @NotBlank
    @Column(nullable=false, length=45)
    private String email;
    
    @NotNull
    @Column(nullable=false, precision=10)
    private Integer role;
    
    @NotNull
    @Column(nullable=false, precision=10)
    private Integer status;

    /** Default constructor. */
    public User() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(int aId) {
        id = aId;
    }

    /**
     * Access method for fullName.
     *
     * @return the current value of fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Setter method for fullName.
     *
     * @param aFullName the new value for fullName
     */
    public void setFullName(String aFullName) {
        fullName = aFullName;
    }

    /**
     * Access method for password.
     *
     * @return the current value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for password.
     *
     * @param aPassword the new value for password
     */
    public void setPassword(String aPassword) {
        password = aPassword;
    }

    /**
     * Access method for email.
     *
     * @return the current value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for email.
     *
     * @param aEmail the new value for email
     */
    public void setEmail(String aEmail) {
        email = aEmail;
    }

    /**
     * Access method for role.
     *
     * @return the current value of role
     */
    public Integer getRole() {
        return role;
    }

    /**
     * Setter method for role.
     *
     * @param aRole the new value for role
     */
    public void setRole(Integer aRole) {
        role = aRole;
    }

    /**
     * Access method for status.
     *
     * @return the current value of status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Setter method for status.
     *
     * @param aStatus the new value for status
     */
    public void setStatus(Integer aStatus) {
        status = aStatus;
    }

    /**
     * Compares the key for this instance with another User.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class User and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User that = (User) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another User.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof User)) return false;
        return this.equalKeys(other) && ((User)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = getId();
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[User |");
        sb.append(" id=").append(getId());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("id", Integer.valueOf(getId()));
        return ret;
    }

}
