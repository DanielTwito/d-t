package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyCompressorOutputStream extends OutputStream {
    private ArrayList<Byte> ans;
    private OutputStream out;
    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;

    }

    @Override
    /**
     * write the data
     * starting from the meta-data
     * -2 separete bettwen value
     * -3 end meta-data and start maze data
     */
    public void write(byte[] b) throws IOException {
        ans = new ArrayList<>();
        int countOnes=0;
        int countZero=0;
        int index =0 ;

        while (b[index]!=-3){
            ans.add(b[index]);
            //out.write(b[index]);
            index++;
        }
        index++;
        ans.add((byte)-3);
        //out.write(-3);
        boolean flag = false;
        int i = index;
        while (i < b.length) {
            countOnes=0;
            countZero=0;
                while ( i< b.length && !flag) {
                    if(b[i]==0) {
                        flag = true;
                        break;
                    }
                    countOnes++;
                    i++;
                }

                this.write(countOnes);

                while (i< b.length && flag) {
                    if(b[i]==1) {
                        flag = false;
                        break;
                    }
                    countZero++;
                    i++;
                }
             this.write(countZero);
            }
        ObjectOutputStream o =new ObjectOutputStream(out);
            o.writeObject(ans.toArray());

        }



    @Override
    public void write(int b) throws IOException {

        while(b>127) {
            ans.add((byte) 127);
            b -= 127;
        }
        ans.add((byte) b);
        //out.write(b);
//        out.write(-2);

    }
}
