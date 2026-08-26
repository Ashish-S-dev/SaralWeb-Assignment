package com.saralweb.assignment.SaralWeb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saralweb.assignment.SaralWeb.dto.PriorityQueueDto;
import com.saralweb.assignment.SaralWeb.model.PriorityQueueItem;

@Service
public class PriorityQueueService {
	
	@Autowired
	private PriorityQueueDto priorityQueueDto;
	
//	Insert and Element into the queue
	public PriorityQueueItem insertElement(PriorityQueueItem priorityQueueItem) {
		return priorityQueueDto.save(priorityQueueItem);
	}
	
	
//	Accesssing the top element of the priotity queue, top means the element whose priority is greater
//	Smaller Value Higher Probability 
	public Optional<PriorityQueueItem> topElement() {
		
		Optional<PriorityQueueItem> topItem =  priorityQueueDto.findFirstByOrderByPriorityAscIdAsc();
		return topItem;
		
	}
}
