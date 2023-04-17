import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class App {
    public static void main(String[] args) throws Exception {
        try{
            String fileName=args[0];
            if(!fileName.endsWith(".arxml")){
                throw new NotValidAutosarFile("not arxml file");
            }
            File file=new File(fileName);
            if(file.length()==0){
                throw new EmptyAutosarFile("File is Empty");
            }
            FileInputStream fileStream=new FileInputStream(file);
            int charByte;
            StringBuilder stringBuilder=new StringBuilder();
            while((charByte=fileStream.read())!=-1){
                stringBuilder.append((char)charByte);
            }
            //System.out.println(stringBuilder);
            
            String example=stringBuilder.toString();
            Scanner scanner=new Scanner(example);
            ArrayList<Container> containers= new ArrayList<>();
            while(scanner.hasNextLine()){
                String line=scanner.nextLine();
                if(line.contains("<CONTAINER")){
                    String id=line.substring(line.indexOf("=")+2,line.indexOf(">"));

                    String shortNameTag =scanner.nextLine();
                    String shortName=shortNameTag.substring(shortNameTag.indexOf(">")+1, shortNameTag.indexOf("</"));
                    String longNameTag =scanner.nextLine();
                    String longName=longNameTag.substring(longNameTag.indexOf(">")+1, longNameTag.indexOf("</"));
                    // System.out.println(id);
                    // System.out.println(shortName);
                    // System.out.println(longName);
                    Container contain= new Container(id,shortName,longName);
                    //System.out.println(contain.toString());

                    containers.add(contain);
                }
            }
            Collections.sort(containers);
            String newFileName= fileName.substring(0, fileName.indexOf("."))+"_mod.arxml";
            FileOutputStream fileOutputStream=new FileOutputStream(newFileName);
            fileOutputStream.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes());
            fileOutputStream.write("<AUTOSAR>\n".getBytes());
            for(int i=0;i<containers.size();i++){
                fileOutputStream.write(containers.get(i).toString().getBytes());
                //System.out.println(containers.get(i).toString());
            }
            fileOutputStream.write("</AUTOSAR>".getBytes());


        }
        catch(NotValidAutosarFile ex){
            System.out.println("NotValidAutosarFile");
        }
        catch(IOException ex){
            System.out.println("IO Error");
        }
        // catch(IndexOutOfBoundsException ex){
        //     System.out.println("Out of boundaries");
        // }
        catch(EmptyAutosarFile ex){
            System.out.println("File is Empty");
        }
    }
}
