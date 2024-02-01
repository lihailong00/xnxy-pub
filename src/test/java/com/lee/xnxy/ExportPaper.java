package com.lee.xnxy;

import com.lee.xnxy.model.dao.paper.Paper;
import com.lee.xnxy.service.PaperService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SpringBootTest
public class ExportPaper {
    @Resource
    private PaperService paperService;
//    @Test
    public void exportFiles() throws IOException {
        List<Paper> paperList = paperService.list();
        for (Paper paper : paperList) {
            String paperName = paper.getPaperName();
            byte[] paperContent = paper.getPaperContent();
            Path path = Paths.get("d:/files/" + paperName);
            Files.write(path, paperContent);
        }
    }
}
