package com.dev.backend.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "person")
public class Person implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String cpf;
  private String email;
  private String address;
  private String zipCode;
  private String password;
  private String passwordRecoveryCode;

  @Temporal(TemporalType.TIMESTAMP)
  private Date passwordRecoveryExpirationDate;

  @ManyToOne
  @JoinColumn(name = "city_id")
  private City city;

  @OneToMany(mappedBy = "person", orphanRemoval = true, cascade = { CascadeType.PERSIST,
      CascadeType.MERGE }, fetch = FetchType.EAGER)
  @Setter(value = AccessLevel.NONE)
  private List<PersonPermission> personPermissions;

  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Temporal(TemporalType.TIMESTAMP)
  private Date updateDate;

  public void setPersonPermission(List<PersonPermission> pp) {
    for (PersonPermission p : pp) {
      p.setPerson(this);
    }
    this.personPermissions = pp;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return personPermissions;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
