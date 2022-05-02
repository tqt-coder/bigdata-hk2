package com.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadDownloadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadDownloadController.class);
    static StringBuilder data = new StringBuilder();

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

        return ((BodyBuilder)ResponseEntity.ok().header("Content-Disposition", new String[]{"attachment; filename=\"" + resource.getFilename() + "\""})).body(resource);
    }

    @PostMapping({"/upload"})
    public void upload(@RequestParam MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            HashMap salesMap = new HashMap();

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] map = line.split("\t");
                salesMap.put(map[0].toUpperCase(), (int)Math.round(Double.parseDouble(map[1])));
            }

            StringBuilder salesData = new StringBuilder();
            salesData.append(salesMap.containsKey("ALIENWARE") ? salesMap.get("ALIENWARE") + "," : "0,");
            salesData.append(salesMap.containsKey("APPLE") ? salesMap.get("APPLE") + "," : "0,");
            salesData.append(salesMap.containsKey("DELL") ? salesMap.get("DELL") + "," : "0,");
            salesData.append(salesMap.containsKey("LENOVO") ? salesMap.get("LENOVO") + "," : "0,");
            salesData.append(salesMap.containsKey("SAMSUNG") ? salesMap.get("SAMSUNG") + "," : "0,");
            salesData.append(salesMap.containsKey("SONY") ? salesMap.get("SONY") + "," : "0,");
            salesData.append(salesMap.containsKey("TOSHIBA") ? salesMap.get("TOSHIBA") + "," : "0,");
            bufferedReader.close();
            logger.info("UploadData: {}", salesData.toString());
            data.append(salesData.toString() + "\n");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }

    @GetMapping({"/data"})
    public String getData() {
        return data.toString();
    }
}
