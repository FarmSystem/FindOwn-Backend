package Farm.Team4.findOwn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class TestController {
    @PostMapping("/image")
    public byte[] checkImage(MultipartFile file) throws IOException {
        return file.getBytes();
    }
}
