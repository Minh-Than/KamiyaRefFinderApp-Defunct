import java.util.Scanner;
import java.awt.EventQueue;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class kmyref extends javax.swing.JFrame{
   int size = 500;

   private final JSplitPane splitPane;  // split the window in top and bottom
   private final JPanel topPanel;       // container panel for the top
   private final JPanel bottomPanel;    // container panel for the bottom
   
   // container panel for the inputs
   // text fields for the inputs
   private final JPanel inputPanel;     
   private final JTextField sqrIntText;     
   private final JTextField sqrSqrtText;
   private final JTextField prefIntText;
   private final JTextField prefSqrtText;

   // container panel for the buttons
   // text fields for the buttons
   private final JPanel buttonPanel;
   private final JButton findButton;
   private final JButton nextButton;
   private final JButton backButton;

   static boolean isPowerOfTwo(int n){
      if (n == 0)
         return false;

      return (int)(Math.ceil((Math.log(n) / Math.log(2))))
            == (int)(Math.floor(((Math.log(n) / Math.log(2)))));
   }

   ActionListener findButtonActionListener = new ActionListener(){
      public void actionPerformed(ActionEvent e){
         int sqrInt = 0; 
         int sqrSqrt = 0;
         int prefInt = 0;
         int prefSqrt = 0;
         boolean hasPref = false; // bool var for checking if there's pref length

         // No pref 
         if(prefIntText.getText().equals("") && prefSqrtText.getText().equals("")){
            try{
               sqrInt = Integer.parseInt(sqrIntText.getText());
               sqrSqrt = Integer.parseInt(sqrSqrtText.getText());
            }
            catch (NumberFormatException ex){
               // ex.printStackTrace();
               JOptionPane.showMessageDialog(null, "The input must only be integer. Try Again.","Wrong Input Format", JOptionPane.WARNING_MESSAGE);
            }
         } else { // Has pref
            try{
               sqrInt = Integer.parseInt(sqrIntText.getText());
               sqrSqrt = Integer.parseInt(sqrSqrtText.getText());
               prefInt = Integer.parseInt(prefIntText.getText());
               prefSqrt = Integer.parseInt(prefSqrtText.getText());
               hasPref = true;
            }
            catch (NumberFormatException ex){
               // ex.printStackTrace();
               JOptionPane.showMessageDialog(null, "The input must only be integer. Try Again.","Wrong Input Format", JOptionPane.WARNING_MESSAGE);
            } 
         }

         if(isPowerOfTwo(sqrInt) == true && sqrSqrt == 0){
            JOptionPane.showMessageDialog(null, "Just divide in half once or multiple times you massive knobhead.","Dumb Bitch Message", JOptionPane.WARNING_MESSAGE);
         }
      }
   };

   public kmyref(){
      splitPane = new JSplitPane();

      topPanel = new JPanel();         // our top component
      bottomPanel = new JPanel();      // our bottom component

      inputPanel = new JPanel(new GridBagLayout());
      sqrIntText = new JTextField();
      sqrSqrtText = new JTextField();
      prefIntText = new JTextField();
      prefSqrtText = new JTextField();

      buttonPanel = new JPanel();
      findButton = new JButton("Find");
      nextButton = new JButton("Next");
      backButton = new JButton("Previous");

      setPreferredSize(new Dimension(size, size));
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      getContentPane().setLayout(new GridLayout());
      getContentPane().add(splitPane);   
      setTitle("Kamiya Reference Finder");

      splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  // we want it to split the window verticaly
      splitPane.setDividerLocation(size / 5);               // the initial position of the divider is 200 (our window is 400 pixels high)
      splitPane.setTopComponent(topPanel);                  // at the top we want our "topPanel"
      splitPane.setBottomComponent(bottomPanel);            // and at the bottom we want our "bottomPanel"

      topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
      topPanel.add(inputPanel);
      topPanel.add(buttonPanel);

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.fill=GridBagConstraints.HORIZONTAL;

      gbc.gridx = 0; gbc.gridy = 0; 
      inputPanel.add(new JLabel("Square Length:"), gbc);
      gbc.gridx = 1; gbc.gridy = 0;
      inputPanel.add(sqrIntText, gbc);
      gbc.gridx = 2; gbc.gridy = 0; gbc.weightx=0;
      inputPanel.add(new JLabel("+"), gbc);
      gbc.gridx = 3; gbc.gridy = 0;gbc.weightx=1.;
      inputPanel.add(sqrSqrtText, gbc);
      gbc.gridx = 4; gbc.gridy = 0;
      inputPanel.add(new JLabel("√2"), gbc);
      gbc.gridx = 0; gbc.gridy = 1;
      inputPanel.add(new JLabel("Preferred length:"), gbc);
      gbc.gridx = 1; gbc.gridy = 1;
      inputPanel.add(prefIntText, gbc);
      gbc.gridx = 2; gbc.gridy = 1;gbc.weightx=0;
      inputPanel.add(new JLabel("+"), gbc);
      gbc.gridx = 3; gbc.gridy = 1;
      inputPanel.add(prefSqrtText, gbc);
      gbc.gridx = 4; gbc.gridy = 1;
      inputPanel.add(new JLabel("√2"), gbc);

      buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
      buttonPanel.add(findButton);
      buttonPanel.add(nextButton);
      nextButton.setEnabled(false);
      buttonPanel.add(backButton);
      backButton.setEnabled(false);

      findButton.addActionListener(findButtonActionListener);

      pack();
   }

   public static void main(String args[]){
      EventQueue.invokeLater(new Runnable(){
         @Override
         public void run(){
            new kmyref().setVisible(true);
         }
      });
   }
}