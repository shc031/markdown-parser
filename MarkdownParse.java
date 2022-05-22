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
            
            int exclamation = markdown.indexOf("!");
            int openBracket = markdown.indexOf("[", currentIndex);
            if(openBracket == -1){
                break;
            }
            int closeBracket = markdown.indexOf("]", openBracket);
            if(closeBracket == -1){
                break;
            }


            //check if there is `` 
            int Backtick = markdown.indexOf("`", currentIndex);
            int nextline = markdown.indexOf("\n", Backtick);
            if(Backtick != -1 && Backtick <= openBracket){
                if(nextline != -1){
                    int NextBacktick = markdown.indexOf("`", Backtick + 1);
                    if(NextBacktick != -1 && NextBacktick <= nextline){
                        currentIndex = NextBacktick + 1;
                        continue;
                    }
                }
            }

            //check if it skip a line between ()
            nextline = markdown.indexOf("\n", openBracket);
            int howManyLine = 0;
            while(nextline < closeBracket){
                howManyLine += 1;
                currentIndex = nextline + 1;
                nextline = markdown.indexOf("\n", nextline + 1);
            }
            if(howManyLine > 2){
                continue;
            }
            
            
            /*
            System.out.println(Backtick + " and "+ openBracket);
            if(Backtick != -1 && Backtick <= openBracket){
                int NextBacktick = markdown.indexOf("`", Backtick + 1);
                System.out.println(NextBacktick);
                if(NextBacktick != -1){
                    currentIndex = NextBacktick + 1;
                    System.out.println("enter");
                    continue;
                }
            }
            */
            int openParen = markdown.indexOf("(", closeBracket);
            if(openParen == -1){
                break;
            }
            int closeParen = markdown.indexOf(")", openParen);
            if(closeParen == -1){
                break;
            }
            //checks if () is in ()
            nextline = markdown.indexOf("\n", openParen);
            int temp = markdown.indexOf("(", closeBracket);
            while(temp < nextline && temp < closeParen && closeParen < nextline){
                temp = markdown.indexOf("(", temp + 1);
                if(temp > nextline || temp == -1  ||  temp > closeParen){
                    break;}
                closeParen = markdown.indexOf(")", closeParen + 1);
            }

            //check if it skip a line in []
            nextline = markdown.indexOf("\n", openParen);
            howManyLine = 0;
            while(nextline < closeParen){
                howManyLine += 1;
                currentIndex = nextline + 1;
                nextline = markdown.indexOf("\n", nextline + 1);
            }
            if(howManyLine > 2){
                continue;
            }


            if(exclamation == -1 ||  exclamation != openBracket - 1){
                nextline = markdown.indexOf("\n", openParen);
                int end = markdown.indexOf("\n", nextline + 1);
                //to contain URL without extra line
                if(nextline < closeParen && nextline != -1 && end < closeParen && end != -1){
                    toReturn.add(markdown.substring(nextline + 1, end - 1));
                }
                else{
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                }
            }
            currentIndex = closeParen + 1;
           
        }

        return toReturn;
    }

    /*
    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
        
        int i;
        for(i = 0; i<content.length(); i++){
            System.out.println(i + ": " + content.charAt(i));
        }
        
    }
    */
}
