package com.xc.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserLoginZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        System.out.println("filterType..............");
        return "pre"; // 设置过滤器类型为：pre
    }

    @Override
    public int filterOrder() {
        System.out.println("filterOrder.............");
        return 0;// 设置执行顺序为0
    }

    @Override
    public boolean shouldFilter() {
        System.out.println("shouldFilter.............");
        return true; // 该过滤器需要执行
    }

    @Override
    public Object run() throws ZuulException {//编写业务逻辑
        System.out.println("run.............");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            requestContext.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
            requestContext.setResponseStatusCode(401); // 设置响应状态码
            requestContext.setResponseBody(" token is empty!!"); // 设置响应状态码
            return null;
        }
        return null;
    }
}
