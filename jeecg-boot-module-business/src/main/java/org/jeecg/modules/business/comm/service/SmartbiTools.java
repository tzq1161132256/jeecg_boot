package org.jeecg.modules.business.comm.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.exception.JeecgBootException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import smartbi.sdk.ClientConnector;
import smartbi.sdk.InvokeResult;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2019/1/16.
 */
@Component
public class SmartbiTools {

    private  static String userNameSmartbi;


    private static  String passwordSmartbi;


    private static  String urlSmartbi;

    @Value("${smartbi.username}")
    public void setUserNameSmartbi(String userNameSmartbi) {
        this.userNameSmartbi = userNameSmartbi;
    }

    @Value("${smartbi.password}")
    public void setPasswordSmartbi(String passwordSmartbi) {
        this.passwordSmartbi = passwordSmartbi;
    }

    @Value("${smartbi.url}")
    public void setUrlSmartbi(String urlSmartbi) {
        System.out.println("---------------------setUrlSmartbi");
        this.urlSmartbi = urlSmartbi;
    }

//同步用户

/*

    public static String synUser(RoleNameAndUserName roleNameAndUserName) {
        StringBuffer stringBuffer = new StringBuffer();
        if ("admin".equals(roleNameAndUserName.getUsername()) || "001".equals(roleNameAndUserName.getUsername()) || "002".equals(roleNameAndUserName.getUsername())) {
            return "";
        }

        String Smartbi_URL =  PropertyUtil.getProperty("SMARTBI_URL");
        String Smartbi_userName =  PropertyUtil.getProperty("SMARTBI_USERNAME");
        String Smartbi_password =  PropertyUtil.getProperty("SMARTBI_PASSWORD");

        String flag="MXReadRole";
        if(roleNameAndUserName.getExportFlag().equals("1")){
            flag= "MXReadRole_export";
        }



        if(Smartbi_URL==null){
            throw new BaseException("Smartbi_URL不存在或Smartbi_URL为空");
        }
      //  String Smartbi_URL = "http://47.101.50.231:18080/smartbi";
        ClientConnector conn = new ClientConnector(Smartbi_URL);


        try {
            // 第一次调用必须建立一个连接，后续调用则不必再建连接
            boolean ret = conn.open(Smartbi_userName, Smartbi_password);
            if (ret) {
                // 用户管理
                UserManagerService userManagerService = new UserManagerService(conn);
                String departmentId = "DEPARTMENT";


                //判断用户是否存在 当前用户组

                IUser iuser = userManagerService.getUserByName(roleNameAndUserName.getUsername());


                //如果 存在 删除------
                if (iuser != null) {
                    //根据用户名获取用户id

                    //删除这个用户
                    Boolean deleteflag = userManagerService.deleteUser(iuser.getId());
                    if (deleteflag) {
                        System.out.println("用户删除成功");
                        //在创建这个用户并赋予角色
                    } else {
                        System.out.println("用户删除失败");
                        return "用户删除失败";
                    }
                }


                String newUserId = "";
                if(iuser != null ) {

                    userManagerService.createUserById(departmentId,iuser.getId(),roleNameAndUserName.getUsername(),roleNameAndUserName.getUsername(),roleNameAndUserName.getUsername(), "123456", true);
                    IUser iuser_new = userManagerService.getUserByName(roleNameAndUserName.getUsername());

                    newUserId = iuser.getId();
                } else {
                    //通过用户组ID    并新建用户(返回用户的ID)
                     newUserId = userManagerService.createUser(departmentId, roleNameAndUserName.getUsername(), roleNameAndUserName.getUsername(), roleNameAndUserName.getUsername(), "123456", true);
                }
                //  遍历Resids 根据角色Name 拿到 角色 ID
                if("Y".equals(roleNameAndUserName.getEncryptFlag())) {
                    userManagerService.addUserAttribute(newUserId, "ENCRYPTFLAG", "Y", "Y");
                }else {
                    userManagerService.addUserAttribute(newUserId, "ENCRYPTFLAG", "N", "N");
                }
                //创建存一个roleName的List
                List roleNameList = new ArrayList<>();
                //根据角色名字获取角色对象
//                IRole iroleMX = userManagerService.getRoleByName("MXReadRole");
                IRole iroleMX = userManagerService.getRoleByName(flag);
                //将MXReadRole的角色Id存进集合
                roleNameList.add(iroleMX.getId());
                for (String roleName : roleNameAndUserName.getResids()) {

                    try {
                        //根据角色名字获取角色对象
                        IRole irole = userManagerService.getRoleByName(roleName);
                        roleNameList.add(irole.getId());
                    } catch (Exception e) {
                        stringBuffer.append(roleName).append(",");
                        e.printStackTrace();
                    }



                }


                //给用户分配角色 updateRoleCache - 是否更新角色的缓存

                Boolean flag1 = userManagerService.assignRolesToUserInner(newUserId, roleNameList, true);
                if (flag1) {
                    System.out.println("角色分配成功");
                } else {
                    System.out.println("角色分配失败");
                }
                if(StringUtils.isNotBlank(stringBuffer.toString())) {
                    stringBuffer.insert(0,"以下资源未配置角色名称: ");
                }

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            // 关闭应用连接器
            conn.close();
        }
           return stringBuffer.toString();

    }
*/

    //解析json 获取name值 并存进集合
    public static List jxList(List list) {
        //创建一个List集合
        List alist = new ArrayList<>();
        //解析list
        for (int i = 1; i < list.size(); i++) {
            JSONArray js = JSON.parseArray(list.get(i).toString());
            for (int y = 0; y < js.size(); y++) {
                //在拿到第一个json数组
                JSONObject jsono = js.getJSONObject(y);
                //将name值存进alist集合
                alist.add(jsono.getString("name"));

            }
        }
        return alist;
    }


//    //创建用户并赋予角色
//    public static void createUser(RoleNameAndUserName roleNameAndUserName){
//        //通过用户组ID    并新建用户(返回用户的ID)
//        String userId = userManagerService.createUser(departmentId, roleNameAndUserName.getUsername(), roleNameAndUserName.getUsername(), roleNameAndUserName.getUsername(), "123456", true);
//        //根据角色名字获取角色对象
//        IRole irole=   userManagerService.getRoleByName("MXReadRole");
//
//        System.out.println("irole>>"+irole);
//        //将角色id存进list集合
//        List iroleList=new ArrayList<>();
//        iroleList.add(irole.getId());
//        //给用户分配角色 updateRoleCache - 是否更新角色的缓存
//        Boolean flag1= userManagerService.assignRolesToUserInner(userId,iroleList,true);
//        if(flag){
//            System.out.println("角色分配成功");
//        }else{
//            System.out.println("角色分配失败");
//        }
//    }

    public  void callJob(String scheduleId){


        if(StringUtils.isBlank(urlSmartbi)){
            throw new JeecgBootException("Smartbi_URL不存在或Smartbi_URL为空");
        }
        //  String Smartbi_URL = "http://47.101.50.231:18080/smartbi";
        ClientConnector conn = new ClientConnector(urlSmartbi);
        try {
            boolean ret = conn.open(userNameSmartbi, passwordSmartbi);
            if (ret) {
                InvokeResult result = conn.remoteInvoke("ScheduleSDK", "run", new Object[]{scheduleId});
                System.out.println("----------------scheduleId=" + scheduleId + "  " + result.getResult());
                conn.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
            conn.close();
        }finally {
            conn.close();
        }
    }

    public static void main(String args[]) {


      SmartbiTools smartbiTools = new SmartbiTools();
      smartbiTools.setPasswordSmartbi("manager1");
        smartbiTools.setUserNameSmartbi("admin");
        smartbiTools.setUrlSmartbi("http://106.14.195.28/smartbi");
        smartbiTools.callJob("I8a281b090167c713c7135bd70167c8a2578219fb");
    }
}