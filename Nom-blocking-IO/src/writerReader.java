import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.Random;

public class writerReader {
    Status.State status;
    String fname = "theTextFile.txt";
    Random rnd = new Random();
    RandomAccessFile file;

    {
        try {
            file = new RandomAccessFile(fname, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    FileChannel channel = file.getChannel();

    public writerReader(Status.State status) throws IOException, InterruptedException {
        this.status = status;
        init();
    }

    public void init() throws IOException, InterruptedException {
        switch (status) {
            case READ: {
                doTheReading();
                break;
            }
            case WRITE: {
                doTheWriting();
                break;
            }
        }
    }

    public void doTheWriting() throws IOException, InterruptedException {
        while (true) {
            MappedByteBuffer buf;
            IntBuffer ibuf;
            if (file.getChannel().size() == 0) { //if this is an empty file
                buf = channel.map(
                        FileChannel.MapMode.READ_WRITE,  // read-write mode
                        0,  // starting from the beginning of the file
                        8 // map the mark and random digit
                );
                ibuf = buf.asIntBuffer();
                ibuf.put(0); //initialize the mark for program to start reading-writing
            } else {
                buf = channel.map(
                        FileChannel.MapMode.READ_WRITE,  // read-write mode
                        0,  // starting from the beginning of the file
                        channel.size() // map the whole file
                );
                ibuf = buf.asIntBuffer();
            }
            if (ibuf.get() == 0) {
                int number = rnd.nextInt(100);
                System.out.println("Writing now");
                ibuf.put(number);
                ibuf.rewind();
                ibuf.put(1); // 1= we are ready to be read
                buf.force();
            } else {
                System.out.println("Waiting for Reader");
                Thread.sleep(500);
            }
        }
    }

    public void doTheReading() throws IOException, InterruptedException {
        MappedByteBuffer buf;
        buf = channel.map(
                FileChannel.MapMode.READ_WRITE,  // read-write mode
                0,  // starting from the beginning of the file
                (int) channel.size()  // map the whole file
        );
        while (true) {
            IntBuffer ibuf = buf.asIntBuffer();
            if (ibuf.get(0) == 1) {
                System.out.println("Reading the number " + ibuf.get(1) + " and squaring it");
                System.out.println(ibuf.get(1) * ibuf.get(1));
                ibuf.clear();
                ibuf.rewind();
                ibuf.put(0);//Switch mark to writing
                buf.force();
            }
            Thread.sleep(1000);
        }

    }

    public static void main(String[] args) throws Exception {
        //new writerReader(Status.State.WRITE);
        new writerReader(Status.State.READ);

    }
}

