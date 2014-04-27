package cloud.app.utils;

public class CloudFile {
	private byte[] data;
	private String title;
	private String description;
	private String mimeType;
	private long size;
	
	public CloudFile(byte[] data, String title, String description,
			String mimeType, long size) {
		this.data = data;
		this.title = title;
		this.description = description;
		this.mimeType = mimeType;
		this.size = size;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
}
