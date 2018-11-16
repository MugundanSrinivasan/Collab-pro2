package com.niit.dao;

import java.util.List;

import com.niit.models.Job;

public interface JobDAO {
	void saveJob(Job job);
	List<Job> getAllJobs();
	void deleteJob(int id);
	void updateJob(Job job);
	Job getJob(int id);
}
