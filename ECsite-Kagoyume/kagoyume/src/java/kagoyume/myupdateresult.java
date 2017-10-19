package kagoyume;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * myupdateresult.jspに対応するサーブレット
 */
@WebServlet(name = "myupdateresult", urlPatterns = {"/myupdateresult"})
public class myupdateresult extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {

            request.setCharacterEncoding("UTF-8"); // 文字コードをUTF-8に

            // 不正アクセスチェック
            String acschk = request.getParameter("ac");
            if (acschk.equals("null") || (Integer) session.getAttribute("ac") != Integer.parseInt(acschk)) {
                throw new Exception("不正アクセスチェック");
            }

            // UserDataに格納
            UserData ud = new UserData();
            ud.setName(request.getParameter("name"));
            ud.setPassword(request.getParameter("password"));
            ud.setMail(request.getParameter("mail"));
            ud.setAddress(request.getParameter("address"));

            // 入力に不備がないかを確かめる　ある場合は myupdate.jspに戻す
            ArrayList<String> chkList = ud.chkProperties();
            if (!chkList.isEmpty()) {
                session.setAttribute("chkList", chkList);
                request.getRequestDispatcher("/myupdate.jsp").forward(request, response);
            } else if (UserDataDAO.check(request.getParameter("name")) != null) {
                session.setAttribute("registered", "registered");
                request.getRequestDispatcher("/myupdate.jsp").forward(request, response);

            }
            
            else {

                // DB用にマッピング
                UserDataDTO udd = new UserDataDTO();
                ud.toDTOMapping(udd);

                // userIDをwelcomeUDDから取ってくる
                UserDataDTO uddid = (UserDataDTO) session.getAttribute("welcomeUDD");
                udd.setUserID(uddid.getUserID());

                // ユーザー情報更新
                UserDataDAO.getInstance().update(udd);

                // mydata.jspの情報を更新
                session.removeAttribute("welcomeUDD");
                session.setAttribute("welcomeUDD", udd);

                // 結果表示用のセッション"ud" myupdateresult.jspへ
                request.setAttribute("ud", ud);
                request.getRequestDispatcher("/myupdateresult.jsp").forward(request, response);

            }

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
