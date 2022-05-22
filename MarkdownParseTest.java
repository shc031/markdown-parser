
import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParseTest{
    /*
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
*/
    @Test
    public void labReportMDTest1() throws IOException{
        Path fileName = Path.of("labReportTestFile1.md");
        String content = Files.readString(fileName);
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.add("`google.com");
        toReturn.add("google.com");
        toReturn.add("ucsd.edu");
        assertEquals(toReturn, MarkdownParse.getLinks(content));
    }

    @Test
    public void labReportMDTest2() throws IOException{
        Path fileName = Path.of("labReportTestFile2.md");
        String content = Files.readString(fileName);
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.add("a.com");
        toReturn.add("a.com(())");
        toReturn.add("example.com");
        assertEquals(toReturn, MarkdownParse.getLinks(content));
    }

    @Test
    public void labReportMDTest3() throws IOException{
        Path fileName = Path.of("labReportTestFile3.md");
        String content = Files.readString(fileName);
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.add("https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule");
        assertEquals(toReturn, MarkdownParse.getLinks(content));
    }
    
}
