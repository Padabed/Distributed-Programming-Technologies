package eu.glowacki.jaxws.api.filetransfer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType( //
		namespace = "http://glowacki.eu/big-data" //
)
public final class BigData {

		private String Name;
		private long FileSize;
		private byte[] file;
		private List<String> keywords;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public long getFileSize() {
		return FileSize;
	}

	public void setFileSize(long fileSize) {
		FileSize = fileSize;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
}