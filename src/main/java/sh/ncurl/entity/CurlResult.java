package sh.ncurl.entity;

import lombok.Data;

/**
 * @author mei
 * @date 2020/5/23
 */
@Data
public class CurlResult {
    private String id;
    private String webUrl;
    private Integer expire;
}
