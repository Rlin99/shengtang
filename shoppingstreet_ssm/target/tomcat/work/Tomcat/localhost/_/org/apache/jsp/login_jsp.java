/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-07-18 07:18:45 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
      out.write("    <link type=\"text/css\" rel=\"stylesheet\" href=\"css/style.css\" />\n");
      out.write("    <!--[if IE 6]>\n");
      out.write("    <script src=\"js/iepng.js\" type=\"text/javascript\"></script>\n");
      out.write("    <script type=\"text/javascript\">\n");
      out.write("        EvPNG.fix('div, ul, img, li, input, a');\n");
      out.write("    </script>\n");
      out.write("    <![endif]-->\n");
      out.write("    <script type=\"text/javascript\" src=\"js/jquery-1.11.1.min_044d0927.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"js/jquery.bxslider_e88acd1b.js\"></script>\n");
      out.write("\n");
      out.write("    <script type=\"text/javascript\" src=\"js/jquery-1.8.2.min.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"js/menu.js\"></script>\n");
      out.write("\n");
      out.write("    <script type=\"text/javascript\" src=\"js/select.js\"></script>\n");
      out.write("\n");
      out.write("    <script type=\"text/javascript\" src=\"js/lrscroll.js\"></script>\n");
      out.write("\n");
      out.write("    <script type=\"text/javascript\" src=\"js/iban.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"js/fban.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"js/f_ban.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"js/mban.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"js/bban.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"js/hban.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"js/tban.js\"></script>\n");
      out.write("\n");
      out.write("    <script type=\"text/javascript\" src=\"js/lrscroll_1.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("    <title>购物街</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<!--Begin Header Begin-->\n");
      out.write("<div class=\"soubg\">\n");
      out.write("    <div class=\"sou\">\n");
      out.write("        <span class=\"fr\">\n");
      out.write("        \t<span class=\"fl\">你好，请<a href=\"Login.html\">登录</a>&nbsp; <a href=\"regist.jsp\" style=\"color:#ff4e00;\">免费注册</a>&nbsp; </span>\n");
      out.write("            <span class=\"fl\">|&nbsp;关注我们：</span>\n");
      out.write("            <span class=\"s_sh\"><a href=\"#\" class=\"sh1\">新浪</a><a href=\"#\" class=\"sh2\">微信</a></span>\n");
      out.write("            <span class=\"fr\">|&nbsp;<a href=\"#\">手机版&nbsp;<img src=\"images/s_tel.png\" align=\"absmiddle\" /></a></span>\n");
      out.write("        </span>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("<!--End Header End-->\n");
      out.write("<!--Begin Login Begin-->\n");
      out.write("<div class=\"log_bg\">\n");
      out.write("    <div class=\"top\">\n");
      out.write("        <div class=\"logo\"><a href=\"Index.html\"><img src=\"images/logo.png\" /></a></div>\n");
      out.write("    </div>\n");
      out.write("    <div class=\"login\">\n");
      out.write("        <div class=\"log_img\"><img src=\"images/l_img.png\" width=\"611\" height=\"425\" /></div>\n");
      out.write("        <div class=\"log_c\">\n");
      out.write("            <form action=\"login.do\" method=\"post\">\n");
      out.write("                <table border=\"0\" style=\"width:370px; font-size:14px; margin-top:30px;\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("                    <tr height=\"50\" valign=\"top\">\n");
      out.write("                        <td width=\"55\">&nbsp;</td>\n");
      out.write("                        <td>\n");
      out.write("                            <span class=\"fl\" style=\"font-size:24px;\">登录</span>\n");
      out.write("                            <span class=\"fr\">还没有商城账号，<a href=\"regist.jsp\" style=\"color:#ff4e00;\">立即注册</a></span>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr height=\"70\">\n");
      out.write("                        <td>用户名</td>\n");
      out.write("                        <td><input name=\"loginName\" type=\"text\" value=\"\" class=\"l_user\" /></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr height=\"70\">\n");
      out.write("                        <td>密&nbsp; &nbsp; 码</td>\n");
      out.write("                        <td><input name=\"password\" type=\"password\" value=\"\" class=\"l_pwd\" /></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>&nbsp;</td>\n");
      out.write("                        <td style=\"font-size:12px; padding-top:20px;\">\n");
      out.write("                \t<span style=\"font-family:'宋体';\" class=\"fl\">\n");
      out.write("                    \t<label class=\"r_rad\"><input type=\"checkbox\" name=\"checkbox\" value=\"1\"/></label><label class=\"r_txt\">请保存我这次的登录信息</label>\n");
      out.write("                    </span>\n");
      out.write("                            <span class=\"fr\"><a href=\"#\" style=\"color:#ff4e00;\">忘记密码</a></span>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr height=\"60\">\n");
      out.write("                        <td>&nbsp;</td>\n");
      out.write("                        <td><input type=\"submit\" value=\"登录\" class=\"log_btn\" /></td>\n");
      out.write("                    </tr>\n");
      out.write("                </table>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("<!--End Login End-->\n");
      out.write("<!--Begin Footer Begin-->\n");
      out.write("<div class=\"btmbg\">\n");
      out.write("    <div class=\"btm\">\n");
      out.write("        备案/许可证编号：京ICP备070360号   Copyright © 2016-2019 购物街 All Rights Reserved. 复制必究 , Technical Support: ICT Group <br />\n");
      out.write("        <img src=\"images/b_1.gif\" width=\"98\" height=\"33\" /><img src=\"images/b_2.gif\" width=\"98\" height=\"33\" /><img src=\"images/b_3.gif\" width=\"98\" height=\"33\" /><img src=\"images/b_4.gif\" width=\"98\" height=\"33\" /><img src=\"images/b_5.gif\" width=\"98\" height=\"33\" /><img src=\"images/b_6.gif\" width=\"98\" height=\"33\" />\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("<!--End Footer End -->\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("\n");
      out.write("<!--[if IE 6]>\n");
      out.write("<script src=\"//letskillie6.googlecode.com/svn/trunk/2/zh_CN.js\"></script>\n");
      out.write("<![endif]-->\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
