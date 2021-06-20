package com.test.backend.Controller;

import com.test.backend.Models.VideoFile.VideoFile;
import com.test.backend.Services.VideoFile.VideoFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("videoFiles")
public class VideoFileController {
    private VideoFileService VideoFileService;

    @Autowired
    public void setVideoFileService(VideoFileService VideoFileService) {
        this.VideoFileService = VideoFileService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/getAll/{page}", produces = "application/json")
    public List<VideoFile> getAll(@PathVariable Integer page){
        try {
            List<VideoFile> list = VideoFileService.listAll(page);
            if (list.isEmpty()) {
                return null;
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/getById/{title}", produces = "application/json")
    public VideoFile getById(@PathVariable String title){
        try {
            return VideoFileService.getById(title);
        } catch (Exception e) {
            return null;
        }
    }


    @CrossOrigin(origins = "*")
    @PostMapping(path = "/save", produces = "application/json")
    public VideoFile save(@RequestBody VideoFile VideoFile){
        try {
            return VideoFileService.saveOrUpdate(VideoFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping (path = "/deleteById/{title}")
    public void deleteById(@PathVariable String title){
        try {
             VideoFileService.delete(title);
        } catch (Exception e) {

        }
    }
}
