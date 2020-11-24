package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import po.Request;
import po.Response;
import po.User;
import service.FriendService;
import service.RequestService;
import service.ResponseService;
import service.UserService;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/socket")
public class SockServer {
    //peers 包含所有的online用户的peer
    //here we keep all online users' peers
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    //所有的Socket里面使用的Services
    //All Services which we use in Socket
    ResponseService responseService = new ResponseService();
    RequestService requestService = new RequestService();
    UserService userService = new UserService();
    FriendService friendService = new FriendService();

    //When client send message to server
    //用户把信息发到Socket[Server]的事件
    @OnMessage
    public String onMessage(Session peer, String message) throws SQLException {
        Gson gson = new Gson(); //新建gson     GSON实现java对象和json数据之间的相互转换

        JsonParser parser = new JsonParser();   //JavaParser 可以把string转换到JsonObject
        JsonObject json = (JsonObject) parser.parse(message);

        //我们用一个 "param"变量 为了保存信息的内容的类型
        //we use "param" for distinguish the message type
        switch (json.get("param").toString()){
            case ("\"req\""):
                System.out.println("It's request   这是请求");
                //因为我们知道这是请求， 我们把message转换到request对象
                //Convert message to Request object
                Request request = gson.fromJson(message,Request.class);
                //把request保存到数据库
                //Save request to DataBase
                if(requestService.SaveNewRequest(request)) {
                    //find this request by userId, cause we need to find request_id
                    String req = gson.toJson(requestService.FindRequestByUserId(request.getUser_id()));
                    //find user's name for sending
                    String user_name = userService.findNameByID(request.getUser_id());
                    //convert request String to JsonObject
                    JsonObject json1 = (JsonObject) parser.parse(req);
                    //Add two property - param for distinguish the type for client and user_name for sending
                    json1.addProperty("param", "request");
                    json1.addProperty("user_name", user_name);
                    String req1 = gson.toJson(json1); //Convert JsonObject to String
                    System.out.println(json.get("private"));
                     //Is this request private or not?
//                    if (Integer.parseInt(json.get("private").toString()) == 1) {
//                        //If request is private and only for friends
//                        //We need to find all this user's friends
//                        for (FriendInfo u : friendService.FindMyFriendsInfoByID(request.getUser_id())) {
//                            // we need to check online this friend or not
//                            if (!u.getPeer().isEmpty()) {
//                                for (Session peer1 : peers) {
//                                    //if this friend online, we send him(her) this request
//                                    if (peer1.toString().equals(u.getPeer())) {
//                                        return req1;
//                                    } else {
//                                        return null;
//                                    }
//                                }
//                            }
//                        }
//                    } else if (Integer.parseInt(json.get("private").toString()) == 0) {
//                        //If request is public, we send request for all online users except request's author
//                        for (Session peer1 : peers) {
//                            if (peer1.toString().equals(peer.toString())) {
//                                return null;
//                            } else {
//                                return req1;
//                            }
//                        }
//                    }
                    return req1;  //USE FOR CHECK
                }
            case("\"openSocket\""):
                System.out.println("Socket is open");
                //保存peer在数据库
                userService.SaveUserPeer(peer.toString(), Integer.parseInt(json.get("user_id").toString()));
                //можно ничего не отправлять?
                JsonObject json1 = new JsonObject();
                json1.addProperty("param","opened");
                String req1 = gson.toJson(json1);
                return req1;

            case("\"res\""):
                System.out.println("It's response");
                //因为我们知道这是响应， 我们把message转换到response对象
                //Convert message to Response object
                Response response = gson.fromJson(message, Response.class);
                //把response保存到数据库
                //Save this response information to DataBase
                if(responseService.SaveNewResponse(response.getUser_id(),response.getRequest_id())){
                    if(requestService.FindRequestByReqId(response.getRequest_id()).getStatus()==0){
                        //改变 request的status     为1
                        // Change request's status    to 1[it's mean this request already have response]
                        requestService.UpdateRequestStatus(response.getRequest_id());
                        //Send response user name to request's author
                        User user = userService.findUserById(response.getUser_id());
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("param", "response");
                        jsonObject.addProperty("user_name", user.getUser_name());
                        return gson.toJson(jsonObject); //USE FOR CHECK
                    }else {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("param", "responseX");
                    }
                }

//                for (Session peer1: peers) {
//                    //System.out.println("RESPONSE PEER "+peer1+" AND "+userService.FindPeerByRequest(response.getRequest_id()));
//                    if(peer1.toString().equals(userService.FindPeerByRequest(response.getRequest_id()))){
//                        return gson.toJson(jsonObject);
//                    }else{
//                        return null;
//                    }
//                }
            default: return null;
        }
    }

    @OnOpen
    public void onOpen (Session peer) {
        peers.add(peer);
        System.out.println("Socket On Open"+ peer);
    }

    @OnClose
    public void onClose (Session peer) {
        peers.remove(peer);
        userService.DeleteUserPeer(peer.toString());
        System.out.println("Socket On Close");
    }
}