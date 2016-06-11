import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.undo.*;
import javax.swing.event.*;
import javax.swing.text.Document;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
class KskNotepad extends JFrame
{
 JTextArea ta=new JTextArea();
 JScrollPane sp=new JScrollPane(ta);
 JMenuBar mb=new JMenuBar();
 JMenu m[]=new JMenu[4];
 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
 JFileChooser jfc=new JFileChooser();
JLabel jl;
 Border bo=BorderFactory.createTitledBorder("");
 JMenuItem mi[]=new JMenuItem[17];
 String txt[]={
"File","Edit","Format","Help","New","Open","Save","Save As..","Print","Exit","Undo","Find Text","Replace Text","Time/Date","Change Pen..","Change Background..","Font","View Status","View Help","About KskNotepad"};
  String tx="<html><FONT COLOR=BLUE>Designed By.......</FONT>"+"<FONT COLOR=RED>KsK</FONT></html>";
 int n=0;
     KskNotepad(String s) 
{
   super(s);
    Notepad np=new Notepad(this);
    ta.addMouseListener(np);
    jfc.addActionListener(np);
   FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Documents","txt","java","cpp","c","srt");
    jfc.setFileFilter(filter);
    int x=0;
   for(int i=0;i<4;i++)  
{
   m[i]=new JMenu(txt[i]);
   mb.add(m[i]);
  }
   for(int i=4;i<10;i++)  
{
   mi[x]=new JMenuItem(txt[i]);
mi[x].addActionListener(np);
   m[0].add(mi[x]);
x++;
  }
   for(int i=10;i<14;i++) 
 {
   mi[x]=new JMenuItem(txt[i]);
mi[x].addActionListener(np);
   m[1].add(mi[x]);
x++;
  }
   for(int i=14;i<18;i++)
  {
   mi[x]=new JMenuItem(txt[i]);
mi[x].addActionListener(np);
   m[2].add(mi[x]);
x++;
  }
    for(int i=18;i<20;i++)
  {
   mi[x]=new JMenuItem(txt[i]);
   mi[x].addActionListener(np);
   m[3].add(mi[x]);
    x++;
  }
    jl=new JLabel(tx,JLabel.CENTER);
  jl.setBorder(bo);
   
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  add(jl,BorderLayout.SOUTH);
  add(mb,BorderLayout.NORTH);
  add(sp,BorderLayout.CENTER);
  setSize(800,600);
  setVisible(true);
 }
 public static void main(String... s) 
{
   new KskNotepad("Untitled");
 }
}


class Notepad extends MouseAdapter implements ActionListener,UndoableEditListener
{
  KskNotepad k;
File f;
String s;
JButton jb1,jb;
JTextArea tf,tf1,tf2;
UndoManager um=new UndoManager();
  Highlighter hl;           
String help="<html><FONT COLOR=RED>Ksk-Paint Help........</FONT><br><br>1:File Menu has options to Save,open,print an existing File and Option to access new File.<br>2:Edit menu will provide you some Basic Tools to Find,Replace and Print Time/Date in your Text.<br>3:Formar menu can be use to change Text Color,Font and Background Color<br>4:Help menu can be used to access Help or to know about Ksk-Paint.</html>";
  String about="<html><FONT COLOR=GREEN>Ksk-Notepad Version 1.0<br>Basic Text Editing Application.</FONT><br><br>"+"<FONT COLOR=RED>Designed By Kshittiz Kumar.</FONT></html>";
  Notepad(KskNotepad k)
   {
     this.k=k;
     Document doc=k.ta.getDocument();
     hl = k.ta.getHighlighter(); 
     doc.addUndoableEditListener(this);
   }
   public void actionPerformed(ActionEvent e) 
 {
        if((e.getActionCommand()).equals("Exit"))      
             System.exit(0);
         if((e.getActionCommand()).equals("View Status"))      
                k.ta.append("Total Lines in this File: "+k.ta.getLineCount()+"\n");
        if((e.getActionCommand()).equals("Save"))    
          {
        k.jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       if(k.n==0)      
      {
       int x=k.jfc.showSaveDialog(null);
        if(x==JFileChooser.APPROVE_OPTION)         
      {
            f=k.jfc.getSelectedFile();
            s=f.getPath();
            System.out.println(s);
             try                 
                {
                               FileWriter fw=new FileWriter(s);
                      String txt=k.ta.getText();
                          // System.out.println(txt);
                      txt = txt.replaceAll("(?!\\r)\\n", "\r\n");
                      fw.write(txt);
                      fw.close();
                  }
             catch(Exception ex)           
           {
                System.out.println(ex);
             }
          k.n++;
      }
 
               
        }
       
           else      
         {
               try                 
              {
                               FileWriter fw=new FileWriter(s);
                      String txt=k.ta.getText();
                             // System.out.println(txt);
                      txt = txt.replaceAll("(?!\\r)\\n", "\r\n");
                      fw.write(txt);
                      fw.close();
                  }
             catch(Exception ex)            
             {
                System.out.println(ex);
             }
       }
                k.setTitle(f.getName());
        }
//end of save            

      if((e.getActionCommand()).equals("Save As.."))  
         {
        k.jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x=k.jfc.showSaveDialog(null);
        if(x==JFileChooser.APPROVE_OPTION)       
           {
            f=k.jfc.getSelectedFile();
            s=f.getPath();
            System.out.println(s);
             try            
                  {
                               FileWriter fw=new FileWriter(s);
                      String txt=k.ta.getText();
                      //System.out.println(txt);
                      txt = txt.replaceAll("(?!\\r)\\n", "\r\n");
                      fw.write(txt);
                      fw.close();
                  }
             catch(Exception ex) 
             {
                System.out.println(ex);
             }
          }
        k.setTitle(f.getName());
         }
//end of save as                
        
       if((e.getActionCommand()).equals("Open"))   
          {
             
        k.jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
       int x=k.jfc.showOpenDialog(null);
        if(x==JFileChooser.APPROVE_OPTION)      
            {
                k.ta.setText("");k.n=0;
            f=k.jfc.getSelectedFile();
            s=f.getPath();
            System.out.println(s);
             try                 
               {
                               FileReader fr=new FileReader(s);
                             BufferedReader br=new BufferedReader(fr);
                              
                    //  int i=0;
                      String txt="";
                     //while((i=fr.read())!=-1) 
                          while(txt!=null)
                       {                     
                        // txt=txt+(char)i;
                          txt=br.readLine();
                           if(txt!=null)  
                           // System.out.println(txt);
                          k.ta.append(txt+"\n");
                       }
                        fr.close();
                  }
             catch(Exception ex)            
              {
                System.out.println(ex);
             }
          }
                 k.setTitle(f.getName());
         }
//end of Open   
                    
             if((e.getActionCommand()).equals("New"))    
       {
                  k.ta.setText("");
               k.setTitle("Untitled");
        }
                  if((e.getActionCommand()).equals("Change Pen.."))      
        {
          Color setclr=JColorChooser.showDialog(k,"Choose Color",Color.RED);
           k.ta.setForeground(setclr);
        }
                    if((e.getActionCommand()).equals("Change Background.."))      
         {
          Color setclr=JColorChooser.showDialog(k,"Choose Color",Color.RED);
           k.ta.setBackground(setclr);
        }
         if((e.getActionCommand()).equals("Font"))      
      {
          JFontChooser jf=new JFontChooser(k);
       }
        if((e.getActionCommand()).equals("View Help"))    
        {
     JDialog jd=new JDialog(k,"Help",true);
     Canvas dc=new Canvas();
     JLabel l=new JLabel(help,JLabel.LEFT);
     jd.add(l,BorderLayout.CENTER);
     jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
     jd.setSize(500,200);
     jd.setVisible(true);
            }
      if((e.getActionCommand()).equals("About KskNotepad")) 
      {
     JDialog jd=new JDialog(k,"About",true);
     Canvas dc=new Canvas();
     JLabel l=new JLabel(about,JLabel.LEFT);
     jd.add(l,BorderLayout.CENTER);
     jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
     jd.setSize(500,120);
     jd.setVisible(true);
           }
      if((e.getActionCommand()).equals("Time/Date"))   
    {
       Date date = new Date();
        k.ta.append(k.dateFormat.format(date));
      }
             if((e.getActionCommand()).equals("Undo"))  
      {
        try{
             um.undoOrRedo();
           }
        catch(Exception en)     
   {}
          
 }
           if((e.getActionCommand()).equals("Replace Text"))   
      {
        JDialog jd=new JDialog(k,"Replace Text",false);
         tf=new JTextArea("Find What?......");
         tf1=new JTextArea("Replace With......");
         jb=new JButton("Replace");
        jd.setLayout(new BorderLayout());
         jd.setSize(350,100);
         jd.add(tf,BorderLayout.NORTH);
        jd.add(jb,BorderLayout.CENTER);
          jd.add(tf1,BorderLayout.SOUTH);
         jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         jb.addActionListener(this);
         jd.setVisible(true);
       }
            
           if((e.getActionCommand()).equals("Find Text"))   
      {
        JDialog jd1=new JDialog(k,"Find Text",false);
         tf2=new JTextArea();
         jb1=new JButton("Find");
        jd1.setLayout(new BorderLayout());
          jd1.add(tf2,BorderLayout.CENTER);jd1.add(jb1,BorderLayout.EAST);
         jd1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         jb1.addActionListener(this);
          jd1.setSize(250,70);
         jd1.setVisible(true);
       }
      if(e.getSource()==jb)    
     {
           int kk=0;
        String s1=k.ta.getText();
        String s2=tf.getText();
        String s3=tf1.getText();
         char ch1[]=s1.toCharArray();
        int l=s2.length();
         char ch2[]=s2.toCharArray();
           if(l<s1.length())           
        {
               for(int i=0;i<=(s1.length()-s2.length());i++)          
            {
                      for(int j=0;j<l;j++)                       
                        {
                           if(ch2[j]==ch1[i+j])                     
                              kk++;
                         }
                        if(kk==s2.length())                       
                          {
                                k.ta.replaceRange(s3,i,i+l);
                                    break;
                           }
                          kk=0;
                 }
            }
      }
          if(e.getSource()==jb1)    
          {
        
           int kk=0;
        String s1=k.ta.getText();
        String s2=tf2.getText();
         char ch1[]=s1.toCharArray();
        int l=s2.length();
         char ch2[]=s2.toCharArray();
           if(l<s1.length())           
            {
               for(int i=0;i<=(s1.length()-s2.length());i++)          
              {
                      for(int j=0;j<l;j++)                       
                        {
                           if(ch2[j]==ch1[i+j])                     
                              kk++;
                         }
                        if(kk==s2.length())                       
                          {
                            try{
                           hl.addHighlight(i,i+l,DefaultHighlighter.DefaultPainter);    
                                     }
                                catch(Exception ee) {}
                           }
                          kk=0;
                 }
               }
         }
}//end of action performed
    
      public void undoableEditHappened(UndoableEditEvent ue)   
       {
        um.addEdit(ue.getEdit());
       }
      public void mousePressed(MouseEvent me)
      {
          hl.removeAllHighlights();
      }

   }



class JFontChooser extends JDialog implements ActionListener
{
 KskNotepad k;
 int fo,st;
 String na;
 String s[]=new String[3];
 JComboBox jb[]=new JComboBox[3];
 String size[]={"10","15","20","25","30","40","50"};
 String style[]={"Bold","Italic","Plain"};
 String font[]={"SERIF","SANS_SERIF","MONOSPACED"};
 
 JFontChooser(KskNotepad k)
  {
   super(k,"Choose Your Font",true);
   this.k=k;
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         jb[0]=new JComboBox(size);
jb[0].addActionListener(this);
     jb[1]=new JComboBox(style);
jb[1].addActionListener(this);
     jb[2]=new JComboBox(font);
jb[2].addActionListener(this);
     setLayout(new FlowLayout());
     add(jb[0]);
add(jb[1]);
add(jb[2]);
          setSize(280,70);
     setVisible(true);
 }
  
 public void actionPerformed(ActionEvent e) 
   {
     for(int i=0;i<3;i++)   
     s[i]=(String)jb[i].getSelectedItem();
          if(s[0].equals("10"))     fo=Integer.parseInt("10");
    if(s[0].equals("15"))     fo=Integer.parseInt("15");
    if(s[0].equals("20"))     fo=Integer.parseInt("20");
    if(s[0].equals("25"))     fo=Integer.parseInt("25");
    if(s[0].equals("30"))     fo=Integer.parseInt("30");
    if(s[0].equals("40"))     fo=Integer.parseInt("40");
    if(s[0].equals("50"))     fo=Integer.parseInt("50");
    if(s[1].equals("Bold"))     st=1;
    if(s[1].equals("Italic"))     st=2;
    if(s[1].equals("Plain"))     st=0;
    if(s[2].equals("SERIF"))     na="serif";
    if(s[2].equals("SANS_SERIF"))     na="SANS_SERIF";
    if(s[2].equals("MONOSPACED"))     na="MONOSPACED";
             Font f=new Font(na,st,fo);
       k.ta.setFont(f);
        }
 }
       
