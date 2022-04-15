//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            
            int exclamation = -1;
            exclamation = markdown.indexOf("!");
            openBracket = markdown.indexOf("[", currentIndex);
            closeBracket = markdown.indexOf("]", openBracket);
            openParen = markdown.indexOf("(", closeBracket);
            closeParen = markdown.indexOf(")", openParen);
            if(exclamation == -1 ||  exclamation != openBracket - 1){
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            currentIndex = closeParen + 1;
            System.out.println(currentIndex);
           
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
        /*
        int i;
        for(i = 0; i<content.length(); i++){
            System.out.println(i + ": " + content.charAt(i));
        }
        */
    }
}
