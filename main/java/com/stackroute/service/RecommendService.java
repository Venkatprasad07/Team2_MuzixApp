package com.stackroute.service;


import java.util.List;

import com.stackroute.exception.RecommendAlreadyExistsException;
import com.stackroute.exception.RecommendNotFoundException;
import com.stackroute.model.Recommend;

public interface RecommendService {
	
	boolean saveRecommend(Recommend recommend) throws com.stackroute.exception.RecommendAlreadyExistsException;
	
	boolean deleteRecommend(String id) throws RecommendNotFoundException, RecommendAlreadyExistsException;
	
	public List<Recommend> getAllRecommend(String loggedInUser) throws RecommendNotFoundException;
	
	
}
