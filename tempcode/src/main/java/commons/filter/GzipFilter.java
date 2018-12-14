package commons.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GzipFilter implements Filter {
	Map headers = new HashMap();
	Map ignoreURL = new HashMap();
	public static int GZIP_THRESHOLD = 200;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		if (req instanceof HttpServletRequest) {
			doFilter((HttpServletRequest) req, (HttpServletResponse) res, chain);
		} else {
			chain.doFilter(req, res);
		}
	}

	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		if (request.getServletPath() != null && acceptsGzip(request)) {
			String[] servletPath = request.getServletPath().split("/");
			String endURL = servletPath[servletPath.length - 1];
			//System.out.println("endURL:" + endURL);
			if (ignoreURL.containsKey(endURL)){
				//System.out.println("don't gzip endURL:" + endURL);
				chain.doFilter(request, response);
				return;
			}
			// 如果是.do结尾的，压缩后再加请求头gzip
			if (request.getServletPath().endsWith(".do") || request.getServletPath().endsWith(".css")) {
				// 所有.do返回数据不进行压缩
				if (GzipFilter.GZIP_THRESHOLD == -1) {
					chain.doFilter(request, response);
					return;
				}
				// 根据大小，超过就进行压缩，0等于全部都要压缩
				else {
					GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(
							response);
					chain.doFilter(request, wrappedResponse);
					wrappedResponse.finishResponse();
					return;
				}

			} else {
				for (Iterator it = headers.entrySet().iterator(); it.hasNext();) {
					Map.Entry entry = (Map.Entry) it.next();
					response.addHeader((String) entry.getKey(),
							(String) entry.getValue());
				}
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		String headersStr = config.getInitParameter("headers");
		String[] headers = headersStr.split(",");
		for (int i = 0; i < headers.length; i++) {
			String[] temp = headers[i].split("=");
			this.headers.put(temp[0].trim(), temp[1].trim());
		}
		String gzipThresh = config.getInitParameter("gzip_threshold");
		if (gzipThresh != null && gzipThresh.length() > 0) {
			try {
				GzipFilter.GZIP_THRESHOLD = Integer.parseInt(gzipThresh);
			} catch (NumberFormatException n) {
				n.printStackTrace();
				GzipFilter.GZIP_THRESHOLD = -1;
			}
		}
		String ignoreURLStr = config.getInitParameter("ignoreURL");
		String[] ignoreURLs = ignoreURLStr.split(",");
		for (int i = 0; i < ignoreURLs.length; i++) {
			if (ignoreURLs[i] != null && !ignoreURLs[i].trim().equals("")) {
				this.ignoreURL.put(ignoreURLs[i], "");
			}
		}
	}

	private boolean acceptsGzip(HttpServletRequest request) {
		String ae = request.getHeader("accept-encoding");
		return ae != null && ae.indexOf("gzip") != -1;
	}

}
