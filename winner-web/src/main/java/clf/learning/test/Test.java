package clf.learning.test;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.*;

public class Test {

    private static final String USER="chenlongfei";
    private static final String PASSWORD="00";
    private static final String HOST="172.16.219.130";
    private static final int DEFAULT_SSH_PORT=22;

    public static void main(String args []) {
        try{
            JSch jsch=new JSch();

            Session session = jsch.getSession(USER,HOST,DEFAULT_SSH_PORT);
            session.setPassword(PASSWORD);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();


//            ChannelExec channel = (ChannelExec)session.openChannel("exec");
//            channel.setCommand("pwd");

            ChannelShell channel = (ChannelShell) session.openChannel("shell");
            channel.connect();

            InputStream inputStream = channel.getInputStream();
            OutputStream outputStream = channel.getOutputStream();

            PrintWriter printWriter = new PrintWriter(outputStream);



            printWriter.println("ls");
            printWriter.println("pwd");
            printWriter.println("sudo shutdown -h now");
            printWriter.println("00");

            printWriter.println("exit");//加上个就是为了，结束本次交互
            printWriter.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String msg = null;
            while((msg = in.readLine())!=null){
                System.out.println(msg);
            }
            in.close();


            channel.disconnect();
            session.disconnect();

        }  catch(Exception e){
            System.out.println(e);
        }

    }



}


