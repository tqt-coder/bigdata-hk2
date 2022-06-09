package com.app;

import com.app.entity.UserEntity;
import com.app.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UploadDownloadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadDownloadController.class);
    static List<String[]> data;
    static List<String[]> data2;
    static List<String[]> data3;
    @Autowired
    UserRepo userRepo;

    public UploadDownloadController() {
    }

    @GetMapping({"/download/{fileName:.+}"})
    public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
        ClassPathResource resource = null;

        try {
            logger.info("DownloadFile: {}", fileName);
            resource = new ClassPathResource("classpath:data/" + fileName);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return ((BodyBuilder) ResponseEntity.ok().header("Content-Disposition", new String[]{"attachment; filename=\"" + resource.getFilename() + "\""})).body(resource);
    }

    @GetMapping("/user")
    public ResponseEntity<?> postData() throws Exception {
        try {

            data.stream().forEach(x->{
                UserEntity e = new UserEntity();
                e.setProduct_id(x[0]);
                e.setPrice(x[1]);
                e.setStock(x[2]);
                e.setWeight(x[3]);
                e.setBrand(x[4]);
                e.setSerial_number(x[5]);
                e.setProduct(x[6]);
                e.setCategory(x[7]);
                e.setCreated_on(x[8]);
                e.setUpdated_on(x[9]);
                e.setModel_number(x[10]);
                e.setProduct_name(x[11]);
                e.setCategory_type(x[12]);
                boolean result = userRepo.addUser(e);
            });

            return new ResponseEntity<>("Insert successfull in Database Noe4j", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping({"/upload"})
    public void upload(@RequestParam MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            List<String[]> arrList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] map = line.split("\t");
                arrList.add(map);
            }


            StringBuilder salesData = new StringBuilder();
            bufferedReader.close();
            data = arrList;
//            logger.info("result: {}", data);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }

    @GetMapping({"/data"})
    public List<String[]> getData() {
        return data;
    }

    @GetMapping({"/data2"})
    public List<String[]> getData2() {
        return data2;
    }

    @PostMapping({"/upload2"})
    public void upload2(@RequestParam MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            List<String[]> arrList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] map = line.split("\t");
                arrList.add(map);
            }


            StringBuilder salesData = new StringBuilder();
            bufferedReader.close();
            data2 = arrList;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }

    @GetMapping({"/data3"})
    public List<String[]> getData3() {
        return data3;
    }

    @PostMapping({"/upload3"})
    public void upload3(@RequestParam MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            List<String[]> arrList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] map = line.split("\t");
                arrList.add(map);
            }


            StringBuilder salesData = new StringBuilder();
            bufferedReader.close();
            data3 = arrList;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }

}
