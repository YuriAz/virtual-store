package com.dev.backend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Date purchaseDate;
  private String observation;
  private String situation;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;

  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Temporal(TemporalType.TIMESTAMP)
  private Date updateDate;
}
