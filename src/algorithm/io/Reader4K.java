/**
 * This is my implmentation of a popular interview question Reader4K
 *
 * @author Azra Irshad Rabbani, dsalgoazra@gmail.com
 */

package algorithm.io;

import java.io.*;

public class Reader4K {

    private int BUFFER_SIZE = 4 * 1024;
    private byte[] buffer;
    private int bufferOffset = 0;
    private File output;
    private String fileName;
    private FileOutputStream fos;
    private BufferedOutputStream bos;

    public Reader4K() throws Exception {
        this("reader4k.txt");
    }

    public Reader4K (String outputFileName) throws Exception {
        this.fileName = outputFileName;
        init(outputFileName);
    }

    private void init(String outputFileName) throws Exception {
        try {
            buffer = new byte[BUFFER_SIZE];
            output = new File(outputFileName);
            fos = new FileOutputStream(output);
            bos = new BufferedOutputStream(fos);
        } catch(Exception e) {
            System.out.println("Exception init "+e);
            throw e;
        }
    }

    public void read(byte[] input){
        if(input == null || input.length == 0) return;
        for(int i = 0 ; i < input.length; i++) {
            if(bufferOffset >= BUFFER_SIZE) {
                flushBuffer();
            }
            buffer[bufferOffset++] = input[i];
        }
    }

    private void flushBuffer(){
        try{
            bos.write(buffer);
            bos.flush();
            bufferOffset = 0;
            buffer = new byte[BUFFER_SIZE];

        } catch ( Exception e) {
            System.out.println("Exception "+e);
        }
    }

    public void close(){
        flushBuffer();
        try{
            if(fos != null) fos.close();
            if(bos != null) bos.close();
        } catch (Exception e) {
            System.out.println("Exception "+e);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public static void main(String[] args){
        Reader4K reader = null;
        try {
            byte[] input = createInput(10, (byte)'i');
            reader = new Reader4K();
            reader.read(input);
            input = createInput(10, (byte)'r');
            reader.read(input);
            input = createInput(10, (byte)'k');
            reader.read(input);
            printContents(reader);


        } catch(Exception e){
            System.out.println("Exception" + e.getMessage());
        } finally {
            if(reader != null) {
                reader.close();
            }
        }
    }

    private static void printContents(Reader4K reader) throws IOException {
        File f = new File(reader.getFileName());
        if(f.exists()){
            FileInputStream fis = new FileInputStream(f);
            int i = 0;
            int size=0;
            while((i=fis.read())!=-1){
                System.out.print((byte)i);
                size++;
            }
            fis.close();
            System.out.println("Actual Size "+size +" | Expected Size "+8000);
        }
    }

    private static byte[] createInput(int length, byte character) {

        byte b[] = new byte[length];
        for(int i = 0; i < length; i++){
            b[i] =  character;
        }
        return b;
    }
}
