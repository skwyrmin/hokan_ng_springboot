package org.freakz.hokan_ng_springboot.bot.jpa.repository.service;

import lombok.extern.slf4j.Slf4j;
import org.freakz.hokan_ng_springboot.bot.jpa.entity.Property;
import org.freakz.hokan_ng_springboot.bot.jpa.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Petri Airio on 27.3.2015.
 */
@Service
@Slf4j
public class PropertyRepositoryService implements PropertyService {

  @Autowired
  private PropertyRepository repository;

  @Override
  public List<Property> findAll() {
    return repository.findAll();
  }

  @Override
  public Property save(Property property) {
    return repository.save(property);
  }

  @Override
  public void delete(Property object) {
    repository.delete(object);
  }
}
