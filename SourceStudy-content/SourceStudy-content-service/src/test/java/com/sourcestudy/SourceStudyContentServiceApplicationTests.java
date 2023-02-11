package com.sourcestudy;

import com.sourcestudy.content.mapper.CourseBaseMapper;
import com.sourcestudy.content.model.po.CourseBase;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {SourceStudyContentServiceApplication.class})
@RunWith(SpringRunner.class)
public class SourceStudyContentServiceApplicationTests {

    @Autowired
    private CourseBaseMapper courseBaseMapper;

    @Test
    public void testCourseBaseMapper() {
        CourseBase courseBase = courseBaseMapper.selectById(22);
        Assertions.assertNotNull(courseBase);
    }

}
