package com.test.backend.Services.VideoFile;

import com.test.backend.Models.VideoFile.VideoFile;
import com.test.backend.Repositories.VideoFile.VideoFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoFileServiceImpl implements VideoFileService {
    private VideoFileRepository videoFileRepository;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    public VideoFileServiceImpl(VideoFileRepository videoFileRepository) {
        this.videoFileRepository = videoFileRepository;
    }

    /**
     * get all VideoFile from database
     * @return
     */
    @Override
    public List<VideoFile> listAll(int page) {
        Pageable pageable = PageRequest.of(page-1, 5);
        List<VideoFile> listVideoFiles = new ArrayList<>();
        videoFileRepository.findAll(pageable).forEach(listVideoFiles::add);
        return listVideoFiles;
    }


    /**
     * get VideoFile element from database by title
     * @param title
     * @return
     */
    @Override
    public VideoFile getById(String title) {
        VideoFile result = null;
        Optional<VideoFile> casefile = videoFileRepository.findById(title);
        if(casefile.isPresent()){
            result =  casefile.get();
        }
        return result;
    }

    /**
     * Save or Update VideoFile element
     * @param videoFile
     * @return
     */
    @Override
    public VideoFile saveOrUpdate(VideoFile videoFile) {
        videoFileRepository.save(videoFile);
        return videoFile;
    }

    /**
     * Set Active to 0 on VideoFile element
     * @param title
     */
    @Override
    public void delete(String title) {
        videoFileRepository.deleteById(title);
    }




}
