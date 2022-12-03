package function;

import chatting.protocol;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class get_data {
    public get_data(){
        // 빈 생성자
    }
    private InputStream is;
    private OutputStream os;
    private DataInputStream di;
    private DataOutputStream ds;
    private PrintWriter pw;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private DataOutputStream dos;
    private BufferedOutputStream bos;
    private String user_id;
    private int typeofrequest;
    private String roomnumber;
    private ArrayList<String> my_room_list; // 내가 속한 방 목록
    private ArrayList<String> userlist_in_room; // 방에 있는 유저 목록
    private ArrayList<String> allUserList; // 전체 유저 목록
    private String follow_yes_or_no; // 팔로우 여부
    private int followNum; // 팔로우 수
    private int followerNum; // 팔로워 수
    private int postNum; // 게시물 수
    private String id;
    public void setType9(int typeofrequset, String user_id, String id){
        this.typeofrequest = typeofrequset;
        this.user_id = user_id;
        this.id = id;
    }
    public void setType10(int typeofrequest, String user_id){
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
    }
    public void setType11(int typeofrequest, String user_id){
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
    }
    public void setType12(int typeofrequest, String user_id, String roomnumber){
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
        this.roomnumber = roomnumber;
    }
    public void setType15(int typeofrequest, String user_id){
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
    }
    public void setType19(int typeofrequest, String user_id){
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
    }
    public void setType20(int typeofrequest, String user_id){
        this.typeofrequest = typeofrequest;
        this.user_id = user_id;
    }

    public void request(protocol content){
        try{
            this.oos.writeObject(content); // 프로토콜로 담은 내용 전송
            this.oos.flush();
        }
        catch(Exception e2){
            System.out.println(e2);
        }
    }

    public ArrayList<String> getMy_room_list() {
        return my_room_list;
    }

    public ArrayList<String> get_users_in_room() {
        return userlist_in_room;
    }
    public ArrayList<String> getAllUserList() {
        return allUserList;
    }
    public int getFollowNum() {
        return followNum;
    }
    public int getFollowerNum() {
        return followerNum;
    }
    public int getPostNum() {
        return postNum;
    }
    public String getFollow_yes_or_no() {
        return follow_yes_or_no;
    }

    public void start(){
        try{
            Socket socket = new Socket("swiftsjh.tplinkdns.com",9998);
            System.out.println("서버 연결 성공");
            this.is = socket.getInputStream();
            this.os = socket.getOutputStream();

            this.di = new DataInputStream(is);


            this.ds = new DataOutputStream(os);
            this.pw = new PrintWriter(os);
            this.oos = new ObjectOutputStream(os);
            this.dos = new DataOutputStream(os);
            this.bos =new BufferedOutputStream(os);

            this.pw = new PrintWriter(bos);
            if(typeofrequest == 1){

            }
            else if (typeofrequest == 9){
                protocol p = new protocol(typeofrequest, user_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 9){
                            this.follow_yes_or_no = t.getFollow();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 10){
                protocol p = new protocol(typeofrequest, user_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 10){
                            this.postNum = t.getPostNum();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 11) {
                protocol p = new protocol(typeofrequest, user_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while (true) {
                    System.out.println("방 목록 받기");
                    try {
                        protocol t = (protocol) ois.readObject();
                        if (t.getTypeofrequest() == 11) {
                            this.my_room_list = t.getList();
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 12){
                protocol p = new protocol(typeofrequest, user_id, roomnumber);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 12){
                            userlist_in_room = t.getList();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 15){
                protocol p = new protocol(typeofrequest, user_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 15){
                            allUserList = t.getList();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 19){
                protocol p = new protocol(typeofrequest, user_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 19){
                            followerNum = t.get_follower_num();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            else if(typeofrequest == 20){
                protocol p = new protocol(typeofrequest, user_id);
                request(p);
                this.ois = new ObjectInputStream(is);
                while(true){
                    try{
                        protocol t = (protocol) ois.readObject();
                        if(t.getTypeofrequest() == 20){
                            followNum = t.getFollow_num();
                            break;
                        }
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }

            is.close();
            os.close();
            di.close();
            ois.close();
            ds.close();
            pw.close();
            oos.close();
            bos.close();
            pw.close();
            socket.close();
            System.out.println("서버 연결 종료");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}