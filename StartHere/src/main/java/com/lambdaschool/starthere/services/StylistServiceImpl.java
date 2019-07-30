package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Images;
import com.lambdaschool.starthere.models.Stylist;
import com.lambdaschool.starthere.repository.StylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "stylistService")
public class StylistServiceImpl implements StylistService{

    @Autowired
    private StylistRepository stylistrepos;

    @Override
    public List<Stylist> findAll() {
        List<Stylist> list = new ArrayList<>();
        stylistrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Stylist findStylistById(long id) {
        return stylistrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id) {
        if(stylistrepos.findById(id).isPresent()){
            stylistrepos.deleteById(id);
        } else {
            throw new ResourceNotFoundException(Long.toString(id));
        }

    }

    @Override
    public Stylist save(Stylist stylist) {
        Stylist newStylist = new Stylist();
        newStylist.setStylistUserName(stylist.getStylistUserName());
        newStylist.setPassword(stylist.getPassword());

        for (Images q : stylist.getImages())
        {
            newStylist.getImages().add(new Images(q.getimage(), newStylist));
        }
        return stylistrepos.save(newStylist);
    }

    @Override
    public Stylist update(Stylist stylist, long id) {
        return null;
    }
}
