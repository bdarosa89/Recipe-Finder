
package recipefinder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import org.json.JSONObject;
public class RecipeFinder extends JFrame implements ActionListener {
    private final JLabel searchLabel;
    private final JTextField searchField;
    private final JTextArea resultsBox;
    private final JButton searchButton;
    private String searchString;
    Recipes recipeList = new Recipes();
    RecipeFinder(){
        GridBagConstraints layoutConst;
        
        setTitle("Recipe Finder");
        searchLabel = new JLabel();
        searchField = new JTextField(15);
        searchField.setEditable(true);
        resultsBox = new JTextArea(30,30);
        resultsBox.setEditable(false);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchField.addActionListener(this);
        setLayout(new GridBagLayout());
        
        layoutConst = new GridBagConstraints();
        layoutConst.gridx=0;
        layoutConst.gridy=0;
        layoutConst.insets = new Insets(10,10,10,10);
        add(searchLabel, layoutConst);        
        
        layoutConst = new GridBagConstraints();
        layoutConst.gridx=0;
        layoutConst.gridy=1;
        layoutConst.insets = new Insets(10,10,10,10);
        add(searchField, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.gridx=0;
        layoutConst.gridy=2;
        layoutConst.insets = new Insets(10,10,10,10);
        add(resultsBox, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.gridx=1;
        layoutConst.gridy=1;
        layoutConst.insets = new Insets(10,10,10,10);
        add(searchButton, layoutConst);       
    }
 
    
     @Override//on search or enter get user string and query api
    public void actionPerformed(ActionEvent e) {
        searchString = searchField.getText().replace(" ", ",");
        int i ;
        int loopLimit =  recipeList.getCount();
        JSONObject jsonObj = getUrlContent();
        for (i=0;i<30;i++){
       resultsBox.append(jsonObj.getJSONArray("recipes").getJSONObject(i).getString("title")+ "\n");
        
              }
        
        
    }
    
    //API call
    private  JSONObject getUrlContent(){
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL("http://food2fork.com/api/search?key=062ed3807a0059019a492bc5451a5eae&q="+ searchString);//set variable for search and api key
            URLConnection urlConnection = url.openConnection();
            try (BufferedReader bufferRead = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                String line;
                while ((line = bufferRead.readLine()) != null){
                    content.append(line + "\n");
                    
                }
            }
        }
        catch(Exception e){
        }
        JSONObject contentJson = new JSONObject(content.toString());
        recipeList.setCount(contentJson.getInt("count"));
        return contentJson;
    }
    
    public static void main(String[] args) {
        
        RecipeFinder myFrame = new RecipeFinder();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setVisible(true);
    }

   
    
}
