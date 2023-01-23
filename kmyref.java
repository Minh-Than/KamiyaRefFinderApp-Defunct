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
import java.lang.Integer;
import java.lang.Math;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.swing.JComboBox;
import java.util.Map;
import java.util.Hashtable;

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

   // container panel for the dropdowns
   private final JPanel flagPanel;
   private final String[] negIntFlags = {"None", "No single -int", "No double -int"};
   private final String[] negSqrtFlags = {"None", "No single -√2", "No double -√2"};
   private final JComboBox<String> negIntCB = new JComboBox<String>(negIntFlags);
   private final JComboBox<String> negSqrtCB = new JComboBox<String>(negSqrtFlags);

   // container panel for the buttons
   // text fields for the buttons
   private final JPanel buttonPanel;
   private final JButton findButton;
   private final JButton nextButton;
   private final JButton backButton;

   private static Map<Double, PresetData> presetDict = new Hashtable<>();

   public class ResultData{
      double x;
      double y;
      String x1;
      String x2;
      String ystr;
      String ratio1;
      String ratio2;
      String png1;
      String png2;
      int order;

      public ResultData(double x, double y, String x1, String x2, String ystr, String ratio1, String ratio2, String png1, String png2, int order){
         this.x = x;
         this.y = y;
         this.x1 = x1;
         this.x2 = x2;
         this.ystr = ystr;
         this.ratio1 = ratio1;
         this.ratio2 = ratio2;
         this.png1 = png1;
         this.png2 = png2;
         this.order = order;
      }
   }

   public static class PresetData{
      String ratio;
      double doubleNum;
      int intNum;

      public PresetData(String ratio, double doubleNum, int intNum){
         this.ratio = ratio;
         this.doubleNum = doubleNum;
         this.intNum = intNum;
      }

      public String getRatio(){
         return this.ratio;
      }
   }

   public static boolean isPowerOfTwo(int n){
      if (n == 0)
         return false;

      return (int)(Math.ceil((Math.log(n) / Math.log(2))))
            == (int)(Math.floor(((Math.log(n) / Math.log(2)))));
   }

      public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(Double.toString(value));
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
   }

   public static void setIndividualPreset(Map<Double, PresetData> presetDict, double key, String ratio, double doubleNum, int intNum){
      presetDict.put(key, new PresetData(ratio, doubleNum, intNum));
   }

   public static void initilizePresetData(Map<Double, PresetData> presetDict){
      setIndividualPreset(presetDict, 1, "1:1", 1, 1);
      setIndividualPreset(presetDict, 0.5, "1:2", 0.5, 2);
      setIndividualPreset(presetDict, 2, "2:1", 2, 2);
      setIndividualPreset(presetDict, 0.25, "1:4", 0.25, 3);
      setIndividualPreset(presetDict, 0.75, "3:4", 0.75, 3);
      setIndividualPreset(presetDict, 1.33333, "4:3", 1.33333, 3);
      setIndividualPreset(presetDict, 4, "4:1", 4, 3);
      setIndividualPreset(presetDict, 0.125, "1:8", 0.125, 4);
      setIndividualPreset(presetDict, 0.375, "3:8", 0.375, 4);
      setIndividualPreset(presetDict, 0.625, "5:8", 0.625, 4);
      setIndividualPreset(presetDict, 0.875, "7:8", 0.875, 4);
      setIndividualPreset(presetDict, 1.14286, "8:7", 1.14286, 4);
      setIndividualPreset(presetDict, 1.6, "8:5", 1.6, 4);
      setIndividualPreset(presetDict, 2.66667, "8:3", 2.66667, 4);
      setIndividualPreset(presetDict, 8, "8:1", 8, 4);
      setIndividualPreset(presetDict, 0.0625, "1:16", 0.0625, 5);
      setIndividualPreset(presetDict, 0.1875, "3:16", 0.1875, 5);
      setIndividualPreset(presetDict, 0.3125, "5:16", 0.3125, 5);
      setIndividualPreset(presetDict, 0.4375, "7:16", 0.4375, 5);
      setIndividualPreset(presetDict, 0.5625, "9:16", 0.5625, 5);
      setIndividualPreset(presetDict, 0.6875, "11:16", 0.6875, 5);
      setIndividualPreset(presetDict, 0.8125, "13:16", 0.8125, 5);
      setIndividualPreset(presetDict, 0.9375, "15:16", 0.9375, 5);
      setIndividualPreset(presetDict, 1.06667, "16:15", 1.06667, 5);
      setIndividualPreset(presetDict, 1.23077, "16:13", 1.23077, 5);
      setIndividualPreset(presetDict, 1.45455, "16:11", 1.45455, 5);
      setIndividualPreset(presetDict, 1.77778, "16:9", 1.77778, 5);
      setIndividualPreset(presetDict, 3.2, "16:5", 3.2, 5);
      setIndividualPreset(presetDict, 5.33333, "16:3", 5.33333, 5);
      setIndividualPreset(presetDict, 16, "16:1", 16, 5);
      setIndividualPreset(presetDict, 0.17678, "1:4√2", 0.17678, 6);
      setIndividualPreset(presetDict, 0.53033, "3:4√2", 0.53033, 6);
      setIndividualPreset(presetDict, 0.35355, "1:2√2", 0.35355, 5);
      setIndividualPreset(presetDict, 0.70711, "1:√2", 0.70711, 4);
      setIndividualPreset(presetDict, 1.41421, "√2:1", 1.41421, 4);
      setIndividualPreset(presetDict, 2.82843, "2√2:1", 2.82843, 5);
      setIndividualPreset(presetDict, 1.88562, "4√2:3", 1.88562, 6);
      setIndividualPreset(presetDict, 5.65685, "4√2:1", 5.65685, 6);
      setIndividualPreset(presetDict, 0.2612, "1:2+√2", 0.2612, 3);
      setIndividualPreset(presetDict, 0.41421, "1:1+√2", 0.41421, 2);
      setIndividualPreset(presetDict, 0.58579, "2:2+√2", 0.58579, 3);
      setIndividualPreset(presetDict, 1.54692, "4+2√2:3+√2", 1.54692, 5);
      setIndividualPreset(presetDict, 0.64645, "3+√2:4+2√2", 0.64645, 5);
      setIndividualPreset(presetDict, 4.82843, "2+2√2:1", 4.82843, 4);
      setIndividualPreset(presetDict, 0.20711, "1:2+2√2", 0.20711, 4);
      setIndividualPreset(presetDict, 1.70711, "2+√2:2", 1.70711, 3);
      setIndividualPreset(presetDict, 2.41421, "1+√2:1", 2.41421, 2);
      setIndividualPreset(presetDict, 3.41421, "2+√2:1", 3.41421, 4);
      setIndividualPreset(presetDict, 1.2612, "2+2√2:1+2√2", 1.2612, 4);
      setIndividualPreset(presetDict, 0.79289, "1+2√2:2+2√2", 0.79289, 4);
      setIndividualPreset(presetDict, 1.20711, "1+√2:2", 1.20711, 4);
      setIndividualPreset(presetDict, 0.82843, "2:1+√2", 0.82843, 4);
      setIndividualPreset(presetDict, 0.17157, "1:3+2√2", 0.17157, 4);
      setIndividualPreset(presetDict, 5.82843, "3+2√2:1", 5.82843, 4);
      setIndividualPreset(presetDict, 1.17157, "4:2+√2", 1.17157, 5);
      setIndividualPreset(presetDict, 0.85355, "2+√2:4", 0.85355, 5);
      setIndividualPreset(presetDict, 0.14645, "1:4+2√2", 0.14645, 5);
      setIndividualPreset(presetDict, 6.82843, "4+2√2:1", 6.82843, 5);
   }

   public PresetData preset(Map<Double, PresetData> presetDict, double key){
      return presetDict.containsKey(key) ? presetDict.get(key) : new PresetData("NA", 0, 0);
   }

   public static String output(int n1, int n2){
      String result = "".concat(Integer.toString(n1)).concat("√2");

      if (n2 == 1) { result = "√2"; }
      if (n1 != 0) { 
         result = "".concat(Integer.toString(n1)).concat("+").concat(result); 
      }
      if (n2 == 0){ result = Integer.toString(n1); }

      return result;
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
               JOptionPane.showMessageDialog(null, "The input must only be integer. Please try Again.","Wrong Input Format", JOptionPane.WARNING_MESSAGE);
               return;
            }
         } // Has no input 
         else if(sqrIntText.getText().equals("") && sqrSqrtText.getText().equals("") && prefIntText.getText().equals("") && prefSqrtText.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Inputs are empty. Please try again.","Empty Inputs", JOptionPane.WARNING_MESSAGE);
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
               JOptionPane.showMessageDialog(null, "The input must only be integer. Please try Again.","Wrong Input Format", JOptionPane.WARNING_MESSAGE);
               return;
            } 
         }

         // If is 2^n + 0*√2 (only power of 2)
         if(isPowerOfTwo(sqrInt) == true && sqrSqrt == 0){
            JOptionPane.showMessageDialog(null, "Just divide in half once or multiple times you massive knobhead.","Dumb Bitch Message", JOptionPane.WARNING_MESSAGE);
         }


         ArrayList<ResultData> resultData = new ArrayList<ResultData>();
         double papersize = round(sqrInt + (sqrSqrt * Math.sqrt(2)), 5);
         double x1 = 0;
         double x2 = 0;
         double y = 0;
         PresetData result1;
         PresetData result2;
         int num = 0;

         for(int ixr1 = 0; ixr1 < Math.ceil(papersize); ixr1++){
            for(int ixr2 = 0; ixr2 < Math.ceil(papersize / Math.sqrt(2)); ixr2++){
               for(int iyr1 = 0; iyr1 < Math.ceil(papersize); iyr1++){
                  for(int iyr2 = 0; iyr2 < Math.ceil(papersize / Math.sqrt(2)); iyr2++){
                     if(((ixr1 != prefInt) && (ixr2 != prefSqrt)) && ((iyr1 != prefInt) && (iyr2 != prefSqrt))){
                        continue;
                     } else {
                        x1 = round(ixr1 + (ixr2 * Math.sqrt(2)), 5);
                        x2 = round(papersize - x1, 5);
                        y = round(iyr1 + (iyr2 * Math.sqrt(2)), 5);

                        if((x1 != 0) && (y != 0) && (x1 < papersize) && (y <= papersize)){
                           result1 = preset(presetDict, round(x1 / y, 5));
                           result2 = preset(presetDict, round(x2 / y, 5));

                           if (!result1.getRatio().equals("NA") && (!result2.getRatio().equals("NA"))){
                              if(Double.toString(round(x1 / y, 5)) == Double.toString(round(x2 / y, 5))){
                                 continue;
                              }

                              num += 1;
                              // ResultData tempRes = new ResultData (round(x1 / papersize, 5), round(y / papersize, 5), output(ixr1, ixr2), output(sqrInt - ixr1, sqrSqrt - ixr2), output(iyr1, iyr2), result1.getRatio(), result2.getRatio(), "".concat());

                           }
                        }
                     }
                  }
               }
            }
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

      flagPanel = new JPanel(new GridBagLayout());

      buttonPanel = new JPanel();
      findButton = new JButton("Find");
      nextButton = new JButton("Next");
      backButton = new JButton("Previous");

      setPreferredSize(new Dimension((int)(Double.valueOf(size) * 1.5), size));
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
      topPanel.add(flagPanel);
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
      inputPanel.add(new JLabel("Preferred ratio (optional):"), gbc);
      gbc.gridx = 1; gbc.gridy = 1;
      inputPanel.add(prefIntText, gbc);
      gbc.gridx = 2; gbc.gridy = 1;gbc.weightx=0;
      inputPanel.add(new JLabel("+"), gbc);
      gbc.gridx = 3; gbc.gridy = 1;
      inputPanel.add(prefSqrtText, gbc);
      gbc.gridx = 4; gbc.gridy = 1;
      inputPanel.add(new JLabel("√2"), gbc);

      // flagPanel.setLayout(new BoxLayout(flagPanel, BoxLayout.Y_AXIS));
      gbc.gridx = 0; gbc.gridy = 0; 
      flagPanel.add(new JLabel("Filter -√2:"), gbc);
      gbc.gridx = 1; gbc.gridy = 0; 
      flagPanel.add(negIntCB, gbc);
      gbc.gridx = 0; gbc.gridy = 1; 
      flagPanel.add(new JLabel("Filter -integer:"), gbc);
      gbc.gridx = 1; gbc.gridy = 1; 
      flagPanel.add(negSqrtCB, gbc);


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
      // Map<Double, PresetData> presetDict = new Hashtable<>();
      initilizePresetData(presetDict);

      EventQueue.invokeLater(new Runnable(){
         @Override
         public void run(){
            new kmyref().setVisible(true);
         }
      });
   }
}