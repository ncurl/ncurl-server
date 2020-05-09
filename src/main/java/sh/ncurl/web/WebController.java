package sh.ncurl.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sh.ncurl.entity.ClientRequest;
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

    @GetMapping("/api/instants/{id}")
    public ResponseEntity<ClientRequest> get(@PathVariable String id) {
        try {
            return ResponseEntity.ok(ncurlService.get(id));
        } catch (JsonProcessingException e) {
            log.error("get error. id: " + id, e);
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/api/instants")
    public ResponseEntity<String> post(@RequestBody ClientRequest body) {
        try {
            return ResponseEntity.ok(ncurlService.save(body));
        } catch (JsonProcessingException e) {
            log.error("post error" + body, e);
            return ResponseEntity.badRequest().build();
        }
    }
}
