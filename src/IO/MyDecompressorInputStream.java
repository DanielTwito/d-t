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

        byte[] compressed = (in.readAllBytes());

        ArrayList<Byte> deCompressed = new ArrayList<>();
        int index=0;
        while (compressed[index]!=-3){
            deCompressed.add(compressed[index]);
            index++;
        }
        index++;
        for (int j=1; index < compressed.length; index++) {
            byte tmp=compressed[index];
            while(tmp>0) {
                deCompressed.add((byte) j);
                tmp--;
            }
            if(j==1)
                j=0;
            else
                j=1;

        }
        for (int i = 0; i < deCompressed.size(); i++) {
            readTo[i]= deCompressed.get(i);
        }

        return compressed.length;
    }

}
