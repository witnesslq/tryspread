<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.io.*" %>
<%@ page import="sun.misc.BASE64Decoder" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>UTIL</title>
</head>
<%
    ////////////////////////////////////请求参数//////////////////////////////////////
    StringBuffer info = new java.lang.StringBuffer();
    InputStream in = request.getInputStream();
    BufferedInputStream buf = new BufferedInputStream(in);
    byte[] buffer = new byte[1024];
    int iRead;
    while ((iRead = buf.read(buffer)) != -1) {
        info.append(new String(buffer, 0, iRead, "UTF-8"));
    }
    System.out.println(info);
//		String parameter = Base64.getFromBase64(String.valueOf(info));
    String parameter = null;
    //解密
    byte[] by = null;
//		String result = null;
    if (String.valueOf(info) != null) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            by = decoder.decodeBuffer(String.valueOf(info));
            parameter = new String(by, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    String str = "Time : {" + new Date() + "},IP : {" + request.getRemoteAddr() + "} , user-agent : {" + request.getHeader("User-agent") + "} , Parameters : {" + parameter + "}";
    String log = session.getServletContext().getRealPath("/") + "log.txt";
    System.out.println(log);
    File file = new File(log);
    if (!file.exists()) {
        file.createNewFile();
    }
    byte bytes[] = new byte[512];
    bytes = str.getBytes();
    int b = str.length();
    FileOutputStream fos = new FileOutputStream(file, true);
    fos.write(bytes, 0, b);
    fos.close();
    PrintWriter writer = response.getWriter();
    writer.println("OK!");
%>
<body>
</body>
</html>
