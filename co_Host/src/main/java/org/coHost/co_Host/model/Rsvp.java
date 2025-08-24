package org.coHost.co_Host.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rsvps", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "event_id" }))
public class Rsvp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "event_id", nullable = false)
	private Event event;

	@Column(nullable = false, length = 20)
	private String status;

	public Rsvp() {
	}

	public Rsvp(Long id, User user, Event event, String status) {
		this.id = id;
		this.user = user;
		this.event = event;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
