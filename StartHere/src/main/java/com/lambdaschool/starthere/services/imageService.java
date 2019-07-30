package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Images;

import java.util.List;

public interface imageService
{
    List<Images> findAll();

    Images findimageById(long id);

    List<Images> findByUserName(String username);

    void delete(long id);

    Images save(Images images);
}
