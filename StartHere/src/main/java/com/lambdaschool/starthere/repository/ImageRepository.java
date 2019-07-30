package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Images;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Images, Long>
{

}
