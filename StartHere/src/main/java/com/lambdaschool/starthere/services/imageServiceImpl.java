package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Images;
import com.lambdaschool.starthere.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "imageService")
public class imageServiceImpl implements imageService
{
    @Autowired
    private ImageRepository imagerepos;

    @Override
    public List<Images> findAll()
    {
        List<Images> list = new ArrayList<>();
        imagerepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Images findimageById(long id)
    {
        return imagerepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (imagerepos.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (imagerepos.findById(id).get().getStylist().getStylistUserName().equalsIgnoreCase(authentication.getName()))
            {
                imagerepos.deleteById(id);
            } else
            {
                throw new ResourceNotFoundException(id + " " + authentication.getName());
            }
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Images save(Images images)
    {
        return imagerepos.save(images);
    }

    @Override
    public List<Images> findByUserName(String username)
    {
        List<Images> list = new ArrayList<>();
        imagerepos.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getStylist().getStylistUserName().equalsIgnoreCase(username));
        return list;
    }
}
