package cn.myesn.controller;

import cn.myesn.service.StuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("stu")
public class StuController {

    private final StuService stuService;

    public StuController(StuService stuService) {
        this.stuService = stuService;
    }

    @GetMapping("get")
    public Object get(int id) {
        return stuService.find(id);
    }

    @PostMapping("save")
    public Object save() {
        stuService.save();
        return "ok";
    }

    @PostMapping("update")
    public Object update(int id) {
        stuService.update(id);
        return "ok";
    }

    @PostMapping("delete")
    public Object delete(int id) {
        stuService.delete(id);
        return "ok";
    }
}
