package eu.glowacki.jaxws.implementation.filetransfer;

import eu.glowacki.jaxws.api.filetransfer.BigData;
import eu.glowacki.jaxws.api.filetransfer.IFileTransfer;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@MTOM
@WebService( //
        name = "IFileTransfer", //
        targetNamespace = "http://glowacki.eu/big-data/mtom" //
)
public class FileTransferImpl implements IFileTransfer {

    public static List<BigData> filesInTheDirectory = new ArrayList<BigData>();

    private static final Logger LOGGER = Logger.getAnonymousLogger();


    public static void main(String... args) {
        Endpoint.publish(IFileTransfer.URI, new FileTransferImpl());
        LOGGER.info("SERVICE STARTED");
    }


    @Override
    public String upload(BigData file) {
        filesInTheDirectory.add(file);
        //upload to server
        String filePath = "C:\\Users\\Weronika Wawrzyniak\\Desktop\\University\\TPO\\Edek11\\jax-ws-service-implementation\\FileTransfer\\eu\\glowacki\\jaxws\\implementation\\filetransfer\\uploads\\" + file.getName();

        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            BufferedOutputStream outputStream = new BufferedOutputStream(fos);
            outputStream.write(file.getFile());
            outputStream.close();

            System.out.println("Received file: " + filePath);

        } catch (IOException ex) {
            System.err.println(ex);
            throw new WebServiceException(ex);
        }
        return "Success";
    }

    @Override
    public List<BigData> query(List<String> keywords) {
        List<BigData> filesWithKeyWords = new ArrayList<BigData>();
        for (BigData file : filesInTheDirectory)
        {
            for (String givenKeyword: keywords)
            {
                for (String fileKeyword : file.getKeywords())
                {
                    if (fileKeyword.equals(givenKeyword)) filesWithKeyWords.add(file);
                }
            }
        }
       return filesWithKeyWords;
    }


    @Override
    public byte[] download(String fileName) throws IOException {
        //download from uploads
        String filePath = "C:\\Users\\Weronika Wawrzyniak\\Desktop\\University\\TPO\\Edek11\\jax-ws-service-implementation\\FileTransfer\\eu\\glowacki\\jaxws\\implementation\\filetransfer\\uploads\\" + fileName;
        System.out.println("Sending for download file: " + filePath);

        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream inputStream = new BufferedInputStream(fis);
            byte[] fileBytes = new byte[(int) file.length()];
            inputStream.read(fileBytes);
            inputStream.close();
            return fileBytes;
        } catch (IOException ex) {
            System.err.println(ex);
            throw new WebServiceException(ex);
        }
    }
    }

