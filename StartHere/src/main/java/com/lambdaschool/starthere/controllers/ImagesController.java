package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Images;
import com.lambdaschool.starthere.services.imageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImagesController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    imageService imageService;

    @GetMapping(value = "/images",
                produces = {"application/json"})
    public ResponseEntity<?> listAllimages(HttpServletRequest request)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Images> allImages = imageService.findAll();
        return new ResponseEntity<>(allImages, HttpStatus.OK);
    }


    @GetMapping(value = "/image/{imageId}",
                produces = {"application/json"})
    public ResponseEntity<?> getimage(HttpServletRequest request,
                                      @PathVariable
                                              Long imageId)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        Images q = imageService.findimageById(imageId);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }


    @GetMapping(value = "/username/{userName}",
                produces = {"application/json"})
    public ResponseEntity<?> findimageByUserName(HttpServletRequest request,
                                                 @PathVariable
                                                         String userName)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Images> theImages = imageService.findByUserName(userName);
        return new ResponseEntity<>(theImages, HttpStatus.OK);
    }


    @PostMapping(value = "/image")
    public ResponseEntity<?> addNewimage(HttpServletRequest request, @Valid
    @RequestBody
            Images newImages) throws URISyntaxException
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        newImages = imageService.save(newImages);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newimageURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{imageid}").buildAndExpand(newImages.getimagesid()).toUri();
        responseHeaders.setLocation(newimageURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @DeleteMapping("/image/{id}")
    public ResponseEntity<?> deleteimageById(HttpServletRequest request,
                                             @PathVariable
                                                     long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        imageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
