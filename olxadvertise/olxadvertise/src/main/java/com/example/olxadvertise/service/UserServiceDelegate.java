package com.example.olxadvertise.service;

import java.util.List;
import java.util.Map;

public interface UserServiceDelegate {
	public List<Map> findByUserNames(String usernames);

	public Map findByUserName(String token);

	public boolean isLoggedInUser(String token);
}
