package com.bms.bookmanagementsystem.controller;

import com.bms.bookmanagementsystem.dto.PublisherDto;
import com.bms.bookmanagementsystem.dto.request.publisher.CreatePublisherRequest;
import com.bms.bookmanagementsystem.dto.request.publisher.UpdatePublisherRequest;
import com.bms.bookmanagementsystem.helper.result.DataResult;
import com.bms.bookmanagementsystem.helper.result.Result;
import com.bms.bookmanagementsystem.service.PublisherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vq/publishers")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    // Create Publisher
    @PostMapping
    public Result createPublisher(CreatePublisherRequest request) {
        return publisherService.createPublisher(request);
    }

    // Update Publisher
    @PutMapping("/{id}")
    public Result updatePublisher(@PathVariable String id, UpdatePublisherRequest request) {
        return publisherService.updatePublisher(id, request);
    }

    // Delete Publisher
    @DeleteMapping("/{id}")
    public Result deletePublisher(@PathVariable String id) {
        return publisherService.deletePublisher(id);
    }

    // Get Publisher By id
    @GetMapping("/{id}")
    public DataResult<PublisherDto> findPublisherById(@PathVariable String id) {
        return publisherService.findPublisherById(id);
    }

    // Get All Publishers
    @GetMapping
    public DataResult<List<PublisherDto>> findAllPublishers() {
        return publisherService.findAllPublishers();
    }
}
