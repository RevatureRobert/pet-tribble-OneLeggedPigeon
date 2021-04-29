package com.revature.tribble.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tribble {
	private int id;

	private String name;

	private String color;

	private int mass;

	private int labId;
}