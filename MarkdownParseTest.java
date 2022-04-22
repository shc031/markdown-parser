import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParseTest{
    @Test
    public void addition(){
        assertEquals(2,1+1);
    }

    @Test
    public void markdownTest1() throws IOException{
        Path fileName = Path.of("test-file.md");
        String content = Files.readString(fileName);
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.add("https://something.com");
        toReturn.add("some-thing.html");
        assertEquals(toReturn, MarkdownParse.getLinks(content));
    }
}