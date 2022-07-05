package eu.glowacki.jaxws.client.filetransfer;


import eu.glowacki.jaxws.api.IService;
import eu.glowacki.jaxws.client.filetransfer.proxy.*;

import java.io.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Client {


    private static final Logger LOGGER = Logger.getAnonymousLogger();

    static {
        System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dumpTreshold", "9999999");
        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dumpTreshold", "9999999");
    }

    public String uploadMain(BigData bigData) throws IOException {
        URL wsdl = new URL(eu.glowacki.jaxws.api.filetransfer.IFileTransfer.URI + IService.WSDL_SUFFIX);
        FileTransferImplService service = new FileTransferImplService(wsdl);
        IFileTransfer proxy = service.getIFileTransferPort();
        return proxy.upload(bigData);
    }

    public BigData downloadMain(String fileName, String filePath) throws IOException, IOException_Exception {
        URL wsdl = new URL(eu.glowacki.jaxws.api.filetransfer.IFileTransfer.URI + IService.WSDL_SUFFIX);
        FileTransferImplService service = new FileTransferImplService(wsdl);
        IFileTransfer proxy = service.getIFileTransferPort();
        //download and save
        byte[] fileBytes = proxy.download(fileName);
        FileOutputStream fos = new FileOutputStream(filePath);
        BufferedOutputStream outputStream = new BufferedOutputStream(fos);
        outputStream.write(fileBytes);
        outputStream.close();
        BigData bigData = new BigData();
        bigData.setFile(fileBytes);
        bigData.setName(fileName);
        bigData.setFileSize(fileBytes.length);
        List<String> key = new ArrayList<String>();
        key.add("downloaded");
        bigData.setKeywords(key);
        return bigData;
    }

    public List<BigData> queryMain(List<String> keywords) throws MalformedURLException {
        URL wsdl = new URL(eu.glowacki.jaxws.api.filetransfer.IFileTransfer.URI + IService.WSDL_SUFFIX);
        FileTransferImplService service = new FileTransferImplService(wsdl);
        IFileTransfer proxy = service.getIFileTransferPort();
        return proxy.query(keywords);
    }

}



