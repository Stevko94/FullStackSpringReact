package com.test.backend.Services.VideoFile;


import com.test.backend.Models.VideoFile.VideoFile;

import java.util.List;

public interface VideoFileService {

    List<VideoFile> listAll(int page);

    VideoFile getById(String  title);

    VideoFile saveOrUpdate(VideoFile product);

    void delete(String  title);



}
