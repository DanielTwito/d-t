package IO;

import java.io.IOException;
import java.io.InputStream;
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

        //byte[] compressed=new byte[0];
        ArrayList<Integer> compressed=new ArrayList<>();
        try {
            while(true){
                Integer i=in.read();
                Byte data=i.byteValue();
                if(data == -1)
                    break;
                compressed.add(data.intValue());
            }
        //compressed = (in.readAllBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
        in.close();
        int index=0;
        //copy the all meta data as is
        while (compressed.get(index) !=-3){
            readTo[index]=compressed.get(index).byteValue();
            index++;
        }
        //write "-3"- a sign to end of meta data
        readTo[index]=compressed.get(index).byteValue();
        int loc=++index;

        ArrayList<Integer> deCompressed = new ArrayList<>();

        //decode the compressed data
        for (int j=1; index < compressed.size(); index++) {
            int tmp= compressed.get(index);
            while(tmp>0) {
                deCompressed.add(j);
                tmp--;
            }
            if(j==1)
                j=0;
            else
                j=1;

        }

        for (int i = 0; loc < readTo.length; i++,loc++) {
            readTo[loc]=deCompressed.get(i).byteValue();

        }

        return compressed.size();
    }

}
