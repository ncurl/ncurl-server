package sh.ncurl.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sh.ncurl.entity.CurlData;
import sh.ncurl.entity.CurlResult;
import sh.ncurl.service.NcurlService;

/**
 * @author mei
 * @date 2020/5/3
 */
@Slf4j
@RestController
@RequestMapping("")
public class WebController {

    @Autowired
    private NcurlService ncurlService;

    @GetMapping()
    public String index() {
        return "Hello, world. This is ncurl.";
    }

    @GetMapping("/api/instants/{id}")
    public CurlData get(@PathVariable String id) {
        return ncurlService.get(id);
    }

    @PostMapping("/api/instants")
    public CurlResult post(@RequestBody CurlData curlData) {
        return ncurlService.save(curlData);
    }
}
