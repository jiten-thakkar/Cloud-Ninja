package cloud.app.utils;

public class CloudFileBuilder {
	private byte[] data = null;
	private String title = null;
	private String description = null;
	private String mimeType = null;
	private long size = 0;
	
	public CloudFileBuilder setData(byte[] data) {
		this.data = data;
		return this;
	}
	public CloudFileBuilder setTitle(String title) {
		this.title = title;
		return this;
	}
	public CloudFileBuilder setDescription(String description) {
		this.description = description;
		return this;
	}
	public CloudFileBuilder setMimeType(String mimeType) {
		this.mimeType = mimeType;
		return this;
	}
	public CloudFileBuilder setSize(long size) {
		this.size = size;
		return this;
	}
	
	public CloudFile build() {
		return new CloudFile(data, title, description, mimeType, size);
	}
}
