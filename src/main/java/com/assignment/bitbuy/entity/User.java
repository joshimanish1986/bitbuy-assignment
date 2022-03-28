package com.assignment.bitbuy.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Data
@Entity
@Table(name="TBL_USERS")
@Getter @Setter @ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(Include.NON_NULL)
public class User {
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
     
    @Column(name="user_name")
    private String userName;
    
    @Column(name="password")
    //Only comment below while running the test case
    //@JsonProperty(access = Access.WRITE_ONLY) //hide the password field in response
    private String password;
    
    @Column(name="name")
    private String name;
    
	@Column(name="email")
    private String email;
    
    @Column(name="phone")
    private String phone;
     

    
}
