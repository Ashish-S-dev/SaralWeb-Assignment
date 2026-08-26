package com.saralweb.assignment.SaralWeb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saralweb.assignment.SaralWeb.model.PriorityQueueItem;
import com.saralweb.assignment.SaralWeb.service.PriorityQueueService;

@RestController
@RequestMapping(value="/pq")
public class PriorityQueueContorller {
	
	@Autowired
	private PriorityQueueService priorityQueueService;
	
//	Insert an Element
	@PostMapping(value = "/queue")
	public PriorityQueueItem insertElement(@RequestBody PriorityQueueItem priorityQueueObj) {
		return priorityQueueService.insertElement(priorityQueueObj);
	}
	
//	First element of the Priority Queue
	@GetMapping(value = "/peek")
	public Optional<PriorityQueueItem> topElement() {
		return priorityQueueService.topElement();
	}
	
//	@GetMapping("/extract-min")
//	
//	@GetMapping("/extract-max")
//	
//	@getMapping()
//	
	
}
