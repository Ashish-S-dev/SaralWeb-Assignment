package com.saralweb.assignment.SaralWeb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saralweb.assignment.SaralWeb.model.PriorityQueueItem;
import com.saralweb.assignment.SaralWeb.service.PriorityQueueService;

@RestController
@RequestMapping(value="/api/pq")
public class PriorityQueueContorller {
	
	@Autowired
	private PriorityQueueService service;
	
//	Insert an Element
	@PostMapping(value = "/queue")
	public PriorityQueueItem insertElement(@RequestBody PriorityQueueItem priorityQueueObj) {
		return service.insertElement(priorityQueueObj);
	}
	
//	First element of the Priority Queue
	@GetMapping(value = "/peek")
	public Optional<PriorityQueueItem> topElement() {
		return service.topElement();
	}
 
    // Extract Min
    @GetMapping("/extract-min")
    public ResponseEntity<PriorityQueueItem> extractMin() {
        return ResponseEntity.ok(service.extractMin());
    }

    // Extract Max
    @GetMapping("/extract-max")
    public ResponseEntity<PriorityQueueItem> extractMax() {
        return ResponseEntity.ok(service.extractMax());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<PriorityQueueItem> update(
            @PathVariable Long id,
            @RequestBody PriorityQueueItem item) {

        return ResponseEntity.ok(service.update(id, item));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.ok("Item deleted successfully");
    }

    // Is Empty
    @GetMapping("/is-empty")
    public ResponseEntity<Boolean> isEmpty() {

        return ResponseEntity.ok(service.isEmpty());
    }
	
}
