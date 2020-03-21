package tarq3;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project {

	HashMap<String, Integer> map = new HashMap<String, Integer>();
    public static void main(String[] args){
        Project project = new Project();
        Scanner INPUT_TEXT = project.readFile();
        project.analyse(INPUT_TEXT);
        project.showResults();
    }
    public void analyse(Scanner scanner) {
        String pattern = "[a-zA-Z'-]+";
        Pattern r = Pattern.compile(pattern);
        while (scanner.hasNext()) {
            String Stringcandidate = scanner.next();
            Matcher matcher = r.matcher(Stringcandidate);
            if (matcher.find()) {
                String matchedWord = matcher.group();
                //System.out.println(matchedWord); 
                this.addWord(matchedWord);
            }
        }
        scanner.close();
    }
    public void addWord(String matchedWord) {
    	
        if (map.containsKey(matchedWord.toLowerCase())) {
            int occurrence = map.get(matchedWord.toLowerCase());
            occurrence++;
            map.put(matchedWord.toLowerCase(), occurrence);
        } else {
            map.put(matchedWord.toLowerCase(), 1);
        }
    }
    @SuppressWarnings("resource")
	public Scanner readFile() {
        Scanner scanner = null;     
         try { 
        	 scanner = new Scanner(new File("text.txt")).useDelimiter(" "); 
    	 } catch (Exception e) {
    		 e.printStackTrace(); 
    	 }
        return scanner;
    }

    public void showResults() {
    	map.entrySet()
    	  .stream()
    	  .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
    	  .forEach(System.out::println);
    }
    
}