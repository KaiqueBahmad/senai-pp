package classes.request;

public class CSVRequest implements ICSVRequest {
	private String content;
	private String delimiter = ",";

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	
	
}
