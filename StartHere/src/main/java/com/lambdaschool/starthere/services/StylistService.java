package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Stylist;

import java.util.List;

public interface StylistService {
    List<Stylist> findAll();

    Stylist findStylistById(long id);

    void delete(long id);

    Stylist save(Stylist stylist);

    Stylist update(Stylist stylist, long id);
}
