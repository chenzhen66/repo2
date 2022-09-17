package com.chenzhen.Servlet;

import com.chenzhen.entity.Food;
import com.chenzhen.service.FoodService;
import com.chenzhen.service.impl.FoodServiceImpl;
import com.chenzhen.utils.BaseCalculate;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "ImageUploadServlet", value = "/ImageUploadServlet")
public class ImageUploadServlet extends HttpServlet {
    Map<String,String> map = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doPost(request, response);
    }

    /*
    * 添加餐品,把普通表单项存入map，文件名称存入数据库
    * */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String path = "uploadImages/";
        String ext[] = {"jpg","gif","bmp","png","JPG","GIF","BMP","PNG","jpeg","JPEG"};
        try {
            String imgpath = upload(path,request,response,ext);
            if (imgpath.equals("")) {
                request.setAttribute("msg","alert('图片路径为空');");
                request.setAttribute("local","admin/food/addfood.jsp");
                request.getRequestDispatcher("web/msg.jsp").forward(request,response);
                return;
            }
            String name = map.get("name");
            String foodtype_id =map.get("foodtype_id");
            float tpeice = Float.parseFloat( map.get("tprice"));
            float eprice = Float.parseFloat("0."+map.get("eprice"));
            float price= BaseCalculate.add(tpeice,eprice);
            System.out.println(imgpath);
            Food food = new Food();
            FoodService foodService = new FoodServiceImpl();
            food.setName(name);
            food.setFoodtype_id(Integer.parseInt(foodtype_id));
            food.setPrice(price);
            food.setImgpath(imgpath);
            int res = foodService.addFood(food);
            if (res >= 1) {
                request.setAttribute("msg","alert('添加成功');");
                request.setAttribute("local","FoodServlet?action=findAll");
                request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            }else {
                request.setAttribute("msg","alert('添加失败了');");
                request.setAttribute("local","admin/food/addfood.jsp");
                request.getRequestDispatcher("web/msg.jsp").forward(request,response);
            }

        } catch (Exception e) {
            request.setAttribute("msg",e.getMessage());
        }

    }

    //上传文件,返回上传路径
    public String upload(String path,HttpServletRequest request, HttpServletResponse response,String... ext) throws Exception {
        /*
         * 1.创建工厂对象
         * */
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2.通过工厂对象创建解析器
        ServletFileUpload sfu = new ServletFileUpload(factory);
        String filename = "";
        try {
            //3.解析request，获取FileItem的列表
            List<FileItem> Items = sfu.parseRequest(request);
            //4.通过循环获取表单项
            for (FileItem item:Items){
                //判断是否是普通表单项
                if (item.isFormField()){
                    String name = item.getFieldName();//获取表单项的name
                    String value = item.getString ("utf-8");//获取表单项的值，防止中文乱码
                    map.put(name,value);
                }else {
                    //文件表单项
                    filename = item.getName();//获取文件名
                    //判断文件名是否为空
                    if (filename == null || filename.trim().equals("")){
                        throw new Exception("文件名不能为空");
                    }
                    //判断文件扩展名是否正确
                    String type = filename.substring(filename.lastIndexOf(".")+1);
                    boolean flag = false;
                    if (ext!=null&&ext.length>0){
                        for (int i=0;i<ext.length;i++){
                            if (type.equals(ext[i])){
                                flag =true;
                                break;
                            }
                        }
                        if (!flag){
                            throw new RuntimeException("文件类型错误");
                        }
                    }

                    //example: e:/a/b.txt 获取/截取后面的部分
                    //截取文件名
                    int index = filename.indexOf("\\");
                    if (index!=-1){
                        filename = filename.substring(index+1);
                    }

                    //防止上传重名的问题
                    filename = UUID.randomUUID().toString().replace("-","")+filename;
                    //上传路径
                    filename = path + filename;
                    File file  = new File(this.getServletContext().getRealPath(filename));
                    if (!file.exists()){
                        file.getParentFile().mkdirs();//创建上层目录
                    }
                    //保存
                    item.write(file);

                }
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }
        return filename;
     }
}
