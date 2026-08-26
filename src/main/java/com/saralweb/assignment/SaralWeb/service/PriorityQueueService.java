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
	

    // Extract Min
    public PriorityQueueItem extractMin() {
        PriorityQueueItem item = priorityQueueDto.findTopByOrderByPriorityAsc()
                .orElseThrow(() -> new RuntimeException("Priority Queue is empty"));

        priorityQueueDto.delete(item);

        return item;
    }

    // Extract Max
    public PriorityQueueItem extractMax() {
        PriorityQueueItem item = priorityQueueDto.findTopByOrderByPriorityDesc()
                .orElseThrow(() -> new RuntimeException("Priority Queue is empty"));

        priorityQueueDto.delete(item);

        return item;
    }

    // Update
    public PriorityQueueItem update(Long id, PriorityQueueItem updatedItem) {

        PriorityQueueItem existingItem = priorityQueueDto.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        existingItem.setValue(updatedItem.getValue());
        existingItem.setPriority(updatedItem.getPriority());

        return priorityQueueDto.save(existingItem);
    }

    // Delete
    public void delete(Long id) {

        PriorityQueueItem item = priorityQueueDto.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        priorityQueueDto.delete(item);
    }

    // Is Empty
    public boolean isEmpty() {
        return priorityQueueDto.count() == 0;
    }
}
