package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import kagoyume.UserDataDTO;
import kagoyume.ProductData;
import kagoyume.kagoyumeHelper;
import com.mysql.jdbc.SocketMetadata.Helper;

public final class item_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

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
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");


    kagoyumeHelper kh = kagoyumeHelper.getInstance();
    HttpSession hs = request.getSession();
    ProductData pd = (ProductData) session.getAttribute("productDetail");
    String name = "";

    boolean log = false;
    if (hs.getAttribute("login") != null) {
        log = true;
        UserDataDTO udd = (UserDataDTO) hs.getAttribute("welcomeUDD");
        name = udd.getName();
    }
    
    hs.setAttribute("page", "item");


      out.write("    \n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge, chrome=1\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("\n");
      out.write("        <!-- BootstrapのCSS読み込み -->\n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("        <!-- jQuery読み込み -->\n");
      out.write("        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>\n");
      out.write("        <!-- BootstrapのJS読み込み -->\n");
      out.write("        <script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("\n");
      out.write("        <title>商品情報</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            <div class=\"logo\">\n");
      out.write("                <strong>かご夢</strong>\n");
      out.write("            </div>\n");
      out.write("            <hr>\n");
      out.write("            ");
 //ログイン,アウト表示分岐
                if (!log) {
            
      out.write("\n");
      out.write("            ");
      out.print( kh.login());
      out.write(" \n");
      out.write("            ");
 } else {
      out.write("\n");
      out.write("            ");
      out.print( kh.logout(name));
      out.write("\n");
      out.write("            ");
 }
      out.write("\n");
      out.write("        </header>\n");
      out.write("        <hr>\n");
      out.write("        <main>\n");
      out.write("            <p><div class=\"data-subject\">商品名:</div><br>\n");
      out.write("            ");
      out.print( pd.getName());
      out.write("</p>\n");
      out.write("            <br><br>\n");
      out.write("            <p><div class=\"data-subject\">イメージ:</div></p>\n");
      out.write("        <img src=\"");
      out.print( pd.getImage());
      out.write("\">\n");
      out.write("        <br><br>\n");
      out.write("        <p><div class=\"data-subject\">概要:</div>\n");
      out.write("        ");
      out.print( pd.getDescription());
      out.write("</p>\n");
      out.write("    <br><br>\n");
      out.write("    <p><div class=\"data-subject\">価格:");
      out.print( pd.getPrice());
      out.write(" 円</div></p>\n");
      out.write("<br>\n");
      out.write("<form action=\"add\" method=\"post\">\n");
      out.write("    <input type=\"submit\" name=\"btn\" value=\"カートに追加\">\n");
      out.write("</form><br>\n");
      out.write("\n");
      out.print( kh.search());
      out.write("\n");
      out.write("<br><br>\n");
      out.print( kh.top());
      out.write("\n");
      out.write("</main>\n");
      out.write("<hr>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
