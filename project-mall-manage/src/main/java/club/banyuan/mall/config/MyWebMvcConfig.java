package club.banyuan.mall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import club.banyuan.mall.common.Constants;
import club.banyuan.mall.interceptor.AdminLoginInterceptor;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private AdminLoginInterceptor adminLoginInterceptor;

	public void addInterceptors(InterceptorRegistry registry) {
		// 添加一个拦截器，拦截以/admin为前缀的url路径（后台登陆拦截）
		registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login")
				.excludePathPatterns("/admin/dist/**").excludePathPatterns("/admin/plugins/**");
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
		registry.addResourceHandler("/static/goods-img/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
	}
}
