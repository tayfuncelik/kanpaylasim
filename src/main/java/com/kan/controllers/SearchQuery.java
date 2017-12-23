package com.kan.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchQuery {

	// import javax.ws.rs.core.MultivaluedMap;

	// @JsonDeserialize(as = MultivaluedHashMap.class)
	// private MultivaluedMap<String, Object> filterMap = new
	// MultivaluedHashMap<String, Object>();

	Map<String, Object> filterMap = new HashMap<String, Object>();
	private int start;
	private int offset;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public Map<String, Object> getFilterMap() {
		return filterMap;
	}

	public void setFilterMap(Map<String, Object> filterMap) {
		this.filterMap = filterMap;
	}

}
