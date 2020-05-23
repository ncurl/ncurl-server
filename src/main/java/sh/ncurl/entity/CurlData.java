package sh.ncurl.entity;

import lombok.Data;

import java.util.List;

/**
 * @author bohan
 */
@Data
public class CurlData {
    private String commands;
    private List<Content> contents;
    /**
     * 有效时间单位秒
     */
    private Integer expire;
}
