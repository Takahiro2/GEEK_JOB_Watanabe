package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import kagoyume.ProductData;
import kagoyume.UserDataDTO;
import kagoyume.kagoyumeHelper;
import net.arnx.jsonic.JSON;
import java.util.ArrayList;

public final class search_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");

    kagoyumeHelper kh = kagoyumeHelper.getInstance(); // ヘルパークラスの呼び出し
    HttpSession hs = request.getSession(); // セッショスタート

    //ログイン状態処理
    String name = "";
    boolean log = false;
    if (hs.getAttribute("login") != null) {
        log = true;
        //名前表示のためにセッションから取り出す
        UserDataDTO udd = (UserDataDTO) hs.getAttribute("welcomeUDD");
        name = udd.getName();
    }
    //login後のページ移行のために情報をセッションに格納
    hs.setAttribute("page", "search");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge, chrome=1\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <!-- BootstrapのCSS読み込み -->\n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("        <!-- jQuery読み込み -->\n");
      out.write("        <script src=\"//code.jquery.com/jquery-1.11.2.min.js\"></script>\n");
      out.write("        <!-- BootstrapのJS読み込み -->\n");
      out.write("        <script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("\n");
      out.write("        <title>検索結果</title>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            <div class=\"container\" align=\"center\">\n");
      out.write("                ");
 //ログイン,アウト表示分岐
                    if (!log) {
                
      out.write("\n");
      out.write("                ");
      out.print( kh.login());
      out.write(" \n");
      out.write("                ");
 } else {
      out.write("\n");
      out.write("                ");
      out.print( kh.logout(name));
      out.write("\n");
      out.write("                ");
 }
      out.write("\n");
      out.write("        </header>\n");
      out.write("\n");
      out.write("        <hr>\n");
      out.write("\n");
      out.write("        ");
 // 商品情報を変数productへ
            ArrayList<ProductData> product = (ArrayList<ProductData>) session.getAttribute("product");
      out.write("\n");
      out.write("        <h3>検索キーワード :");
      out.print( hs.getAttribute("search"));
      out.write("  検索結果数 :");
      out.print( product.get(0).getTotalResultsAvailable());
      out.write("件 </h3>\n");
      out.write("\n");
      out.write("        <hr>\n");
      out.write("\n");
      out.write("\n");
      out.write("        ");
 for (ProductData value : product) {
      out.write("\n");
      out.write("\n");
      out.write("    <tr>\n");
      out.write("\n");
      out.write("    <tb> <a href=\"item?code=");
      out.print( value.getCode());
      out.write("\"><img src=\"");
      out.print( value.getImage());
      out.write("\">  </a> <strong>&yen;  ");
      out.print( value.getPrice());
      out.write("</strong></tb>\n");
      out.write("    <tb><h1><a href=\"item?code=");
      out.print( value.getCode());
      out.write('"');
      out.write('>');
      out.print( value.getName());
      out.write("</a></h1></tb>\n");
      out.write("</tr>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<hr>\n");
 }
      out.write('\n');
      out.write('\n');
      out.print( kh.top());
      out.write("\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
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
