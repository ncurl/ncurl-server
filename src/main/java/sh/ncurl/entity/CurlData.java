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
}
