package eu.glowacki.jaxws.implementation.filetransfer;

import eu.glowacki.jaxws.client.filetransfer.Client;
import eu.glowacki.jaxws.client.filetransfer.proxy.BigData;
import eu.glowacki.jaxws.client.filetransfer.proxy.IOException_Exception;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileTransferImplTest {
    Client c = new Client();
    static List<BigData> bigDataList = new ArrayList<BigData>();
    static List<String> exampleKeywords = new ArrayList<String>();
    
    static void addToList(File file, List<String> keywords) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream inputStream = new BufferedInputStream(fis);
        byte[] imageBytes = new byte[(int) file.length()];
        inputStream.read(imageBytes);
        BigData bigData = new BigData();
        bigData.setFile(imageBytes);
        bigData.setName(file.getName());
        bigData.setFileSize(file.length());
        bigData.setKeywords(keywords);
        inputStream.close();
        bigDataList.add(bigData);

    }

    @BeforeAll
    static void before() {
        FileTransferImpl s = new FileTransferImpl();
        s.main();
        try {
            String filePath = "C:\\Users\\Weronika Wawrzyniak\\Desktop\\University\\TPO\\Edek11\\allFiles\\" + "test.txt";
            File file = new File(filePath);
            List<String> words = new ArrayList<String>();
            words.add("test");
            words.add("text");
            addToList(file, words);
            filePath = "C:\\Users\\Weronika Wawrzyniak\\Desktop\\University\\TPO\\Edek11\\allFiles\\" + "binary.png";
            file = new File(filePath);
            words = new ArrayList<String>();
            words.add("test");
            words.add("image");
            addToList(file, words);

            exampleKeywords.add("text");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void upload() throws IOException {
        Assertions.assertEquals("Success", c.uploadMain(bigDataList.get(0)));
        Assertions.assertEquals("Success", c.uploadMain(bigDataList.get(1)));
    }

    @Test
    void query() {
        try {
            Assertions.assertEquals(1, c.queryMain(exampleKeywords).size());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void download() {
        String fileName = "binary.png";
        //save in the downloads
        String filePath = "C:\\Users\\Weronika Wawrzyniak\\Desktop\\University\\TPO\\Edek11\\jax-ws-client\\FileTransfer\\eu\\glowacki\\jaxws\\downloads\\" + fileName;
        try {
            Assertions.assertEquals("binary.png", c.downloadMain(fileName, filePath).getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IOException_Exception e) {
            e.printStackTrace();
        }
    }
}