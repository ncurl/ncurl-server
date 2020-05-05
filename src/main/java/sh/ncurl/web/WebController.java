package sh.ncurl.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sh.ncurl.service.NcurlService;

/**
 * @author mei
 * @date 2020/5/3
 */
@Slf4j
@Controller
@RestController("/")
public class WebController {

    @Autowired
    private NcurlService ncurlService;

    @GetMapping()
    public String index() {
        return "Hello, world. This is ncurl.";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable String id) {
        return ncurlService.get(id);
    }

    @PostMapping()
    public String post(@RequestBody String body) {
        return ncurlService.save(body);
    }
}
