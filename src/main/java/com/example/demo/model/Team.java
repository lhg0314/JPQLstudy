package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.BatchSize;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Team {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@BatchSize(size = 100)
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<>();

}
