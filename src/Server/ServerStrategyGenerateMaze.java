package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {


    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {

        int rows=0,cols=0;
        MyCompressorOutputStream compressor;
        AMazeGenerator mazeGenerator;
        String s=Configurations.getProperty("GenerateMaze_method");
        if(s.equals("1"))
            mazeGenerator=new MyMazeGenerator();
        else
            mazeGenerator= new SimpleMazeGenerator();
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            OutputStream ba=new ByteArrayOutputStream();
            compressor=new MyCompressorOutputStream(ba);

            int[] mazeSize=(int[]) fromClient.readObject();
            rows=mazeSize[0];
            cols= mazeSize[1];
            compressor.write(mazeGenerator.generate(rows,cols).toByteArray());
            ByteArrayOutputStream b = (ByteArrayOutputStream) ba;
            toClient.writeObject(b.toByteArray());

        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }


    }
}
