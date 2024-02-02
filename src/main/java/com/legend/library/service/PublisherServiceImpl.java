package com.legend.library.service;

import com.legend.library.dao.PublisherRepository;
import com.legend.library.model.Author;
import com.legend.library.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    private PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public void addPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    @Override
    public Publisher findById(int id) {
        Optional<Publisher> result = publisherRepository.findById(id);

        Publisher publisher = null;
        if(result.isPresent()) {
            publisher = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find author id - " + id);
        }
        return publisher;
    }

}
