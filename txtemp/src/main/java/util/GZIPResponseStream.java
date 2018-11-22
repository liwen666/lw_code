package util;

import java.io.*;
import java.util.zip.GZIPOutputStream;
import javax.servlet.*;
import javax.servlet.http.*;

public class GZIPResponseStream extends ServletOutputStream {
	protected ByteArrayOutputStream baos = null;
	protected GZIPOutputStream gzipstream = null;
	protected boolean closed = false;
	protected HttpServletResponse response = null;
	protected ServletOutputStream output = null;

	public GZIPResponseStream(HttpServletResponse response) throws IOException {
		super();
		closed = false;
		this.response = response;
		this.output = response.getOutputStream();
		baos = new ByteArrayOutputStream();
		//gzipstream = new GZIPOutputStream(baos);
	}

	public void close() throws IOException {
		if (closed) {
			throw new IOException("This output stream has already been closed");
		}
		//gzipstream.finish();

		byte[] bytes = baos.toByteArray();
		int responseDataLength = bytes.length;
		//超过指定大小，进行gz压缩
		if (responseDataLength > GzipFilter.GZIP_THRESHOLD){		
			//System.out.println("orginal size:" + responseDataLength + ".   gzip writing...");
			byte[] gzippedOut = gzip(bytes);
			response.addHeader("Content-Encoding", "gzip");
			response.addHeader("Content-Length", Integer.toString(gzippedOut.length));
			output.write(gzippedOut);
			output.flush();
			output.close();
			closed = true;
			return;
		}
		response.addHeader("Content-Length", Integer.toString(responseDataLength));
		output.write(bytes);
		output.flush();
		output.close();
		closed = true;
	}

	public void flush() throws IOException {
		if (closed) {
			throw new IOException("Cannot flush a closed output stream");
		}
		baos.flush();
		//gzipstream.flush();
	}

	public void write(int b) throws IOException {
		if (closed) {
			throw new IOException("Cannot write to a closed output stream");
		}
		baos.write(b);
		//gzipstream.write((byte) b);
	}

	public void write(byte b[]) throws IOException {
		write(b, 0, b.length);
	}

	public void write(byte b[], int off, int len) throws IOException {
		if (closed) {
			throw new IOException("Cannot write to a closed output stream");
		}
		//gzipstream.write(b, off, len);
		baos.write(b, off, len);
	}

	public boolean closed() {
		return (this.closed);
	}

	public void reset() {
		// noop
	}
	
	private byte[] gzip(byte[] in) {
		if (in != null && in.length > 0) {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			try {
				GZIPOutputStream gzipstream = new GZIPOutputStream(bout);
				gzipstream.write(in);
				gzipstream.flush();
				gzipstream.finish();
				gzipstream.close();
				return bout.toByteArray();
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
		return new byte[0];
	}
}
