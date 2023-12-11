package org.example.models;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Node {

    private String id = UUID.randomUUID().toString();
    private String name;
    private List<Node> childNodes;
    private String data;

    public String getData() {
        if(childNodes == null) {
            data = "lfjlajfjfjf fsafdsa";
        }
        return data;
    }

}
