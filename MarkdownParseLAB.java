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
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);

            if (openBracket == -1 || closeBracket == -1 || openParen == -1 || closeParen == -1) {
                break;
            } 
            // To differentiate between a link and image
            if(openBracket != 0) {
                char character = markdown.charAt(openBracket - 1);
                if(character == '!') {
                    currentIndex = closeParen +1;
                    continue;
                }
            }
            int repeat = 1;
            while(markdown.charAt(closeParen-repeat) == '(' && markdown.charAt(closeParen+repeat) == ')') {
                repeat++;
            }
            closeParen += repeat-1;
            //Bracket and Parenthesis should be consecutive
            if (closeBracket + 1 != openParen) {
                if(markdown.charAt(closeBracket - 1) != '\\') {
                    currentIndex += 1;
                    continue;
                }
            }
            String substring = markdown.substring(openParen + 1, closeParen);
            //Link should contain "."
            if(substring.contains(".")) {
                if(!substring.contains("..")) {
                    for ( int z = openParen +1 ; z < closeParen ; z++) {
                        if(markdown.charAt(z) == '\n') {
                            substring = "";
                            substring = markdown.substring(openParen + 1, z) + markdown.substring(z+ 1, closeParen);
                        }
                        // if(substring.charAt(z) == '`') {
                        //     substring = "";
                        //     substring = markdown.substring(openParen + 1, z) + "%60" + markdown.substring(z+ 1, closeParen);
                        // }
                        //OR 
                        //int ascii = (int) character;
                    }
                    toReturn.add(substring.trim());
                }
            }
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
        System.out.println("Tests End");
    }
}