package sh.ncurl.entity;

import lombok.Data;

import java.util.List;

/**
 * @author bohan
 */
@Data
public class CurlData {
    private List<String> commands;
    private String response;
}
