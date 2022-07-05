package eu.glowacki.jaxws.implementation.bigdata;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.MTOM;

import eu.glowacki.jaxws.api.bigdata.BigData;
import eu.glowacki.jaxws.api.bigdata.IBigDataMTOM;

@MTOM
@WebService( //
        name = "IBigDataMTOM", //
        targetNamespace = "http://glowacki.eu/big-data/mtom" //
)
public final class BigDataMTOMImpl implements IBigDataMTOM {

	public static List<BigData> filesInTheDirectory = new ArrayList<>();

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    public static void main(String... args) {
        Endpoint.publish(IBigDataMTOM.URI, new BigDataMTOMImpl());
        LOGGER.info("SERVICE STARTED");
    }

    private BigDataMTOMImpl() {
    }
	private BigDataMTOMImpl(File f, List<String> keys) {

	}


	@Override
	public String upload(BigData file) {
		DataHandler handler = file.getDfile();
		try {
			InputStream is = handler.getInputStream();

			OutputStream os = new FileOutputStream(new File("C:\\Users\\Weronika Wawrzyniak\\Desktop\\University\\TPO\\Edek11\\uploads\\"
					+ file.getName()));
			byte[] b = new byte[100000];
			int bytesRead = 0;
			while ((bytesRead = is.read(b)) != -1) {
				os.write(b, 0, bytesRead);
			}
			os.flush();
			os.close();
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
			return "Failure";
		}
		return "Success";
	}

	@Override
	public List<BigData> filter(List<String> keywords) {

		return null;
	}

	@Override
	public DataHandler download(String fileName) throws IOException {
			FileDataSource dataSource = new FileDataSource("C:\\Users\\Weronika Wawrzyniak\\Desktop\\University\\TPO\\Edek11\\uploads\\"+fileName);
			DataHandler fileDataHandler = new DataHandler(dataSource);
			FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Weronika Wawrzyniak\\Desktop\\University\\TPO\\Edek11\\downloads\\");
			fileDataHandler.writeTo(outputStream);
			outputStream.flush();
		System.out.println(" Download Successful!");
			return fileDataHandler;
	}
}