package com.ray.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import com.ray.entity.Category;
import com.ray.service.CategoryService;


@WebFilter("/*")
public class LoadCategoryFilter extends HttpFilter implements Filter {
	private CategoryService categoryService;
       

    public LoadCategoryFilter() {
        categoryService = new CategoryService();
    }


	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String fullPath = httpRequest.getRequestURI();
		String path = fullPath.substring(httpRequest.getContextPath().length());
		
		if (!path.startsWith("/admin/")) {
			List<Category> categoryList = categoryService.listCategory();
			request.setAttribute("categoryList", categoryList);
		}
		
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
