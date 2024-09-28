package com.example.demo.practice.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
		log.info("log filter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("log filter doFilter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String requestURI = httpRequest.getRequestURI();
		String uuid = UUID.randomUUID().toString();

		// 요청 헤더 정보 로깅
		Enumeration<String> headerNames = httpRequest.getHeaderNames();
		log.info("=== 요청 헤더 정보 ===");
		if (headerNames != null) {
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				String headerValue = httpRequest.getHeader(headerName);
				log.info(headerName + ": " + headerValue);
			}
		}

		try {
			log.info("REQUEST [{}][{}]", uuid, requestURI);

			chain.doFilter(request, response);

			// 응답 헤더 정보 로깅
			log.info("=== 응답 헤더 정보 ===");
			for (String headerName : httpResponse.getHeaderNames()) {
				String headerValue = httpResponse.getHeader(headerName);
				log.info(headerName + ": " + headerValue);
			}

		} catch (Exception e) {
		} finally {
			log.info("RESPONSE [{}][{}]", uuid, requestURI);
		}
	}

	@Override
	public void destroy() {
		log.info("log filter destroy");
	}

}
