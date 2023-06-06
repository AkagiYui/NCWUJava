package com.akagiyui.springboot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author AkagiYui
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private Long id;
	private String name;
	private Integer age;

}
