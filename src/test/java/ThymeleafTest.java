import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Main;
import org.example.models.Node;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@SpringBootTest(classes = {Main.class})
public class ThymeleafTest {

    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void test1() throws IOException {
        Node node = objectMapper.readValue(new File("src/test/java/nodeDummy.json"), Node.class);
//        node.getChildNodes().get(0).setChildNodes(null);
        List<Node> nodes = new ArrayList<>();
        nodes.add(node);
        Context context = new Context();
        context.setVariable("nodes", nodes);
        String process = templateEngine.process("template.html", context);
        Files.write(Paths.get("output.html"), process.getBytes(StandardCharsets.UTF_8));
    }

    Node updateIds(Node node) {
        node.setId(UUID.randomUUID().toString());
        if(node.getChildNodes() != null) {
            for (Node childNode : node.getChildNodes()) {
                updateIds(childNode);
            }
        }
        return node;
    }
}
