import java.io.*;
import java.util.*;
//import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;

public class CrashBoat extends JFrame implements ActionListener{
   
   ArrayList<String> lettersArray = new ArrayList<String>();
   ArrayList<String> tempArray = new ArrayList<String>();
   String word;
   String checkWord;
   int wordCounter = 0;
   String word1 = "Welc";
   String word2 = "ome";
   String word3 = "to";
   String word4 = "CrashBoat";
   File dictionary;
   int index;
   
   //File dictionary = new File("dictionary");
   Random randomGenerator = new Random();
   
   JPanel backgroundPanel = new JPanel(new BorderLayout());
   JPanel viewPanel = new JPanel(new FlowLayout());
   JPanel buttonPanel = new JPanel(new FlowLayout());
   JPanel radioButtonPanel = new JPanel(new FlowLayout());
   JPanel textEnterPanel = new JPanel(new FlowLayout());
   
   JMenuBar jmb = new JMenuBar();
   JMenu menu = new JMenu("Menu");
   JMenuItem dictionaryItem = new JMenuItem("New Dictionary");
   JMenuItem hardMode = new JMenuItem("Hard Mode");
   JMenuItem quitItem = new JMenuItem("Quit");
   
   //JTextPane letterArea = new JTextPane();
   JLabel letterLabel = new JLabel("", SwingConstants.CENTER);
   
   ButtonGroup bg = new ButtonGroup();
   JRadioButton jrb1 = new JRadioButton(word1);
   JRadioButton jrb2 = new JRadioButton(word2);
   JRadioButton jrb3 = new JRadioButton(word3);
   JRadioButton jrb4 = new JRadioButton(word4);
   
   JButton button1 = new JButton("Start");
   JButton button2 = new JButton("Start Over");
   JButton blankButton1 = new JButton("");
   JButton blankButton2 = new JButton("");
   
   JTextField wordCheckField = new JTextField(20);
   JButton enterButton = new JButton("Enter");
   JButton otherEnterButton = new JButton("Enter");

   public CrashBoat(){
      
      setSize(400, 325);
      setResizable(false);
      setLocationRelativeTo(null);
      setTitle("Crash Boat"); 
   
      add(backgroundPanel, BorderLayout.NORTH);
      add(textEnterPanel, BorderLayout.SOUTH);
      backgroundPanel.add(viewPanel, BorderLayout.NORTH);
      backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
      backgroundPanel.add(radioButtonPanel, BorderLayout.SOUTH);
      
      radioButtonPanel.add(jrb1);
      radioButtonPanel.add(jrb2);
      radioButtonPanel.add(jrb3);
      radioButtonPanel.add(jrb4);
      radioButtonPanel.add(otherEnterButton);
      bg.add(jrb1);bg.add(jrb2);bg.add(jrb3);bg.add(jrb4);
     
      buttonPanel.add(button1);
      buttonPanel.add(button2);
      textEnterPanel.add(wordCheckField);
      textEnterPanel.add(enterButton);
     
      viewPanel.add(letterLabel);
      letterLabel.setText("Ready");
      letterLabel.setFont(new Font("Arial", Font.BOLD, 100));
      letterLabel.setOpaque(true);
      
      setJMenuBar(jmb);
      jmb.add(menu);
      menu.add(dictionaryItem);
      menu.add(hardMode);
      menu.add(quitItem);
     
      textEnterPanel.setVisible(false);
      
      button1.addActionListener(this);
      button2.addActionListener(this);
      enterButton.addActionListener(this);
      otherEnterButton.addActionListener(this);
      hardMode.addActionListener(this);
      quitItem.addActionListener(this);
      dictionaryItem.addActionListener(this);
      
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
      
      dictionarySelect();
      getWords();
      randomize();
   }
   
   public void dictionarySelect(){
   
      String file = JOptionPane.showInputDialog(this, "Enter file you want to use:");
      dictionary = new File(file);
   }
   
   public void randomize(){

      boolean complete = false;
      while( tempArray.size() != 4 && complete == false){
         int index = randomGenerator.nextInt(lettersArray.size() );
         tempArray.add(lettersArray.get(index));
         if (!tempArray.contains(word)){
            tempArray.add(word);
         }
         else if(tempArray.size() == 4 ){
            Collections.shuffle(tempArray);
            complete = true;
         }
      }
   }
   
   public void getWords(){
   
      try{
         BufferedReader br = new BufferedReader(new FileReader(dictionary));
         while(br != null){
         
            lettersArray.add(br.readLine().toUpperCase());
         
         }
         System.out.println(lettersArray.get(0));
      }
      catch(FileNotFoundException fnf){
         System.out.println("File not Found");
      }  
      catch(IOException ioe){
         System.out.println("IO Exception");
      }
      catch(NullPointerException npe){
      
         System.out.println("End of file");
      }
   }
   
   public void radioOption(){
      
      tempArray.clear();
      randomize();
      
      word1 = tempArray.get(0);
      word2 = tempArray.get(1);
      word3 = tempArray.get(2);
      word4 = tempArray.get(3);
      jrb1.setText(word1);
      jrb2.setText(word2);
      jrb3.setText(word3);
      jrb4.setText(word4);
      //System.out.println(word1 + word2 + word3 + word4); 
             
   }
   
   public void startOver(){
   
      wordCounter = 0;
      backgroundPanel.add(radioButtonPanel, BorderLayout.SOUTH);
      radioButtonPanel.setVisible(true);
      textEnterPanel.setVisible(false);
      letterLabel.setText("Ready");
      wordCheckField.setText("");
      bg.clearSelection();
      button1.setEnabled(true);
   }
   
   public void actionPerformed(ActionEvent ae){
      
      String actionCommand = ae.getActionCommand();
      if(button1.equals(ae.getSource())){
         //randomize();
         button1.setText("Next");
         try{
            word = lettersArray.get(wordCounter);
            checkWord = word;
            int length = word.length();
            for(int j = 0; j < length; j++){
               
               letterLabel.setText(String.valueOf(word.charAt(j)));
               letterLabel.paintImmediately(letterLabel.getVisibleRect());
               //letterLabel.setText(" ");
               Thread.sleep(90);
               //letterLabel.paintImmediately(letterLabel.getVisibleRect());
               //Thread.sleep(90);
            }
              
            letterLabel.setText("     ");
            wordCounter++;
            radioOption();
            button1.setEnabled(false);
            
            
         } 
         catch(Exception e){
            System.out.println("Exception");
         }          
      }
      
      else if(button2.equals(ae.getSource())){
      
         // wordCounter = 0;
//          backgroundPanel.add(radioButtonPanel, BorderLayout.SOUTH);
//          radioButtonPanel.setVisible(true);
//          textEnterPanel.setVisible(false);
//          letterLabel.setText("Ready");
//          wordCheckField.setText("");
//          bg.clearSelection();
//          button1.setEnabled(true);
         startOver();
      }
      
      else if(enterButton.equals(ae.getSource())){
        
         checkWord = wordCheckField.getText();
         String UcWord = checkWord.toUpperCase();
         if(word.equals(UcWord)){
         
            letterLabel.setText("Success");
            bg.clearSelection();
            
         }
         else{
         
            letterLabel.setText("Missed");
            bg.clearSelection();
            enterButton.setEnabled(false);
         }
         wordCheckField.setText("");
         button1.setEnabled(true);  
      }
      
      else if(otherEnterButton.equals(ae.getSource())){
      
         if(jrb1.isSelected()){
         
            if(word.equals(word1)){
            
               System.out.println(word1);
               letterLabel.setText("Success");
               button1.setEnabled(true);
            } 
            
            else{
               
               System.out.println(word1);
               letterLabel.setText(" ");
               letterLabel.paintImmediately(letterLabel.getVisibleRect());
               //Thread.sleep(150);
               letterLabel.setText("Missed");   
            }
         }
         else if (jrb2.isSelected()){
         
            if(word.equals(word2)){
               
               System.out.println(word2);
               letterLabel.setText("Success");
               button1.setEnabled(true);
            }
            else{
               
               System.out.println(word2);
               letterLabel.setText(" ");
               letterLabel.paintImmediately(letterLabel.getVisibleRect());
               letterLabel.setText("Missed");
            }   
         }
         
         if(jrb3.isSelected()){
         
            if(word.equals(word3)){
               
               System.out.println(word3);
               letterLabel.setText("Success");
               button1.setEnabled(true);
            } 
            
            else{
               
               System.out.println(word3);
               letterLabel.setText(" ");
               letterLabel.paintImmediately(letterLabel.getVisibleRect());
               letterLabel.setText("Missed");   
            }
         }
         
         if(jrb4.isSelected()){
         
            if(word.equals(word4)){
               
               System.out.println(word4);
               letterLabel.setText("Success");
               button1.setEnabled(true);
            } 
            
            else{
               
               System.out.println(word4);
               letterLabel.setText(" ");
               letterLabel.paintImmediately(letterLabel.getVisibleRect());
               letterLabel.setText("Missed");   
            }
         }
      }
      
      else if(ae.getSource() == hardMode){
      
         //radioButtonPanel.setVisible(false);
         //textEnterPanel.setVisible(true);
        
         backgroundPanel.add(textEnterPanel, BorderLayout.SOUTH);
         radioButtonPanel.setVisible(false);
         textEnterPanel.setVisible(true);
         repaint();
         revalidate();
         
         
      }
      
      else if(ae.getSource() == quitItem){
      
         System.exit(0);
      }
      
      else if(ae.getSource() == dictionaryItem){
         
         dictionarySelect();
         tempArray.clear();
         lettersArray.clear();
         startOver();
         getWords();
         //System.out.println(tempArray.get(0));
      }
   }
   
   public static void main(String [] args){

      CrashBoat cshbt1 = new CrashBoat();
      
   }
}