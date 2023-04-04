package com.example.zuuldemo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoginFilter extends ZuulFilter {
    private static final Logger logger =
            LoggerFactory.getLogger(
                    LoginFilter.class);
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        System.out.println(request.getRequestURI());
        String uri=request.getRequestURI();
        List<String> address=new ArrayList<>();
        address.add("/order/order/all");

        if(address.contains(uri)){
            logger.info("没有进入过滤拦截run方法");
            return false;
        }else{
            return  true;
        }
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        // token对象
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            logger.info("没有带token信息，被拦截");

        }
        logger.info("被放行");
        return null;
    }

    // 判断是否需要被拦截
    private boolean isIntercepted(String url, HttpServletRequest request) {
        return url.equalsIgnoreCase(request.getRequestURI());
    }
}
