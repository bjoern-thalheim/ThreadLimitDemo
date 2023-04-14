package com.example.demo.events;

import java.util.Objects;
import java.util.UUID;

public class DemoEvent {
	
	private UUID id = UUID.randomUUID();

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DemoEvent other = (DemoEvent) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "DemoEvent [id=" + id + "]";
	}

	public String getId() {
		return id.toString();
	}
}
