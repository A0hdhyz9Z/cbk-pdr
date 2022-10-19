package team.cbk.demo.utils;

import java.io.*;

public class WriteObjToFile {
    public static void writeObjectToFile(Object obj,String filename)
    {
        File filedest = new File(filename);
        if(!filedest.getParentFile().exists())
            filedest.getParentFile().mkdirs();

        File file =new File(filename);
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut=new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Object readObjectFromFile(String filename)
    {
        Object temp=null;
        File file =new File(filename);
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            temp=objIn.readObject();
            objIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
