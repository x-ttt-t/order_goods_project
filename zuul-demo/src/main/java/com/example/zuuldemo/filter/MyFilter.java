package com.example.zuuldemo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import static com.netflix.discovery.converters.EntityBodyConverter.JSON;

@Component

public class MyFilter extends ZuulFilter {
    public String getIpAddr(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    private  static final Logger logger=
            LoggerFactory.getLogger(
                    MyFilter.class);

    @Override
    public String filterType(){//过滤器类型
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {//优先级越小越高

        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx= RequestContext.getCurrentContext();
        HttpServletRequest req=ctx.getRequest();
        String ipAddr=this.getIpAddr(req);
        logger.info("请求IP地址为：[{}]",ipAddr);
        //配置本地IP白名单，生产环境可放入数据库或者redis中
        List<String> whitelist=new ArrayList<String>();
//        ips.add("127.0.0.1");
        whitelist.add("0:0:0:0:0:0:0:1");
        List<String> blacklist = Arrays.asList("127.0.0.1", "192.168.1.1");

        if(!whitelist.contains(ipAddr)){
            logger.info("IP地址校验不通过！！！");
            if(blacklist.contains(ipAddr)){
                logger.info("哥们，不光不在白名单，还在黑名单");
            }
            ctx.setResponseStatusCode(401);
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("IpAddr is forbidden!");
        }
        logger.info("IP校验通过");
        return null;
//        RequestContext ctx = RequestContext.getCurrentContext();
//        String uri=ctx.getRequest().getRequestURI();
//        HttpServletRequest request = ctx.getRequest();
//        String head=request.getHeader("token");
//        if(StringUtils.isEmpty(head)){
//            logger.info("当前请求没有token");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            return null;
//        }
//        logger.info("通过过滤器");
//        return null;





//        RequestContext context = RequestContext.getCurrentContext();
//        HttpServletRequest request = context.getRequest();
//
//        //ip 黑白名单机制
//        String ip = getIpAddr(request);
//        logger.info("当前请求ip={}", ip);
//        // 在黑名单中禁用
//        //ip白名单
//        whitelist.add("127.0.0.1");
//        if (!whitelist.contains(ip)) {
//            //非白名单
//            context.set("isSuccess", false);
//            context.setSendZuulResponse(false);
//            context.setResponseBody(JSON.toJSONString(SysRes.failure("403", "老铁，你还不是白名单用户！ip=" + ip)));
//            context.getResponse().setContentType("application/json; charset=utf-8");
//            return null;
//        }
//
//        return null;

    }


}
