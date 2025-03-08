package classes.request;

import java.nio.file.Path;

public class FileEnterSeparatedRequest implements IFileEnterSeparatedRequest {
	private Path path;

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}
	
}
