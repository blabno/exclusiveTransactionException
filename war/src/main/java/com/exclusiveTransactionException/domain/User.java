package com.exclusiveTransactionException.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "USEROS", uniqueConstraints = @UniqueConstraint(name = "UNIQUE___USERS___EMAIL", columnNames = "EMAIL"))
public class User implements Serializable {
// ------------------------------ FIELDS ------------------------------

    @NotNull
    @Email
    @Length(min = 1, max = 255)
    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;

    @Id
    @GeneratedValue(generator = "USERS_ID_SEQUENCE", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USERS_ID_SEQUENCE", sequenceName = "USERS_ID_SEQUENCE", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Length(min = 1, max = 255)
    @Column(name = "PASSWORD", length = 255)
    private String password;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    // --------------------- GETTER / SETTER METHODS ---------------------

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

// ------------------------ CANONICAL METHODS ------------------------

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        return !(id != null ? !id.equals(user.id) : user.id != null);
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }
}
