package com.bms.bookmanagementsystem.service;

import com.bms.bookmanagementsystem.dto.PublisherDto;
import com.bms.bookmanagementsystem.dto.converter.PublisherDtoConverter;
import com.bms.bookmanagementsystem.dto.request.publisher.CreatePublisherRequest;
import com.bms.bookmanagementsystem.dto.request.publisher.UpdatePublisherRequest;
import com.bms.bookmanagementsystem.exception.publisher.PublisherAlreadyExistException;
import com.bms.bookmanagementsystem.exception.publisher.PublisherNotFoundException;
import com.bms.bookmanagementsystem.helper.BusinessMessage;
import com.bms.bookmanagementsystem.helper.DateHelper;
import com.bms.bookmanagementsystem.helper.LogMessage;
import com.bms.bookmanagementsystem.helper.result.DataResult;
import com.bms.bookmanagementsystem.helper.result.Result;
import com.bms.bookmanagementsystem.helper.result.SuccessDataResult;
import com.bms.bookmanagementsystem.helper.result.SuccessResult;
import com.bms.bookmanagementsystem.model.Publisher;
import com.bms.bookmanagementsystem.repository.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherDtoConverter converter;

    public PublisherService(PublisherRepository publisherRepository,
                            PublisherDtoConverter converter) {
        this.publisherRepository = publisherRepository;
        this.converter = converter;
    }

    // Create Publisher
    public Result createPublisher(CreatePublisherRequest request) {
        Publisher publisher = new Publisher();
        checkPublisherExistsByName(request.getName());

        publisher.setName(request.getName());
        publisher.setWebsite(request.getWebsite());
        publisher.setIsActive(true);
        publisher.setCreatedAt(DateHelper.getCurrentDateTime());
        publisher.setUpdatedAt(DateHelper.getCurrentDateTime());

        publisherRepository.save(publisher);
        log.info(LogMessage.General.DATA_SUCCESSFULLY_SAVED);
        return new SuccessResult(BusinessMessage.General.DATA_SUCCESSFULLY_SAVED);
    }

    // Update Publisher
    public Result updatePublisher(String id, UpdatePublisherRequest request) {
        Publisher publisher = findPublisherByPublisherId(id);
        checkPublisherExistsByName(request.getName());

        publisher.setName(request.getName());
        publisher.setWebsite(request.getWebsite());
        publisher.setUpdatedAt(DateHelper.getCurrentDateTime());

        publisherRepository.save(publisher);
        log.info(LogMessage.General.DATA_SUCCESSFULLY_UPDATED);
        return new SuccessResult(BusinessMessage.General.DATA_SUCCESSFULLY_UPDATED);
    }

    // Delete Publisher
    public Result deletePublisher(String id) {
        Publisher publisher = findPublisherByPublisherId(id);
        publisher.setIsActive(false);

        publisherRepository.save(publisher);
        log.info(LogMessage.General.DATA_SUCCESSFULLY_DELETED);
        return new SuccessResult(BusinessMessage.General.DATA_SUCCESSFULLY_DELETED);
    }

    // Find Publisher By id
    public DataResult<PublisherDto> findPublisherById(String id) {
        Publisher publisher = findPublisherByPublisherId(id);

        log.info(LogMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
        return new SuccessDataResult<>(converter.convert(publisher),
                BusinessMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
    }

    // Find all Publishers
    public DataResult<List<PublisherDto>> findAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAllByIsActive(true);

        if (publishers.isEmpty()) {
            log.info(LogMessage.Publisher.PUBLISHER_NOT_FOUND);
            throw new PublisherNotFoundException(BusinessMessage.Publisher.PUBLISHER_NOT_FOUND);
        }

        log.info(LogMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
        return new SuccessDataResult<>(converter.convert(publishers),
                BusinessMessage.General.DATA_SUCCESSFULLY_RETRIEVED);
    }

    // Check Publisher Exists by Publisher Name
    private void checkPublisherExistsByName(String name) {
        if (publisherRepository.existsByNameIgnoreCase(name)) {
            log.error(LogMessage.Publisher.PUBLISHER_ALREADY_EXISTS);
            throw new PublisherAlreadyExistException(BusinessMessage.Publisher.PUBLISHER_ALREADY_EXISTS);
        }
    }

    // Find Publisher by Publisher id
    protected Publisher findPublisherByPublisherId(String id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> {
            log.error(LogMessage.Publisher.PUBLISHER_NOT_FOUND);
            throw new PublisherNotFoundException(BusinessMessage.Publisher.PUBLISHER_NOT_FOUND);
        });

        if (!publisher.getIsActive()) {
            log.error(LogMessage.Publisher.PUBLISHER_NOT_FOUND);
            throw new PublisherNotFoundException(BusinessMessage.Publisher.PUBLISHER_NOT_FOUND);
        }

        return publisher;
    }
}
