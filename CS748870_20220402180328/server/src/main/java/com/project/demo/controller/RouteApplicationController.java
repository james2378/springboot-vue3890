package com.project.demo.controller;

import com.project.demo.entity.RouteApplication;
import com.project.demo.service.RouteApplicationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *路线申请：(RouteApplication)表控制层
 *
 */
@RestController
@RequestMapping("/route_application")
public class RouteApplicationController extends BaseController<RouteApplication,RouteApplicationService> {

    /**
     *路线申请对象
     */
    @Autowired
    public RouteApplicationController(RouteApplicationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
