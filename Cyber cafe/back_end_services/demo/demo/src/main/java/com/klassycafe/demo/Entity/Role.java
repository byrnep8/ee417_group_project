package com.klassycafe.demo.Entity;

import java.util.List;

// import jakarta.persistence.*;

// @Entity(name="roles")
public class Role {
	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// @Column(nullable = false)
    // @Enumerated(EnumType.STRING)
//    private RoleName name;
	
	// @ManyToMany(mappedBy = "roles")
	private List<User> users;
	
	public Role() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public RoleName getName() {
//		return name;
//	}
//
//	public void setName(RoleName name) {
//		this.name = name;
//	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
