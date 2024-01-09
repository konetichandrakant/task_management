package com.taskmanagement.repository;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagement.model.TaskDetails;

@Repository
public interface TaskDetailsRepo extends CouchbaseRepository<TaskDetails, String> {

}
