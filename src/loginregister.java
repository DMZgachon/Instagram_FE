import java.io.*;
import java.net.*;
import java.util.*;
import encryption.*;

public class loginregister {

    public static int port=9898;
    public static String host="swiftsjh.tplinkdns.com";

    public static md5 encryptor= new md5();

    public int register(String id,String password){
        try{
            Socket socket= new Socket(host,port);
            OutputStream os=socket.getOutputStream();
            DataOutputStream ds= new DataOutputStream(os);
            InputStream is=socket.getInputStream();
            DataInputStream di=new DataInputStream(is);
            PrintWriter pw= new PrintWriter(os);
            int id_len =id.getBytes().length;
            int pwd_len=password.getBytes().length;
            String enc_password=encryptor.encMD5(password);
            ds.writeInt(100); //100 means register status code
            ds.flush();
            pw.println(id);
            pw.flush();
            pw.println(enc_password);
            pw.flush();
            int register_status=-1;
            register_status = di.readInt(); //get register status
            if(register_status==1){
                System.out.println("회원가입 성공");//
                return  register_status;
            } else if (register_status==2) {
                System.out.println("중복된 아이디 존재");
                return register_status;
            }
            os.close();
            socket.close();

        }catch (Exception e){
            System.out.println(e);
        }

        return 0;
    }
    public int login(String id,String password){
        try{
            Socket socket= new Socket(host,port);
            OutputStream os=socket.getOutputStream();
            InputStream is=socket.getInputStream();
            DataOutputStream ds= new DataOutputStream(os);
            DataInputStream di=new DataInputStream(is);
            PrintWriter pw= new PrintWriter(os);
            int id_len =id.getBytes().length;
            int pwd_len=password.getBytes().length;
            String enc_password=encryptor.encMD5(password);
            ds.writeInt(200); //200 means register status code
            ds.flush();
            pw.println(id);
            pw.flush();
            pw.println(enc_password);
            pw.flush();
            int session=-1;
            session = di.readInt(); //get session id
            os.close();
            socket.close();
            return  session; //session id from server received
        }catch (Exception e){
            System.out.println(e);
        }

        return -1; //when error occured return -1
    }

    public int logout(String id){
        try{
            Socket socket= new Socket(host,port);
            OutputStream os=socket.getOutputStream();
            InputStream is=socket.getInputStream();
            DataOutputStream ds= new DataOutputStream(os);
            DataInputStream di=new DataInputStream(is);
            PrintWriter pw= new PrintWriter(os);

            ds.writeInt(300); //300 means logout status
            ds.flush();
            pw.println(id);
            pw.flush();

            os.close();
            socket.close();
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return -1;
        }

    }
}
