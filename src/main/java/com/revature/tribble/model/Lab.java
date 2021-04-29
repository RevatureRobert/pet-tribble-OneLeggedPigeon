package com.revature.tribble.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lab {
	private int id;

	private String name;

	private List<Tribble> tribbles;
}
