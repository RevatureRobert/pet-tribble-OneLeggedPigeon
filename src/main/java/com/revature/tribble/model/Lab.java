package com.revature.tribble.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lab {
	@Id
	@GeneratedValue
	private int id;

	@OneToMany
	@JoinColumn
	private List<Tribble> tribbles;
}
