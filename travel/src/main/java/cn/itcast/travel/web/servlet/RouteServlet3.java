package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/route/*")
public class RouteServlet3 extends BaseServlet2 {
    private RouteService routeService=new RouteServiceImpl();
    private FavoriteService favoriteService=new FavoriteServiceImpl();
    /*
        分页查询
        @param request
        @param response
        @throws ServletException
        @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接受参数
        String currentPageStr=request.getParameter("currentPage");//当前页码
        String pageSizeStr=request.getParameter("pageSize");//每页显示条目
        String cidStr=request.getParameter("cid");//cid是分页id

        //接受rname线路名称
        String rname=request.getParameter("rname");
        rname=new String(rname.getBytes("iso-8859-1"),"utf-8");


        // 2.处理参数(将String类型转换成Int)
        int cid=0;
        if (cidStr !=null && cidStr.length()>0 && !"null".equals(cidStr)){ //处理cidStr参数
            cid=Integer.parseInt(cidStr);
        }

        int currentPage=0;//当前页码，如果不传递，则默认页码1
        if (currentPageStr !=null && currentPageStr.length()>0){    //处理currentPageStr参数
            currentPage=Integer.parseInt(currentPageStr);
        }else {
            currentPage=1;
        }

        int pageSize=0;//每页显示条目，如果不传递，默认每页显示条目5
        if (pageSizeStr !=null && pageSizeStr.length()>0){    //处理pageSizeStr参数
            pageSize=Integer.parseInt(pageSizeStr);
        }else {
            pageSize=5;
        }

        // 3.调用service查询PageBean对象
        PageBean<Route> pb=routeService.pageQuery(cid,currentPage,pageSize,rname);
        // 4.将pageBean序列化 返回
        writeValue(pb,response);
    }

    /*
        根据id查询一个旅游线路的详细信息
        @param request
        @Param response
        @throws ServletException
        @throws IOException

     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收id
       String rid= request.getParameter("rid");
        // 2.调用service查询route对象
        Route route=routeService.findOne(rid);
        // 3.转成json写回客户端
        writeValue(route,response);
    }

    /*
        判断当前登录用户是否收藏过该线路
        @param request
        @param response
        @throws ServletException
        @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取线路
        String rid=request.getParameter("rid");
        // 2.当前登录用户 user
        User user= (User) request.getSession().getAttribute("user");
        int uid=0;
        if(user== null){
            //用户尚未登录
            uid=0;
        }else{
            //用户已经登录
            uid=user.getUid();
        }
        // 3.调用FavoriteService查询是否收藏
        boolean flag=favoriteService.isFavorite(rid,uid);
        // 4.写回客户端
        writeValue(flag,response);
    }

    /*
        添加收藏
        @param request
        @param response
        @throws ServletException
        @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取线路rid
        String rid=request.getParameter("rid");
        // 2.获取当前登录用户
        User user= (User) request.getSession().getAttribute("user");
        int uid=0;
        if(user== null){
            //用户尚未登录
            return ;
        }else{
            //用户已经登录
            uid=user.getUid();
        }

        // 3.调用service添加
        favoriteService.add(rid,uid);
    }
}
