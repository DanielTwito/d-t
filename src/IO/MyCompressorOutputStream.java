package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

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

        int countOnes=0;
        int countZero=0;
        int index =0 ;

        while (b[index]!=-3){
            if(b[index]==-2) {
                index++;
                continue;
            }
            this.write(b[index]);
            out.write(-2);
            index++;
        }
        index++;
        out.write(-3);
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

        }



    @Override
    public void write(int b) throws IOException {

        while(b>127) {
            out.write(127);
            b -= 127;
        }
        out.write(b);
//        out.write(-2);

    }
}
