package com.saralweb.assignment.SaralWeb.dto;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saralweb.assignment.SaralWeb.model.PriorityQueueItem;



@Repository
public interface PriorityQueueDto extends CrudRepository<PriorityQueueItem, Integer> {
	// All the database operation can be done by the repository, we don't need to do Anything
	public Optional<PriorityQueueItem> findFirstByOrderByPriorityAscIdAsc();
	
	public Optional<PriorityQueueItem> findTopByOrderByPriorityAsc();
	
	public Optional<PriorityQueueItem> findTopByOrderByPriorityDesc();

	public Optional<PriorityQueueItem> findById(Long id);
}
