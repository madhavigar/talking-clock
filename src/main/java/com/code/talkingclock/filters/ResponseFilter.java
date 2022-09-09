package com.code.talkingclock.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


/***
 * Adds secure headers to http response
 * @author 91959
 *
 */
@Component
public class ResponseFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse httServletResponse = (HttpServletResponse) response;
		httServletResponse.addHeader("X-XSS-Protection", "1");
		httServletResponse.addHeader("X-Frame-Options", "SAMEORIGIN");
		httServletResponse.addHeader("Referrer-Policy", "strict-origin");
		httServletResponse.addHeader("X-Content-Type-Options", "nosniff");
		httServletResponse.addHeader("Contenty-Security-Policy", "default-src 'self'www.w3.org");		
		chain.doFilter(request, httServletResponse);		
	}

}
