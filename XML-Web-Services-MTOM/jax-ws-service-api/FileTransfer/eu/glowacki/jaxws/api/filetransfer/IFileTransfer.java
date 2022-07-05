package eu.glowacki.jaxws.api.filetransfer;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;
import java.io.IOException;
import java.util.List;

@MTOM
@WebService( //
		name = "IFileTransfer", //
		targetNamespace = "http://glowacki.eu/big-data/mtom" //
)
public interface IFileTransfer {

	public static final String URI = "http://localhost:8080/big-data/mtom";

	@WebMethod(action = "http://glowacki.eu/big-data/mtom/upload")
	public String upload(BigData file);

	@WebMethod(action = "http://glowacki.eu/big-data/mtom/query")
	public List<BigData> query(List<String> keywords);

	@WebMethod(action = "http://glowacki.eu/big-data/mtom/download")
	public byte[] download(String fileName) throws IOException;
}