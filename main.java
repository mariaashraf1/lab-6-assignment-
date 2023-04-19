import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;
import java.util.Scanner;
public class main {
    public static void main(String[] args) throws NotValidExtension, EmptyAutosarFileException  {
        String filename=args[0];
        File file=new File(filename) ;
        try{
           
            if(!filename.endsWith(".ARXML")){
                throw new NotValidExtension("NotVaildAutosarFileException");
            }
            if(file.length()==0){
                throw new EmptyAutosarFileException("EmptyAutosarFileException");

            }
       
        FileInputStream input=new FileInputStream(file);
        int x;
        StringBuilder s=new StringBuilder();
        while ((x=input.read())!=-1) 
        {
           s.append((char)x);
        }
        String data= s.toString();
        Scanner m=new Scanner(data);
        ArrayList<container> c=new ArrayList<>();
        while(m.hasNextLine()){
            String l= m.nextLine();
            if(l.contains("<CONTAINER")){
                String uuid=l.substring(l.indexOf("UUID="),l.indexOf(">"));
                String short_name=m.nextLine();
                String shortname=short_name.substring(short_name.indexOf(">")+1,short_name.indexOf("</"));
                String long_name=m.nextLine();
                String longname=long_name.substring(long_name.indexOf(">")+1, long_name.indexOf("</"));
                container y=new container();
                y.setUUID(uuid);
                y.setShort_name(shortname);
                y.setLong_name(longname);
                c.add(y);
            }

            }
            Collections.sort(c);
            String finalname=filename.substring(0, filename.indexOf("."))+"_mod.ARXML";
            FileOutputStream outputStream=new FileOutputStream(finalname);
            outputStream.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes());
            outputStream.write("<AUTOSAR>\n".getBytes());
            for (int i = 0; i < c.size(); i++) {
                outputStream.write(c.get(i).toString().getBytes());
                
            }
            outputStream.write("</AUTOSAR>\n".getBytes());
        }
            catch(FileNotFoundException e){
                e=new FileNotFoundException("file not found");
            }
            catch(IOException z){
                z=new IOException(" IO exception");
            }
    }
}

       
    





        
        

    

    

