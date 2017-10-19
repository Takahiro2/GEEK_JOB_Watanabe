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
 * myhistoryに対応するサーブレット
 */
@WebServlet(name = "myhistory", urlPatterns = {"/myhistory"})
public class myhistory extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {
            // 不正アクセスチェック
            request.setCharacterEncoding("UTF-8");
            String acschk = request.getParameter("ac");
            if (acschk.equals("null") || (Integer) session.getAttribute("ac") != Integer.parseInt(acschk)) {
                throw new Exception("不正アクセスです");
            }
            // ログイン中のユーザーからuserIDを取得
            UserDataDTO udd = (UserDataDTO) session.getAttribute("welcomeUDD");
            int userId = udd.getUserID();

            // 購入済み商品をDBから取得
            ArrayList<UserDataDTO> buyList = UserDataDAO.getInstance().searchHistory(userId);

            // 商品コードを取得し、ArrayListに追加
            ArrayList<String> codeList = new ArrayList<>();
            for (int i = 0; i < buyList.size(); i++) {
                String code;
                code = buyList.get(i).getItemCode();
                codeList.add(code);
            }

            // 商品コードから再検索をして必要な情報をArrayListに追加
            ArrayList<ProductData> lastList = new ArrayList<>();

            for (int j = 0; j < codeList.size(); j++) {
                ProductData dataList = webAPI.getInstance().searchDetail(codeList.get(j));
                ProductData pd = new ProductData();
                pd.setName(dataList.getName());
                pd.setPrice(dataList.getPrice());
                pd.setImage(dataList.getImage());
                lastList.add(pd);
            }

            session.setAttribute("lastList", lastList);

            request.getRequestDispatcher("/myhistory.jsp").forward(request, response);

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
