package com.legend.library.service;

import com.legend.library.model.Publisher;

import java.util.List;


public interface PublisherService {

    List<Publisher> findAll();
    void addPublisher(Publisher publisher);

    Publisher findById(int id);
}
