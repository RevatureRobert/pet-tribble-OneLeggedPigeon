package com.revature.tribble.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tribble {
	@Id
	@GeneratedValue
	private int id;

	private String name;

	private String color;

	private int mass;
}