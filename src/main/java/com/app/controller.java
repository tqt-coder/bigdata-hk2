package com.app;

import com.app.entity.UserEntity;
import com.app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {


    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/count")
    public String count() {
        return "Count";
    }

    @GetMapping("/sale")
    public String sale() {
        return "SalePrice";
    }

    @GetMapping("/PieChartProduct")
    public String pieChartProduct(){
        return "PieChartProduct";
    }

    @GetMapping("/LineChart")
    public String lineChart(){
        return "LineChart";
    }

}
