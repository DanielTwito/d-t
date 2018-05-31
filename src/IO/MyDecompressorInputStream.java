package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.*;

public class MyDecompressorInputStream extends InputStream {

    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {


        return 0;
    }

    /**
     *
     * @param readTo - a buffer to save the data we read
     * @return the number of bytes that actually read.
     * @throws IOException
     */
    public int read(byte[] readTo) throws IOException {

        byte[] compressed=null;
        //ArrayList<Integer> compressed=new ArrayList<>();
        try {
            /*while(true){
                Integer i=in.read();
                Byte data=i.byteValue();
                if(data == -1)
                    break;
                compressed.add(data.intValue());*/
                ObjectInputStream o = new ObjectInputStream(in);
            compressed = (byte[]) o.readObject();

        //compressed = (in.readAllBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
        in.close();
        int index=0;
        //copy the all meta data as is
        while (compressed[index] !=-3){
            readTo[index]=compressed[index];
            index++;
        }
        //write "-3"- a sign to end of meta data
        readTo[index]=compressed[index];
        int loc=++index;

        ArrayList<Integer> deCompressed = new ArrayList<>();

        //decode the compressed data
        for (int j=1; index < compressed.length; index++) {
            int tmp= compressed[index];
            while(tmp>0) {
                deCompressed.add(j);
                tmp--;
            }
            if(j==1)
                j=0;
            else
                j=1;

        }

        for (int i = 0; loc < Math.min(readTo.length,deCompressed.size()); i++,loc++) {
            readTo[loc]=deCompressed.get(i).byteValue();

        }

        return compressed.length;
    }

}
