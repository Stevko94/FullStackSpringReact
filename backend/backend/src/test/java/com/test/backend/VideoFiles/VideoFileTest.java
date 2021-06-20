package com.test.backend.VideoFiles;

import com.test.backend.Models.VideoFile.VideoFile;
import com.test.backend.Repositories.VideoFile.VideoFileRepository;
import com.test.backend.Services.VideoFile.VideoFileService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.Optional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VideoFileTest {
    private static final String TITLE = "Title";
    private static final String LINK = "https://www.youtube.com/watch?v=IYCa1F-OWmk";
    private static final Date CREATION_DATE = Date.valueOf("2021-01-01");

    @Autowired
    private VideoFileRepository videoFileRepository;
    @Autowired
    private VideoFileService videoFileService;


    @Before
    public void setUp() throws Exception {

    }

    @Test
    @Order(1)
    public void saveVideoFile() {
        //given
        VideoFile VideoFileActual = new VideoFile();
        VideoFileActual.setLink(LINK);
        VideoFileActual.setTitle(TITLE);
        VideoFileActual.setCreationDate(CREATION_DATE);



        VideoFile responnse =videoFileRepository.save(VideoFileActual);

        //then
        Assert.assertNotNull(responnse);

        Assert.assertEquals( responnse.getTitle(),TITLE);
        Assert.assertEquals( responnse.getLink(),LINK);
        Assert.assertEquals( responnse.getCreationDate(), CREATION_DATE);
    }
    @Test
    @Order(2)
    public void testGetVideoFileById() {
        //given
        VideoFile VideoFileActual = new VideoFile();
        VideoFileActual.setLink(LINK);
        VideoFileActual.setTitle(TITLE);
        VideoFileActual.setCreationDate(CREATION_DATE);



        Optional<VideoFile> expected =videoFileRepository.findById(TITLE);

        //then
        Assert.assertNotNull(expected);
        VideoFile VideoFileExpected = expected.get();

        Assert.assertEquals( VideoFileActual.getTitle(),TITLE);
        Assert.assertEquals( VideoFileActual.getLink(),LINK);
        Assert.assertEquals( VideoFileActual.getCreationDate(), CREATION_DATE);
    }




}
